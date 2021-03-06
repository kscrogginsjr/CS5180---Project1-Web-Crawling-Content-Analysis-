import os
from bs4 import BeautifulSoup as bs, Comment, Doctype, Tag
import re
import html
from threading import Thread

DENSITY = "density"
DENSITY_SUM = "density_sum"
MARK = "mark"

# calculate all characters within a tags text
def token_count(tag) -> int:
    test_string = tag.string if tag.string else ''
    groups = re.findall(r'\w+', test_string)
    result = 0
    for group in groups:
        result += len(group)
    return result

# calculate number of tags
def tag_count(tag) -> int:
    # +1 for counting itself
    return len(list(tag.descendants)) + 1

# calculate text density in a given tag
def get_density(tag) -> float:
    return float(token_count(tag)) / float(tag_count(tag))

# find tag that matches density value by attribute id
def find_tag(tag: Tag, id: str, density: float) -> Tag:
    id = str(id)
    density = str(density)
    try:
        if tag[id] == density:
            return tag
    except:
        pass
    tag_id_lookup = {id: density}
    target = tag.find_next(**tag_id_lookup)
    if target is None:
        for sibling in tag.descendants:
            if isinstance(sibling, Tag):
                target = find_tag(sibling, id, density)
                break
    return target

# find the max density sum of a given tags family
def get_max_density_sum(tag: Tag) -> float:
    try:
        max_density_sum = tag[DENSITY]
    except:
        max_density_sum = 0
    temp_max = 0
    for child in tag.children:
        if isinstance(child, Tag):
            temp_max = get_max_density_sum(child)
        max_density_sum = max(temp_max, max_density_sum)
    tag[DENSITY_SUM] = max_density_sum
    return max_density_sum

# set attribute "denisty" to all html tags with calculated value
def set_tag_density(soup):
    for tag in soup.findAll():
        tag[DENSITY] = get_density(tag)
    soup[DENSITY] = get_density(soup)

# get threshold to use as base line for which tags to keep
def get_threshold(tag: Tag, max_density_sum: float) -> float:
    target = find_tag(tag, DENSITY_SUM, max_density_sum)
    if target is None:
        threshold = float(tag[DENSITY])
        return threshold
    threshold = float(target[DENSITY])
    set_mark(target, 1) # keep tag
    parent = target.parent
    while parent.name != 'html':
        text_density = float(parent[DENSITY])
        threshold = min(threshold, text_density)
        parent[MARK] = 2 # keep this tag and descendants
        parent = parent.parent
    return threshold

# set attribute "mark" to all html tags with initial value
def set_mark(tag: Tag, value: int):
    tag[MARK] = value
    for child in tag.children:
        if isinstance(child, Tag):
            set_mark(child, value)

# change attirbute marks value based on  a tags density sum
def mark_tag_content(tag: Tag, threshold: float):
    text_density = float(tag[DENSITY])
    max_density_sum = float(tag[DENSITY_SUM])
    mark = int(tag[MARK])
    if mark != 1 and threshold > text_density: 
        find_max_density_sum_tag(tag, max_density_sum)
        if isinstance(tag, Tag):
            for child in tag.children:
                mark_tag_content(child, threshold)

# find tag with the highest denisty sum and mark its children as content to be kept
def find_max_density_sum_tag(tag: Tag, max_density_sum: float):
    target = find_tag(tag, DENSITY_SUM, max_density_sum)
    mark = int(tag[MARK])
    if mark != 1:
        set_mark(target, 1) # keep tag
        parent = target.parent
        while parent.name != 'html':
            parent[MARK] = 2 #keep tag and descendants
            parent = parent.parent
            
# remove unecessary tags from html doc
def clean_up(soup) -> bs:
    comments = soup.findAll(text=lambda text:isinstance(text, Comment))
    [comment.extract() for comment in comments] #remove comments
    doctype = soup.findAll(text=lambda text:isinstance(text, Doctype))
    [doctype.extract() for doctype in doctype] #remove <!DOCTYPE html> 
    # remove tags that will not contain text content
    for tags in soup.find_all(['script', 'style', 'input', 'nav', 'img', 'button', 'video', 'meta', 'noindex']):
        tags.decompose() 
    return soup

def get_plain_text(soup):
    for tag in soup.find_all():
        tag.unwrap()
    pretty = soup.prettify()
    remove_body = re.sub(r'<.*?>', '', pretty).strip()
    splits = [x.strip() for x in remove_body.split('\n')]
    return html.unescape('\n'.join(splits))

def write_to_file(content, filename):
    if not os.path.exists('../repository-cleaned'):
        os.makedirs('../repository-cleaned')
    name = os.path.splitext(filename)[0]
    filename = name + ".txt"
    with open(os.path.join('../repository-cleaned', filename), 'w', encoding='utf-8') as file:
        file.write(content)
        file.close()

def compute_density(folder_name, filename):
    # read file in
    html_doc = read_file(folder_name, filename)

    # initialize beautifulsoup object
    soup = bs(html_doc, 'html.parser').body

    # if beautifulsoup was unable to parse html_doc return
    if not soup:
        return

    # remove unwanted tags, comments and DOCTYPE in html doc
    soup = clean_up(soup)

    # calculate tag denisities for every tag and add to html tags
    set_tag_density(soup)

    # calculate max density sum to normalize threshold
    max_density_sum = get_max_density_sum(soup)

    # initialize and add mark attributes to 0 to html tags 
    set_mark(soup, 0)

    # calculate threshold to know which content to keep and which to remove
    threshold = get_threshold(soup, max_density_sum)

    # mark content to be removed and kept
    mark_tag_content(soup, threshold)

    # remove any content with a mark of 0
    tag_look_up={MARK : 0}
    mark_zero = soup.find_all(**tag_look_up)
    [tag.decompose() for tag in mark_zero]

    # remove extra whitespace and duplicate newlines
    output = get_plain_text(soup)

    # write altered html files to noise-html-output folder
    write_to_file(output, filename)

def read_file(folder_name, filename):
    with open(os.path.join(folder_name, filename), 'r', encoding='utf8') as f:
        html_doc = f.read()
    
    return html_doc

# parse all html files from folder and extract text
def extract_text(folder_name):
    threads = []
    for filename in os.listdir(folder_name):
        t = Thread(target=compute_density, args=(folder_name, filename))
        t.start()
        threads.append(t)
    
    for t in threads:
        t.join()


if __name__ == "__main__":
    folder_name = '../repository/English'
    extract_text(folder_name)

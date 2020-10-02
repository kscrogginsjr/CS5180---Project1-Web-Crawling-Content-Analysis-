import os
from bs4 import BeautifulSoup as bs, Comment, Doctype, Tag
import re

DENSITY_ID = "density"
DENSITY_SUM_ID = "density_sum"
MARK_ID = "mark"

def token_count(tag):
    test_string = tag.string if tag.string else ''
    groups = re.findall(r'\w+', test_string)

    result = 0
    for group in groups:
        result += len(group)
    return result

def tag_count(tag):
    # +1 for counting itself
    return len(list(tag.descendants)) + 1

def get_density(tag):
    return float(token_count(tag)) / float(tag_count(tag))

def search_tag(tag: Tag, id: str, density: float) -> Tag:
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
                target = search_tag(sibling, id, density)
                break
    return target

def get_max_density_sum(tag: Tag) -> float:
    try:
        max_density_sum = tag[DENSITY_ID]
    except:
        max_density_sum = 0

    temp_max = 0
    for child in tag.children:
        if isinstance(child, Tag):
            temp_max = get_max_density_sum(child)
        max_density_sum = max(temp_max, max_density_sum)
    tag[DENSITY_SUM_ID] = max_density_sum
    return max_density_sum

def get_tag_density(soup):
    for tag in soup.findAll():
        tag[DENSITY_ID] = get_density(tag)

    soup[DENSITY_ID] = get_density(soup)

def get_threshold(tag: Tag, max_density_sum: float) -> float:
    target = search_tag(tag, DENSITY_SUM_ID, max_density_sum)
    threshold = float(target[DENSITY_ID])
    set_mark(target, 1)
    parent = target.parent
    while parent.name != 'html':
        text_density = float(parent[DENSITY_ID])
        threshold = min(threshold, text_density)
        parent[MARK_ID] = 2
        parent = parent.parent
    return threshold

def set_mark(tag: Tag, mark: int):
    tag[MARK_ID] = mark
    for child in tag.children:
        if isinstance(child, Tag):
            set_mark(child, mark)

def mark_tag_content(tag: Tag, threshold: float):
    text_density = float(tag[DENSITY_ID])
    max_density_sum = float(tag[DENSITY_SUM_ID])
    mark = int(tag[MARK_ID])

    if mark != 1 and threshold > text_density: 
        get_max_density_sum_tag(tag, max_density_sum)
        if isinstance(tag, Tag):
            for child in tag.children:
                mark_tag_content(child, threshold)

def get_max_density_sum_tag(tag: Tag, max_density_sum: float):
    target = search_tag(tag, DENSITY_SUM_ID, max_density_sum)
    mark = int(tag[MARK_ID])
    if mark != 1:
        set_mark(target, 1)
        parent = target.parent
        while parent.name != 'html':
            parent[MARK_ID] = 2 #keep this tag and descendants
            parent = parent.parent

def clean_up(soup):
    comments = soup.findAll(text=lambda text:isinstance(text, Comment))
    [comment.extract() for comment in comments]
    doctype = soup.findAll(text=lambda text:isinstance(text, Doctype))
    [doctype.extract() for doctype in doctype]
    # Remove tags that will not contain content
    for tags in soup.find_all(['script', 'style', 'input', 'nav', 'img', 'button', 'video', 'meta']):
        tags.decompose()
    
    return soup

def get_plain_text(soup):
    output = soup.get_text()
    output = re.compile(r'(\s{2,}|\t)').sub(' ', output)
    output = '\n'.join(list(filter(lambda s: len(s.strip()) > 0, output.splitlines())))
    return output

def write_to_file(content, filename):
    with open(os.path.join('./noise-html-output', filename), 'w', encoding='utf-8') as file:
        file.write(content)
        file.close()

def compute_density(html_doc, filename):
    soup = bs(html_doc, 'html.parser').body

    # Remove any comments in the html
    soup = clean_up(soup)

    #Calculate tag denisities for every tag in the html doc
    get_tag_density(soup)

    #Calculate max density sum to normalize threshold
    max_density_sum = get_max_density_sum(soup)

    #Initialize content_marks dict to mark content for removing or keeping
    set_mark(soup, 0)

    #Calculate threshold to know which content to keep and which to remove
    threshold = get_threshold(soup, max_density_sum)

    #Mark content to be removed and kept
    mark_tag_content(soup, threshold)

    #Remove content
    tag_look_up={MARK_ID : 0}
    mark_zero = soup.find_all(**tag_look_up)
    [tag.decompose() for tag in mark_zero]

    # remove extra whitespace and duplicate newlines
    output = get_plain_text(soup)

    # Write altered html files to noise-html-output folder
    write_to_file(output, filename)

def extract_text(folder_name):
    #=============  Parse HTML files from repository folder==========
    for filename in os.listdir(folder_name):
        with open(os.path.join(folder_name, filename), 'r', encoding='utf8') as f:
            html_doc = f.read()
            compute_density(html_doc, filename)

if __name__ == "__main__":
    folder_name = '../repository'
    extract_text(folder_name)

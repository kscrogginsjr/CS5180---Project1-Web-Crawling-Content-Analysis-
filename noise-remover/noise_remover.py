import os
from bs4 import BeautifulSoup as bs, Comment, Doctype, Tag
import re

"""
Key: bs Tag
Value: float (density)
"""
tag_densities = {}
max_tag_densities = {}
"""
Key: bs Tag
Value: int
"""
content_marks = {}

def token_count(tag):
    test_string = tag.text
    result = len(re.findall(r'\w+', test_string))
    return result

def tag_count(tag):
    # +1 for counting itself
    return len(list(tag.descendants)) + 1

def get_density(tag):
    """
    Char num: number of all characters in its subtree
    Tag num: number of all Tags in its sub tree
    density: Char/Tag
    """
    density = float(token_count(tag)) / float(tag_count(tag))
    return density

def search_tag(tag: Tag, max_density_sum: float) -> Tag:
    """
    A method for returning the first occurrence of a Tag having the value specified
    for the attribute provided
    :param tag:
    :param attribute:
    :param value:
    :return:
    """
    try:
        target = tag_densities[tag]
        if target == max_density_sum:
            return tag
    except KeyError:
        target = None
    if target is None:
        for sibling in tag.descendants:
            if isinstance(sibling, Tag):
                target = search_tag(sibling, max_density_sum)
                break
    for k,v in tag_densities.items():
         if v == target:
             target = k          
    return target

def get_max_density_sum(tag: Tag) -> float:
    try:
        max_density_sum = tag_densities[tag]
    except KeyError:
        max_density_sum = 0
    temp_max = 0
    for child in tag.children:
        if isinstance(child, Tag):
            temp_max = get_max_density_sum(child)
        max_density_sum = max(temp_max, max_density_sum)
    max_tag_densities[tag] = max_density_sum
    return max_density_sum

def get_tag_density(soup):
    for tag in soup.findAll():
        tag_densities[tag] = get_density(tag)
    # tag_densities[soup] = get_density(soup)

def get_threshold(tag: Tag, max_density_sum: float) -> float:
    target = search_tag(tag, max_density_sum)
    threshold = tag_densities[target]
    set_mark(target, 1)
    parent = target
    while parent.name != 'html' and parent.name != 'body':
        text_density = tag_densities[parent]
        threshold = min(threshold, text_density)
        content_marks[parent] = 2
        parent = parent.parent
    return max_density_sum

def set_mark(tag: Tag, mark: int):
    content_marks[tag] = mark
    for child in tag.children:
        if isinstance(child, Tag):
            set_mark(child, mark)

def mark_tag_content(tag: Tag, threshold: float):
    # print(tag.name)
    # print(tag)
    
    if isinstance(tag, Tag):
        for child in tag.children:
            if isinstance(child, Tag) and child.name != 'body':
                mark_tag_content(child, threshold)

    if tag.name == 'body':
        return

    text_density = tag_densities[tag]
    max_density_sum = max_tag_densities[tag]
    mark = content_marks[tag]

    if mark != 1 and threshold > text_density: 
        get_max_density_sum_tag(tag, max_density_sum)
        if isinstance(tag, Tag):
            for child in tag.children:
                if isinstance(child, Tag) and child.name != 'body':
                    mark_tag_content(child, threshold)

def get_max_density_sum_tag(tag: Tag, max_density_sum: float):
    """
    Finds the tag having the max density sum and "marks" it for inclusion later
    :param tag:
    :param max_density_sum:
    """
    target = search_tag(tag, max_density_sum)
    mark = content_marks[target]
    if mark != 1:
        set_mark(target, 1)
        parent = target.parent
        while parent.name != 'html':
            content_marks[target] = 2 #keep this tag and descendants
            parent = parent.parent

def clean_up(soup):
    comments = soup.findAll(text=lambda text:isinstance(text, Comment))
    [comment.extract() for comment in comments]
    doctype = soup.findAll(text=lambda text:isinstance(text, Doctype))
    [doctype.extract() for doctype in doctype]
    # Remove tags that will not contain content
    for tags in soup.find_all(['link', 'script', 'style', 'input', 'nav', 'noindex', 'img', 'button', 'video', 'br', 'meta', 'footer']):
        tags.decompose()
    
    return soup

def compute_density(html_doc):
    soup = bs(html_doc, 'html.parser').body
    # Remove any comments in the html
    soup = clean_up(soup)

    check = soup.get_text()
    check = re.compile(r'(\s{2,}|\t)').sub(' ', check)
    check = '\n'.join(list(filter(lambda s: len(s.strip()) > 0, check.splitlines())))

    print(check)
    #Calculate tag denisities for every tag in the html doc
    get_tag_density(soup)
    #Calculate max density sum to normalize threshold
    max_density_sum = get_max_density_sum(soup)
    #Initialize content_marks dict to mark content for removing or keeping
    set_mark(soup, 0)
    # content_marks[soup] = 1
    #Calculate threshold to know which content to keep and which to remove
    threshold = get_threshold(soup, max_density_sum)
    #Mark content to be removed and kept
    mark_tag_content(soup, threshold)
    print(content_marks.values())
    #Remove content
    output_dirty = soup.get_text()
    for tag, value in content_marks.items():
        if value == 2:
            output_dirty += tag.get_text()
            # removeText = str(tag.get_text()) 
            # output_dirty = output_dirty.replace(removeText, "") + "\n"
    output_dirty = soup.get_text()
    # remove extra whitespace and duplicate newlines
    output_dirty = re.compile(r'(\s{2,}|\t)').sub(' ', output_dirty)
    output_cleaned = '\n'.join(list(filter(lambda s: len(s.strip()) > 0, output_dirty.splitlines())))

    # Write altered html files to noise-html-output folder
    with open(os.path.join('./noise-html-output', filename), 'w', encoding='utf-8') as file:
        file.write(output_cleaned)
        file.close()

    with open(os.path.join('./noise-html-output', 'clean_test.html'), 'w', encoding='utf-8') as file:
        file.write(check)   
        file.close()

def extract_text(folder_name):
    #=============  Parse HTML files from repository folder==========
    for filename in os.listdir(folder_name):
        with open(os.path.join(folder_name, filename), 'r', encoding='utf8') as f:
            html_doc = f.read()
            compute_density(html_doc)
        break

"""
Notes: Need to get token count of blocks
    thoughts - tag.head.text , returns all the children text in a string concatinated
Need to get token count of single tag
    can still use tag.text, but might also consider using tag.content and dealing with None case

Do we iterate through each tag one by one and calculate the density
    maybe put in a dictionary for easy search up after

Do we decompose all the html tags that dont meet the criteria?
"""

if __name__ == "__main__":
    folder_name = '../repository'
    extract_text(folder_name)

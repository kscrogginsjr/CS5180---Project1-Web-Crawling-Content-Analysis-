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

def get_tag_density(tag: Tag):
    tag_densities[tag] = get_density(tag)
    for child in tag.children:
        if isinstance(child, Tag):
            get_tag_density(child)
    max_tag_densities[tag] = get_density(tag)

def get_threshold(tag: Tag, max_density_sum: float) -> float:
    target = search_tag(tag, max_density_sum)
    threshold = tag_densities[target]
    set_mark(target, 1)
    parent = target.parent
    while parent.name != 'html':
        text_density = tag_densities[parent] # KEY ERROR HERE!!!!!!!!
        threshold = min(threshold, text_density)
        content_marks[parent] = 2
        parent = parent.parent
    return max_density_sum

def set_mark(tag: Tag, mark: int):
    #set mark values
    content_marks[tag] = mark
    for child in tag.children:
        if isinstance(child, Tag):
            set_mark(child, mark)

def mark_tag_content(tag: Tag, threshold: float):
    text_density = tag_densities[tag]
    max_density_sum = max_tag_densities[tag]
    mark = content_marks[tag]
    if mark != 1 and threshold > text_density: 
        get_max_density_sum_tag(tag, max_density_sum)
        if isinstance(tag, Tag):
            for child in tag.children:
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
        while parent.name.lower() != 'html':
            content_marks[target] = 2 #keep this tag and descendants
            parent = parent.parent

#=============  Parse HTML files from repository folder==========
for filename in os.listdir('../repository'):
    with open(os.path.join('../repository', filename), 'r', encoding='utf8') as f:
        html_doc = f.read()
        f.close()
        soup = bs(html_doc, 'html.parser').body
        # Remove any comments in the html
        comments = soup.findAll(text=lambda text:isinstance(text, Comment))
        [comment.extract() for comment in comments]
        doctype = soup.findAll(text=lambda text:isinstance(text, Doctype))
        [doctype.extract() for doctype in doctype]
        # Remove tags that will not contain content
        for tags in soup.find_all(['link', 'script', 'style', 'input', 'nav', 'noindex', 'img', 'button', 'video', 'br', 'meta']):
            tags.decompose()
        """
        calculating tag densitys
        Key:BSObject, Value = Density(float)
        should return the list of the tags in BeautifulSoup Object Type
        """
        get_tag_density(soup)
        set_mark(soup, 0)
        max_density_sum = get_max_density_sum(soup)
        threshold = get_threshold(soup, max_density_sum)
        mark_tag_content(soup, threshold)

        for content, value in content_marks.items():
            if value == 0:
                # soup.find(content).decompose()
                print(soup)
        output_dirty = soup.get_text()
        # print(output_dirty)
        output_dirty = re.sub(' ', output_dirty)
        # output_dirty = whitespace_regex.sub(' ', output_dirty)
        output = '\n'.join(list(filter(lambda s: len(s.strip()) > 0, output_dirty.splitlines())))

        # Write altered html files to noise-html-output folder
        with open(os.path.join('./noise-html-output', filename), 'w', encoding='utf-8') as file:
            file.write(output)
            file.close()
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
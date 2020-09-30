import os
from bs4 import BeautifulSoup as bs, Comment, Doctype, Tag
import re


ID_MAX_DENSITY_SUM = 'max-density-sum'
tag_densities_dict = {}
max_tag_densisities_dict = {}

def token_count(tag):
    test_string = tag.text
    result = len(re.findall(r'\w+', test_string))
    return result

def tag_count(tag):
    # +1 for counting itself
    return len(list(tag.descendants)) + 1

def get_density(tag):
    """
    Char num : number of all characters in its subtree
    Tag num : number of all Tags in its sub tree
    density = Char/Tag
    """
    density = float(token_count(tag)) / float(tag_count(tag))
    return density

# def get_max_density_sum(tag: Tag):
#     max_density_sum = 0
#     temp_max = 0
#     max_child = tag   
#     for child in tag.children:
#         if isinstance(child, Tag):
#             temp_max = tag_densities_dict[child]
#             if temp_max > max_density_sum:
#                 max_child = child
#                 max_density_sum = temp_max
#             next_max = get_max_density_sum(child)
#     max_tag_densisities_dict[max_child] = max_density_sum
#     return max_density_sum

def get_max_density_sum(tag: Tag) -> float:
    try:
        max_density_sum = tag_densities_dict[tag]
    except KeyError:
        max_density_sum = 0
    temp_max = 0
    for child in tag.children:
        if isinstance(child, Tag):
            temp_max = get_max_density_sum(child)
        max_density_sum = max(temp_max, max_density_sum)
    max_tag_densisities_dict[tag] = max_density_sum
    return max_density_sum

# def find_max_density(tag: Tag, max_density_sum: float):

# def find_tag(tag: Tag, text: str, value: float):

#=============  Parse HTML files from repository folder==========
for filename in os.listdir('../repository'):
    with open(os.path.join('../repository', filename), 'r', encoding='utf8') as f:
        html_content = f.read()
        f.close()
        soup = bs(html_content, 'html.parser')
        # Remove any comments in the html
        comments = soup.findAll(text=lambda text:isinstance(text, Comment))
        [comment.extract() for comment in comments]
        doctype = soup.findAll(text=lambda text:isinstance(text, Doctype))
        [doctype.extract() for doctype in doctype]
        # Remove tags that will not contain content
        for tags in soup.find_all(['link', 'script', 'style', 'input', 'nav', 'noindex', 'img', 'button', 'video', 'br', 'meta']):
            tags.decompose()

        body = soup.body
        #calculating tag densitys
        #Key:BSObject, Value = Density(float)
        #should return the list of the tags in BeautifulSoup Object Type
        tags = [tag for tag in body.find_all()]
        #should get all the densitys in an array 
        for tag in tags:
            tag_densities_dict[tag] = get_density(tag)
        #body density is initial threshold    
        threshold = tag_densities_dict[tags[0]]
        max_density = get_max_density_sum(body)
        print(tag_densities_dict.values())
        print(max_tag_densisities_dict.values())
        # Write altered html files to noise-html-output folder
        # soup = str(soup.prettify())
        # with open(os.path.join('./noise-html-output', filename), 'w', encoding='utf-8') as file:
        #     file.write(soup)
        #     file.close()
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
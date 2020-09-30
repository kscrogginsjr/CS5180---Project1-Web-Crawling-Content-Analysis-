import os
from bs4 import BeautifulSoup as bs, Comment, Doctype
import re

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

        body = soup.find('body')
        #calculating tag densitys
        #Key:BSObject, Value = Density(float)
        tag_densities_dict = {}
        #should return the list of the tags in BeautifulSoup Object Type
        tags = [tag for tag in body.find_all()]
        #should get all the densitys in an array 
        for tag in tags:
            tag_densities_dict[tag] = get_density(tag)
        #body density is initial threshold    
        threshold = tag_densities_dict[tags[0]]
        for child in body.children:
            if tag_densities_dict[child] >= threshold:        
                for kids in child.children:

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
import os
from bs4 import BeautifulSoup as bs, Comment, Doctype
import re

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

        # Write altered html files to noise-html-output folder
        soup = str(soup.prettify())
        with open(os.path.join('./noise-html-output', filename), 'w', encoding='utf-8') as file:
            file.write(soup)
            file.close()
    break

def token_count(tag):
    test_string = tag.text
    result = len(re.findall(r'\w+', test_string))
    return result

def get_density(tag, block):
    density = float(token_count(tag) / token_count(block))
    return density

"""
Notes: Need to get token count of blocks
    thoughts - tag.head.text , returns all the children text in a string concatinated
Need to get token count of single tag
    can still use tag.text, but might also consider using tag.content and dealing with None case

Do we iterate through each tag one by one and calculate the density
    maybe put in a dictionary for easy search up after

Do we decompose all the html tags that dont meet the criteria?
"""
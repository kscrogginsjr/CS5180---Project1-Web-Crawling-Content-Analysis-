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
        for tags in soup.find_all(['link', 'script','style', 'a', 'input', 'nav', 'img ', 'noindex', 'img', 'button', 'video', 'br', 'meta']):
            tags.decompose()
        # Remove tags, but leave content in the following tags
        # for match in soup.findAll(['li','div', 'span', 'head']):
        #     match.replaceWithChildren()
        for match in soup.findAll():
            match.replaceWithChildren()
        # Write altered html files to noise-html-output folder
        soup = str(soup.prettify())
        with open(os.path.join('./noise-html-output', filename), 'w', encoding='utf-8') as file:
            file.write(soup)
            file.close()

def token_count(tag):
    test_string = tag.text
    result = len(re.findall(r'\w+', test_string))
    return result

def get_density(tag, block):
    density = float(token_count(tag) / token_count(block))
    return density
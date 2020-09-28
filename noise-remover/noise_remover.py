import os
from bs4 import BeautifulSoup as bs, Comment

#=============  Parse HTML files from repository folder==========
for filename in os.listdir('../repository'):
    with open(os.path.join('../repository', filename), 'r', encoding='utf8') as f:
        html_content = f.read()
        f.close()
        soup = bs(html_content, 'html.parser')
        # Remove any comments in the html
        comments = soup.findAll(text=lambda text:isinstance(text, Comment))
        [comment.extract() for comment in comments]
        # Remove tags that will not contain content
        for tags in soup.find_all(['link', 'script','style', 'a', 'input', 'nav', 'img ', 'head', '<!--', 'img']):
            tags.decompose()
        # Remove tags, but leave content in the following tags
        for match in soup.findAll(['li','div', 'span']):
            match.replaceWithChildren()
        # Write altered html file to soupText.html in noise-remover folder
        soup = str(soup)
        [soup.strip('\n') for s in soup]
        with open('soupText.html', 'w', encoding='utf-8') as file:
            file.write(soup)
            file.close()
    break
# ======= For pulling data out of HTML and XML files ======
# $ easy_install beautifulsoup4 - install beautifulsoup4

# ================== Installing a parser ===================
# $ easy_install lxml
# $ easy_install html5lib (parses HTML the way a web browser does)
import os
from bs4 import BeautifulSoup as bs, Comment

#=============  Parse a document (can pass string or open filehandle) ==========
for filename in os.listdir("../repository"):
    with open(os.path.join("../repository", filename), 'r', encoding="utf8") as f:
        html_content = f.read()
        f.close()
        soup = bs(html_content, "html.parser")
        # Remove any comments in the html
        comments = soup.findAll(text=lambda text:isinstance(text, Comment))
        [comment.extract() for comment in comments]
        # Remove tags that will not contain content
        for tags in soup.find_all(["link", "script","style", "a", "input", "nav", "img ", "head", "<!--"]):
            tags.decompose()
        print(soup)
    break
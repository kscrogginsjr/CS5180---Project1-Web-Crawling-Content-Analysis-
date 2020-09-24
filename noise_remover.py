# ======= For pulling data out of HTML and XML files ======
# $ easy_install beautifulsoup4 - install beautifulsoup4

# ================== Installing a parser ===================
# $ easy_install lxml
# $ easy_install html5lib (parses HTML the way a web browser does)

from bs4 import BeautifulSoup

#=============  Parse a document (can pass string or open filehandle) ==========
with open("index.html") as fp:
    soup = BeautifulSoup(fp, features="lxml")

soup = BeautifulSoup("<html>a web page</html>", features="lxml")

print(BeautifulSoup("<html><head></head><body>Sacr&eacute; bleu!</body></html>", features="lxml"))
# <html><head></head><body>Sacré bleu!</body></html>

# =============== Tags =====================
soup = BeautifulSoup('<b class="boldest">Extremely bold</b>', features="lxml")
tag = soup.b
type(tag)
# <class 'bs4.element.Tag'>
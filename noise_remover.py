# ======== Make HTTP requests to get data from web ======
# $ pip install requests==2.22.0

# ======= For pulling data out of HTML and XML files ======
# $ easy_install beautifulsoup4 - install beautifulsoup4

# ================== Installing a parser ===================
# $ easy_install lxml
# $ easy_install html5lib (parses HTML the way a web browser does)
import requests
from bs4 import BeautifulSoup

#=============  Parse a document (can pass string or open filehandle) ==========
with open("index.html") as fp:
    soup = BeautifulSoup(fp, features="lxml")

soup = BeautifulSoup("<html>a web page</html>", features="lxml")

print(BeautifulSoup("<html><head></head><body>Sacr&eacute; bleu!</body></html>", features="lxml"))
# <html><head></head><body>Sacré bleu!</body></html>

# ===== Create a BeautifulSoup object with the HTML from GET request to the web page ====
vgm_url = 'https://www.vgmusic.com/music/console/nintendo/nes/'
html_text = requests.get(vgm_url).text
soup = BeautifulSoup(html_text, 'html.parser')

#================== find and find_all =================
#soup.find() is great for cases where you know there is only one element (ex. body tag)
#soup.find_all() iterate through all of the hyperlinks on the page and print their URLs:
for link in soup.find_all('a'):
    print(link.get('href'))
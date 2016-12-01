import frequency
import urllib.request
from bs4 import BeautifulSoup

def get_only_text(url):
 """
  return the title and the text of the article
  at the specified url
 """
 page = urllib.request.urlopen(url).read().decode('utf8')
 soup = BeautifulSoup(page, "lxml")
 text = ' '.join(map(lambda p: p.text, soup.find_all('p')))
 return soup.title.text, text
# We can finally apply our summarizer on a set of articles extracted from the BBC news feed:
feed_xml = urllib.request.urlopen('http://feeds.bbci.co.uk/news/rss.xml').read()
feed = BeautifulSoup(feed_xml.decode('utf8'), "lxml")
to_summarize = list(map(lambda p: p.text, feed.find_all('guid')))

fs = frequency.FrequencySummarizer()
print("hi")
for article_url in to_summarize[:5]:
  title, text = get_only_text(article_url)
  print ('----------------------------------')
  print (title)
  for s in fs.summarize(text, 2):
   print ('*',s)

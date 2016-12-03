from bs4 import BeautifulSoup
import frequency

file = open('testfile.txt', 'r')
fs = frequency.FrequencySummarizer()
text = file.read()

s = fs.summarize(text, 1)
print ('*',s)

import requests
import bs4
import re

url=input("Enter a URL: ")
response = requests.get(url)
#print(type(response))
#print(response.text)
filename = "temp.html"
bs = bs4.BeautifulSoup(response.text,"html.parser")
formatted_text = bs.prettify() 
print(formatted_text)
pattern = "/.*geek.*/g"
results = re.findall(pattern,formatted_text)

#with open(filename, "w") as f:
 #   f.write(formatted_text)
    
import requests
from bs4 import BeautifulSoup
from collections import OrderedDict
import urllib.request

url = 'http://cafe.naver.com/ArticleList.nhn?search.clubid=15754634&search.menuid=485&search.boardtype=L&search.questionTab=A&search.totalCount=151&search.page='
baseUrl = 'http://cafe.naver.com/'
wordDB= []
dewordDB = []
titles = []
urls = []
temp_titles = []
temp_urls = []
temp2_titles = []
temp2_urls = []
filename = []
def main() :
    print('ICT 관련 공모전 크롤링 프로그램 : 2017/3/30 ~ 2017/4/2 by 위대')
    print('Edited 2017/4/2 ~ 2017/4/x by 위대')
    maxPages = input("Input the volume of the data(minimum 1) : ")
    spider(int(maxPages))
    analyze()
    puzzle()
    temp2_titles = reassemble(temp_titles)
    temp2_urls = reassemble(temp_urls)
    pageParsing(temp2_urls)
    #imgdown(temp2_urls)
    dellData()
    for data in temp2_titles :
        print(data + " : "+ temp2_urls[temp2_titles.index(data)])
    #
    #   이까지 완성된 자료는 temp2_titles, temp2_urls
    #
    menu = input("Download the images from the url(y/n) : ")
    if menu == "y" :
        imageDown()
    else :
        print("Program Down")

def spider(maxPages): #크롤링.
    page = 1
    while(page <= maxPages):
        sum_url = url+str(page)
        source_code = requests.get(sum_url)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text, 'lxml')
        for link in soup.select('span > a') :
            title = link.string
            url_temp =  link.get('href')
            urls.append(baseUrl + url_temp)
            titles.append(title)
        page+=1

def analyze() : #DB에 txt파일 저장
    with open("wordDiction.txt", "r") as f:
        lines = f.readlines()
        for line in lines:
            wordDB.append(line.strip('\n'))
        #print(wordDB)
    with open("dewordDiction.txt", "r") as f:
        lines = f.readlines()
        for line in lines:
            dewordDB.append(line.strip('\n'))

def puzzle() : ##DB에 맞춰서 wordDiction이랑 맞으면 저장, 안 맞으면(dewordDiction)이면 예외. 삭제.
    for items in titles:
        for wordDBdata in wordDB:
            if items != None:
                if items.find(wordDBdata) != -1:
                    temp_titles.append(items)
                    temp_urls.append(urls[titles.index(items)])
    for items in titles:
        for wordDBdata in dewordDB:
            if items != None:
                if items.find(wordDBdata) != -1:
                    temp_urls.remove(urls[titles.index(items)])
                    temp_titles.remove(items)

def dellData() :
    try:
        del urls
        del temp_titles
        del temp_urls
        del wordDB
        del titles
    except :
        print("Unnecessary data removed")

def reassemble(list1) :
    return list(OrderedDict.fromkeys(list1))

def pageParsing(urlList) :
    for data in urlList :
        sum_url = data
        source_code = requests.get(sum_url)
        plain_text = source_code.text
        soup = BeautifulSoup(plain_text, 'lxml')
        #여기서부터 img형식을 찾음.
        #for link in soup.select('span > a'):
        #    title = link.string
        #    url_temp = link.get('href')
        #    urls.append(baseUrl + url_temp)
        #    titles.append(title)
        #page += 1
        #여기서 다운 하면 댐.
        #urllib.request.urlretrieve(url , filename+".jpg")
        #filename.append(filename)
main()
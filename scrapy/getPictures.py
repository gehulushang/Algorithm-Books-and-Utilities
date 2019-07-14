

#一个简单的获取图片的爬虫方法
#引入 BeautifulSoup 解析文档，提取需要抓取的数据
from bs4 import BeautifulSoup

#引入request库 获取内容
import requests

import os

#新建文件夹
os.makedirs('./img/', exist_ok=True)

#网址
URL = "http://www.nationalgeographic.com.cn/animals/"

#获取网址信息
html = requests.get(URL).text

#对网址信息进行解析
soup = BeautifulSoup(html, 'html.parser')

#获取所有有关于图片的内容
img_ul = soup.find_all('ul', {"class": "img_list"})


for ul in img_ul:
    imgs = ul.find_all('img')
    for img in imgs:
        url = img['src']
        #用response储存在获取url的响应
        r = requests.get(url, stream=True)
        image_name = url.split('/')[-1]
        #'wb': 读写方式标识符，以二进制打开文件用于写入
        with open('./img/%s' % image_name, 'wb') as f:
            #利用Respose对象的iter_content()方法循环
            for chunk in r.iter_content(chunk_size=128):
                f.write(chunk)
        print('Saved %s' % image_name)

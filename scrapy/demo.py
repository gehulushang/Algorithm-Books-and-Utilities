

#爬取天气信息并进行可视化
from bs4 import BeautifulSoup
import requests
import pandas as pd
from matplotlib import pyplot as plt

#定义获取数据的方法
def get_data(url):

    resp = requests.get(url)

    html = resp.content.decode('gbk')

    soup = BeautifulSoup(html,'html.parser')

    tr_list = soup.find_all('tr')

    dates,conditions,temp = [],[],[]

    for data in tr_list[1:]:
        sub_data = data.text.split()
        #日期
        dates.append(sub_data[0])
        #天气
        conditions.append(''.join(sub_data[1:3]))
        #温度
        temp.append(''.join(sub_data[3:6]))
        
    #引入pandas库引入DataFrame()，将数据保存
    _data = pd.DataFrame()
    _data['日期'] = dates
    _data['天气'] = conditions
    _data['气温'] = temp
    #返回DataFrame()数据
    return _data


data_1_month = get_data('http://www.tianqihoubao.com/lishi/hangzhou/month/201901.html')

data_2_month = get_data('http://www.tianqihoubao.com/lishi/hangzhou/month/201902.html')
data_3_month = get_data('http://www.tianqihoubao.com/lishi/hangzhou/month/201903.html')

#数据拼接
data = pd.concat([data_1_month,data_2_month,data_3_month]).reset_index(drop = True)

#转为csv格式的文件
data.to_csv('杭州.csv', index = False,encoding='utf-8')

#获取最高温度个最低温度
data['最高气温']=data['气温'].str.split('/',expand = True)[0]

data['最低气温']=data['气温'].str.split('/',expand = True)[1]

#转换数据格式
data['最高气温'] = data['最高气温'].map(lambda x : int(x.replace('℃','')))

data['最低气温'] = data['最低气温'].map(lambda x : int(x.replace('℃','')))

#提取数据
dates = data['日期']

highs = data['最高气温']

lows = data['最低气温']

#用来正常显示中文标签
plt.rcParams['font.sans-serif'] = ['SimHei']

#用来正常显示负号
plt.rcParams['axes.unicode_minus'] = False

fig = plt.figure(dpi=128,figsize=(10,6))

plt.plot(dates,highs,c = 'red',alpha = 0.5)

plt.plot(dates,lows,c = 'blue',alpha = 0.5)

plt.fill_between(dates,highs,lows,facecolor = 'blue',alpha = 0.2)

plt.title('2019年一季度天气')

plt.xlabel('日期',fontsize = 24)

fig.autofmt_xdate()

plt.ylabel('气温',fontsize = 12)

plt.tick_params(axis='both',which = 'major',labelsize = 10)

plt.xticks(dates[::20])

plt.show()

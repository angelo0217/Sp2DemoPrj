# Sp2DemoPrj
SpringBoo2專案  oauth2、aop、Mybatis、cache
>搭配Docker起Redis及Mysql 5.7
>>Docker部屬jar指令
>>將Dockerfile及jar檔放置同目錄下，註冊成Image
>>>docker build -t="demo/basic" .
>>啟動容器
>>>docker run --name schedule -d -p 8888:8888 demo/basic
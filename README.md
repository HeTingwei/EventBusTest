# EventBusTest
使用EventBus框架，以及它的特性测试<br>
EventBus的github网址：https://github.com/greenrobot/EventBus<br>
需要在gradle中添加的依赖(可以在原项目github上找最新的)：<br>
```
compile 'org.greenrobot:eventbus:3.1.1'
```
对接收消息的方法的官网讲解：[点击跳转](http://greenrobot.org/eventbus/documentation/delivery-threads-threadmode/)
### 下面是在主线程发消息，对应方法线程情况：
![图片加载失败](https://github.com/HeTingwei/EventBusTest/blob/master/doc/%E4%B8%BB%E7%BA%BF%E7%A8%8B%E5%8F%91%E6%B6%88%E6%81%AF.png)
### 下面是在一个子线程发消息，对应方法情况
![图片加载失败)](https://github.com/HeTingwei/EventBusTest/blob/master/doc/%E5%AD%90%E7%BA%BF%E7%A8%8B%E5%8F%91%E6%B6%88%E6%81%AF.png)


# EventBusTest
使用EventBus框架，以及它的特性测试<br>
EventBus的github网址：https://github.com/greenrobot/EventBus<br>
需要在gradle中添加的依赖(可以在原项目github上找最新的)：<br>
```
compile 'org.greenrobot:eventbus:3.1.1'
```

项目中，接受消息的方法名是自己取的，关键是方法前的@Subscribe后的参数，以及要求方法参数为发消息发的对象。<br>
举例：下面是一个接受消息的方法，方法名自己随意定义，但@Subscribe一行才是重点，Msg为自己随意定义的类<br>
```
@Subscribe(threadMode = ThreadMode.POSTING)
    public void postingThread(Msg msg) {}
```
对接收消息的方法的官网讲解：[点击跳转](http://greenrobot.org/eventbus/documentation/delivery-threads-threadmode/)
### 下面是在主线程发消息，对应方法线程情况：
![图片加载失败](https://github.com/HeTingwei/EventBusTest/blob/master/doc/%E4%B8%BB%E7%BA%BF%E7%A8%8B%E5%8F%91%E6%B6%88%E6%81%AF.png)
### 下面是在一个子线程发消息，对应方法情况
![图片加载失败)](https://github.com/HeTingwei/EventBusTest/blob/master/doc/%E5%AD%90%E7%BA%BF%E7%A8%8B%E5%8F%91%E6%B6%88%E6%81%AF.png)


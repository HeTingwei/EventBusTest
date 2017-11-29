package com.example.eventbustest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 *学习网址：http://www.jianshu.com/p/31e3528ca7e5
 * 官网讲解：http://greenrobot.org/eventbus/documentation/delivery-threads-threadmode/
 *
 * 本项目是对EventBus的学习
 * 这里是在同一个活动发送和接收，可以在不同组件键传输活动，并且发送消息的方法没有上下文做参数
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


//注册监听上下文，这里是在MainActivity中监听
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

//注销监听
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    //在主线程发送一条消息
    public void mainThreadClick(View view) {
        Log.d(TAG, "主线程发消息，线程名："+Thread.currentThread().getName());
        EventBus.getDefault().post(new Msg("主线程发出的消息"));
    }

    //在子线程发出消息
    public void childThreadClick(View view) {
        new Thread(){
            @Override
            public void run() {
                Log.d(TAG, "子线程发消息，线程名："+Thread.currentThread().getName());
                EventBus.getDefault().post(new Msg("子线程发出的消息"));

            }
        }.start();
    }

//注意：以下方法全是接受消息的方法，方法名是随意命名的，重点看@Subscribe后面的参数
    //每次发出消息，下面所有方法都会接受到
    //需要有发出的消息对象参数


// 在与发送消息的相同线程回调.这个是默认方法下面一行等价于 ：@Subscribe
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void postingThread(Msg msg) {
        Log.d(TAG, "postingThread:接收到： "+msg.message+"\n现在方法所在线程："+Thread.currentThread().getName());
    }

//在主线程回调，要求方法执行不能耗时过多
    @Subscribe(threadMode=ThreadMode.MAIN)
    public void mainThread(Msg msg) {
        Log.d(TAG, "mainThread:接收到： "+msg.message+"\n现在方法所在线程："+Thread.currentThread().getName());
    }

//在主线程按发消息的顺序执行
    @Subscribe(threadMode = ThreadMode.MAIN_ORDERED)
    public void mainOrderedThread(Msg msg) {
        Log.d(TAG, "mainOrderedThread:接收到： "+msg.message+"\n在方法所在线程："+Thread.currentThread().getName());
    }

    // 如果调用post方法的线程不是主线程，则直接在该线程执行
    // 如果是主线程，则切换到后台单例线程，多个方法公用同个后台线程，按顺序执行，不要有耗时操作
    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void backgroundThread(Msg msg){
        Log.d(TAG, "backgroundThread:接收到： "+msg.message+"\n在方法所在线程："+Thread.currentThread().getName());
    }

    //任何线程发消息，此方法都开辟新独立线程，用来执行耗时操作，例如网络访问
    //EventBus内部使用了线程池，但是要尽量避免大量长时间运行的异步线程，限制并发线程数量
    //可以通过EventBusBuilder修改，默认使用Executors.newCachedThreadPool()
    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void asyncThread(Msg msg){
        Log.d(TAG, "asyncThread:接收到： "+msg.message+"\n在方法所在线程："+Thread.currentThread().getName());
    }

}



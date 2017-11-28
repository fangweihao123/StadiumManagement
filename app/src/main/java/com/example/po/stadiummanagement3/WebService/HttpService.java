package com.example.po.stadiummanagement3.WebService;

/**
 * Created by 13701 on 2017/11/25.
 */

import java.util.concurrent.TimeUnit;

import okhttp3.*;
/**
 * Created by 13701 on 2017/11/23.
 */

public class HttpService {
    public static void sendOkHttpRequest(final String _url,okhttp3.Callback callback){                       //发送请求的动作
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();//在builder的时候就实例化了一个dispatcher
        final Request request = new Request.Builder().get()                 //用到build的设计模式
                .url(_url).build();
        client.newCall(request).enqueue(callback);

        //okhttp 异步请求方法
        //1.创建okhttpClient和Request对象
        //2.将Request封装为call对象
        //3.调用Call的enqueue方法进行异步请求
    }
}
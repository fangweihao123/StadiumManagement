package com.example.po.stadiummanagement3.WebService;

/**
 * Created by 13701 on 2017/11/25.
 */

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

import okhttp3.*;
/**
 * Created by 13701 on 2017/11/23.
 */

public class HttpService {
    public static final String junUrl = "http://192.168.1.105:8888/";
    public static void sendOkHttpRequest(final String _url,okhttp3.Callback callback){                       //发送请求的动作
        OkHttpClient client = new OkHttpClient.Builder().readTimeout(5, TimeUnit.SECONDS).build();//在builder的时候就实例化了一个dispatcher
        final Request request = new Request.Builder().get()                 //用到build的设计模式
                .url(junUrl+_url).build();
        client.newCall(request).enqueue(callback);                      //newCall 返回call 通过call来调用enqueue
        //client.newCall(request).execute();
        //okhttp 异步请求方法
        //1.创建okhttpClient和Request对象
        //2.将Request封装为call对象
        //3.调用Call的enqueue方法进行异步请求
    }
    public static Response sendGetRequest(final String url) throws ExecutionException, InterruptedException
    {
        FutureTask<Response> task=new FutureTask<Response>(new Callable<Response>() {
            @Override
            public Response call() throws IOException {

                OkHttpClient client = new OkHttpClient();
                Response response;
                Request request=new Request.Builder().url(junUrl+url).build();
                String s="";
                response = client.newCall(request).execute();
                s=response.toString();
                return response;
            }
        });
        new Thread(task).start();
        return task.get();
    }

}
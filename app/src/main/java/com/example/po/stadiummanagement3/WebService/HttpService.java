package com.example.po.stadiummanagement3.WebService;

/**
 * Created by 13701 on 2017/11/25.
 */

import okhttp3.*;
/**
 * Created by 13701 on 2017/11/23.
 */

public class HttpService {
    public static void sendOkHttpRequest(final String _url,okhttp3.Callback callback){                       //发送请求的动作
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder().get()
                .url(_url).build();
        client.newCall(request).enqueue(callback);
    }
}
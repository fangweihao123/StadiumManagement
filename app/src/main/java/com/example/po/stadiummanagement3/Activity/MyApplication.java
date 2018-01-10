package com.example.po.stadiummanagement3.Activity;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.widget.TextView;

import com.example.po.stadiummanagement3.step.config.Constant;
import com.example.po.stadiummanagement3.step.service.StepService;

/**
 * Created by 13701 on 2018/1/10.
 */

public class MyApplication extends Application implements Handler.Callback{

    private long TIME_INTERVAL = 500;
    private Handler delayHandler;
    private Messenger messenger;
    private Messenger mGetReplyMessenger = new Messenger(new Handler(this));
    //使用bind形式开启服务，故有ServiceConnection接受回调
    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                messenger = new Messenger(service);
                Message msg = Message.obtain(null, Constant.MSG_FROM_CLIENT);
                msg.replyTo = mGetReplyMessenger;
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        //delayHandler = new Handler(this);    //初始化handler
        setupService();
    }

    public void setupService() {
        Intent intent = new Intent(this, StepService.class);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
        startService(intent);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}

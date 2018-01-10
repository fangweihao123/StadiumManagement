package com.example.po.stadiummanagement3.Activity;

/**
 * Created by 13701 on 2017/11/25.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.TextView;

import com.example.po.stadiummanagement3.Fragment.LoadingFragment;
import com.example.po.stadiummanagement3.R;
import com.example.po.stadiummanagement3.step.config.Constant;
import com.example.po.stadiummanagement3.step.service.StepService;

/**
 * Created by 13701 on 2017/11/23.
 */

public class LoadingActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new LoadingFragment();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //使用bind形式开启服务，故有ServiceConnection接受回调
}
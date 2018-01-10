package com.example.po.stadiummanagement3.step.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by 田雍恺 on 2018/1/7.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context, StepService.class);
        context.startService(intent1);
    }
}
package com.example.po.stadiummanagement3.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.po.stadiummanagement3.Fragment.ScheduleFragment;

/**
 * Created by 13701 on 2017/11/29.
 */

public class ScheduleActivity  extends SingleFragmentActivity {
    private String areaName;
    @Override
    protected Fragment createFragment() {
        Bundle bundle = getIntent().getExtras();
        areaName = bundle.getString("areaName");
        Bundle bundle1 = new Bundle();
        bundle1.putString("areaName",areaName);
        ScheduleFragment fragment = new ScheduleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}

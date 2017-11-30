package com.example.po.stadiummanagement3.Activity;

import android.support.v4.app.Fragment;

import com.example.po.stadiummanagement3.Fragment.ScheduleFragment;

/**
 * Created by 13701 on 2017/11/29.
 */

public class ScheduleActivity  extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ScheduleFragment();
    }
}

package com.example.po.stadiummanagement3.Activity;

import android.support.v4.app.Fragment;

import com.example.po.stadiummanagement3.Fragment.MainFragment;

public class MainActivity extends SingleFragmentActivity{                                       //Navigation Drawer是导航抽屉 设计用于应用导航
    @Override
    protected Fragment createFragment() {
        return new MainFragment();
    }
}

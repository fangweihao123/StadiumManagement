package com.example.po.stadiummanagement3.Activity;

/**
 * Created by 13701 on 2017/11/25.
 */

import android.support.v4.app.Fragment;

import com.example.po.stadiummanagement3.Fragment.LoadingFragment;

/**
 * Created by 13701 on 2017/11/23.
 */

public class LoadingActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LoadingFragment();
    }

}
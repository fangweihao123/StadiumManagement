package com.example.po.stadiummanagement3.Activity;

import android.support.v4.app.Fragment;

import com.example.po.stadiummanagement3.Fragment.ReleaseFragment;

/**
 * Created by 13701 on 2018/1/10.
 */

public class ReleaseActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new ReleaseFragment();
    }
}

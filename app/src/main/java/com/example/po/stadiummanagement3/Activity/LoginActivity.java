package com.example.po.stadiummanagement3.Activity;

import android.support.v4.app.Fragment;

import com.example.po.stadiummanagement3.Fragment.LoginFragment;

/**
 * Created by 13701 on 2017/12/3.
 */

public class LoginActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new LoginFragment();
    }
}

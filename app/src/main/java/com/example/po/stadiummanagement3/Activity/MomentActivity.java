package com.example.po.stadiummanagement3.Activity;

/**
 * Created by 13701 on 2017/11/25.
 */
import android.support.v4.app.Fragment;

import com.example.po.stadiummanagement3.Fragment.MomentFragment;

/**
 * Created by 13701 on 2017/11/24.
 */

public class MomentActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new MomentFragment();
    }
}

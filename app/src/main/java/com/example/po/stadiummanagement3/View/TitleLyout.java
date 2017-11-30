package com.example.po.stadiummanagement3.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.po.stadiummanagement3.R;

/**
 * Created by 13701 on 2017/11/29.
 */

public class TitleLyout extends LinearLayout {

    public TitleLyout(Context context, AttributeSet attr) {
        super(context, attr);
        LayoutInflater.from(context).inflate(R.layout.layout_title, this);
    }
}
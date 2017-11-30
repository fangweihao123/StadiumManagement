package com.example.po.stadiummanagement3.Holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.po.stadiummanagement3.Activity.ScheduleActivity;
import com.example.po.stadiummanagement3.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 13701 on 2017/11/29.
 */

public class AreaHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.imageView1)
    public ImageView imageView1;
    @BindView(R.id.imageView2)
    public ImageView imageView2;
    private Context _context;
    public AreaHolder(View itemView, Context context) {
        super(itemView);
        _context = context;

        ButterKnife.bind(this,itemView);
    }

    public void bindInfo(String text,int drawable_id){
        imageView1.setImageResource(drawable_id);
        imageView2.setImageResource(drawable_id);
    }

}
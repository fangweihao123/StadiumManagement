package com.example.po.stadiummanagement3.Holder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.po.stadiummanagement3.Activity.ScheduleActivity;
import com.example.po.stadiummanagement3.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 13701 on 2017/11/29.
 */

public class AreaHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.text_view1)
    public TextView textView;
    @BindView(R.id.imageView2)
    public ImageView imageView2;
    @BindView(R.id.home_card)
    public CardView cardView;

    private Context _context;

    public AreaHolder(View itemView, Context context) {
        super(itemView);
        _context = context;

        ButterKnife.bind(this,itemView);
    }

    public void bindInfo(String text){
        if(text.equals("swimmingpool")){
            imageView2.setImageResource(R.drawable.pool);
            textView.setText(text);
        }else if(text.equals("basketball")){
            imageView2.setImageResource(R.drawable.basketball);
            textView.setText(text);
        }else if(text.equals("乒乓球馆")){
            imageView2.setImageResource(R.drawable.pingpang);
            textView.setText(text);
        }else if(text.equals("羽毛球馆")){
            imageView2.setImageResource(R.drawable.badminton);
            textView.setText(text);
        }
        //Glide.with(_context)
         //       .load("http://192.168.43.83:8888/stadium/pic?name=basketball").into(imageView2);
        //imageView2.setImageResource(R.drawable.badminton);
    }

}
package com.example.po.stadiummanagement3.Holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.po.stadiummanagement3.Gson.Moment;
import com.example.po.stadiummanagement3.R;

/**
 * Created by 13701 on 2018/1/5.
 */

public class MomentHolder extends RecyclerView.ViewHolder {
    public TextView desc_text;
    public CardView cardView;
    public ImageView imageView;
    public MomentHolder(View view){
        super(view);
        cardView = view.findViewById(R.id.moment_card);
        desc_text = view.findViewById(R.id.moment_desc);
        imageView = view.findViewById(R.id.moment_image);
    }

    public void bindInfo(Context context,Moment moment){
        desc_text.setText(moment.getDesc());

        //Glide.with(context).load(R.drawable.test).into(imageView);
       // imageView.setImageBitmap();
    }
}

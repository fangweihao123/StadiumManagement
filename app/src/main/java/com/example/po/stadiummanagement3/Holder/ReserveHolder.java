package com.example.po.stadiummanagement3.Holder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.po.stadiummanagement3.R;

/**
 * Created by 13701 on 2017/11/29.
 */

public class ReserveHolder extends RecyclerView.ViewHolder{
    public ImageView imageView;
    public TextView textView;
    public CardView cardView;
    public ReserveHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.pic_id);
        textView = itemView.findViewById(R.id.reserve_text1);
        cardView = itemView.findViewById(R.id.card_view);
    }

    public void bindInfo(String text,int drawable_id){
        imageView.setImageResource(drawable_id);
        textView.setText(text);
    }
}

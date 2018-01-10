package com.example.po.stadiummanagement3.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.po.stadiummanagement3.Gson.Moment;
import com.example.po.stadiummanagement3.Holder.MomentHolder;
import com.example.po.stadiummanagement3.Holder.ReserveHolder;
import com.example.po.stadiummanagement3.R;

import java.util.List;

/**
 * Created by 13701 on 2018/1/5.
 */

public class MomentAdapter extends RecyclerView.Adapter<MomentHolder> {
    private List<Moment> momentList;
    private Context context;
    public MomentAdapter(List<Moment> _momentList,Context _context){
        momentList = _momentList;
        context = _context;
    }

    @Override
    public MomentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.moment_item,parent,false);
        MomentHolder mMomentHolder = new MomentHolder(v);
        return mMomentHolder;
    }

    @Override
    public void onBindViewHolder(MomentHolder holder, int position) {
        //Bitmap bitmap = BitmapFactory.decodeResource(context,R.drawable.badminton);
        Glide.with(context).load("http://cn.bing.com/az/hprichbg/rb/SamburuNests_ROW13189624824_1920x1080.jpg").into(holder.imageView);
        holder.bindInfo(context,momentList.get(position));
    }

    @Override
    public int getItemCount() {
        return momentList.size();
    }
}

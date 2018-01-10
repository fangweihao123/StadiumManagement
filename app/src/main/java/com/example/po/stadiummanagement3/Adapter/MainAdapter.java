package com.example.po.stadiummanagement3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.po.stadiummanagement3.Activity.ScheduleActivity;
import com.example.po.stadiummanagement3.Fragment.MainFragment;
import com.example.po.stadiummanagement3.Gson.AreaInfo;
import com.example.po.stadiummanagement3.Holder.AreaHolder;
import com.example.po.stadiummanagement3.R;

import java.util.List;

/**
 * Created by 13701 on 2017/11/29.
 */

public class MainAdapter extends RecyclerView.Adapter<AreaHolder>{
    private List<AreaInfo> infos;
    private Context context;

    public MainAdapter(List<AreaInfo> _infos,Context _context){
        infos = _infos;
        context = _context;
    }
    @Override
    public AreaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.area_layout,parent,false);
        AreaHolder mHolder = new AreaHolder(v,context);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(AreaHolder holder, final int position) {

        holder.bindInfo(infos.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ScheduleActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle mBundle = new Bundle();
                mBundle.putString("areaName",infos.get(position).getName());
                intent.putExtras(mBundle);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return infos.size();
    }
}
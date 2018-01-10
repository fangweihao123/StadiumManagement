package com.example.po.stadiummanagement3.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.po.stadiummanagement3.Gson.AreaInfo;
import com.example.po.stadiummanagement3.Holder.ReserveHolder;
import com.example.po.stadiummanagement3.R;

import java.util.List;

/**
 * Created by 13701 on 2017/11/29.
 */

public class ReserveAdapter extends RecyclerView.Adapter<ReserveHolder>{
    private List<AreaInfo> infos;
    private Context context;

    public ReserveAdapter(List<AreaInfo> _infos, Context _context){
        infos = _infos;
        context = _context;
    }


    @Override
    public ReserveHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.reserve_item,parent,false);
        ReserveHolder mHolder = new ReserveHolder(v);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(ReserveHolder holder, int position) {
        holder.bindInfo(infos.get(position).getName(),R.drawable.test);
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {                       //click设置在这里
                Toast.makeText(context,"1111",Toast.LENGTH_LONG);
            }
        });
    }

    @Override
    public int getItemCount() {
        return infos.size();
    }
}
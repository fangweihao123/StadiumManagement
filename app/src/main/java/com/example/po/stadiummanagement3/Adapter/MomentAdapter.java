package com.example.po.stadiummanagement3.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.po.stadiummanagement3.Fragment.MomentFragment;
import com.example.po.stadiummanagement3.Gson.AreaInfo;
import com.example.po.stadiummanagement3.Holder.MomentHolder;
import com.example.po.stadiummanagement3.R;

import java.util.List;

/**
 * Created by 13701 on 2017/11/29.
 */

public class MomentAdapter extends RecyclerView.Adapter<MomentHolder>{
    private List<AreaInfo> infos;
    private Context context;

    public MomentAdapter(List<AreaInfo> _infos,Context _context){
        infos = _infos;
        context = _context;
    }


    @Override
    public MomentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.moment_item,parent,false);
        MomentHolder mHolder = new MomentHolder(v);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(MomentHolder holder, int position) {
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
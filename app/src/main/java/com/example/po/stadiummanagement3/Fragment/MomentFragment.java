package com.example.po.stadiummanagement3.Fragment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.po.stadiummanagement3.Gson.AreaInfo;
import com.example.po.stadiummanagement3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13701 on 2017/11/24.
 */

public class MomentFragment extends Fragment {
    private List<AreaInfo> list;
    private MomentAdapter mAdapter;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.moment_layout,container,false);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        list = new ArrayList<>();
        list.add(new AreaInfo("aaaaaaaa"));
        list.add(new AreaInfo("bbbbbbb"));
        list.add(new AreaInfo("ccccccc"));
        mAdapter = new MomentAdapter(list,getActivity());
        recyclerView = v.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        return v;
    }

    private class MomentAdapter extends RecyclerView.Adapter<MomentHolder>{
        private List<AreaInfo> infos;
        private Context context;

        MomentAdapter(List<AreaInfo> _infos,Context _context){
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

    private class MomentHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public  TextView textView;
        public CardView cardView;
        public MomentHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pic_id);
            textView = itemView.findViewById(R.id.text_view);
            cardView = itemView.findViewById(R.id.card_view);
        }

        public void bindInfo(String text,int drawable_id){
            textView.setText(text);
            imageView.setImageResource(drawable_id);
            //imageView.setBackgroundResource(drawable_id);
        }

    }
}

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

import com.example.po.stadiummanagement3.Adapter.MomentAdapter;
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

}

package com.example.po.stadiummanagement3.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.po.stadiummanagement3.Activity.ScheduleActivity;
import com.example.po.stadiummanagement3.Adapter.MainAdapter;
import com.example.po.stadiummanagement3.Gson.AreaInfo;
import com.example.po.stadiummanagement3.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 田雍恺 on 2017/11/28.
 */

public class HomeFragment extends Fragment{
    private List<AreaInfo> list;
    private MainAdapter mAdapter;
    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);
        initRecycleView();
        return view;
    }


    private void initRecycleView(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        list = new ArrayList<>();
        list.add(new AreaInfo("aaaaaaaa"));
        list.add(new AreaInfo("bbbbbbb"));
        list.add(new AreaInfo("ccccccc"));
        list.add(new AreaInfo("ccccccc"));
        list.add(new AreaInfo("ccccccc"));
        list.add(new AreaInfo("ccccccc"));
        list.add(new AreaInfo("ccccccc"));
        list.add(new AreaInfo("ccccccc"));
        list.add(new AreaInfo("ccccccc"));
        list.add(new AreaInfo("ccccccc"));
        mAdapter = new MainAdapter(list,getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}

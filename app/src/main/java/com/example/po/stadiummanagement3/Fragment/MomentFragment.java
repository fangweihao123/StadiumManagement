package com.example.po.stadiummanagement3.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.po.stadiummanagement3.Adapter.MomentAdapter;
import com.example.po.stadiummanagement3.Gson.Moment;
import com.example.po.stadiummanagement3.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 13701 on 2017/12/13.
 */

public class MomentFragment extends Fragment {
    @BindView(R.id.moment_recycler)
    RecyclerView mRecyclerView;

    List<Moment> momentList = new ArrayList<Moment>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_moment,container,false);
        ButterKnife.bind(this,v);
        initRecyclerView();
        return v;
    }

    private void initRecyclerView(){
        Moment moment1 = new Moment();
        Moment moment2 = new Moment();
        Moment moment3 = new Moment();
        moment1.setDesc("1111111");
        moment2.setDesc("2222222");
        moment3.setDesc("3333333");
        momentList.add(moment1);
        momentList.add(moment2);
        momentList.add(moment3);
        MomentAdapter momentAdapter = new MomentAdapter(momentList,getActivity());
        mRecyclerView.setAdapter(momentAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        //spancount 是gridview的列数
        mRecyclerView.setLayoutManager(gridLayoutManager);

    }
}

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
import com.example.po.stadiummanagement3.WebService.HttpService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.google.gson.reflect.TypeToken;

/**
 * Created by 田雍恺 on 2017/11/28.
 */

public class HomeFragment extends Fragment{                         //在homefragment发出网络请求要求 ip:8888/stadium/all get请求获得所有信息
    private List<AreaInfo> list;                                    //ip:8888/stadium/pic?name=xxxxx
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
        final Gson gson = new Gson();
        HttpService.sendOkHttpRequest("stadium/all", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                list = gson.fromJson(s,
                        new TypeToken<List<AreaInfo>>(){}.getType());
            }
        });
        while (list.size()==0){}
        /*try {
            Response response = HttpService.sendGetRequest("stadium/all");
            String s = response.body().string();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        mAdapter = new MainAdapter(list,getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }
}

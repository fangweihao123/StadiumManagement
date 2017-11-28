package com.example.po.stadiummanagement3.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.po.stadiummanagement3.Gson.AreaInfo;
import com.example.po.stadiummanagement3.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 13701 on 2017/11/24.
 */

public class MainFragment extends Fragment {
    @BindView(R.id.nav_view) NavigationView navView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.main_recycler_view) RecyclerView recyclerView;
    private List<AreaInfo> list;
    private MainAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container,false);
        ButterKnife.bind(this,v);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.phone) {
                    // Handle the camera action
                } else if (id == R.id.help) {
                    Toast.makeText(getContext(),"22222",Toast.LENGTH_LONG).show();
                } else if (id == R.id.settings) {

                } else if (id == R.id.sign_out) {

                }
                drawer.closeDrawers();
                return true;
            }
        });

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        list = new ArrayList<>();
        list.add(new AreaInfo("aaaaaaaa"));
        list.add(new AreaInfo("bbbbbbb"));
        list.add(new AreaInfo("ccccccc"));
        mAdapter = new MainFragment.MainAdapter(list,getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
        return v;
    }

    public class MainAdapter extends RecyclerView.Adapter<MainFragment.MomentHolder>{
        private List<AreaInfo> infos;
        private Context context;

        MainAdapter(List<AreaInfo> _infos,Context _context){
            infos = _infos;
            context = _context;
        }


        @Override
        public MainFragment.MomentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(context).inflate(R.layout.area_layout,parent,false);
            MainFragment.MomentHolder mHolder = new MainFragment.MomentHolder(v);
            return mHolder;
        }

        @Override
        public void onBindViewHolder(MainFragment.MomentHolder holder, int position) {
            holder.bindInfo(infos.get(position).getName(),R.drawable.test);
        }

        @Override
        public int getItemCount() {
            return infos.size();
        }
    }

    public class MomentHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView1)
        public ImageView imageView1;
        @BindView(R.id.imageView2)
        public ImageView imageView2;
        public MomentHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindInfo(String text,int drawable_id){
            imageView1.setImageResource(drawable_id);
            imageView2.setImageResource(drawable_id);
        }
    }
}

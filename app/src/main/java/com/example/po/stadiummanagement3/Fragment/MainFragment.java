package com.example.po.stadiummanagement3.Fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.po.stadiummanagement3.Adapter.MainAdapter;
import com.example.po.stadiummanagement3.Gson.AreaInfo;
import com.example.po.stadiummanagement3.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 13701 on 2017/11/24.
 */

public class MainFragment extends Fragment
        implements BottomNavigationBar.OnTabSelectedListener {
    @BindView(R.id.nav_view) NavigationView navView;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.navigation_bar) BottomNavigationBar bottomNavigationBar;
    private HomeFragment homeFragment;
    private MomentFragment momentFragment;
    private OrderFragment orderFragment;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main,container,false);
        homeFragment = new HomeFragment();
        momentFragment = new MomentFragment();
        orderFragment = new OrderFragment();
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
        initNavigationBar(v);
        return v;
    }

    private void initNavigationBar(View v) {
        //bottomNavigationBar = (BottomNavigationBar)v.findViewById(R.id.navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_action_home, "主页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_order, "预定"))
                .addItem(new BottomNavigationItem(R.drawable.ic_action_discover, "动态"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(this);
        setDefaultFragment();
    }

    /**
     * 设置默认界面
     */
    private void setDefaultFragment() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_layout, homeFragment);
        transaction.commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onTabSelected(int position) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (position) {
            case 0:
                transaction.replace(R.id.main_layout, homeFragment);
                transaction.commit();
                break;
            case 1:
                transaction.replace(R.id.main_layout, orderFragment);
                transaction.commit();
                break;
            case 2:
                transaction.replace(R.id.main_layout, momentFragment);
                transaction.commit();
                break;
            default:
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}

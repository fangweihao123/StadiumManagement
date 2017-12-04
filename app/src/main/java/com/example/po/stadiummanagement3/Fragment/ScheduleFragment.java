package com.example.po.stadiummanagement3.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.po.stadiummanagement3.Adapter.ScheduleAdapter;
import com.example.po.stadiummanagement3.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 13701 on 2017/11/29.
 */

public class ScheduleFragment extends Fragment {
    //@BindView(R.id.venue_picture)
    private ImageView areaImage;
    private GridView gridView;
    private String [] [] content;
    private ScheduleAdapter scheduleAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_schedule,container,false);
        areaImage = v.findViewById(R.id.venue_picture);
        ButterKnife.bind(this,v);
        areaImage.setBackgroundResource(R.drawable.pool);
        initContent();
        gridView = (GridView) v.findViewById(R.id.information);
        scheduleAdapter = new ScheduleAdapter(getContext(), content, 5 ,7);
        gridView.setAdapter(scheduleAdapter);
        return v;
    }


    private void initContent() {
        content = new String[5][7];
        for(int i=0;i<5;i++){
            for(int j=0; j<7; j++) {
                content[i][j] = "羽毛球";
            }
        }
        content[3][4]="乒乓球";
        content[1][5]="羽毛球";
        content[2][2]="游泳";
    }
}

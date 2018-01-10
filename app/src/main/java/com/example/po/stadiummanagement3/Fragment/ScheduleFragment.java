package com.example.po.stadiummanagement3.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.po.stadiummanagement3.Adapter.ScheduleAdapter;
import com.example.po.stadiummanagement3.Gson.OrderSet;
import com.example.po.stadiummanagement3.R;
import com.example.po.stadiummanagement3.WebService.HttpService;
import com.loonggg.weekcalendar.view.WeekCalendar;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.ButterKnife;
import okhttp3.Response;
/**
 * Created by 13701 on 2017/11/29.
 */

public class ScheduleFragment extends Fragment {
    private ImageView areaImage;
    private ExpandableListView schedule;
    private ScheduleAdapter adapter;
    private List<OrderSet> timeList = new ArrayList<>();
    private WeekCalendar weekCalendar;
    private String areaName;
    private String selectedDate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //获取场馆名称
        Bundle bundle = ScheduleFragment.this.getArguments();
        areaName = bundle.getString("areaName");

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        selectedDate = year+"-"+(month+1)+"-"+day;
        View v = inflater.inflate(R.layout.fragment_schedule,container,false);
        areaImage = v.findViewById(R.id.venue_picture);
        ButterKnife.bind(this,v);
        schedule = (ExpandableListView) v.findViewById(R.id.time_line);
        weekCalendar = (WeekCalendar) v.findViewById(R.id.week);
        initTimeGroup();
        adapter = new ScheduleAdapter(getContext(), timeList);
        schedule.setAdapter(adapter);
        schedule.setGroupIndicator(null);
        for(int i=0; i<5;i++){
            schedule.expandGroup(i);
        }
        schedule.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        schedule.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                if(timeList.get(groupPosition).getCanOrder()){
                    showDialog(groupPosition);
                }
                return false;
            }
        });
        weekCalendar.setOnDateClickListener(new WeekCalendar.OnDateClickListener() {
            @Override
            public void onDateClick(String s) {
                selectedDate = s;
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Calendar now = Calendar.getInstance();
                try {
                    Date date1 = format.parse(s);
                    int year = now.get(Calendar.YEAR);
                    int month = now.get(Calendar.MONTH);
                    int day = now.get(Calendar.DAY_OF_YEAR);
                    Date date2 = new Date(year-1900, month, day);
                    int differ = (int) ((date1.getTime()-date2.getTime())/86400000);
                    if(differ<0||differ>6){
                        timeList.clear();
                        for(int i=0;i<5;i++){
                            timeList.add(new OrderSet(false));
                        }
                    } else {
                        timeList.clear();
                        initTimeGroup();
                    }
                    adapter.notifyDataSetChanged();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        areaImage.setBackgroundResource(R.drawable.pool);
        return v;
    }
    private void initTimeGroup(){

        try {
            Response response = HttpService.sendGetRequest("stadium/all");
            String s = response.body().string();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<5;i++){
            timeList.add(new OrderSet(true));
        }
        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        if(hour>=8){
            timeList.get(0).setCanOrder(false);
        }
        if(hour>=10){
            timeList.get(1).setCanOrder(false);
        }
        if(hour>=12){
            timeList.get(2).setCanOrder(false);
        }
        if(hour>=14){
            timeList.get(3).setCanOrder(false);
        }
        if(hour>=16){
            timeList.get(4).setCanOrder(false);
        }
        if(hour>=18){
            timeList.get(5).setCanOrder(false);
        }
    }

    private void showDialog(final int position){
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final AlertDialog dialog = builder.create();
        View view = View.inflate(getContext(), R.layout.dialog_reserve, null);
        TextView determine = (TextView) view.findViewById(R.id.determin);
        TextView cancle = (TextView) view.findViewById(R.id.cancle);
        determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeList.get(position).setCanOrder(false);
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setView(view);
        dialog.show();
    }
}

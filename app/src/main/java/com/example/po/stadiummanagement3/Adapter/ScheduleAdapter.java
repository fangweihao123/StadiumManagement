package com.example.po.stadiummanagement3.Adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.po.stadiummanagement3.R;
import com.example.po.stadiummanagement3.Gson.OrderSet;

import java.util.List;

/**
 *
 * Created by 田雍恺 on 2018/1/10.
 */

public class ScheduleAdapter extends BaseExpandableListAdapter{
    private Context context;
    private List<OrderSet> list;
    private String [] timeList = {" 8:00","10:00","12:00","14:00","16:00","18:00"};


    public ScheduleAdapter(Context context, List<OrderSet> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getGroupCount() {
        return timeList.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return timeList[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_schedule_group,parent,false);
            viewHolder = new GroupViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(timeList[groupPosition]);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder;
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_schedule_child, parent, false);
            viewHolder = new ChildViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder)convertView.getTag();
        }
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display defaultDisplay = manager.getDefaultDisplay();
        DisplayMetrics dm = new DisplayMetrics();
        defaultDisplay.getMetrics(dm);
        int height = defaultDisplay.getHeight();
        double multiple = dm.densityDpi / 160.0;
        int textViewHeight = (int)((height - 457*multiple)/5);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewHolder.textView.getLayoutParams();
        layoutParams.height = textViewHeight;
        viewHolder.textView.setLayoutParams(layoutParams);
        if(list.get(groupPosition).getCanOrder()){
            viewHolder.textView.setBackgroundResource(R.drawable.canorder);
            viewHolder.textView.setText("可预订");
        } else {
            viewHolder.textView.setBackgroundResource(R.drawable.cannotorder);
            viewHolder.textView.setText("");
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    static class GroupViewHolder{
        TextView textView;
    }

    static class ChildViewHolder{
        TextView textView;
    }
}

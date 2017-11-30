package com.example.po.stadiummanagement3.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.po.stadiummanagement3.R;

/**
 * Created by 13701 on 2017/11/29.
 */

public class ScheduleAdapter extends BaseAdapter {
    private Context context;

    private String [] [] contents;

    private int rowTotlal;

    private int columnTotle;

    private int positionTotle;

    public ScheduleAdapter(Context context, String [] [] contents, int rowTotlal, int columnTotle) {
        this.context = context;
        this.contents = contents;
        this.rowTotlal = rowTotlal;
        this.columnTotle = columnTotle;
        positionTotle = rowTotlal * columnTotle;

    }

    public int getCount() {
        return positionTotle;
    }

    public long getItemId(int position) {
        return position;
    }

    public Object getItem(int position) {
        int column = position % columnTotle;
        int row = position % rowTotlal;
        return contents[row][column];
    }


    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_schedule_item, null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.schedule_item);
        if(!getItem(position).equals("")) {
            textView.setText((String)getItem(position));
            textView.setTextColor(Color.WHITE);
            textView.setBackground(context.getResources().getDrawable(R.drawable.schedule_item_bg3));
        }
        return convertView;
    }
}

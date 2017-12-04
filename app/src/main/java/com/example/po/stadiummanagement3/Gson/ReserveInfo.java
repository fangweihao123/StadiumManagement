package com.example.po.stadiummanagement3.Gson;

/**
 * Created by 13701 on 2017/12/4.
 */

public class ReserveInfo {
    private String name;
    private int timeID;
    private boolean state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimeID() {
        return timeID;
    }

    public void setTimeID(int timeID) {
        this.timeID = timeID;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}

package com.example.po.stadiummanagement3.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 13701 on 2017/11/25.
 */

public class AreaInfo {
    private int id;
    private String name;
    private String pic;
    //private List<Object> orderSet;
    public AreaInfo(String _name){
        name = _name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

}

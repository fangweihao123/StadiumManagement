package com.example.po.stadiummanagement3.Gson;

/**
 * Created by 田雍恺 on 2018/1/10.
 */

public class OrderSet {
    private Boolean canOrder;

    public OrderSet(Boolean canOrder) {
        this.canOrder = canOrder;
    }

    public void setCanOrder(Boolean canOrder){
        this.canOrder = canOrder;
    }
    public Boolean getCanOrder() {
        return canOrder;
    }
}

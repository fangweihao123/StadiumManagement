package com.example.MyBoot.repository.entity;


import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "order_info")
public class Order {


    @Id
    @GeneratedValue
    private int id;

    @ManyToOne( cascade = CascadeType.ALL)
    private UserInfo userInfo;


    @ManyToOne(cascade = CascadeType.ALL)
    private Stadium stadiumInfo;


    private Integer time;

    public Order() {
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Stadium getStadiumInfo() {
        return stadiumInfo;
    }

    public void setStadiumInfo(Stadium stadiumInfo) {
        this.stadiumInfo = stadiumInfo;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}

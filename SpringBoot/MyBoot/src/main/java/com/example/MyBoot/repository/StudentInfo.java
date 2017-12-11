package com.example.MyBoot.repository;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="stud_info")
public class StudentInfo implements Serializable {
    public StudentInfo() {
    }

    @Id
    private String stu_num;


    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "phone_num", unique = true)
    private UserInfo user_info;


    public String getStu_num() {
        return stu_num;
    }

    public void setStu_num(String stu_num) {
        this.stu_num = stu_num;
    }
}

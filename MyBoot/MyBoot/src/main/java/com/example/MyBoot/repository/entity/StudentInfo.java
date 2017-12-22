package com.example.MyBoot.repository.entity;


import javax.persistence.*;


@Entity
@Table(name = "student_info")
public class StudentInfo {

    @Id
    private String studentNum;



    @OneToOne(cascade = CascadeType.ALL)
    private UserInfo userInfo;

    public StudentInfo() {
    }

    public String getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(String studentNum) {
        this.studentNum = studentNum;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}

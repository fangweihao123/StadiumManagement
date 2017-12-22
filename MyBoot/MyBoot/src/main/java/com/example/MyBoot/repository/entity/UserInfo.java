package com.example.MyBoot.repository.entity;




import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="user_info")
public class UserInfo {


    @Id
    private String phoneNumber;

    private String name;




    private String email;
    private String icon;


    @OneToOne(cascade = CascadeType.ALL , mappedBy = "userInfo")
    private StudentInfo studentInfo;



    @OneToMany(mappedBy = "userInfo")
    private Set<Order> orderSet;


    @OneToMany(mappedBy = "userInfo")
    private Set<Action> actionSet;


    @ManyToMany(mappedBy = "praiseSet")
    private Set<Action> praiseSet;

    @OneToMany(mappedBy = "userInfo" , cascade = CascadeType.REMOVE)
    private Set<Comment> commentSet;


    public UserInfo() {
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public Set<Action> getActionSet() {
        return actionSet;
    }

    public void setActionSet(Set<Action> actionSet) {
        this.actionSet = actionSet;
    }

    public Set<Action> getPraiseSet() {
        return praiseSet;
    }

    public void setPraiseSet(Set<Action> praiseSet) {
        this.praiseSet = praiseSet;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }
}

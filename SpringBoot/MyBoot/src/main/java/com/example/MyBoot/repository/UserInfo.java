package com.example.MyBoot.repository;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="user_info")
public class UserInfo implements Serializable {
    public UserInfo() {
    }


    @Id
    private String phone_number;

    private String name;



    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
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

    private String email;
    private String icon;

    @OneToOne(cascade= CascadeType.ALL , mappedBy = "user_info")
    private StudentInfo stu_info;

    public StudentInfo getStu_info() {
        return stu_info;
    }

    public void setStu_info(StudentInfo stu_info) {
        this.stu_info = stu_info;
    }

    public Set<Stadium> getOrder_stadium() {
        return order_stadium;
    }

    public void setOrder_stadium(Set<Stadium> order_stadium) {
        this.order_stadium = order_stadium;
    }

    @ManyToMany(mappedBy = "order_list")
    private Set<Stadium> order_stadium;

    @OneToMany(mappedBy = "user_info")
    private Set<Action> action_list;


    @ManyToMany(mappedBy = "praised_set")
    private Set<Action> praise_set;

    public Set<Action> getAction_list() {
        return action_list;
    }

    public void setAction_list(Set<Action> action_list) {
        this.action_list = action_list;
    }

    public Set<Action> getPraise_set() {
        return praise_set;
    }

    public void setPraise_set(Set<Action> praise_set) {
        this.praise_set = praise_set;
    }


    @OneToMany(mappedBy = "userinfo" , cascade = CascadeType.REMOVE)
    private Set<Comment> comment_set;
}

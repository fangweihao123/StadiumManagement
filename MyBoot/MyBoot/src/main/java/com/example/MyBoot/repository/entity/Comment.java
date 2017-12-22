package com.example.MyBoot.repository.entity;


import javax.persistence.*;

@Entity
@Table(name = "comment_info")
public class Comment {

    @Id
    @GeneratedValue
    private int id;


    @ManyToOne( cascade = CascadeType.ALL)
    private UserInfo userInfo;


    @ManyToOne(cascade = CascadeType.ALL)
    private Action actionInfo;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comment() {
    }
}

package com.example.MyBoot.repository;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="comment_info")
public class Comment implements Serializable {
    public Comment() {
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    @Id
    @ManyToOne
    private Action action;

    @Id
    @ManyToOne
    private UserInfo userinfo;

    private String comment_content;
}

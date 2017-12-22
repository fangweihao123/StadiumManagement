package com.example.MyBoot.repository.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "action_info")
public class Action {



    @Id
    @GeneratedValue
    private int actionId;
    private Date time;

    @Column(nullable = false)
    private String img;
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_phone")
    private UserInfo userInfo;

    @ManyToMany
    @JoinTable(name="praise_info", joinColumns = {@JoinColumn(name="action_id" , referencedColumnName = "actionId")} , inverseJoinColumns = {@JoinColumn(name="user_phone" , referencedColumnName = "phoneNumber")})
    private Set<UserInfo> praiseSet;

    @OneToMany(mappedBy = "actionInfo" , cascade = CascadeType.REMOVE)
    private Set<Comment> commentSet;





    public Action() {
    }


    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public Set<UserInfo> getPraiseSet() {
        return praiseSet;
    }

    public void setPraiseSet(Set<UserInfo> praiseSet) {
        this.praiseSet = praiseSet;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }
}

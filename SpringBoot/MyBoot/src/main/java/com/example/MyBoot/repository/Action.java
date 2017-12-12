package com.example.MyBoot.repository;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "action_info")
public class Action {
    public Action() {
    }

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAction_id() {
        return action_id;
    }

    public void setAction_id(int action_id) {
        this.action_id = action_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    @ManyToOne
    @JoinColumn(name="phone_number")
    private UserInfo user_info;

    private String content;

    @Id
    @GeneratedValue
    private int action_id;


    private Date time;

    @Column(nullable = false)
    private String pic_url;

    public Set<UserInfo> getPraised_set() {
        return praised_set;
    }

    public void setPraised_set(Set<UserInfo> praised_set) {
        this.praised_set = praised_set;
    }



    @ManyToMany
    @JoinTable(name="praise_info", joinColumns = {@JoinColumn(name="action_id" , referencedColumnName = "action_id")} , inverseJoinColumns = {@JoinColumn(name="user_phone" , referencedColumnName = "phone_number")})
    private Set<UserInfo>  praised_set;

    @OneToMany(mappedBy = "action" , cascade = CascadeType.REMOVE)
    private Set<Comment> commented_set;

}

package com.example.MyBoot.repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity
@Table(name="stadium_info")
public class Stadium implements Serializable {


    public Stadium() {
    }

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String pic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Set<UserInfo> getOrder_list() {
        return order_list;
    }

    public void setOrder_list(Set<UserInfo> order_list) {
        this.order_list = order_list;
    }

    @ManyToMany
    @JoinTable(name="order_info",joinColumns ={ @JoinColumn(name="stadium_id", referencedColumnName="id" )},inverseJoinColumns = {@JoinColumn(name = "phone_number" , referencedColumnName ="phone_number")})
    private Set<UserInfo> order_list;
}

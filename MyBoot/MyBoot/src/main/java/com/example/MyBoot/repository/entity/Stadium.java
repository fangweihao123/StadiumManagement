package com.example.MyBoot.repository.entity;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "stadium_info")
public class Stadium {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String pic;



    @OneToMany(mappedBy = "stadiumInfo")
    private Set<Order> orderSet;




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

    public Set<Order> getOrderSet() {
        return orderSet;
    }

    public void setOrderSet(Set<Order> orderSet) {
        this.orderSet = orderSet;
    }

    public Stadium() {
    }
}

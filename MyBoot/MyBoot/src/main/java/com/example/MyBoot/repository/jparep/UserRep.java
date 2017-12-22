package com.example.MyBoot.repository.jparep;

import com.example.MyBoot.repository.entity.UserInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface UserRep extends JpaRepository<UserInfo , String> {


    public List<UserInfo> getByPhoneNumber(@Param(value = "phone_number") String phone_number);
}

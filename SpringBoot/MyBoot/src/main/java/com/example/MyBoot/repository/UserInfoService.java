package com.example.MyBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoService extends JpaRepository<UserInfo,String> {
}

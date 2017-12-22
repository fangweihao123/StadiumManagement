package com.example.MyBoot.repository.jparep;

import com.example.MyBoot.repository.entity.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRep extends JpaRepository<StudentInfo ,String > {

}

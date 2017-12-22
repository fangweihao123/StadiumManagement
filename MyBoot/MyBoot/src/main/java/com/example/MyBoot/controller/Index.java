package com.example.MyBoot.controller;


import com.example.MyBoot.configuration.SessionConfiguration;
import com.example.MyBoot.repository.entity.StudentInfo;
import com.example.MyBoot.repository.entity.UserInfo;
import com.example.MyBoot.repository.jparep.ActionRep;
import com.example.MyBoot.repository.jparep.StudentRep;
import com.example.MyBoot.repository.jparep.UserRep;
import com.example.MyBoot.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;


@Controller
public class Index {


    @Autowired
    UserRep userRep;

    @Autowired
    StudentRep studentRep;

    @Autowired
    UserService userService;



    @RequestMapping(value = "/login" , method=RequestMethod.GET)
    public String login(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse)
    {

        return "login";
    }



    @RequestMapping(value = "/login" , method=RequestMethod.POST)
    public String loginPost(String phone,HttpServletRequest httpRequest, HttpServletResponse httpServletResponse)
    {
        List<UserInfo> list=userRep.getByPhoneNumber(phone);
        if (list.size()!=0)
        {
            httpServletResponse.addCookie(new Cookie("KICookie" , phone+"#"+list.get(0).getName()));
            userService.userLogin(list.get(0), httpRequest.getSession());
        }
        return "login";
    }


    @RequestMapping(value = "/sign" , method=RequestMethod.GET)
    public String index()
    {

        return "index";
    }


    @RequestMapping(value= "/sign" , method = RequestMethod.POST)
    @ResponseBody
    public String sign(String phone , String aname , String id, String email , MultipartFile icon)
    {


        String response="";

        if (id==null)
        {
            response="Id can`t be null!";
            return response;
        }
        if (studentRep.findOne(id)==null)
        {

            if (userRep.getByPhoneNumber(phone).size()==0) {

                if (icon!=null) {
                    try {
                        BufferedOutputStream fo = new BufferedOutputStream(new FileOutputStream(new File(phone+"_icon.png")));
                        fo.write(icon.getBytes());
                        fo.flush();
                        fo.close();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }



                UserInfo userInfo = new UserInfo();
                userInfo.setName(aname);
                userInfo.setEmail(email);
                userInfo.setPhoneNumber(phone);
                userInfo.setIcon(phone+"_icon.png");
                StudentInfo studentInfo = new StudentInfo();
                studentInfo.setStudentNum(id);
                studentInfo.setUserInfo(userInfo);
                userInfo.setStudentInfo(studentInfo);
                userRep.save(userInfo);
                response = "successful!";
            }
            else
            {
                response="The phone has already been signed!";
            }
        }
        else
        {
            response="The student number has already been signed";
        }




        return response;
    }
}

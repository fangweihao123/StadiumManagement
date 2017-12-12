package com.example.MyBoot.controller;


import com.example.MyBoot.repository.UserInfo;
import com.example.MyBoot.repository.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class UserController {


    @Autowired
    UserInfoService userRepository;

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String loginUser(@RequestParam(value="phone" ,required = false ,defaultValue = "11111111111") String phone, @RequestParam(value = "name",required = false, defaultValue = "person") String name)
    {
        UserInfo u=userRepository.findOne(phone);
        if (u!=null) {
            return "wrong!";
        }
        UserInfo new_u=new UserInfo();
        new_u.setPhone_number(phone);
        new_u.setName(name);
        userRepository.save(new_u);
        return "successful!";
    }
}

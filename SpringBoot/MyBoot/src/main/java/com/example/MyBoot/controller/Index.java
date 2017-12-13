package com.example.MyBoot.controller;


import com.example.MyBoot.repository.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@ResponseBody
public class Index {



    @RequestMapping(value="/index", method = RequestMethod.GET)
    public String index(@RequestParam(value="cityname" ,required = false ,defaultValue = "city") String cityname, @RequestParam(value = "teamname",required = false, defaultValue = "team") String teamName)
    {


        return "index";
    }




}

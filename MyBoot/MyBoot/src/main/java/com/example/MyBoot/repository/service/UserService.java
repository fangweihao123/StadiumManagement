package com.example.MyBoot.repository.service;


import com.example.MyBoot.configuration.SessionConfiguration;
import com.example.MyBoot.repository.entity.UserInfo;
import com.example.MyBoot.repository.jparep.ActionRep;
import com.example.MyBoot.repository.jparep.UserRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRep userRep;
    @Autowired
    ActionRep actionRep;


    public UserInfo getUserInSession(HttpSession session)
    {
        String username = (String) session.getAttribute(SessionConfiguration.SESSION_PHONENUMBER);
        UserInfo userInSession = userRep.getByPhoneNumber(username).get(0);
        return userInSession;
    }

    public void userLogin(UserInfo userToLogin,HttpSession session)
    {
        session.setAttribute(SessionConfiguration.SESSION_PHONENUMBER,userToLogin.getPhoneNumber());

        session.setAttribute(SessionConfiguration.SESSION_USERNAME,userToLogin.getName());
    }
    public void userLogout(HttpSession session)
    {
        session.removeAttribute(SessionConfiguration.SESSION_PHONENUMBER);
    }

}

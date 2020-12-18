package com.example.wechating.utils;


import com.example.wechating.domain.User;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {
    public static boolean addUser(User user){//添加用户方法
        List<User> users=new ArrayList();
        users=findAllUser();
        String username=user.getUsername();


        for(User e:users){
            if(e.getUsername().equals(username))
                return false;
        }
        return user.save();
    }



    public static List<User> findAllUser(){//查询所有用户的方法

        List<User> users= DataSupport.findAll(User.class);
        return users;
    }

    public static boolean login(User user){
        List<User> users=new ArrayList();
        users=findAllUser();
        String username=user.getUsername();
        String password=user.getPassword();
        for(User e:users)
            if((e.getUsername().equals(username))&&(e.getPassword().equals(password)))
                return true;//登陆成功


        return false;//登录失败
    }
}

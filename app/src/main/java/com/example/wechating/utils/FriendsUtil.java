package com.example.wechating.utils;

import android.widget.Toast;

import com.example.wechating.domain.Friends;
import com.example.wechating.domain.User;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class FriendsUtil {
    public static boolean addFriends(Friends friends){//添加好友
        List<Friends> lists=new ArrayList();
        lists=findAllFriends();
        String name=friends.getName();
        for(Friends e:lists)
            if(e.getName().equals(name)){
                return false;
            }
        friends.save();
        return true;
    }

    public static List<Friends> findAllFriends(){//查询所有好友
        List<Friends> lists= DataSupport.findAll(Friends.class);
        return lists;

    }

    public static boolean findFriendsByName(String name){//根据名称查找用户
        List<User> users=UserUtil.findAllUser();
        for(User e:users)
            if(e.getUsername().equals(name))
                return true;//存在该名称的用户

            return false;//不存在该名称的用户
    }

}

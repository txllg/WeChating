package com.example.wechating.domain;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by txllg on 2020/11/29.
 */

public class User extends DataSupport {
    @Column(unique = true)//用户名唯一
    private String username;
    @Column(nullable = false)//密码不为空
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

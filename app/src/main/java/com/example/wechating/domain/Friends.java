package com.example.wechating.domain;

import com.example.wechating.component.Cn2Spell;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

public class Friends extends DataSupport implements Comparable<Friends>{
    private String name;
    private String username;

    @Column(ignore = true)
    private String pinyin; // 姓名对应的拼音
    @Column(ignore = true)
    private String firstLetter; // 拼音的首字母

    public Friends() {
    }

    public Friends(String name) {
        this.name = name;
        pinyin = Cn2Spell.getPinYin(name); // 根据姓名获取拼音
        firstLetter = pinyin.substring(0, 1).toUpperCase(); // 获取拼音首字母并转成大写
        if (!firstLetter.matches("[A-Z]")) { // 如果不在A-Z中则默认为“#”
            firstLetter = "#";
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    public String getUsername() {
        return username;
    }



    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {
        return "Friends{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public int compareTo(Friends another) {
        if (firstLetter.equals("#") && !another.getFirstLetter().equals("#")) {
            return 1;
        } else if (!firstLetter.equals("#") && another.getFirstLetter().equals("#")){
            return -1;
        } else {
            return pinyin.compareToIgnoreCase(another.getPinyin());
        }

    }
}

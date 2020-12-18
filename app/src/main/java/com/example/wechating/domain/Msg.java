package com.example.wechating.domain;
 
public class Msg {
    public static final int type_received = 0;
    public static final int type_sent = 1;
    private String content;
    private int type;
    private int headerid;
 
    public Msg(String content, int type) {
        this.content = content;
        this.type = type;
    }
 
    public static int getType_sent() {
        return type_sent;
    }
 
    public String getContent() {
        return content;
    }
 
    public void setContent(String content) {
        this.content = content;
    }
 
    public int getType() {
        return type;
    }
 
    public void setType(int type) {
        this.type = type;
    }
 
    public int getHeaderid() {
        return headerid;
    }
 
    public void setHeaderid(int headerid) {
        this.headerid = headerid;
    }
 
    public static int getType_received() {
        return type_received;
    }
}
package com.example.wechating.domain;

import org.litepal.crud.DataSupport;

public class Friends extends DataSupport {
    private String name;
    private String imageId;

    public Friends() {
    }

    public Friends(String name, String imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "Friends{" +
                "name='" + name + '\'' +
                ", imageId='" + imageId + '\'' +
                '}';
    }
}

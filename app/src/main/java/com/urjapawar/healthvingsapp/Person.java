package com.urjapawar.healthvingsapp;

/**
 * Created by DELL on 22-04-2016.
 */
public class Person {
    String name;
    String desc;
    int photoId;

    Person(String name, String desc, int photoId) {
        this.name = name;
        this.desc = desc;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public int getPhotoId() {
        return photoId;
    }

    public String getDesc() {
        return desc;
    }
}

package com.example.ericbell.hackeruproject.firebase;

/**
 * Created by eric.bell on 6/27/2017.
 */

public class User {

    String uid;
    String getDisplayName;
    String image;


    //constractor for firebase
    public User() {
    }

    public User(String uid, String getDisplayName, String image) {
        this.uid = uid;
        this.getDisplayName = getDisplayName;
        this.image = image;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGetDisplayName() {
        return getDisplayName;
    }

    public void setGetDisplayName(String getDisplayName) {
        this.getDisplayName = getDisplayName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", getDisplayName='" + getDisplayName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}

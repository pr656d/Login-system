package com.premp.model;

public class User {
    private String mUser;
    private String mPassword;

    public User(String user, String password) {
        mUser = user;
        mPassword = password;
    }

    public User() {

    }

    public String getUser() {
        return mUser;
    }

    public void setUser(String user) {
        mUser = user;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}

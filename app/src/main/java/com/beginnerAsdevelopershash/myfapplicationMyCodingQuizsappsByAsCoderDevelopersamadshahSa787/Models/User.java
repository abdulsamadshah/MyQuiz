package com.beginnerAsdevelopershash.myfapplicationMyCodingQuizsappsByAsCoderDevelopersamadshahSa787.Models;

public class User {
    private String name, email, pass,userimg;
    private long coins = 25;

    public User() {
    }

    public User(String name, String email, String pass, String userimg) {
        this.name = name;
        this.email = email;
        this.pass = pass;
        this.userimg = userimg;
    }

    public String getName() {
        return name;
    }

    public long getCoins() {
        return coins;
    }

    public void setCoins(long coins) {
        this.coins = coins;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUserimg() {
        return userimg;
    }

    public void setUserimg(String userimg) {
        this.userimg = userimg;
    }
}

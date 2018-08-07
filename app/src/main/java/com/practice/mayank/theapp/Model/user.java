package com.practice.mayank.theapp.Model;

public class user {
    private String username;
    private String password;
    private String email;
    private int coins;

    public user(){

    }
    public user(String user, int coins){
        this.username=user;
        this.coins=coins;
    }

    public user(String username, String password, String email,int coins) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.coins=coins;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

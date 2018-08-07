package com.practice.mayank.theapp.Model;

public class INformation {
    private String Country,City,Name,Location,CategoryId;
    private int no_users;
    private float rating;

    public INformation() {
    }

    public INformation(String country, String city, String name, String location, String categoryId, float rating, int no_users) {
        Country = country;
        City = city;
        Name = name;
        Location = location;
        CategoryId = categoryId;
        this.rating = rating;
        this.no_users = no_users;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNo_users() {
        return no_users;
    }

    public void setNo_users(int no_users) {
        this.no_users = no_users;
    }
}

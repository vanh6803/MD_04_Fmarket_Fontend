package com.example.hn_2025_online_shop.model;

public class Order1 {
    String image;
    String name;
    String oder;
    String time;
    double count;

    public Order1(String image, String name, String oder, String time, double count) {
        this.image = image;
        this.name = name;
        this.oder = oder;
        this.time = time;
        this.count = count;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOder() {
        return oder;
    }

    public void setOder(String oder) {
        this.oder = oder;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }
}

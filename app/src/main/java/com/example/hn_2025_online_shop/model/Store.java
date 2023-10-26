package com.example.hn_2025_online_shop.model;

public class Store {
    String textSale;
    String price;
    String date;

    public Store(String textSale, String price, String date) {
        this.textSale = textSale;
        this.price = price;
        this.date = date;
    }

    public String getTextSale() {
        return textSale;
    }

    public void setTextSale(String textSale) {
        this.textSale = textSale;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

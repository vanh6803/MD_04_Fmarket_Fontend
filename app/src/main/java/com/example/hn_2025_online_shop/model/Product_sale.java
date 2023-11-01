package com.example.hn_2025_online_shop.model;

public class Product_sale{
    String name;
    Number price;
    Number price_sale;
    String img;

    public Product_sale(String name, Number price, Number price_sale, String img) {
        this.name = name;
        this.price = price;
        this.price_sale = price_sale;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Number getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(Number price_sale) {
        this.price_sale = price_sale;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

package com.example.hn_2025_online_shop.model;

public class ProductStore {
    String name;
    Number price;
    Number price_sale;
    String img;
    int quanity_sold;

    public ProductStore() {
    }

    public ProductStore(String name, Number price, Number price_sale, String img, int quanity_sold) {
        this.name = name;
        this.price = price;
        this.price_sale = price_sale;
        this.img = img;
        this.quanity_sold = quanity_sold;
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

    public int getQuanity_sold() {
        return quanity_sold;
    }

    public void setQuanity_sold(int quanity_sold) {
        this.quanity_sold = quanity_sold;
    }
}

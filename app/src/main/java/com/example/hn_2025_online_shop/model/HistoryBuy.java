package com.example.hn_2025_online_shop.model;

public class HistoryBuy {
    String name;
    String price;
    String priceSale;
    String typeColor;
    int size;
    String img;


    public HistoryBuy(String name, String price, String priceSale, String typeColor, int size, String img) {
        this.name = name;
        this.price = price;
        this.priceSale = priceSale;
        this.typeColor = typeColor;
        this.size = size;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPriceSale() {
        return priceSale;
    }

    public void setPriceSale(String priceSale) {
        this.priceSale = priceSale;
    }

    public String getTypeColor() {
        return typeColor;
    }

    public void setTypeColor(String typeColor) {
        this.typeColor = typeColor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}

package com.example.hn_2025_online_shop.model;

public class Order {
    private String image;
    private String nameProduct;
    private String nameUser;
    private String date;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Order() {
    }

    public Order(String image, String nameProduct, String nameUser, String date, int quantity) {
        this.image = image;
        this.nameProduct = nameProduct;
        this.nameUser = nameUser;
        this.date = date;
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

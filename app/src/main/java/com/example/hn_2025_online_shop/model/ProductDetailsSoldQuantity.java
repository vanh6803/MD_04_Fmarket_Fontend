package com.example.hn_2025_online_shop.model;

public class ProductDetailsSoldQuantity {
    private String name;
    private String image;

    public ProductDetailsSoldQuantity() {
    }

    public ProductDetailsSoldQuantity(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

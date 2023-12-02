package com.example.hn_2025_online_shop.model;

public class ProductRevenue {
    private String image;
    private String nameProduct;
    private int revenue;

    public ProductRevenue() {
    }

    public ProductRevenue(String image, String nameProduct, int revenue) {
        this.image = image;
        this.nameProduct = nameProduct;
        this.revenue = revenue;
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

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}

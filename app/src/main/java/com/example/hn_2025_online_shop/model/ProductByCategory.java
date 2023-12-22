package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductByCategory implements Serializable {
    @SerializedName("_id")
    private String id;
    private String nameCategory;
    private List<Product> product;

    public ProductByCategory() {

    }

    public ProductByCategory(String id, String nameCategory, List<Product> product) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductByCategory{" +
                "id='" + id + '\'' +
                ", nameCategory='" + nameCategory + '\'' +
                ", product=" + product +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }
}

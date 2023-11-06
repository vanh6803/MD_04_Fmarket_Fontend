package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class ProductType {
    @SerializedName("_id")
    private String id;
    private String name;
    private String image;

    public ProductType(String id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public ProductType() {
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}

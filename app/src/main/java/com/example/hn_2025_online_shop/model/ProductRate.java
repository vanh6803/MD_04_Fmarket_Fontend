package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductRate {
    @SerializedName("_id")
    private String id;
    private String product_id;
    private String user_id;
    private String content;
    private String image;
    private int rate;

    public ProductRate() {
    }

    public ProductRate(String id, String product_id, String user_id, String content, String image, int rate) {
        this.id = id;
        this.product_id = product_id;
        this.user_id = user_id;
        this.content = content;
        this.image = image;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ProductRate{" +
                "id='" + id + '\'' +
                ", product_id='" + product_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                ", rate=" + rate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}

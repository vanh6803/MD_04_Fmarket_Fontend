package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment {
    @SerializedName("_id")
    private String id;
    @SerializedName("product_id")
    private Product product;
    @SerializedName("user_id")
    private User user;
    private String content;
    private List<String> image;
    private int rate;
    private String createdAt;
    private String updatedAt;

    public Comment() {
    }

    public Comment(User user, String content, int rate, String updatedAt) {
        this.user = user;
        this.content = content;
        this.rate = rate;
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Comment(Product product) {
        this.product = product;
    }

    public Comment(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

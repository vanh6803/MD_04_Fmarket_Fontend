package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Comment {
    @SerializedName("_id")
    private String id;
    @SerializedName("product_id")
    private String productId;
    @SerializedName("user_id")
    private String userId;
    private String content;
    private List<String> image;
    private int rate;
    private String createdAt;
    private String updatedAt;

    public Comment() {
    }

    public Comment(String userId, String content, int rate, String updatedAt) {
        this.userId = userId;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}

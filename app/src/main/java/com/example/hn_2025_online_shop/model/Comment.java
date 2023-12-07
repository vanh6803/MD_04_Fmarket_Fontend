package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class Comment {
    @SerializedName("_id")
    private String id;
    @SerializedName("account_id")
    private String accountId;
    @SerializedName("product_id")
    private String productId;
    private String content;
    private String image;
    private String createdAt;
    private String updatedAt;

    public Comment(String content, String updatedAt) {
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public Comment() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

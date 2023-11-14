package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class Cart {
    @SerializedName("_id")
    private String id;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("option_id")
    private String optionId;
    private int quantity;

    public Cart() {

    }

    public Cart(String id, String userId, String optionId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.optionId = optionId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

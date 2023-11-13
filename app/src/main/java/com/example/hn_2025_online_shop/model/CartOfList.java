package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class CartOfList {
    @SerializedName("_id")
    private String id;
    @SerializedName("option_id")
    private OptionProduct optionProduct;
    private int quantity;

    public CartOfList() {
    }

    public CartOfList(String id, OptionProduct optionProduct, int quantity) {
        this.id = id;
        this.optionProduct = optionProduct;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "CartOfList{" +
                "id='" + id + '\'' +
                ", optionProduct=" + optionProduct +
                ", quantity=" + quantity +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OptionProduct getOptionProduct() {
        return optionProduct;
    }

    public void setOptionProduct(OptionProduct optionProduct) {
        this.optionProduct = optionProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

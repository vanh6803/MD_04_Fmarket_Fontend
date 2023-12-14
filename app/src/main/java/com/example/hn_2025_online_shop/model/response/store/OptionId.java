package com.example.hn_2025_online_shop.model.response.store;

import com.google.gson.annotations.SerializedName;

public class OptionId {
    @SerializedName("_id")
    private String idOptions;
    private String product_id;
    private String name_color;
    private String color_code;
    private String image;
    private double price;
    private int discount_value;
    private int quantity;
    private boolean hot_option;
    private String createdAt;
    private String updatedAt;
    private int __v;

    public OptionId(String idOptions, String product_id, String name_color, String color_code, String image, double price, int discount_value, int quantity, boolean hot_option, String createdAt, String updatedAt, int __v) {
        this.idOptions = idOptions;
        this.product_id = product_id;
        this.name_color = name_color;
        this.color_code = color_code;
        this.image = image;
        this.price = price;
        this.discount_value = discount_value;
        this.quantity = quantity;
        this.hot_option = hot_option;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public String getIdOptions() {
        return idOptions;
    }

    public void setIdOptions(String idOptions) {
        this.idOptions = idOptions;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getName_color() {
        return name_color;
    }

    public void setName_color(String name_color) {
        this.name_color = name_color;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(int discount_value) {
        this.discount_value = discount_value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isHot_option() {
        return hot_option;
    }

    public void setHot_option(boolean hot_option) {
        this.hot_option = hot_option;
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}

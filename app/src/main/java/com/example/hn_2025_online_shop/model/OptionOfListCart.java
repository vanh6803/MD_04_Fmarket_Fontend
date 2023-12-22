package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class OptionOfListCart {
    @SerializedName("_id")
    private String id;
    @SerializedName("product_id")
    private Product product;
    @SerializedName("name_color")
    private String nameColor;
    @SerializedName("color_code")
    private String colorCode;
    private String image;
    private int price;
    @SerializedName("discount_value")
    private int discountValue;
    private int quantity;
    private int soldQuantity;
    private boolean hot_option;


    private String createdAt;

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

    private String updatedAt;

    public OptionOfListCart() {
    }

    public OptionOfListCart(String id, Product product, String nameColor, String colorCode, String image, int price, int discount_value, int quantity, int soldQuantity,
                            boolean hot_option, String createdAt, String updatedAt) {
        this.id = id;
        this.product = product;
        this.nameColor = nameColor;
        this.colorCode = colorCode;
        this.image = image;
        this.price = price;
        this.discountValue = discount_value;
        this.quantity = quantity;
        this.soldQuantity = soldQuantity;
        this.hot_option = hot_option;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id='" + id + '\'' +
                ", product='" + product + '\'' +
                ", nameColor='" + nameColor + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", discount_value=" + discountValue +
                ", quantity=" + quantity +
                ", soldQuantity=" + soldQuantity +
                ", hot_option=" + hot_option +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getNameColor() {
        return nameColor;
    }

    public void setNameColor(String nameColor) {
        this.nameColor = nameColor;
    }

    public String getColorCode() {
        return colorCode;
    }

    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(int discount_value) {
        this.discountValue = discount_value;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(int soldQuantity) {
        this.soldQuantity = soldQuantity;
    }

    public boolean isHot_option() {
        return hot_option;
    }

    public void setHot_option(boolean hot_option) {
        this.hot_option = hot_option;
    }
}

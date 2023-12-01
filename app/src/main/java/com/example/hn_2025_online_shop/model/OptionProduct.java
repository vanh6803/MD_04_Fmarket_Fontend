package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class OptionProduct {
    @SerializedName("_id")
    private String id;
    @SerializedName("product_id")
    private String productId;
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

    public OptionProduct() {
    }

    public OptionProduct(String id, String productId, String nameColor, String colorCode, String image, int price, int discount_value, int quantity, int soldQuantity, boolean hot_option) {
        this.id = id;
        this.productId = productId;
        this.nameColor = nameColor;
        this.colorCode = colorCode;
        this.image = image;
        this.price = price;
        this.discountValue = discount_value;
        this.quantity = quantity;
        this.soldQuantity = soldQuantity;
        this.hot_option = hot_option;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

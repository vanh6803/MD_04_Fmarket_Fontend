package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class OptionProductBestSeller {
    @SerializedName("_id")
    private String id;
    @SerializedName("product_id")
    private Product productId;
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

    public OptionProductBestSeller() {
    }

    public OptionProductBestSeller(String id, Product productId, String nameColor, String colorCode, String image, int price, int discountValue, int quantity, int soldQuantity, boolean hot_option) {
        this.id = id;
        this.productId = productId;
        this.nameColor = nameColor;
        this.colorCode = colorCode;
        this.image = image;
        this.price = price;
        this.discountValue = discountValue;
        this.quantity = quantity;
        this.soldQuantity = soldQuantity;
        this.hot_option = hot_option;
    }

    @Override
    public String toString() {
        return "OptionProductBestSeller{" +
                "id='" + id + '\'' +
                ", productId=" + productId +
                ", nameColor='" + nameColor + '\'' +
                ", colorCode='" + colorCode + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", discountValue=" + discountValue +
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

    public Product getProductId() {
        return productId;
    }

    public void setProductId(Product productId) {
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

    public void setDiscountValue(int discountValue) {
        this.discountValue = discountValue;
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

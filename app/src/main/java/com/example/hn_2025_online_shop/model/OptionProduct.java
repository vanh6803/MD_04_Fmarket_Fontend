package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class OptionProduct {
    @SerializedName("_id")
    private String id;
    String product_id;
    String name_color;
    String image;
    String color_code;
    Number ram;
    Number rom;
    Number price;
    Number discount_value;
    Number quantity;
    Number soldQuantity;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public OptionProduct() {
    }

    public OptionProduct(String id, String product_id, String name_color, String image, String color_code, Number ram, Number rom, Number price, Number discount_value, Number quantity, Number soldQuantity) {
        this.id = id;
        this.product_id = product_id;
        this.name_color = name_color;
        this.image = image;
        this.color_code = color_code;
        this.ram = ram;
        this.rom = rom;
        this.price = price;
        this.discount_value = discount_value;
        this.quantity = quantity;
        this.soldQuantity = soldQuantity;
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

    public Number getRam() {
        return ram;
    }

    public void setRam(Number ram) {
        this.ram = ram;
    }

    public Number getRom() {
        return rom;
    }

    public void setRom(Number rom) {
        this.rom = rom;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Number getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(Number discount_value) {
        this.discount_value = discount_value;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }

    public Number getSoldQuantity() {
        return soldQuantity;
    }

    public void setSoldQuantity(Number soldQuantity) {
        this.soldQuantity = soldQuantity;
    }
}

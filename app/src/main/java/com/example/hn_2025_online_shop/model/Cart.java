package com.example.hn_2025_online_shop.model;

public class Cart {
    private int idProduct;
    private String name;
    private String image;
    private int price;
    private int quantity;

    public Cart() {
    }

    public Cart(int idProduct, String name, String image, int price, int quantity) {
        this.idProduct = idProduct;
        this.name = name;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idProduct=" + idProduct +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

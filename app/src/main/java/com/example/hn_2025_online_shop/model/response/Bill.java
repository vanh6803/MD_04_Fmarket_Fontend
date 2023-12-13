package com.example.hn_2025_online_shop.model.response;

public class Bill {
    String code;
    String nameBill;
    double price;
    String dateTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameBill() {
        return nameBill;
    }

    public void setNameBill(String nameBill) {
        this.nameBill = nameBill;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Bill(String code, String nameBill, double price, String dateTime) {
        this.code = code;
        this.nameBill = nameBill;
        this.price = price;
        this.dateTime = dateTime;
    }
}

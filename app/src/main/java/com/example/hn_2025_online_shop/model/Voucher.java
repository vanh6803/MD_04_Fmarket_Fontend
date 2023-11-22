package com.example.hn_2025_online_shop.model;

public class Voucher {
    String nameVoucher;
    String price;
    String date;
    String sale;

    public Voucher(String nameVoucher, String price, String date, String sale) {
        this.nameVoucher = nameVoucher;
        this.price = price;
        this.date = date;
        this.sale = sale;
    }

    public String getNameVoucher() {
        return nameVoucher;
    }

    public void setNameVoucher(String nameVoucher) {
        this.nameVoucher = nameVoucher;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }
}

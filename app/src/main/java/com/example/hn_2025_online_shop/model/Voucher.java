package com.example.hn_2025_online_shop.model;

public class Voucher {
    String nameVoucher;
    String price;
    String date;

    public Voucher(String nameVoucher, String price, String date) {
        this.nameVoucher = nameVoucher;
        this.price = price;
        this.date = date;
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
}

package com.example.hn_2025_online_shop.model;

import java.util.List;

public class Producct_type {
    String title;
    List<Product_main> list;

    public Producct_type(String title, List<Product_main> list) {
        this.title = title;
        this.list = list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product_main> getList() {
        return list;
    }

    public void setList(List<Product_main> list) {
        this.list = list;
    }
}

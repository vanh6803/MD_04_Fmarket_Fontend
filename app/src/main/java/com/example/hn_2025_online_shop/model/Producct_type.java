package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Producct_type {
    @SerializedName("_id")
    private int id;
    String name;
    List<Product_main> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producct_type(int id, String name, List<Product_main> list) {
        this.id = id;
        this.name = name;
        this.list = list;
    }

    public Producct_type() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product_main> getList() {
        return list;
    }

    public void setList(List<Product_main> list) {
        this.list = list;
    }
}

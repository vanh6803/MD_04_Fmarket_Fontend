package com.example.hn_2025_online_shop.model.response.store;

import com.example.hn_2025_online_shop.model.Product;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductId implements Serializable {
    @SerializedName("_id")
    private  String id;
    StoreId store_id;
    private String name;

    public ProductId(String id, StoreId store_id, String name) {
        this.id = id;
        this.store_id = store_id;
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StoreId getStore_id() {
        return store_id;
    }

    public void setStore_id(StoreId store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

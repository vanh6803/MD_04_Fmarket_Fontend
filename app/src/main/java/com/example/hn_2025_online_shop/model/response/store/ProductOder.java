package com.example.hn_2025_online_shop.model.response.store;

import com.example.hn_2025_online_shop.model.OptionOfListCart;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductOder implements Serializable {
    OptionBill option_id;
    private  int quantity;
    @SerializedName("_id")
    private String id_option;

    public ProductOder(OptionBill option_id, int quantity, String id_option) {
        this.option_id = option_id;
        this.quantity = quantity;
        this.id_option = id_option;
    }

    public OptionBill getOption_id() {
        return option_id;
    }

    public void setOption_id(OptionBill option_id) {
        this.option_id = option_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId_option() {
        return id_option;
    }

    public void setId_option(String id_option) {
        this.id_option = id_option;
    }
}

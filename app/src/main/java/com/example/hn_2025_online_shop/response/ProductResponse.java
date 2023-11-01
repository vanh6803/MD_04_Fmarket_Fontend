package com.example.hn_2025_online_shop.response;

import com.example.hn_2025_online_shop.model.Product_main;

import java.util.List;

public class ProductResponse {
    String message;
    int code;
    List<Product_main> data;

    public ProductResponse() {
    }

    public ProductResponse(String message, int code, List<Product_main> data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Product_main> getData() {
        return data;
    }

    public void setData(List<Product_main> data) {
        this.data = data;
    }
}

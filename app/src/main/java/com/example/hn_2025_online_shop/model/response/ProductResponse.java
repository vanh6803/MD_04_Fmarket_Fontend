package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.Product;

import java.util.List;

public class ProductResponse {
    String message;
    int code;
    List<Product> result;

    public ProductResponse() {
    }

    public ProductResponse(String message, int code, List<Product> result) {
        this.message = message;
        this.code = code;
        this.result = result;
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

    public List<Product> getResult() {
        return result;
    }

    public void setResult(List<Product> result) {
        this.result = result;
    }
}
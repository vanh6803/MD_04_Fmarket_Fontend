package com.example.hn_2025_online_shop.model.response.statistical;

import com.example.hn_2025_online_shop.model.ProductWithSoldQuantity;

import java.util.List;

public class SoldQuantityProductResponse {
    private int code;
    private String message;
    private List<ProductWithSoldQuantity> data;

    public SoldQuantityProductResponse() {
    }

    public SoldQuantityProductResponse(int code, String message, List<ProductWithSoldQuantity> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ProductWithSoldQuantity> getData() {
        return data;
    }

    public void setData(List<ProductWithSoldQuantity> data) {
        this.data = data;
    }
}

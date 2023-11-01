package com.example.hn_2025_online_shop.response;

import com.example.hn_2025_online_shop.model.Producct_type;

import java.util.List;

public class ProductTypeResponse {
    String message;
    int code;
    List<Producct_type> data;

    public ProductTypeResponse() {
    }

    public ProductTypeResponse(String message, int code, List<Producct_type> data) {
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

    public List<Producct_type> getData() {
        return data;
    }

    public void setData(List<Producct_type> data) {
        this.data = data;
    }
}

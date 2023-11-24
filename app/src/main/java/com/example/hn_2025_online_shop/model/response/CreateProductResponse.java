package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.Product;

import java.util.List;

public class CreateProductResponse {
    String message;
    int code;
    Product result;

    public CreateProductResponse() {
    }

    public CreateProductResponse(String message, int code, Product result) {
        this.message = message;
        this.code = code;
        this.result = result;
    }

    @Override
    public String toString() {
        return "CreateProductResponse{" +
                "message='" + message + '\'' +
                ", code=" + code +
                ", result=" + result +
                '}';
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

    public Product getResult() {
        return result;
    }

    public void setResult(Product result) {
        this.result = result;
    }
}

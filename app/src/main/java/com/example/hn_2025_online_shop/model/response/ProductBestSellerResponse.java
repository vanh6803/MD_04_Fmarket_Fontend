package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.OptionProductBestSeller;

import java.util.List;

public class ProductBestSellerResponse {
    private int code;
    private String message;
    private List<OptionProductBestSeller> result;

    public ProductBestSellerResponse() {
    }

    public ProductBestSellerResponse(int code, String message, List<OptionProductBestSeller> result) {
        this.code = code;
        this.message = message;
        this.result = result;
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

    public List<OptionProductBestSeller> getResult() {
        return result;
    }

    public void setResult(List<OptionProductBestSeller> result) {
        this.result = result;
    }
}

package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.CartOfList;

import java.util.List;

public class CartReponse {
    private int code;
    private List<CartOfList> data;

    public CartReponse() {
    }

    public CartReponse(int code, List<CartOfList> data) {
        this.code = code;
        this.data = data;
    }

    @Override
    public String toString() {
        return "CartReponse{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<CartOfList> getData() {
        return data;
    }

    public void setData(List<CartOfList> data) {
        this.data = data;
    }
}

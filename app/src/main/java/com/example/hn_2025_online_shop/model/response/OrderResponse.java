package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.adapter.OrderAdapter;
import com.example.hn_2025_online_shop.model.Order;

import java.util.List;

public class OrderResponse {
    private int code;
    private String message;
    private List<Order> result;
    private OrderAdapter  orderAdapter;

    public OrderResponse(int code, String message, List<Order> result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    @Override
    public String toString() {
        return "OrderDetailResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
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

    public List<Order> getResult() {
        return result;
    }

    public void setResult(List<Order> result) {
        this.result = result;
    }
}

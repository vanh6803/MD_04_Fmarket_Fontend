package com.example.hn_2025_online_shop.model.response.statistical;

public class RevenueByMonthResponse {
    private int code;
    String message;
    private int data;

    public RevenueByMonthResponse() {
    }

    public RevenueByMonthResponse(int code, String message, int data) {
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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}

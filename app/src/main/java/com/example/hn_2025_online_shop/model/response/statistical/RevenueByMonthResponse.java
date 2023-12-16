package com.example.hn_2025_online_shop.model.response.statistical;

import java.math.BigInteger;

public class RevenueByMonthResponse {
    private int code;
    private String message;
    private BigInteger data;

    public RevenueByMonthResponse() {
    }

    public RevenueByMonthResponse(int code, String message, BigInteger data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BigInteger getData() {
        return data;
    }

    public void setData(BigInteger data) {
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

}

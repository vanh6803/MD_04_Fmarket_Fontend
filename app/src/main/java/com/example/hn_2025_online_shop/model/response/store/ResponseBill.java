package com.example.hn_2025_online_shop.model.response.store;

import java.util.List;

public class ResponseBill {
    private  int code;
    List<Result> result;
    private String message;

    public ResponseBill(int code, List<Result> result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.example.hn_2025_online_shop.model.response.store;

import java.io.Serializable;

public class DetailBills implements Serializable {
    private  int code;
    ResultBuil result;
    private String message;

    public DetailBills(int code, ResultBuil result, String message) {
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

    public ResultBuil getResult() {
        return result;
    }

    public void setResult(ResultBuil result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

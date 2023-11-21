package com.example.hn_2025_online_shop.model.response.store;

public class InfoStore {
    private int code;
    private DataStore data;
    private  String message;

    public InfoStore(int code, DataStore data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataStore getData() {
        return data;
    }

    public void setData(DataStore data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.Notifi;

import java.util.List;

public class ListNotifiReponse {
    private int code;
    private String message;
    private List<Notifi> result;

    public ListNotifiReponse() {
    }

    public ListNotifiReponse(int code, String message, List<Notifi> result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    @Override
    public String toString() {
        return "ListNotifiReponse{" +
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

    public List<Notifi> getResult() {
        return result;
    }

    public void setResult(List<Notifi> result) {
        this.result = result;
    }
}

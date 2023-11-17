package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.Info;

import java.util.List;

public class InfoResponse {
    private int code;
    private String message;
    private List<Info> result;

    public InfoResponse() {
    }

    public InfoResponse(int code, String message, List<Info> result) {
        this.code = code;
        this.message = message;
        this.result = result;
    }

    @Override
    public String toString() {
        return "InfoResponse{" +
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

    public List<Info> getResult() {
        return result;
    }

    public void setResult(List<Info> result) {
        this.result = result;
    }
}

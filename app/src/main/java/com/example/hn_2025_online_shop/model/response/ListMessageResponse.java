package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.Message;
import com.example.hn_2025_online_shop.model.User;

import java.util.List;

public class ListMessageResponse {
    private int code;
    private List<Message> result;
    private String message;

    public ListMessageResponse() {
    }

    public ListMessageResponse(int code, List<Message> result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ListMessageResponse{" +
                "code=" + code +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<Message> getResult() {
        return result;
    }

    public void setResult(List<Message> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

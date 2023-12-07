package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.PeopleMsg;
import com.example.hn_2025_online_shop.model.User;

import java.util.List;

public class ListChatResponse {
    private int code;
    private List<PeopleMsg> result;
    private String message;

    public ListChatResponse() {
    }

    public ListChatResponse(int code, List<PeopleMsg> result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ListChatResponse{" +
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

    public List<PeopleMsg> getResult() {
        return result;
    }

    public void setResult(List<PeopleMsg> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

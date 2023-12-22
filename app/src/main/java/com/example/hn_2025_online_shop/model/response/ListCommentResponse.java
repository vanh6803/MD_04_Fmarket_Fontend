package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.Comment;

import java.util.List;

public class ListCommentResponse {
    private int code;
    private List<Comment> data;
    private String message;

    public ListCommentResponse() {
    }

    public ListCommentResponse(int code, List<Comment> data, String message) {
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


    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

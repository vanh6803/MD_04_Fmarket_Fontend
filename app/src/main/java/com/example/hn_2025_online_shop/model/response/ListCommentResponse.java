package com.example.hn_2025_online_shop.model.response;

import com.example.hn_2025_online_shop.model.CommentAccount;

import java.util.List;

public class ListCommentResponse {
    private int code;
    private List<CommentAccount> result;
    private String message;

    public ListCommentResponse() {
    }

    public ListCommentResponse(int code, List<CommentAccount> result, String message) {
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

    public List<CommentAccount> getResult() {
        return result;
    }

    public void setResult(List<CommentAccount> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

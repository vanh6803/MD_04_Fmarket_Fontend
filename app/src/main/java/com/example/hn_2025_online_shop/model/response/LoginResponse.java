package com.example.hn_2025_online_shop.model.response;

public class LoginResponse {
    private int code;
    private String token, message;

    public LoginResponse(int code, String token, String message) {
        this.code = code;
        this.token = token;
        this.message = message;
    }

    public LoginResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

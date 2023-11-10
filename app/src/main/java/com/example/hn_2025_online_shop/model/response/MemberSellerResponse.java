package com.example.hn_2025_online_shop.model.response;

public class MemberSellerResponse {
    private int code;
    private String  message;

    public MemberSellerResponse() {
    }

    public MemberSellerResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "MemberSellerResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
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
}

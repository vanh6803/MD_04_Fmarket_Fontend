package com.example.hn_2025_online_shop.model.response;

public class CheckStoreResponse {
    private int code;
    private String message;
    private boolean isExiting;

    public CheckStoreResponse() {

    }

    @Override
    public String toString() {
        return "CheckStoreResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", isExiting=" + isExiting +
                '}';
    }

    public CheckStoreResponse(int code, String message, boolean isExiting) {
        this.code = code;
        this.message = message;
        this.isExiting = isExiting;
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

    public boolean isExiting() {
        return isExiting;
    }

    public void setExiting(boolean exiting) {
        isExiting = exiting;
    }
}

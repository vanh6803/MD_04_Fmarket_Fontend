package com.example.hn_2025_online_shop.model;

public class DataToken {
    private String userId;
    private String iat;

    public DataToken() {
    }

    public DataToken(String userId, String iat) {
        this.userId = userId;
        this.iat = iat;
    }

    @Override
    public String toString() {
        return "DataToken{" +
                "userId='" + userId + '\'' +
                ", iat='" + iat + '\'' +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }
}

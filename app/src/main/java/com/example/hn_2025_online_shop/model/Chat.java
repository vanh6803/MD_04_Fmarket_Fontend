package com.example.hn_2025_online_shop.model;

public class Chat {
    private String avatar;
    private String username;
    private String message;

    public Chat(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

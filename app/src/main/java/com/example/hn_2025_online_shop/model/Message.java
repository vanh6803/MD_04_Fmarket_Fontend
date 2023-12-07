package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("sender_id")
    private String senderId;
    @SerializedName("receiver_id")
    private String receiverId;
    private String content;
    private String createdAt;
    private String updatedAt;

    public Message() {
    }

    public Message(String senderId, String receiverId, String content, String updatedAt) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.updatedAt = updatedAt;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
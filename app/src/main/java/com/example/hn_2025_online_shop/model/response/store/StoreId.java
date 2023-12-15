package com.example.hn_2025_online_shop.model.response.store;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class StoreId implements Serializable {
    @SerializedName("_id")
    private  String id;
    private String account_id;
    private  String name;
    private  String avatar;
    private  String banner;
    private  String address;
    private  String createdAt;
    private  String updatedAt;
    private  boolean is_active;

    public StoreId(String id, String account_id, String name, String avatar, String banner, String address, String createdAt, String updatedAt, boolean is_active) {
        this.id = id;
        this.account_id = account_id;
        this.name = name;
        this.avatar = avatar;
        this.banner = banner;
        this.address = address;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.is_active = is_active;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}

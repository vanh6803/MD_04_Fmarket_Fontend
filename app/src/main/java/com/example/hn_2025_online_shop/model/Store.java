package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Store implements Serializable {
    @SerializedName("_id")
    private String id;
    private String account_id;
    private String name;
    private String avatar;
    private String banner;
    private String address;
    private boolean is_active;

    public Store() {
    }

    public Store(String id, String account_id, String name, String avatar, String banner, String address, boolean is_active) {
        this.id = id;
        this.account_id = account_id;
        this.name = name;
        this.avatar = avatar;
        this.banner = banner;
        this.address = address;
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id='" + id + '\'' +
                ", account_id='" + account_id + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", banner='" + banner + '\'' +
                ", address='" + address + '\'' +
                ", is_active=" + is_active +
                '}';
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

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }
}

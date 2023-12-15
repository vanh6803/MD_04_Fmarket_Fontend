package com.example.hn_2025_online_shop.model.response.store;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InforId implements Serializable {
    @SerializedName("_id")
    private String id;
    private String user_id;
    private String name;
    private String address;
    private String phone_number;
    private boolean checked;

    public InforId(String id, String user_id, String name, String address, String phone_number, boolean checked) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
        this.checked = checked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

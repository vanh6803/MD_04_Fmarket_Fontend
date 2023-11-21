package com.example.hn_2025_online_shop.model.response.store;

public class DataStore {
    private  String _id;
    private AccountId account_id;
    private  String name;
    private  String avatar;
    private  String banner;
    private  String address;
    private  boolean is_active;
    private  String createdAt;
    private  String updatedAt;
    private  int  __v;

    public DataStore(String _id, AccountId account_id, String name, String avatar, String banner, String address, boolean is_active, String createdAt, String updatedAt, int __v) {
        this._id = _id;
        this.account_id = account_id;
        this.name = name;
        this.avatar = avatar;
        this.banner = banner;
        this.address = address;
        this.is_active = is_active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public AccountId getAccount_id() {
        return account_id;
    }

    public void setAccount_id(AccountId account_id) {
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}

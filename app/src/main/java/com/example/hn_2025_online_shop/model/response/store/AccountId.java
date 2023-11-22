package com.example.hn_2025_online_shop.model.response.store;

public class AccountId {
    private  String _id;
    private  String email;
    private  String password;
    private  boolean isVerify;
    private  boolean is_active;
    private  String role_id;
    private  String confirmationCode;
    private  String confirmationExpiration;
    private  String username;
    private  String createdAt;
    private  String updatedAt;
    private  String __v;
    private  String token;
    private  String avatar;
    private  String birthday;

    public AccountId(String _id, String email, String password, boolean isVerify, boolean is_active, String role_id, String confirmationCode, String confirmationExpiration, String username, String createdAt, String updatedAt, String __v, String token, String avatar, String birthday) {
        this._id = _id;
        this.email = email;
        this.password = password;
        this.isVerify = isVerify;
        this.is_active = is_active;
        this.role_id = role_id;
        this.confirmationCode = confirmationCode;
        this.confirmationExpiration = confirmationExpiration;
        this.username = username;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
        this.token = token;
        this.avatar = avatar;
        this.birthday = birthday;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    public String getConfirmationExpiration() {
        return confirmationExpiration;
    }

    public void setConfirmationExpiration(String confirmationExpiration) {
        this.confirmationExpiration = confirmationExpiration;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}

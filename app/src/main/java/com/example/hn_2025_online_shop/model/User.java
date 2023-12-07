package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
    @SerializedName("_id")
    private String id;
    private String email;
    private String password;
    private String avatar;
    private String username;
    @SerializedName("full_name")
    private String fullname;
    private String phone;
    private String address;
    private String birthday;
    private String token;
    private Boolean isVerify;
    private String confirmationCode;
    private String confirmationExpiration;
    private Boolean is_active;
    private String role_id;
    private String createdAt;
    private String updatedAt;

    public User() {
    }

    public User(String avatar, String username) {
        this.avatar = avatar;
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", username='" + username + '\'' +
                ", fullname='" + fullname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", birthday='" + birthday + '\'' +
                ", token='" + token + '\'' +
                ", isVerify=" + isVerify +
                ", confirmationCode='" + confirmationCode + '\'' +
                ", confirmationExpiration='" + confirmationExpiration + '\'' +
                ", is_active=" + is_active +
                ", role_id=" + role_id +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getVerify() {
        return isVerify;
    }

    public void setVerify(Boolean verify) {
        isVerify = verify;
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

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
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

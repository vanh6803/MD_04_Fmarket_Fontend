package com.example.hn_2025_online_shop.model.response.store;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bill {
    @SerializedName("_id")
    private String id;
    @SerializedName("user_id")
    private  String userId;

    private List<ProductOrder> productOrderList;
    private  double total_price;
    private  String status;
    private  String info_id;
    private  String createdAt;
    private  String updatedAt;
    private  int __v;

    public Bill(String id, String userId, List<ProductOrder> productOrderList, double total_price, String status, String info_id, String createdAt, String updatedAt, int __v) {
        this.id = id;
        this.userId = userId;
        this.productOrderList = productOrderList;
        this.total_price = total_price;
        this.status = status;
        this.info_id = info_id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ProductOrder> getProductOrderList() {
        return productOrderList;
    }

    public void setProductOrderList(List<ProductOrder> productOrderList) {
        this.productOrderList = productOrderList;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
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

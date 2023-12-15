package com.example.hn_2025_online_shop.model.response.store;

import com.example.hn_2025_online_shop.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultBuil {
    @SerializedName("_id")
    private  String idPro;
    User user_id;
    List<ProductOder> productsOrder;

    private double total_price;
    private String status;
    private InforId info_id;
    private String createdAt;
    private String updatedAt;
    private String __v;

    public ResultBuil(String idPro, User user_id, List<ProductOder> productsOrder, double total_price, String status, InforId info_id, String createdAt, String updatedAt, String __v) {
        this.idPro = idPro;
        this.user_id = user_id;
        this.productsOrder = productsOrder;
        this.total_price = total_price;
        this.status = status;
        this.info_id = info_id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.__v = __v;
    }

    public String getIdPro() {
        return idPro;
    }

    public void setIdPro(String idPro) {
        this.idPro = idPro;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public List<ProductOder> getProductsOrder() {
        return productsOrder;
    }

    public void setProductsOrder(List<ProductOder> productsOrder) {
        this.productsOrder = productsOrder;
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

    public InforId getInfo_id() {
        return info_id;
    }

    public void setInfo_id(InforId info_id) {
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

    public String get__v() {
        return __v;
    }

    public void set__v(String __v) {
        this.__v = __v;
    }
}

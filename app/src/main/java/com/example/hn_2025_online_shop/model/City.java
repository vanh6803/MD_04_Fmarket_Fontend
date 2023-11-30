package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class City {
    @SerializedName("province_id")
    private String provinceId;
    @SerializedName("province_name")
    private String provinceName;

    public City() {
    }


    public City(String provinceName) {
        this.provinceName = provinceName;
    }

    public City(String provinceId, String provinceName) {
        this.provinceId = provinceId;
        this.provinceName = provinceName;
    }

    @Override
    public String toString() {
        return "City{" +
                "provinceId='" + provinceId + '\'' +
                ", provinceName='" + provinceName + '\'' +
                '}';
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}

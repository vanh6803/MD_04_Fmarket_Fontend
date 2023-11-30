package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class District {
    @SerializedName("district_id")
    private String districtId;
    @SerializedName("district_name")
    private String districtName;

    public District() {
    }

    public District(String districtId, String districtName) {
        this.districtId = districtId;
        this.districtName = districtName;
    }

    public District(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public String toString() {
        return "District{" +
                "districtId='" + districtId + '\'' +
                ", districtName='" + districtName + '\'' +
                '}';
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}

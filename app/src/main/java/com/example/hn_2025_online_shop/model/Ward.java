package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class Ward {
    @SerializedName("ward_id")
    private String wardId;
    @SerializedName("ward_name")
    private String wardName;

    public Ward() {
    }

    public Ward(String wardName) {
        this.wardName = wardName;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "wardId='" + wardId + '\'' +
                ", wardName='" + wardName + '\'' +
                '}';
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }
}

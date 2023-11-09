package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class Banner {
    @SerializedName("_id")
    private String id;
    private String note;
    private String image;

    public Banner() {

    }

    public Banner(String id, String note, String image) {
        this.id = id;
        this.note = note;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Banner{" +
                "id=" + id +
                ", note='" + note + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

package com.example.hn_2025_online_shop.model.response.store;

import com.google.gson.annotations.SerializedName;

public class ProductOrder {
    @SerializedName("option_id")
    private OptionId optionId;

    private int quantity;
    @SerializedName("_id")
    private  String idOrder;

    public ProductOrder(OptionId optionId, int quantity, String idOrder) {
        this.optionId = optionId;
        this.quantity = quantity;
        this.idOrder = idOrder;
    }

    public OptionId getOptionId() {
        return optionId;
    }

    public void setOptionId(OptionId optionId) {
        this.optionId = optionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }
}

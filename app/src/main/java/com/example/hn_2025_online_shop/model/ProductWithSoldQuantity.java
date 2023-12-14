package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class ProductWithSoldQuantity {
    @SerializedName("productDetails")
    private ProductDetailsSoldQuantity productDetails;

    @SerializedName("product_id")
    private String productId;

    @SerializedName("store_id")
    private String storeId;

    @SerializedName("totalSoldQuantity")
    private int totalSoldQuantity;

    public ProductWithSoldQuantity() {
    }

    public ProductDetailsSoldQuantity getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetailsSoldQuantity productDetails) {
        this.productDetails = productDetails;
    }

    public ProductWithSoldQuantity(ProductDetailsSoldQuantity productDetails, String productId, String storeId, int totalSoldQuantity) {
        this.productDetails = productDetails;
        this.productId = productId;
        this.storeId = storeId;
        this.totalSoldQuantity = totalSoldQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public int getTotalSoldQuantity() {
        return totalSoldQuantity;
    }

    public void setTotalSoldQuantity(int totalSoldQuantity) {
        this.totalSoldQuantity = totalSoldQuantity;
    }
}

package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class Product_main {
   @SerializedName("_id")
   private String id;
   Number store_id;
   Number category_id;
   String name;
   String image;
   float price;
   Number quantity;
   Number units_sold;
   String discription;
   boolean is_active;
   Number status;
   String manufacturer;
   Object attribute;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public Product_main() {
    }

   public Product_main(String id, Number store_id, Number category_id, String name, String image, float price, Number quantity, Number units_sold, String discription, boolean is_active, Number status, String manufacturer, Object attribute) {
      this.id = id;
      this.store_id = store_id;
      this.category_id = category_id;
      this.name = name;
      this.image = image;
      this.price = price;
      this.quantity = quantity;
      this.units_sold = units_sold;
      this.discription = discription;
      this.is_active = is_active;
      this.status = status;
      this.manufacturer = manufacturer;
      this.attribute = attribute;
   }

   public Number getStore_id() {
      return store_id;
   }

   public void setStore_id(Number store_id) {
      this.store_id = store_id;
   }

   public Number getCategory_id() {
      return category_id;
   }

   public void setCategory_id(Number category_id) {
      this.category_id = category_id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public float getPrice() {
      return price;
   }

   public void setPrice(float price) {
      this.price = price;
   }

   public Number getQuantity() {
      return quantity;
   }

   public void setQuantity(Number quantity) {
      this.quantity = quantity;
   }

   public Number getUnits_sold() {
      return units_sold;
   }

   public void setUnits_sold(Number units_sold) {
      this.units_sold = units_sold;
   }

   public String getDiscription() {
      return discription;
   }

   public void setDiscription(String discription) {
      this.discription = discription;
   }

   public boolean isIs_active() {
      return is_active;
   }

   public void setIs_active(boolean is_active) {
      this.is_active = is_active;
   }

   public Number getStatus() {
      return status;
   }

   public void setStatus(Number status) {
      this.status = status;
   }

   public String getManufacturer() {
      return manufacturer;
   }

   public void setManufacturer(String manufacturer) {
      this.manufacturer = manufacturer;
   }

   public Object getAttribute() {
      return attribute;
   }

   public void setAttribute(Object attribute) {
      this.attribute = attribute;
   }
}

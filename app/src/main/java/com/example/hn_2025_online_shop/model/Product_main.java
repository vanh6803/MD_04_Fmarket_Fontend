package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class Product_main {
   @SerializedName("_id")
   private String id;
   private String name;
   private boolean discounted;
   private String image;
   private String price;
   private double minPrice;
   private double maxPrice;
   private int averageRate;

   public Product_main() {
   }


   @Override
   public String toString() {
      return "Product_main{" +
              "id='" + id + '\'' +
              ", name='" + name + '\'' +
              ", discounted=" + discounted +
              ", image='" + image + '\'' +
              ", price='" + price + '\'' +
              ", minPrice=" + minPrice +
              ", maxPrice=" + maxPrice +
              ", averageRate=" + averageRate +
              '}';
   }

   public Product_main(String id, String name, boolean discounted, String image, String price, double minPrice, double maxPrice, int averageRate) {
      this.id = id;
      this.name = name;
      this.discounted = discounted;
      this.image = image;
      this.price = price;
      this.minPrice = minPrice;
      this.maxPrice = maxPrice;
      this.averageRate = averageRate;
   }

   public String getPrice() {
      return price;
   }

   public void setPrice(String price) {
      this.price = price;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public boolean isDiscounted() {
      return discounted;
   }

   public void setDiscounted(boolean discounted) {
      this.discounted = discounted;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public double getMinPrice() {
      return minPrice;
   }

   public void setMinPrice(double minPrice) {
      this.minPrice = minPrice;
   }

   public double getMaxPrice() {
      return maxPrice;
   }

   public void setMaxPrice(double maxPrice) {
      this.maxPrice = maxPrice;
   }

   public int getAverageRate() {
      return averageRate;
   }

   public void setAverageRate(int averageRate) {
      this.averageRate = averageRate;
   }
}

package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Product implements Serializable {
   @SerializedName("_id")
   private String id;
   private String name;
   private boolean discounted;
   private String image;
   private int minPrice;
   private double averageRate;
   private int review;
   private int soldQuantity;

   public Product() {
   }



   public Product(String id, String name, boolean discounted, String image, int minPrice, double averageRate, int review) {
      this.id = id;
      this.name = name;
      this.discounted = discounted;
      this.image = image;
      this.minPrice = minPrice;
      this.averageRate = averageRate;
      this.review = review;
   }

   @Override
   public String toString() {
      return "Product{" +
              "id='" + id + '\'' +
              ", name='" + name + '\'' +
              ", discounted=" + discounted +
              ", image='" + image + '\'' +
              ", minPrice=" + minPrice +
              ", averageRate=" + averageRate +
              ", review=" + review +
              '}';
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

   public int getMinPrice() {
      return minPrice;
   }

   public void setMinPrice(int minPrice) {
      this.minPrice = minPrice;
   }

   public double getAverageRate() {
      return averageRate;
   }

   public void setAverageRate(double averageRate) {
      this.averageRate = averageRate;
   }

   public int getReview() {
      return review;
   }

   public void setReview(int review) {
      this.review = review;
   }

   public int getSoldQuantity() {
      return soldQuantity;
   }

   public void setSoldQuantity(int soldQuantity) {
      this.soldQuantity = soldQuantity;
   }
}

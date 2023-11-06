package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class Product {
   @SerializedName("_id")
   private String id;
   private String name;
   private boolean discounted;
   private String image;
   private int price;
   private double averageRate;
   private int review;

   public Product() {
   }

   public Product(String id, String name, boolean discounted, String image, int price, double averageRate, int review) {
      this.id = id;
      this.name = name;
      this.discounted = discounted;
      this.image = image;
      this.price = price;
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
              ", price=" + price +
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

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
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
}

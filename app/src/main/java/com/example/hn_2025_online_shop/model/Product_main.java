package com.example.hn_2025_online_shop.model;

import com.google.gson.annotations.SerializedName;

public class Product_main {
   @SerializedName("_id")
   private String id;
    private String name;
   private String image;
   private String description;

   public Product_main() {
   }

   public Product_main(String id, String name, String image, String description) {
      this.id = id;
      this.name = name;
      this.image = image;
      this.description = description;
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

   public String getImage() {
      return image;
   }

   @Override
   public String toString() {
      return "Product_main{" +
              "id='" + id + '\'' +
              ", name='" + name + '\'' +
              ", image='" + image + '\'' +
              ", description='" + description + '\'' +
              '}';
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}

package com.example.hn_2025_online_shop.api;

import com.example.hn_2025_online_shop.response.ProductResponse;
import com.example.hn_2025_online_shop.response.ProductTypeResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface BaseApi {
    Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
    BaseApi API = new Retrofit.Builder()
            .baseUrl("http://192.168.100.4:3000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BaseApi.class);
    @GET("/products/")
    Call<ProductResponse> getListProduct();
    @GET("category/get-list")
    Call<ProductTypeResponse> getListTypeProduct();
}

package com.example.hn_2025_online_shop.api;

import com.example.hn_2025_online_shop.model.response.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface BaseApi {
    Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
    BaseApi API = new Retrofit.Builder()
            .baseUrl("http://192.168.1.171:3000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BaseApi.class);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);
}

package com.example.hn_2025_online_shop.api;

import com.example.hn_2025_online_shop.model.response.BannerReponse;
import com.example.hn_2025_online_shop.model.response.CheckStoreResponse;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.model.response.DetailUserReponse;
import com.example.hn_2025_online_shop.model.response.ProductByCategoryReponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.model.response.LoginResponse;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.model.response.ProductTypeResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface BaseApi {
    Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
    // 10.0.2.2
    // 10.0.3.2
    BaseApi API = new Retrofit.Builder()

            .baseUrl("http://10.0.2.2:3000/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(BaseApi.class);

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("register")
    Call<ServerResponse> register(@Field("email") String email,
                                  @Field("password") String password);
    @GET("logout")
    Call<ServerResponse> logout(@Header("Authorization") String authorization);

    @GET("verify/{idCode}")
    Call<ServerResponse> verify(@Path("idCode") String idCode);

    @FormUrlEncoded
    @PUT("user/change-password/{idUser}")
    Call<ServerResponse> changePassword(@Header("Authorization") String authorization,
                                        @Path("idUser") String idUser,
                                        @Field("oldPassword") String oldPassword,
                                        @Field("newPassword") String newPassword);

    @GET("user/detail-profile/{idUser}")
    Call<DetailUserReponse> detailProfile(@Header("Authorization") String authorization,
                                          @Path("idUser") String idUser);

    @FormUrlEncoded
    @POST("resend-code")
    Call<ServerResponse> resendCode(@Field("email") String email);

    @FormUrlEncoded
    @POST("forgot-password")
    Call<ServerResponse> forgotPassword(@Field("email") String email);

    @GET("products/all-product")
    Call<ProductResponse> getListAllProduct();



    @GET("products/all-product-by-category")
    Call<ProductByCategoryReponse> getListProductByCategory();

    @GET("category/get-list")
    Call<ProductTypeResponse> getListTypeProduct();

    @GET("products/detail-product/{idProduct}")
    Call<DetailProductResponse> getDetailProduct(@Path("idProduct") String idProduct);
    @GET("products//similar-product/{idProduct}")
    Call<ProductResponse> getDataSimilarlProduct(@Path("idProduct") String idProduct);


    @GET("banner/get-list")
    Call<BannerReponse> getListBanner();

    @Multipart
    @POST("store/create/{idUser}")
    Call<ServerResponse> registerMemberSeller(@Path("idUser") String idUser,
                                                    @Part MultipartBody.Part avatar,
                                                    @Part MultipartBody.Part banner,
                                                    @Part("name") RequestBody name,
                                                    @Part("address") RequestBody address);
    @GET("products/all-product-by-store/{storeId}")
    Call<ProductResponse> getDataProductStore(@Path("storeId") String storeId);

    @Multipart
    @PUT("user/upload-avatar/{idUser}")
    Call<ServerResponse> uploadAvatar(@Header("Authorization") String authorization,
                                      @Path("idUser") String idUser,
                                      @Part MultipartBody.Part avatar);

    @FormUrlEncoded
    @PUT("user/edit-profile/{idUser}")
    Call<ServerResponse> editProfile(@Header("Authorization") String authorization,
                                     @Path("idUser") String idUser,
                                     @Field("username") String username,
                                     @Field("birthday") String birthday);
    @GET("store/check-exiting")
    Call<CheckStoreResponse> checkStoreExiting(@Header("Authorization") String authorization);
}

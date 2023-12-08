package com.example.hn_2025_online_shop.api;

import com.example.hn_2025_online_shop.model.Order;
import com.example.hn_2025_online_shop.model.body.PurchaseBody;
import com.example.hn_2025_online_shop.model.response.BannerReponse;
import com.example.hn_2025_online_shop.model.response.CartReponse;
import com.example.hn_2025_online_shop.model.response.CheckStoreResponse;
import com.example.hn_2025_online_shop.model.response.CreateProductResponse;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.model.response.DetailUserReponse;
import com.example.hn_2025_online_shop.model.response.InfoResponse;
import com.example.hn_2025_online_shop.model.response.ListChatResponse;
import com.example.hn_2025_online_shop.model.response.ListCommentResponse;
import com.example.hn_2025_online_shop.model.response.ListMessageResponse;
import com.example.hn_2025_online_shop.model.response.OrderResponse;
import com.example.hn_2025_online_shop.model.response.ProductByCategoryReponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.model.response.LoginResponse;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.model.response.ProductTypeResponse;
import com.example.hn_2025_online_shop.model.response.StoreIdResponse;
import com.example.hn_2025_online_shop.model.response.store.InfoStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BaseApi {
    Gson gson = new GsonBuilder().setDateFormat("yyyy/MM/dd HH:mm:ss").create();
    // 10.0.2.2
    // 10.0.3.2
    // 172.20.10.3
    // 192.168.0.106
//    192.168.100.4
    String LOCALHOT = "10.0.2.2"; // đc cho socket
    BaseApi API = new Retrofit.Builder()
            .baseUrl("http://" + LOCALHOT +":3000/api/")
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
    @POST("store/create")
    Call<ServerResponse> registerMemberSeller(@Header("Authorization") String authorization,
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

    @FormUrlEncoded
    @POST("cart/create-cart-item")
    Call<ServerResponse> createCartItem(@Header("Authorization") String authorization,
                                        @Field("option_id") String optionId,
                                        @Field("quantity") int quantity);

    @GET("cart/all-cart-user")
    Call<CartReponse> allCartUser(@Header("Authorization") String authorization);



    @DELETE("cart/delete-cart-item/{idCart}")
    Call<ServerResponse> deleteCartItem(@Header("Authorization") String authorization,
                                        @Path("idCart") String idCart);
    @FormUrlEncoded
    @PUT("cart/update-quantity/{idCart}")
    Call<ServerResponse> updateQuantityCartItem(@Header("Authorization") String authorization,
                                            @Path("idCart") String idCart,
                                            @Field("quantity") int quantity);
    @FormUrlEncoded
    @PUT("store/edit-avatar/{storeId}")
    Call<ServerResponse> updateAvatarStore(@Header("Authorization") String authorization,
                                           @Path("storeId") String storeId,
                                           @Part MultipartBody.Part avatar);
    @FormUrlEncoded
    @POST("info/add")
    Call<ServerResponse> addInfo(@Header("Authorization") String authorization,
                                 @Field("name") String name,
                                 @Field("address") String address,
                                 @Field("phone_number") String phone_number,
                                 @Field("checked") Boolean checked);

    @GET("info")
    Call<InfoResponse> getInfo(@Header("Authorization") String authorization);
    @POST("order/create-order")
    Call<ServerResponse> createOrder(@Header("Authorization") String authorization,
                                     @Body PurchaseBody purchaseBody);
    @GET("store/check-exiting")
    Call<CheckStoreResponse> checkStoreExiting(@Header("Authorization") String authorization);

    @GET("store/get-store-id/{accountId}")
    Call<StoreIdResponse> getidMyStore(@Header("Authorization") String authorization,
                                       @Path("accountId") String accountId);


    @FormUrlEncoded
    @PUT("info/edit-info/{idInfo}")
    Call<ServerResponse> editInfo(@Header("Authorization") String authorization,
                                  @Path("idInfo") String idInfo,
                                  @Field("name") String name,
                                  @Field("address") String address,
                                  @Field("phone_number") String phone_number,
                                  @Field("checked") Boolean checked);

    @DELETE("info/delete/{idInfo}")
    Call<ServerResponse> deleteInfo(@Header("Authorization") String authorization,
                                    @Path("idInfo") String idInfo);

    @FormUrlEncoded
    @POST("products/create-product")
    Call<CreateProductResponse> createProductMyStore(@Header("Authorization") String authorization,
                                                     @Field("category_id") String category_id,
                                                     @Field("name") String name,
                                                     @Field("description") String description,
                                                     @Field("status") String status,
                                                     @Field("screen") String screen,
                                                     @Field("camera") String camera,
                                                     @Field("chipset") String chipset,
                                                     @Field("cpu") String cpu,
                                                     @Field("gpu") String gpu,
                                                     @Field("ram") int ram,
                                                     @Field("rom") int rom,
                                                     @Field("operatingSystem") String operatingSystem,
                                                     @Field("battery") String battery,
                                                     @Field("weight") int weight,
                                                     @Field("connection") String connection,
                                                     @Field("specialFeature") String specialFeature,
                                                     @Field("manufacturer") String manufacturer,
                                                     @Field("other") String other);

    @Multipart
    @POST("products/create-option")
    Call<ServerResponse> createOption(@Header("Authorization") String authorization,
                                 @Part("product_id") RequestBody product_id,
                                 @Part("name_color") RequestBody name_color,
                                 @Part MultipartBody.Part image,
                                 @Part("price") RequestBody price,
                                 @Part("discount_value") RequestBody discount_value,
                                 @Part("quantity") RequestBody quantity,
                                 @Part("hot_option") RequestBody hot_option);





    @GET("order")
    Call<OrderResponse> getListOrder(@Header("Authorization") String authorization,
                                     @Query("status") String status);

    @GET("order/order-for-store")
    Call<OrderResponse> getListOrderStore(@Header("Authorization") String authorization,
                                     @Query("status") String status);

    @FormUrlEncoded
    @PUT("order/update-order-status/{idOrder}")
    Call<ServerResponse> updateOrderStatus(@Header("Authorization") String authorization,
                                     @Path("idOrder") String idOrder,
                                     @Field("status") String status);
    Call<OrderResponse> getListOrder(@Header("Authorization") String authorization);


    @GET("store/info")
    Call<InfoStore> getInfoStore(@Header("Authorization") String authorization);

    @GET("message/get-people-msg-list/{userId}")
    Call<ListChatResponse> getListPeopleChat(@Header("Authorization") String authorization,
                                             @Path("userId") String idUser);

    @GET("message/get-msg-list")
    Call<ListMessageResponse> getListMessage(@Header("Authorization") String authorization,
                                             @Query("userId1") String userId1,
                                             @Query("userId2") String userId2);
    @FormUrlEncoded
    @PUT("products/update-product/{productId}")
    Call<ServerResponse> updateProduct(@Header("Authorization") String authorization,
                                       @Path("productId") String productId,
                                       @Field("name") String name,
                                       @Field("description") String description,
                                       @Field("status") String status,
                                       @Field("screen") String screen,
                                       @Field("camera") String camera,
                                       @Field("chipset") String chipset,
                                       @Field("cpu") String cpu,
                                       @Field("gpu") String gpu,
                                       @Field("ram") int ram,
                                       @Field("rom") int rom,
                                       @Field("operatingSystem") String operatingSystem,
                                       @Field("battery") String battery,
                                       @Field("weight") int weight,
                                       @Field("connection") String connection,
                                       @Field("specialFeature") String specialFeature,
                                       @Field("manufacturer") String manufacturer,
                                       @Field("other") String other);
    @FormUrlEncoded
    @PUT("products/update-option/{optionId}")
    Call<ServerResponse> updateOption(@Header("Authorization") String authorization,
                                      @Path("optionId") String optionId,
                                      @Field("name_color") String name_color,
                                      @Field("price") int price,
                                      @Field("discount_value") int discount_value,
                                      @Field("quantity") int quantity,
                                      @Field("hot_option") boolean hot_option);
    @Multipart
    @PUT("products/update-option-image/{optionId}")
    Call<ServerResponse> updateImageOption(@Header("Authorization") String authorization,
                                           @Path("optionId") String optionId,
                                           @Part MultipartBody.Part image);


    @GET("comment/get-comments-by-product/{productId}")
    Call<ListCommentResponse> getListComment(@Header("Authorization") String authorization,
                                             @Path("productId") String productId);

    @Multipart
    @PUT("store/edit-avatar")
    Call<ServerResponse> updateAvartarStore(@Header("Authorization") String authorization,
                                           @Part MultipartBody.Part avatar);
    @Multipart
    @PUT("store/edit-banner")
    Call<ServerResponse> updateBannerStore(@Header("Authorization") String authorization,
                                           @Part MultipartBody.Part banner);
    @FormUrlEncoded
    @PUT("store/update")
    Call<ServerResponse> updateStore(@Header("Authorization") String authorization,
                                      @Field("name") String name,
                                      @Field("address") String address);
    @GET("products/all-product-by-category")
    Call<ProductResponse> getAllProductByCategory(@Query("category") String category);

    @GET("products/all-product")
    Call<ProductResponse> getAllProductDiscouted(@Query("discounted") boolean discounted);
    @GET("products/topProduct")
    Call<ProductResponse> getTopProductBestSeller();

//    @GET("products/all-product-by-category")
//    Call<ProductResponse> getTop10ProductBestSeller(@Query("categoryId") String categoryId);
}

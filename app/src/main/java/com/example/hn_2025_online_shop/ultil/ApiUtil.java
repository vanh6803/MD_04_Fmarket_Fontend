package com.example.hn_2025_online_shop.ultil;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hn_2025_online_shop.adapter.CartAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.model.CartOfList;
import com.example.hn_2025_online_shop.model.response.CartReponse;
import com.example.hn_2025_online_shop.model.response.DetailUserReponse;
import com.example.hn_2025_online_shop.view.cart_screen.CartActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiUtil {

    public static void getDetailUser(Context context, ProgressLoadingDialog loadingDialog) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        String idUser = JWTUtils.decoded(AccountUltil.TOKEN).getUserId();
        loadingDialog.show();
        BaseApi.API.detailProfile(token, idUser).enqueue(new Callback<DetailUserReponse>() {
            @Override
            public void onResponse(Call<DetailUserReponse> call, Response<DetailUserReponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    DetailUserReponse detailUserReponse = response.body();
                    Log.d(TAG.toString, "onResponse-detailProfile: " + detailUserReponse.toString());
                    if(detailUserReponse.getCode() == 200) {
                        AccountUltil.USER = detailUserReponse.getData(); // lấy user
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-detailProfile: " + errorMessage);
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<DetailUserReponse> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-detailProfile: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    // lấy getAllCart ở mọi nơi
    public static void getAllCart(Context context, ProgressLoadingDialog loadingDialog, CartAdapter cartAdapter) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        loadingDialog.show();
        BaseApi.API.allCartUser(token).enqueue(new Callback<CartReponse>() {
            @Override
            public void onResponse(Call<CartReponse> call, Response<CartReponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    CartReponse cartReponse = response.body();
                    Log.d(TAG.toString, "onResponse-allCartUser: " + cartReponse.toString());
                    if(cartReponse.getCode() == 200) {
                        CartUtil.listCart = cartReponse.getData();
                        if(cartAdapter != null) {
                            cartAdapter.setListCart(CartUtil.listCart);
                        }
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-allCartUser: " + errorMessage);
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-allCartUser: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    public static void setTitleQuantityCart(Context context, ProgressLoadingDialog loadingDialog, TextView tvQuantityCart) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        loadingDialog.show();
        BaseApi.API.allCartUser(token).enqueue(new Callback<CartReponse>() {
            @Override
            public void onResponse(Call<CartReponse> call, Response<CartReponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    CartReponse cartReponse = response.body();
                    Log.d(TAG.toString, "onResponse-allCartUser: " + cartReponse.toString());
                    if(cartReponse.getCode() == 200) {
                        CartUtil.listCart = cartReponse.getData();
                        tvQuantityCart.setText(CartUtil.listCart.size() + "");
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-allCartUser: " + errorMessage);
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                Toast.makeText(context, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-allCartUser: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }
}

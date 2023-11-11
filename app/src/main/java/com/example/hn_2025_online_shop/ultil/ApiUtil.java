package com.example.hn_2025_online_shop.ultil;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.model.response.DetailUserReponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

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
}

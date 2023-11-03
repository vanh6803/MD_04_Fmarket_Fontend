package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ResetPasswordBinding;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.JWTUtils;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.ultil.Validator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassWord extends AppCompatActivity {
    private ResetPasswordBinding binding;
    private ProgressLoadingDialog loadingDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initController();
    }

    private void initController() {
        binding.btnRepassSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOldPassword = binding.edtOldPassword.getText().toString().trim();
                String strNewPassword = binding.edtNewPassword.getText().toString().trim();
                String strReNewPassword = binding.edtReNewPassword.getText().toString().trim();
                repassAccount(strOldPassword, strNewPassword, strReNewPassword);
            }
        });

        binding.backResetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });
    }

    private void repassAccount(String strOldPassword, String strNewPassword, String strReNewPassword) {
        if(validateRepass(strOldPassword, strNewPassword, strReNewPassword)) {
            String token = AccountUltil.BEARER + AccountUltil.TOKEN;
            String idUser = JWTUtils.decoded(AccountUltil.TOKEN).getUserId();
            loadingDialog.show();
            BaseApi.API.changePassword(token, idUser, strOldPassword, strNewPassword).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.isSuccessful()){ // chỉ nhận đầu status 200
                        ServerResponse serverResponse = response.body();
                        Log.d(TAG.toString, "onResponse-repassAccount: " + serverResponse.toString());
                        if(serverResponse.getCode() == 200) {
                            Toast.makeText(getApplicationContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else { // nhận các đầu status #200
                        try {
                            String errorBody = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Log.d(TAG.toString, "onResponse-register: " + errorMessage);
                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }catch (IOException e){
                            e.printStackTrace();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    loadingDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Toast.makeText(ResetPassWord.this, t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG.toString, "onFailure-ResetPassWord: " + t.toString());
                    loadingDialog.dismiss();
                }
            });
        }
    }

    private boolean validateRepass(String strOldPassword, String strNewPassword, String strReNewPassword) {
        if (TextUtils.isEmpty(strOldPassword) || TextUtils.isEmpty(strNewPassword) || TextUtils.isEmpty(strReNewPassword)){
            Toast.makeText(ResetPassWord.this,"Bạn đừng để trống chỗ nhập nhé!", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!strNewPassword.equals(strReNewPassword)) {
            Toast.makeText(ResetPassWord.this,"NewPassword và ReNewPassword không khớp nhau!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        loadingDialog = new ProgressLoadingDialog(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}

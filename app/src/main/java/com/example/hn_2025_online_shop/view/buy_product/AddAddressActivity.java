package com.example.hn_2025_online_shop.view.buy_product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityAddAddressBinding;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressActivity extends AppCompatActivity {
    private ActivityAddAddressBinding binding;
    private ProgressLoadingDialog loadingDialog;
    private Boolean checked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initController();
    }

    private void initController() {
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = binding.edtAddress.getText().toString().trim();
                String phoneNumber = binding.edtPhoneNumber.getText().toString().trim();
                String name = binding.edtUsername.getText().toString().trim();
                if(validateInfo(address, phoneNumber, name)) {
                    urlAddInfo(address, phoneNumber, name);
                }
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });

    }

    private void urlAddInfo(String address, String phoneNumber, String name) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        loadingDialog.show();
        checked = binding.chkChooseDefault.isChecked();
        BaseApi.API.addInfo(token,name, address, phoneNumber, checked).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    Log.d(TAG.toString, "onResponse-addInfo: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Intent intent = new Intent(AddAddressActivity.this, AddressActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-addInfo: " + errorMessage);
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
                Toast.makeText(AddAddressActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-addInfo: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    private boolean validateInfo(String address, String phoneNumber, String name) {
        if(TextUtils.isEmpty(address)) {
            Toast.makeText(this, "Nhập dữ liệu địa chỉ đầy đủ", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "Nhập dữ liệu số điện thoại đầy đủ", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Nhập dữ liệu họ và tên đầy đủ", Toast.LENGTH_SHORT).show();
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
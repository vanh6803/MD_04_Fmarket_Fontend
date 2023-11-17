package com.example.hn_2025_online_shop.view.buy_product;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.DialogDeleteAddressBinding;
import com.example.hn_2025_online_shop.databinding.LayoutEditAddressBinding;
import com.example.hn_2025_online_shop.model.Info;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.CartUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.home_screen.MainActivity;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAddressActivity extends AppCompatActivity {
     private LayoutEditAddressBinding binding;
     private Info info;
     private ProgressLoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutEditAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        setData();
        initController();
    }

    private void initController() {
        binding.tvXoaDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDeleteAddressBinding binding1 = DialogDeleteAddressBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(UpdateAddressActivity.this);
                dialog.setContentView(binding1.getRoot());
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                binding1.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        urlDeleteInfo();
                        dialog.dismiss();
                    }
                });

                binding1.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = binding.edtAddress.getText().toString().trim();
                String phoneNumber = binding.edtPhoneNumber.getText().toString().trim();
                String name = binding.edtUsername.getText().toString().trim();
                if(validateInfo(address, phoneNumber, name)) {
                    urlEditInfo(address, phoneNumber, name);
                }
            }
        });

        binding.imgBack.setOnClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
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

    private void urlEditInfo(String address, String phoneNumber, String name) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        boolean checked = binding.chkChooseDefault.isChecked();
        loadingDialog.show();
        BaseApi.API.editInfo(token, info.getId(), name, address, phoneNumber, checked).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    Log.d(TAG.toString, "onResponse-editInfo: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(UpdateAddressActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        // khi quay trở lại có thể load lại url
                        Intent intent = new Intent(UpdateAddressActivity.this, AddressActivity.class);
                        setResult(RESULT_OK, intent);
                        finish();
                        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-editInfo: " + errorMessage);
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
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-deleteInfo: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    private void urlDeleteInfo() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        loadingDialog.show();
        BaseApi.API.deleteInfo(token, info.getId()).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    Log.d(TAG.toString, "onResponse-deleteInfo: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(UpdateAddressActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        // khi quay trở lại có thể load lại url
                        Intent intent = new Intent(UpdateAddressActivity.this, AddressActivity.class);
                        setResult(RESULT_OK, intent);
                        finish();
                        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-deleteInfo: " + errorMessage);
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
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-deleteInfo: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    private void setData() {
        binding.edtUsername.setText(info.getName());
        binding.edtPhoneNumber.setText(info.getPhoneNumber());
        binding.edtAddress.setText(info.getAddress());
        binding.chkChooseDefault.setChecked(info.getChecked());
    }

    private void initView() {
        loadingDialog = new ProgressLoadingDialog(this);
        Intent intent = getIntent(); // Lấy Intent từ Activity hiện tại
        Bundle bundle = intent.getExtras();
        if (bundle != null) { // Kiểm tra xem Bundle có tồn tại hay không
            info = (Info) bundle.getSerializable("info"); // Ép kiểu đối tượng từ Bundle
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}
package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.RegisterMemberSellerBinding;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterMemberSeller extends AppCompatActivity {
    private RegisterMemberSellerBinding binding;
    private ProgressLoadingDialog loadingDialog;
    private MultipartBody.Part fileImgAvatar;
    private MultipartBody.Part fileImgBanner;
    private int isCheckImage = 0; // 1 là avatar, 2 là banner dùng để set dữ liệu
    private boolean isAvatar = false; // kiểm tra xem avatar có dữ liệu hay chưa
    private boolean isBanner = false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegisterMemberSellerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initController();
        initView();
    }
    private void initController() {
        binding.btnRegisMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edtNameShop.getText().toString().trim();
                String address = binding.edtDiachi.getText().toString().trim();
                registerMemberSeller(name, address);
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });

        binding.imgAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(RegisterMemberSeller.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
                isCheckImage = 1;
            }
        });

        binding.imgBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(RegisterMemberSeller.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
                isCheckImage = 2;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            File file = new File(getPath(uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            if(isCheckImage == 1) {
                isAvatar = true;
                binding.imgAvartar.setImageURI(uri);
                fileImgAvatar = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);
            } else if(isCheckImage == 2) {
                isBanner = true;
                binding.imgBanner.setImageURI(uri);
                fileImgBanner =  MultipartBody.Part.createFormData("banner", file.getName(), requestBody);
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            isAvatar = false;
            isBanner = false;
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
            isAvatar = false;
            isBanner = false;
        }
    }

    private String getPath(Uri uri){
        String result;
        Cursor cursor = getContentResolver()
                .query(uri, null,null,null,null);
        if (cursor == null){
            result = uri.getPath();
        }else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(index);
            cursor.close();
        }
        return result;
    }


    private void registerMemberSeller(String name, String address) {
        if(checkRegister(name,address)) {
            loadingDialog.show();
            String idUser = AccountUltil.USER.getId();
            RequestBody requestBodyName = RequestBody.create(MediaType.parse("multipart/form-data"), name);
            RequestBody requestBodyAddress = RequestBody.create(MediaType.parse("multipart/form-data"), address);
            BaseApi.API.registerMemberSeller(idUser, fileImgAvatar, fileImgBanner, requestBodyName, requestBodyAddress).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.isSuccessful()){ // chỉ nhận đầu status 200
                        ServerResponse serverResponse = response.body();
                        Log.d(TAG.toString, "onResponse-registerMemberSeller: " + serverResponse.toString());
                        if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                            Intent intent = new Intent(RegisterMemberSeller.this, CreateStoreSuccessActivity.class);
                            startActivity(intent);
                        }
                    } else { // nhận các đầu status #200
                        try {
                            String errorBody = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Log.d(TAG.toString, "onResponse-registerMemberSeller: " + errorMessage);
                            Toast.makeText(RegisterMemberSeller.this, errorMessage, Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RegisterMemberSeller.this, t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG.toString, "onFailure-registerMemberSeller: " + t.toString());
                    loadingDialog.dismiss();
                }
            });
        }
    }

    private boolean checkRegister(String name, String address) {
       if(TextUtils.isEmpty(name)) {
           Toast.makeText(this, "Mời nhập tên shop", Toast.LENGTH_SHORT).show();
            return false;
       } else if(!isAvatar) {
           Toast.makeText(this, "Hãy chọn Avatar", Toast.LENGTH_SHORT).show();
           return false;
       } else if(TextUtils.isEmpty(address)) {
           Toast.makeText(this, "Mời nhập địa chỉ", Toast.LENGTH_SHORT).show();
           return false;
       } else if(!isBanner) {
           Toast.makeText(this, "Hãy chọn Banner", Toast.LENGTH_SHORT).show();
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

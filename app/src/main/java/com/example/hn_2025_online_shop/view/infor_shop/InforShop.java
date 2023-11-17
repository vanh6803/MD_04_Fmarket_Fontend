package com.example.hn_2025_online_shop.view.infor_shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageStoreAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.LayoutStoreBinding;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforShop  extends AppCompatActivity {
    private LayoutStoreBinding binding;
    ViewPageStoreAdapter adapter;
    private ProgressLoadingDialog dialog;
   int  curentIndex =0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressLoadingDialog(this);
        adapter = new ViewPageStoreAdapter(getSupportFragmentManager());
        binding.viewPagerHome.setAdapter(adapter);
        binding.textshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curentIndex = 0;
                binding.textshop.setTextColor(getColor(R.color.red));
                binding.textproduct.setTextColor(getColor(R.color.black));
                binding.viewPagerHome.setCurrentItem(curentIndex);
            }
        });

        binding.textproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curentIndex = 1;
                binding.textproduct.setTextColor(getColor(R.color.red));
                binding.textshop.setTextColor(getColor(R.color.black));
                binding.viewPagerHome.setCurrentItem(curentIndex);
            }
        });
        Intent intent = getIntent();
        String storeId = intent.getStringExtra("storeId");
        Log.d("gggg", "onCreate: " + storeId);
        setInforStore();
    }

    private void setInforStore() {
        dialog.show();
        Intent intent = getIntent();
        String id_product = intent.getStringExtra("id_product");
        Log.d("iiii", "setInforStore: " + id_product);
        BaseApi.API.getDetailProduct(id_product).enqueue(new Callback<DetailProductResponse>() {
            @Override
            public void onResponse(Call<DetailProductResponse> call, Response<DetailProductResponse> response) {
                if(response.isSuccessful()){
                    DetailProductResponse detailProductResponse = response.body();
                    if (detailProductResponse.getCode() == 200){
                        binding.tvNameStore.setText(detailProductResponse.getResult().getStore_id().getName());
                        Glide.with(InforShop.this).load(detailProductResponse.getResult().getStore_id().getAvatar()).error(R.drawable.error).into(binding.imgAvartar);
                        Glide.with(InforShop.this).load(detailProductResponse.getResult().getStore_id().getBanner()).error(R.drawable.error).into(binding.bannerStore);
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        // Parse and display the error message
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure(Call<DetailProductResponse> call, Throwable t) {
                Toast.makeText(InforShop.this, "Error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }


}

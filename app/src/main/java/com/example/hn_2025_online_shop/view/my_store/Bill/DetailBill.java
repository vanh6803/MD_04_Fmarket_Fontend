package com.example.hn_2025_online_shop.view.my_store.Bill;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentDetailBillBinding;
import com.example.hn_2025_online_shop.model.response.store.DetailBills;
import com.example.hn_2025_online_shop.model.response.store.ResponseBill;
import com.example.hn_2025_online_shop.model.response.store.Result;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBill extends AppCompatActivity  {
    FragmentDetailBillBinding binding;
    private String id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDetailBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initController();
    }

    public  void initController(){
        Intent intent = getIntent();
        id = intent.getStringExtra("id");


        getDetailBill(id);
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    private void getDetailBill(String id) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getDetailBill(token,id).enqueue(new Callback<DetailBills>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<DetailBills> call, Response<DetailBills> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    DetailBills orderResponse = response.body();
                    Log.d(TAG.toString, "onResponse-getListOrder: " + orderResponse.toString());
                    if(orderResponse.getCode() == 200 || orderResponse.getCode() == 201) {
                        binding.tenkhachang.setText(orderResponse.getResult().getInfo_id().getName());
                        binding.makh.setText(orderResponse.getResult().getUser_id().getId());
                        binding.diachi.setText(orderResponse.getResult().getInfo_id().getAddress());

                        binding.price.setText(String.valueOf(orderResponse.getResult().getTotal_price()) +"đ");

                       for(int i = 0; i< orderResponse.getResult().getProductsOrder().size(); i++){
                           Picasso.get().load(orderResponse.getResult().getProductsOrder().get(i).getOption_id().getImage()).into(binding.img);
                           binding.tensp.setText(orderResponse.getResult().getProductsOrder().get(i).getOption_id().getProduct().getName());
                           binding.mahoadon.setText(orderResponse.getResult().getProductsOrder().get(i).getOption_id().getId());
                           binding.tenshop.setText(orderResponse.getResult().getProductsOrder().get(i).getOption_id().getProduct().getStore_id().getName());
                           binding.time.setText(convertDateFormat(orderResponse.getResult().getProductsOrder().get(i).getOption_id().getCreatedAt()));
                       }
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getListOrder: " + errorMessage);
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                binding.progressBar.setVisibility(View.GONE);
            }


            @Override
            public void onFailure(Call<DetailBills> call, Throwable t) {
                Log.d(TAG.toString, "onFailure-getListOrder: " + t.toString());
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private String convertDateFormat(String inputDate) {
        String outputDate = "";

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;
    }
}

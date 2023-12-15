package com.example.hn_2025_online_shop.view.my_store.Bill;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.databinding.FragmentDetailBillBinding;
import com.example.hn_2025_online_shop.model.response.store.Result;

import java.util.ArrayList;
import java.util.List;

public class DetailBill extends AppCompatActivity  {
    FragmentDetailBillBinding binding;
    List<Result> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDetailBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initController();
    }

    public  void initController(){
        Intent intent = getIntent();
        if(intent != null){
            Bundle bundle = intent.getExtras();
            if(bundle != null){
                Result[] list = (Result[]) bundle.getSerializable("data");
            }

        }
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

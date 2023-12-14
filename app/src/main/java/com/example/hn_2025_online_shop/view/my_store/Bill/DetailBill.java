package com.example.hn_2025_online_shop.view.my_store.Bill;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.databinding.FragmentDetailBillBinding;

public class DetailBill extends AppCompatActivity  {
    FragmentDetailBillBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentDetailBillBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initController();
    }

    public  void initController(){
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

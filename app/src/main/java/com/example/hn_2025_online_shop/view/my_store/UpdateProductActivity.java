package com.example.hn_2025_online_shop.view.my_store;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ActivityUpdateProductBinding;

public class UpdateProductActivity extends AppCompatActivity {
    private ActivityUpdateProductBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
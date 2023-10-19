package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.RegisterMemberSellerBinding;

public class RegisterMemberSeller extends AppCompatActivity {
    private RegisterMemberSellerBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegisterMemberSellerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}

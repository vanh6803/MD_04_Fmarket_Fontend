package com.example.hn_2025_online_shop.view.success_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.hn_2025_online_shop.databinding.ActivityRegisterSuccessBinding;
import com.example.hn_2025_online_shop.view.login.Login;

public class RegisterSuccess extends AppCompatActivity {
    ActivityRegisterSuccessBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSuccess.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterSuccess.this, Login.class);
            startActivity(intent);
            finish();
        });
    }
}
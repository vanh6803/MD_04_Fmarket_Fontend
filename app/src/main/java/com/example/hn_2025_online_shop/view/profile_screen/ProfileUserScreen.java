package com.example.hn_2025_online_shop.view.profile_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutProfileUserBinding;

public class ProfileUserScreen extends AppCompatActivity {
    LayoutProfileUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutProfileUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}
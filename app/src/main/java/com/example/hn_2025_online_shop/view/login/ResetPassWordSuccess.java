package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ResetPassSuccessBinding;

public class ResetPassWordSuccess extends AppCompatActivity {
    ImageView backResetsuccess;
    private ResetPassSuccessBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ResetPassSuccessBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backResetsuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

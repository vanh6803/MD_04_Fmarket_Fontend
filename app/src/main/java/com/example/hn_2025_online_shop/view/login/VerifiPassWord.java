package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.VerifiPassBinding;

public class VerifiPassWord extends AppCompatActivity {
    private VerifiPassBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = VerifiPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.backVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.btnsuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerifiPassWord.this, ResetPassWord.class);
                startActivity(intent);
            }
        });
    }
}

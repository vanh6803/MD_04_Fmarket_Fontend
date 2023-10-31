package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.RegisterBinding;

public class Register extends AppCompatActivity {
    private RegisterBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backRegi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_in_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}

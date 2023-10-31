package com.example.hn_2025_online_shop.view.login;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ForgotPassBinding;

public class ForgotPassWord extends AppCompatActivity {
    private ForgotPassBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ForgotPassBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnVerifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassWord.this, VerifiPassWord.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });

        binding.backfogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}

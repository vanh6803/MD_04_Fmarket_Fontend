package com.example.hn_2025_online_shop.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;

public class ForgotPassWord extends AppCompatActivity {
    Button btnVerifi;
    ImageView backfogot;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_pass);
        btnVerifi= findViewById(R.id.btnVerifi);
        backfogot= findViewById(R.id.backfogot);
        btnVerifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForgotPassWord.this, VerifiPassWord.class);
                startActivity(intent);
            }
        });

        backfogot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

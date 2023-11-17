package com.example.hn_2025_online_shop.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;

public class VerifiPassWord extends AppCompatActivity {
    Button btnsuccess;
    ImageView backVerify;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verifi_pass);
        btnsuccess = findViewById(R.id.btnsuccess);
        backVerify= findViewById(R.id.backVerify);
        backVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnsuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerifiPassWord.this, ResetPassWord.class);
                startActivity(intent);
            }
        });
    }
}

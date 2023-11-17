package com.example.hn_2025_online_shop.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;

public class ResetPassWord extends AppCompatActivity {

    Button btnRepassSuccess;
    ImageView backResetpass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);
        btnRepassSuccess = findViewById(R.id.btnRepassSuccess);
        backResetpass = findViewById(R.id.backResetpass);
        btnRepassSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResetPassWord.this, ResetPassWordSuccess.class);
                startActivity(intent);
            }
        });

        backResetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

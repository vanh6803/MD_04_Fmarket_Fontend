package com.example.hn_2025_online_shop.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.MainActivity;
import com.example.hn_2025_online_shop.R;

public class Login extends AppCompatActivity{
    TextView txtRegister,txtfogotpass;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtRegister= findViewById(R.id.txtRegister);
        txtfogotpass= findViewById(R.id.txtfogotpass);
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
        txtfogotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Login.this, ForgotPassWord.class);
                startActivity(intent2);
            }
        });
    }
}

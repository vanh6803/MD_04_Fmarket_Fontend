package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.view.home_screen.MainActivity;

public class Login extends AppCompatActivity{
    TextView txtRegister,txtfogotpass, load;
    Button btnLogin;
    ProgressBar icon;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        txtRegister= findViewById(R.id.txtRegister);
        txtfogotpass= findViewById(R.id.txtfogotpass);
        btnLogin= findViewById(R.id.btnLogin);
        icon.setVisibility(View.GONE);
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
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }
                }, 3000);
            }
        });
    }
}

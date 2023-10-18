package com.example.hn_2025_online_shop.view.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;

public class splash extends AppCompatActivity {
    ProgressBar icon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        icon = findViewById(R.id.iconLoad);
        icon.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splash.this, Login.class);
                startActivity(intent);
                icon.setVisibility(View.GONE);

            }
        }, 3000);
    }

}

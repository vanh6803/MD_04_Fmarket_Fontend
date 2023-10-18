package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;

public class ResetPassWordSuccess extends AppCompatActivity {
    ImageView backResetsuccess;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_pass_success);
        backResetsuccess= findViewById(R.id.backResetsuccess);
        backResetsuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

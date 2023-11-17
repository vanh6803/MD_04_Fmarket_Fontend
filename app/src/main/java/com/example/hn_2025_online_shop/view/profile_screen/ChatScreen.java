package com.example.hn_2025_online_shop.view.profile_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hn_2025_online_shop.R;

public class ChatScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}
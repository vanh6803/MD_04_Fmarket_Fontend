package com.example.hn_2025_online_shop.view.profile_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutProfileUserBinding;
import com.example.hn_2025_online_shop.ultil.AccountUltil;

public class ProfileUserScreen extends AppCompatActivity {
    LayoutProfileUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutProfileUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setData();
        initController();
    }

    private void initController() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);;
            }
        });
    }

    private void setData() {
        binding.edtUserName.setText(AccountUltil.USER.getUsername());
        Glide.with(this)
                .load(AccountUltil.USER.getAvatar())
                .placeholder(R.drawable.loading)
                .error(R.drawable.avatar1)
                .into(binding.imgAvartar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}
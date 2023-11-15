package com.example.hn_2025_online_shop.view.buy_product;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.hn_2025_online_shop.databinding.DialogDeleteAddressBinding;
import com.example.hn_2025_online_shop.databinding.LayoutEditAddressBinding;

public class UpdateAddressActivity extends AppCompatActivity {
     private LayoutEditAddressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutEditAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.tvXoaDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDeleteAddressBinding binding1 = DialogDeleteAddressBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(UpdateAddressActivity.this);
                dialog.setContentView(binding1.getRoot());
                Window window = dialog.getWindow();
                window.setLayout(900, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                binding1.tvXoa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();


            }
        });
    }
}
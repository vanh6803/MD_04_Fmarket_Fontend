package com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.infor_shop;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageStoreAdapter;
import com.example.hn_2025_online_shop.databinding.LayoutStoreBinding;

public class InforShop  extends AppCompatActivity {
    private LayoutStoreBinding binding;
    ViewPageStoreAdapter adapter;
   int  curentIndex =0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new ViewPageStoreAdapter(getSupportFragmentManager());
        binding.viewPagerHome.setAdapter(adapter);
        binding.textshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curentIndex = 0;
                binding.textshop.setTextColor(getColor(R.color.red));
                binding.textproduct.setTextColor(getColor(R.color.black));
                binding.viewPagerHome.setCurrentItem(curentIndex);
            }
        });

        binding.textproduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curentIndex = 1;
                binding.textproduct.setTextColor(getColor(R.color.red));
                binding.textshop.setTextColor(getColor(R.color.black));
                binding.viewPagerHome.setCurrentItem(curentIndex);
            }
        });


    }
}

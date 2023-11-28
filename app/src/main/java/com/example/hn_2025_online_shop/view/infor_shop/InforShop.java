package com.example.hn_2025_online_shop.view.infor_shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageStoreAdapter;
import com.example.hn_2025_online_shop.databinding.LayoutStoreBinding;
import com.example.hn_2025_online_shop.model.Store;
import com.example.hn_2025_online_shop.model.User;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.chat_message.MessageActivity;

public class InforShop  extends AppCompatActivity {
    private LayoutStoreBinding binding;
    private ViewPageStoreAdapter adapter;
    int  curentIndex =0;
    private Store store;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutStoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initController();
    }

    private void initController() {
        binding.textshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                curentIndex = 0;
                binding.textshop.setTextColor(getColor(R.color.red));
                binding.textproduct.setTextColor(getColor(R.color.black));
                binding.viewPagerHome.setCurrentItem(curentIndex);
            }
        });

        binding.btnChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InforShop.this, MessageActivity.class);
                Bundle bundle = new Bundle();
                User user = new User();
                user.setId(store.getAccount_id());
                user.setAvatar(store.getAvatar());
                user.setUsername(store.getName());
                bundle.putSerializable("receiver_object", user);
                bundle.putSerializable("store", store);
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left );
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
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

    private void initView() {
        adapter = new ViewPageStoreAdapter(getSupportFragmentManager());
        binding.viewPagerHome.setAdapter(adapter);

        Intent intent = getIntent(); // Lấy Intent từ Activity hiện tại
        Bundle bundle = intent.getExtras();
        if (bundle != null) { // Kiểm tra xem Bundle có tồn tại hay không
            store = (Store) bundle.getSerializable("store"); // Ép kiểu đối tượng từ Bundle
            Log.d(TAG.toString, "store: " + store.toString());
        }

        binding.tvNameStore.setText(store.getName());
        Glide.with(InforShop.this)
                .load(store.getAvatar())
                .error(R.drawable.error)
                .into(binding.imgAvartar);
        Glide.with(InforShop.this)
                .load(store.getBanner())
                .error(R.drawable.error)
                .into(binding.bannerStore);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}

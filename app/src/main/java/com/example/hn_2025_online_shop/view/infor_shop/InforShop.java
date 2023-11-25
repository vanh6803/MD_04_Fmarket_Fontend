package com.example.hn_2025_online_shop.view.infor_shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageStoreAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.LayoutStoreBinding;
import com.example.hn_2025_online_shop.model.Store;
import com.example.hn_2025_online_shop.model.User;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.chat_message.MessageActivity;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforShop  extends AppCompatActivity {
    private LayoutStoreBinding binding;
    private ViewPageStoreAdapter adapter;
    private ProgressLoadingDialog dialog;
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
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
        dialog = new ProgressLoadingDialog(this);
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
}

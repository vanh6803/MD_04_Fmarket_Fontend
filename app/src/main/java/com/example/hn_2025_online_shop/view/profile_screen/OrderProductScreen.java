package com.example.hn_2025_online_shop.view.profile_screen;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageOrderAdapter;
import com.example.hn_2025_online_shop.databinding.OrderProductScreenBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class OrderProductScreen extends AppCompatActivity {
    private ViewPageOrderAdapter viewPageHistoryAdapter;
    private OrderProductScreenBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = OrderProductScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initController();
        setTab();
    }

    private void setTab() {
        viewPageHistoryAdapter = new ViewPageOrderAdapter(this);
        binding.viewPagerHistory.setAdapter(viewPageHistoryAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(binding.tabHistory, binding.viewPagerHistory, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Chờ xử lý");
                        break;
                    case 1:
                        tab.setText("Chờ giao hàng");
                        break;
                    case 2:
                        tab.setText("Đã giao hàng");
                        break;
                    case 3:
                        tab.setText("Đã hủy");
                        break;
                }
            }
        });

        mediator.attach();
    }

    private void initController() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });
    }

    private void initView() {
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}

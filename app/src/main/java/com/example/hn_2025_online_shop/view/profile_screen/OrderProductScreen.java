package com.example.hn_2025_online_shop.view.profile_screen;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageHistoryAdapter;
import com.example.hn_2025_online_shop.databinding.OrderProductScreenBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

public class OrderProductScreen extends AppCompatActivity {
    private ViewPageHistoryAdapter viewPageHistoryAdapter;
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
        viewPageHistoryAdapter = new ViewPageHistoryAdapter(this);
        binding.viewPagerHistory.setAdapter(viewPageHistoryAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(binding.tabHistory, binding.viewPagerHistory, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Chờ xử lý");
                        break;
                    case 1:
                        tab.setText("Đang giao");
                        break;
                    case 2:
                        tab.setText("Đã giao");
                        break;
                    case 3:
                        tab.setText("Giao thành công");
                        break;
                    case 4:
                        tab.setText("Đã hủy");
                        break;
                }
            }
        });

        mediator.attach();

        // Đặt số lượng tab hiển thị ban đầu và chế độ cuộn
        binding.tabHistory.setTabMode(TabLayout.MODE_SCROLLABLE);
        binding.tabHistory.setTabGravity(TabLayout.GRAVITY_START);
        binding.tabHistory.setTabMode(TabLayout.MODE_FIXED);
        binding.tabHistory.setTabMode(TabLayout.MODE_AUTO);

        // Đặt số lượng tab hiển thị ban đầu
        binding.viewPagerHistory.setOffscreenPageLimit(3);
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

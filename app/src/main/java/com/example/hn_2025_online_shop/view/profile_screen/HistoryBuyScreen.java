package com.example.hn_2025_online_shop.view.profile_screen;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageHistoryAdapter;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageHomeAdapter;
import com.example.hn_2025_online_shop.databinding.HistoryBuyBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HistoryBuyScreen extends AppCompatActivity {
    private ViewPageHistoryAdapter viewPageHistoryAdapter;
    private HistoryBuyBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HistoryBuyBinding.inflate(getLayoutInflater());
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
                        tab.setText("Chờ xác nhận");
                        break;
                    case 1:
                        tab.setText("Đang giao");
                        break;
                    case 2:
                        tab.setText("Đã giao");
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

    private void setTabTitles(List<String> tabTitles) {
        for (int i = 0; i < tabTitles.size(); i++) {
            TabLayout.Tab tab = binding.tabHistory.getTabAt(i);
            if (tab != null) {
                tab.setText(tabTitles.get(i));
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}

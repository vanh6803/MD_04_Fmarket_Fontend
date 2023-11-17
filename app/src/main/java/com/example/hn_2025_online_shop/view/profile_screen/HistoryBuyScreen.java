package com.example.hn_2025_online_shop.view.profile_screen;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageHistoryAdapter;
import com.example.hn_2025_online_shop.databinding.HistoryBuyBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HistoryBuyScreen extends AppCompatActivity {
    ViewPageHistoryAdapter adapter;
    private HistoryBuyBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HistoryBuyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        List<String> tabTitles = new ArrayList<>();
        tabTitles.add("Đang giao");
        tabTitles.add("Đã giao");
        tabTitles.add("Đã hủy");
        tabTitles.add("Trả hàng");
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });
        adapter = new ViewPageHistoryAdapter(getSupportFragmentManager(), tabTitles);
        binding.viewPagerHistory.setAdapter(adapter);
        binding.tabHistory.setupWithViewPager(binding.viewPagerHistory);
        setTabTitles(tabTitles);

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

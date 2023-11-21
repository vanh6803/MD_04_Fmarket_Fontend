package com.example.hn_2025_online_shop.view.my_store.OrderStore;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.adapter.page_view.ViewPageOrderStoreAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentOrderBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class FragmentOrder extends Fragment {
    private FragmentOrderBinding binding;
    private ViewPageOrderStoreAdapter viewPageOrderStoreAdapter;

    public FragmentOrder() {
        // Required empty public constructor
    }

    public static FragmentOrder newInstance() {
        FragmentOrder fragment = new FragmentOrder();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        initView();
        initController();
        setTab();
    }

    private void setTab() {
        viewPageOrderStoreAdapter = new ViewPageOrderStoreAdapter(getActivity());
        binding.viewPagerOrderStore.setAdapter(viewPageOrderStoreAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(binding.tabOrderStore, binding.viewPagerOrderStore, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Xác nhận");
                        break;
                    case 1:
                        tab.setText("Hàng đang giao");
                        break;
                    case 2:
                        tab.setText("Hàng giao");
                        break;
                    case 3:
                        tab.setText("Hàng hủy");
                        break;
                }
            }
        });

        mediator.attach();
    }

    private void initController() {
    }

    private void initView() {
    }
}
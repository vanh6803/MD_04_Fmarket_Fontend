package com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.infor_shop;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.hn_2025_online_shop.adapter.ProductStoreAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.hn_2025_online_shop.adapter.StoreAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentStoreBinding;
import com.example.hn_2025_online_shop.model.ProductStore;
import com.example.hn_2025_online_shop.model.Store;

import java.util.ArrayList;
import java.util.List;

public class FragmentStore extends Fragment {
    private FragmentStoreBinding binding;
    public Context context;
    List<ProductStore> productStoreList;
    ProductStoreAdapter productStoreAdapter;
    StoreAdapter adapter;
    List<Store> list;


    public FragmentStore() {
    }

    public static FragmentStore newInstance(String param1, String param2) {
        FragmentStore fragment = new FragmentStore();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        list.add(new Store("20%", "200k", "còn 4 ngày"));
        list.add(new Store("10%", "400k", "còn 4 ngày"));
        list.add(new Store("40%", "100k", "còn 4 ngày"));
        list.add(new Store("40%", "220k", "còn 4 ngày"));
        adapter = new StoreAdapter(list,getContext());
        binding.gridStore.setAdapter(adapter);
        productStoreList = new ArrayList<>();
        productStoreList.add(new ProductStore("sản phẩm",111, 111, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11 ));
        productStoreList.add(new ProductStore("sản phẩm",111, 111, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11 ));
        productStoreList.add(new ProductStore("sản phẩm",111, 111, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11 ));
        productStoreList.add(new ProductStore("sản phẩm",111, 111, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11 ));

        productStoreAdapter = new ProductStoreAdapter(productStoreList, getContext());
        binding.recyStore.setAdapter(productStoreAdapter);
    }
}
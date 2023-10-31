package com.example.hn_2025_online_shop.view.product_screen.infor_shop;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductSaleAdapter;
import com.example.hn_2025_online_shop.adapter.StoreAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentStoreBinding;
import com.example.hn_2025_online_shop.model.Product_sale;
import com.example.hn_2025_online_shop.model.Store;
import com.example.hn_2025_online_shop.view.product_screen.DetailProduct;

import java.util.ArrayList;
import java.util.List;

public class FragmentStore extends Fragment {
    private FragmentStoreBinding binding;
    public Context context;
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








    }
}
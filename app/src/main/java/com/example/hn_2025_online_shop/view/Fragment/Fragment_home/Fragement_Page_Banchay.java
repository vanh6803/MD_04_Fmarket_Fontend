package com.example.hn_2025_online_shop.view.Fragment.Fragment_home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductTypeAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentFragementPageBanchayBinding;
import com.example.hn_2025_online_shop.model.Producct_type;
import com.example.hn_2025_online_shop.model.Product_main;

import java.util.ArrayList;
import java.util.List;

public class Fragement_Page_Banchay extends Fragment {
    List<Producct_type> producct_types;
    List<Product_main> product_mains;
    ProductTypeAdapter homeAdapter;

    private FragmentFragementPageBanchayBinding binding;

    public Fragement_Page_Banchay() {
        // Required empty public constructor
    }

    public static Fragement_Page_Banchay newInstance(String param1, String param2) {
        Fragement_Page_Banchay fragment = new Fragement_Page_Banchay();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFragementPageBanchayBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        producct_types= new ArrayList<>();
        product_mains= new ArrayList<>();

        producct_types.add(new Producct_type("Điện thoại / Lap top ",product_mains));
        producct_types.add(new Producct_type("Tivi / Tủ lạnh ",product_mains));
        producct_types.add(new Producct_type("Nồi cơm",product_mains));
        producct_types.add(new Producct_type("Quạt",product_mains));
        homeAdapter= new ProductTypeAdapter(producct_types, getContext());
        binding.recycleProductMain.setAdapter(homeAdapter);
        binding.recycleProductMain.setAdapter(homeAdapter);
    }
}
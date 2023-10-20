package com.example.hn_2025_online_shop.view.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductMainAdapter;
import com.example.hn_2025_online_shop.adapter.ProductTypeAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentHomeBinding;
import com.example.hn_2025_online_shop.model.Producct_type;
import com.example.hn_2025_online_shop.model.Product_main;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends Fragment {

    private FragmentHomeBinding binding;
    List<Producct_type> producct_types;
    ProductTypeAdapter homeAdapter;
    List<Product_main> product_mains;
    public Fragment_Home() {
    }

    public static Fragment_Home newInstance() {
        Fragment_Home fragment = new Fragment_Home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<SlideModel> list  = new ArrayList<>();
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        binding.sliderHome.setImageList(list, ScaleTypes.FIT);


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
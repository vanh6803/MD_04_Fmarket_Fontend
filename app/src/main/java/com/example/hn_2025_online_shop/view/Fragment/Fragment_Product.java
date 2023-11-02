package com.example.hn_2025_online_shop.view.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductTypeAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentHomeBinding;
import com.example.hn_2025_online_shop.databinding.FragmentProductBinding;
import com.example.hn_2025_online_shop.model.Producct_type;
import com.example.hn_2025_online_shop.model.Product_main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fragment_Product extends Fragment {

    List<Producct_type> producct_types;
    List<Product_main> product_mains;

    ProductTypeAdapter productAdapter2;
    private FragmentProductBinding binding;

    public static Fragment_Product newInstance() {
        Fragment_Product fragment = new Fragment_Product();
        return fragment;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //init view và các action vào đây
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        product_mains= new ArrayList<>();
        producct_types= new ArrayList<>();
        for (int i = 1; i< 4; i++){
            producct_types.add(new Producct_type("1","Sản phẩm bán chạy" + i , "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg"));
        }
        productAdapter2= new ProductTypeAdapter(producct_types, getContext());
        binding.recycleProduct.setAdapter(productAdapter2);
        ArrayList<SlideModel> list  = new ArrayList<>();
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        binding.sliderProduct.setImageList(list, ScaleTypes.FIT);
    }
}
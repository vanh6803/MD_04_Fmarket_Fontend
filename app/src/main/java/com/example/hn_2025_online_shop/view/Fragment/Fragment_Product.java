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
import com.example.hn_2025_online_shop.model.Producct_type;
import com.example.hn_2025_online_shop.model.Product_main;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Product extends Fragment {
    ImageSlider imageSlider ;
    RecyclerView recycleProduct;

    List<Producct_type> producct_types;
    List<Product_main> product_mains;

    ProductTypeAdapter productAdapter2;


    public static Fragment_Product newInstance() {
        Fragment_Product fragment = new Fragment_Product();
        return fragment;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__product, container, false);
        imageSlider = view.findViewById(R.id.sliderProduct);
        recycleProduct = view.findViewById(R.id.recycleProduct);
        product_mains= new ArrayList<>();
        producct_types= new ArrayList<>();
        for (int i = 1; i< 4; i++){
            producct_types.add(new Producct_type("Sản phẩm bán chạy"+ i ,product_mains));
        }

        for (int i = 0 ; i< producct_types.size(); i++){
            for (int j = 0; j< producct_types.get(i).getList().size(); j ++){
                if (producct_types.get(i).getTitle() == "Sản phẩm bán chạy2"){
                    Log.d("vv", "onCreateView: aaaaaaaaaaaaaaaaa");
                }
            }
        }

        productAdapter2= new ProductTypeAdapter(producct_types, getContext());
        recycleProduct.setAdapter(productAdapter2);



        ArrayList<SlideModel> list  = new ArrayList<>();
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        imageSlider.setImageList(list, ScaleTypes.FIT);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //init view và các action vào đây
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
package com.example.hn_2025_online_shop.view.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductAdapter;
import com.example.hn_2025_online_shop.model.Product_main;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends Fragment {
    GridView grid_view;
    List<Product_main> list;
    ProductAdapter productAdapter;
    @Nullable
    @Override
    public View onCreateView(     @Nullable LayoutInflater inflater,     @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__home, container, false);
        initView(view);
        return view;
    }


    public  void initView(View view){
        grid_view= view.findViewById(R.id.grid_view);
        list = new ArrayList<>();
        for (int i = 1 ; i< 24; i++){
            list.add(new Product_main("https://d1hjkbq40fs2x4.cloudfront.net/2016-01-31/files/1045.jpg", "ốp"+i));
        }
        productAdapter= new ProductAdapter(view.getContext(), list);
        grid_view.setAdapter(productAdapter);
    }
}
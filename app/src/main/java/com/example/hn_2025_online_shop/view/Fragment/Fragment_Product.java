package com.example.hn_2025_online_shop.view.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductMainAdapter;
import com.example.hn_2025_online_shop.adapter.ProductTypeAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentHomeBinding;
import com.example.hn_2025_online_shop.databinding.FragmentProductBinding;
import com.example.hn_2025_online_shop.model.OptionProduct;
import com.example.hn_2025_online_shop.model.Producct_type;
import com.example.hn_2025_online_shop.model.Product_main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Fragment_Product extends Fragment {

    private List<Product_main> listProdcut;
    private ProductMainAdapter adapter;
    private Product_main product_main;
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
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.recycleProduct.setLayoutManager(layoutManager);

        listProdcut = new ArrayList<>();
        listProdcut.add(new Product_main("1", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));
        listProdcut.add(new Product_main("2", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));
        listProdcut.add(new Product_main("3", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));
        listProdcut.add(new Product_main("4", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));
        listProdcut.add(new Product_main("5", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));


        adapter = new ProductMainAdapter(getContext());
        adapter.setData(listProdcut);
        binding.recycleProduct.setAdapter(adapter);
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
package com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.infor_shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.hn_2025_online_shop.adapter.ProductStoreAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentProductStoreBinding;
import com.example.hn_2025_online_shop.model.ProductStore;

import java.util.ArrayList;
import java.util.List;

public class FragmentProductStore extends Fragment {
    private FragmentProductStoreBinding binding;
    private List<ProductStore> list;
    ProductStoreAdapter adapter;





    public FragmentProductStore() {
    }
    public static FragmentProductStore newInstance(String param1, String param2) {
        FragmentProductStore fragment = new FragmentProductStore();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentProductStoreBinding.inflate(getLayoutInflater());        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.rcvProductStore.setLayoutManager(layoutManager);
        list = new ArrayList<>();
        list.add(new ProductStore("sản phẩm",111, 111, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11 ));
        list.add(new ProductStore("sản phẩm",111, 111, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11 ));
        list.add(new ProductStore("sản phẩm",111, 111, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11 ));
        list.add(new ProductStore("sản phẩm",111, 111, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11 ));
        adapter = new ProductStoreAdapter(list, getContext());
        binding.rcvProductStore.setAdapter(adapter);
    }
}
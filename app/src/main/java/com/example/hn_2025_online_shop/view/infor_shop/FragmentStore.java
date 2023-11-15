package com.example.hn_2025_online_shop.view.infor_shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductAdapter;


import com.example.hn_2025_online_shop.adapter.StoreAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentStoreBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.Voucher;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;

import java.util.ArrayList;
import java.util.List;

public class FragmentStore extends Fragment implements ObjectUtil {
    private FragmentStoreBinding binding;
    public Context context;
    List<Product> productList;
    ProductAdapter productAdapter;
    StoreAdapter adapter;
    List<Voucher> list;


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
        list.add(new Voucher("20%", "200k", "còn 4 ngày"));
        list.add(new Voucher("10%", "400k", "còn 4 ngày"));
        list.add(new Voucher("40%", "100k", "còn 4 ngày"));
        list.add(new Voucher("40%", "220k", "còn 4 ngày"));
        adapter = new StoreAdapter(list,getContext());
        binding.gridStore.setAdapter(adapter);
        productList = new ArrayList<>();
        productList.add(new Product("1", "sản phẩm", true, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11100000, 5.0, 1 ));
        productList.add(new Product("2", "sản phẩm", true, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11100000, 5.0, 1 ));
        productList.add(new Product("3", "sản phẩm", true, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11100000, 5.0, 1 ));
        productList.add(new Product("4", "sản phẩm", true, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11100000, 5.0, 1 ));


        productAdapter = new ProductAdapter(getActivity(), productList, this);
        binding.recyStore.setAdapter(productAdapter);
    }

    @Override
    public void onclickObject(Object object) {
        Product product = (Product) object;
        String id = product.getId();
        Intent intent = new Intent(getActivity(), DetailProduct.class);
        intent.putExtra("id_product", id);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
    }
}
package com.example.hn_2025_online_shop.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductFavoriteAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentFavoriteBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductFind;
import com.example.hn_2025_online_shop.view.chat_message.ChatActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentFavorite extends Fragment {
    private FragmentFavoriteBinding binding;
    ProductFavoriteAdapter adapter;

    List<ProductFind> list;



    @NonNull
    public static FragmentFavorite newInstance() {
        FragmentFavorite fragment = new FragmentFavorite();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(  @Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initController();
    }

    private void initController() {
        binding.imgChat.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
        });
    }


    public  void initView(){
        list = new ArrayList<>();
        for(int i = 0; i<10 ; i++){
            list.add(new ProductFind("Điện thoại "+ i, "https://res.cloudinary.com/dwxavjnvc/image/upload/v1699708633/Product/csddbopt4d1xeeivy8bc.png"));
        }
        adapter = new ProductFavoriteAdapter(getContext(), list);
        binding.recycleView.setAdapter(adapter);
    }
}
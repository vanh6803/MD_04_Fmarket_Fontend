package com.example.hn_2025_online_shop.view.product_screen.infor_shop;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.FragmentProductBinding;
import com.example.hn_2025_online_shop.databinding.FragmentProductStoreBinding;
import com.example.hn_2025_online_shop.databinding.FragmentStoreBinding;

public class FragmentProductStore extends Fragment {
    private FragmentProductStoreBinding binding;

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

    }
}
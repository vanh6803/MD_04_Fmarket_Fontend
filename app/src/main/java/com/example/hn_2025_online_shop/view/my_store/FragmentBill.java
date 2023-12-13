package com.example.hn_2025_online_shop.view.my_store;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.BillAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentBillBinding;
import com.example.hn_2025_online_shop.model.response.Bill;

import java.util.ArrayList;
import java.util.List;

public class FragmentBill extends Fragment {
    FragmentBillBinding binding;

    List<Bill> list;
    BillAdapter adapter;



    public FragmentBill() {
        // Required empty public constructor
    }

    public static FragmentBill newInstance() {
        FragmentBill fragment = new FragmentBill();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBillBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        for (int i = 0; i< 10; i++){
            list.add(new Bill("#06317231","Điện thoại Iphone 15 Promax (2023) , phiên bản giới hạn",123123123 , "1"+ i+ "/12/2023"));
        }
        adapter = new BillAdapter(list, getContext());
        binding.recycleView.setAdapter(adapter);
    }
}
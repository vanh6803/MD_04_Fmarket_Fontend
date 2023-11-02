package com.example.hn_2025_online_shop.view.my_store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;


public class Fragment_KhoSanPham extends Fragment {





    public Fragment_KhoSanPham() {

    }



    public static Fragment_KhoSanPham newInstance() {
        Fragment_KhoSanPham fragment = new Fragment_KhoSanPham();

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
        return inflater.inflate(R.layout.fragment__kho_san_pham, container, false);
    }
}
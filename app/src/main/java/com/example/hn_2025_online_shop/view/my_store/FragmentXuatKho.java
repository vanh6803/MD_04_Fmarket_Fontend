package com.example.hn_2025_online_shop.view.my_store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;


public class FragmentXuatKho extends Fragment {




    public FragmentXuatKho() {
        // Required empty public constructor
    }


    public static FragmentXuatKho newInstance() {
        FragmentXuatKho fragment = new FragmentXuatKho();

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
        return inflater.inflate(R.layout.fragment_xuat_kho, container, false);
    }
}
package com.example.hn_2025_online_shop.view.my_store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_DonDatHang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_DonDatHang extends Fragment {



    public Fragment_DonDatHang() {
        // Required empty public constructor
    }

    public static Fragment_DonDatHang newInstance() {
        Fragment_DonDatHang fragment = new Fragment_DonDatHang();

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
        return inflater.inflate(R.layout.fragment__don_dat_hang, container, false);
    }
}
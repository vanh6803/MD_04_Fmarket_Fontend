package com.example.hn_2025_online_shop.view.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;

public class Fragment_Notification extends Fragment {

    public static Fragment_Notification newInstance() {


        Fragment_Notification fragment = new Fragment_Notification();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__notification, container, false);
    }
}
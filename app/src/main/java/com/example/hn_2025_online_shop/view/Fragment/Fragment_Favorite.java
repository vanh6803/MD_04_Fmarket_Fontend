package com.example.hn_2025_online_shop.view.Fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;

public class Fragment_Favorite extends Fragment {

    @Nullable
    @Override
    public View onCreateView(  @Nullable LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__favorite, container, false);
    }
}
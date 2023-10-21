package com.example.hn_2025_online_shop.view.Fragment.Fragment_home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hn_2025_online_shop.R;
public class Fragment_Page_Danggiamgia extends Fragment {
    public static Fragment_Page_Danggiamgia newInstance(String param1, String param2) {
        Fragment_Page_Danggiamgia fragment = new Fragment_Page_Danggiamgia();;
        return fragment;
    }

    public Fragment_Page_Danggiamgia() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__page__danggiamgia, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
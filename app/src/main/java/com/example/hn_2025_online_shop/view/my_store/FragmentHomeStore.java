package com.example.hn_2025_online_shop.view.my_store;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentHomeStore#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentHomeStore extends Fragment {





    public FragmentHomeStore() {
        // Required empty public constructor
    }

    public static FragmentHomeStore newInstance() {
        FragmentHomeStore fragment = new FragmentHomeStore();
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
        return inflater.inflate(R.layout.fragment_home_store, container, false);
    }
}
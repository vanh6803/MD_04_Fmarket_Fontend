
package com.example.hn_2025_online_shop.view.fragment.fragment_home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
public class FragmentPageOutstanding extends Fragment {

    public FragmentPageOutstanding() {
    }

    public static FragmentPageOutstanding newInstance(String param1, String param2) {
        FragmentPageOutstanding fragment = new FragmentPageOutstanding();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_page_outstanding, container, false);
    }
}
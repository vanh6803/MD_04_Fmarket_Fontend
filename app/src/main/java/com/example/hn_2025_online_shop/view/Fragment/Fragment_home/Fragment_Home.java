package com.example.hn_2025_online_shop.view.Fragment.Fragment_home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductTypeAdapter;
import com.example.hn_2025_online_shop.adapter.ViewPageHomeAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentHomeBinding;
import com.example.hn_2025_online_shop.model.Producct_type;
import com.example.hn_2025_online_shop.model.Product_main;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Home extends Fragment {

    private FragmentHomeBinding binding;
    ViewPageHomeAdapter adapter;

    public Fragment_Home() {
    }

    public static Fragment_Home newInstance() {
        Fragment_Home fragment = new Fragment_Home();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<SlideModel> list  = new ArrayList<>();
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        binding.sliderHome.setImageList(list, ScaleTypes.FIT);
        List<String> tabTitles = new ArrayList<>();
        tabTitles.add("Bán chạy");
        tabTitles.add("Đang giảm giá");
        tabTitles.add("Nổi bật");
        adapter = new ViewPageHomeAdapter(getActivity().getSupportFragmentManager(),tabTitles);
        binding.viewPagerHome.setAdapter(adapter);
        binding.tabHome.setupWithViewPager(binding.viewPagerHome);
        setTabTitles(tabTitles);
    }
    private void setTabTitles(List<String> tabTitles) {
        for (int i = 0; i < tabTitles.size(); i++) {
            TabLayout.Tab tab = binding.tabHome.getTabAt(i);
            if (tab != null) {
                tab.setText(tabTitles.get(i));
            }
        }
    }
}
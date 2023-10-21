package com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.HistoryBuyAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentPageDanggiaoBinding;
import com.example.hn_2025_online_shop.model.HistoryBuy;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_home.Fragment_Home;

import java.util.ArrayList;
import java.util.List;

public class FragmentPageDanggiao extends Fragment {
    private FragmentPageDanggiaoBinding binding;

    List<HistoryBuy> list;

    HistoryBuyAdapter adapter;
    public static FragmentPageDanggiao newInstance(String param1, String param2) {
        FragmentPageDanggiao fragment = new FragmentPageDanggiao();
        return fragment;
    }

    public static Fragment newInstance() {
        FragmentPageDanggiao fragment = new FragmentPageDanggiao();
            return fragment;
        }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPageDanggiaoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        for (int i = 0; i< 4; i++){
            list.add(new HistoryBuy("Ốp lưng iPhone 14 Pro Spigen Liquid Air màu Deep Purple", "123.123", "123", "màu đỏ",2,"https://kenh14cdn.com/203336854389633024/2022/11/29/photo-2-1669724356014553229842.jpg"));
        }
        adapter = new HistoryBuyAdapter(list, getContext());
        binding.recycle.setAdapter(adapter);
    }
}
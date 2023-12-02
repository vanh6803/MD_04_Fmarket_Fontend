package com.example.hn_2025_online_shop.view.my_store;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductRevenueAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentStatisticalBinding;
import com.example.hn_2025_online_shop.model.ProductRevenue;

import java.util.ArrayList;
import java.util.List;


public class FragmentStatistical extends Fragment {
    private FragmentStatisticalBinding binding;
    private List<ProductRevenue> list;
    private ProductRevenueAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match

    public FragmentStatistical() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentStatistical newInstance() {
        FragmentStatistical fragment = new FragmentStatistical();

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
        binding = FragmentStatisticalBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        list.add(new ProductRevenue("https://gaixinhdep.net/wp-content/uploads/2023/10/gai-xinh-lo-num-vu.jpg", "Em Ngân", 500000));
        list.add(new ProductRevenue("https://gaixinhdep.net/wp-content/uploads/2023/10/gai-xinh-lo-vu-khong-che.jpg", "Em Loan", 500000));
        list.add(new ProductRevenue("https://gaixinhdep.net/wp-content/uploads/2023/10/gai-xinh-sexy-lo-vu.jpg", "Em Trinh", 500000));
        list.add(new ProductRevenue("https://gaixinhdep.net/wp-content/uploads/2023/10/anh-gai-xinh-lo-num-vu.jpg", "Em Hồng", 500000));
        list.add(new ProductRevenue("https://gaixinhdep.net/wp-content/uploads/2023/10/anh-gai-xinh-vu-to-lo-ti.jpg", "Em Đào", 500000));
        adapter = new ProductRevenueAdapter(getContext(), list);
        binding.rcvProductRevenue.setAdapter(adapter);
    }
}
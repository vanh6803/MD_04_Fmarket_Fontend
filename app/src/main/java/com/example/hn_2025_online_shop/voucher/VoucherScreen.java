package com.example.hn_2025_online_shop.voucher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.StoreAdapter;
import com.example.hn_2025_online_shop.adapter.VoucherSCAdapter;
import com.example.hn_2025_online_shop.databinding.LayoutVoucherBinding;
import com.example.hn_2025_online_shop.model.Store;

import java.util.ArrayList;
import java.util.List;

public class VoucherScreen extends AppCompatActivity {
    LayoutVoucherBinding binding;
    List<Store> list;
    VoucherSCAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        list.add(new Store("111", "11" , "11"));
        list.add(new Store("111", "11" , "11"));
        list.add(new Store("111", "11" , "11"));
        list.add(new Store("111", "11" , "11"));
        list.add(new Store("111", "11" , "11"));
        list.add(new Store("111", "11" , "11"));
        list.add(new Store("111", "11" , "11"));
        list.add(new Store("111", "11" , "11"));
        list.add(new Store("111", "11" , "11"));
        adapter = new VoucherSCAdapter(this, list);
        binding.rcvVoucherStore.setAdapter(adapter);
    }
}
package com.example.hn_2025_online_shop.view.voucher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hn_2025_online_shop.adapter.VoucherSCAdapter;
import com.example.hn_2025_online_shop.databinding.LayoutVoucherBinding;
import com.example.hn_2025_online_shop.model.Voucher;

import java.util.ArrayList;
import java.util.List;

public class VoucherScreen extends AppCompatActivity {
    LayoutVoucherBinding binding;
    List<Voucher> list;
    VoucherSCAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutVoucherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        list = new ArrayList<>();
        list.add(new Voucher("111", "11" , "11"));
        list.add(new Voucher("111", "11" , "11"));
        list.add(new Voucher("111", "11" , "11"));
        list.add(new Voucher("111", "11" , "11"));
        list.add(new Voucher("111", "11" , "11"));
        list.add(new Voucher("111", "11" , "11"));
        list.add(new Voucher("111", "11" , "11"));
        list.add(new Voucher("111", "11" , "11"));
        list.add(new Voucher("111", "11" , "11"));
        adapter = new VoucherSCAdapter(this, list);
        binding.rcvVoucherStore.setAdapter(adapter);
    }
}
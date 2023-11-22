package com.example.hn_2025_online_shop.view.voucher;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

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
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list = new ArrayList<>();
        list.add(new Voucher("Miễn phí vận chuyển", "11" , "11", "12%"));
        list.add(new Voucher("Giảm 5% với đơn hàng trên 200k", "11" , "11", "5%"));
        list.add(new Voucher("Miễn phí vận chuyển", "11" , "11", "12%"));
        list.add(new Voucher("Giảm 5% với đơn hàng trên 100k", "11" , "11", "12%"));
        list.add(new Voucher("Giảm 5% với đơn hàng trên 100k", "11" , "11", "12%"));
        list.add(new Voucher("Miễn phí vận chuyển", "11" , "11", "12%"));
        list.add(new Voucher("Giảm 5% với đơn hàng trên 100k", "11" , "11", "12%"));
        adapter = new VoucherSCAdapter(this, list);
        binding.rcvVoucherStore.setAdapter(adapter);
    }
}
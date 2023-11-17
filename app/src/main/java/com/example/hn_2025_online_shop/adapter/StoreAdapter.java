package com.example.hn_2025_online_shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.hn_2025_online_shop.databinding.IteamVoucherBinding;
import com.example.hn_2025_online_shop.model.Voucher;

import java.util.List;

public class StoreAdapter extends BaseAdapter {
    List<Voucher> list;
    Context context;
    private IteamVoucherBinding binding;

    public StoreAdapter(List<Voucher> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") IteamVoucherBinding binding = IteamVoucherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.date.setText(list.get(position).getDate());
        binding.sale.setText(list.get(position).getNameVoucher());
        binding.price.setText(list.get(position).getPrice());
        return binding.getRoot();

    }
}

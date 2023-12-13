package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.databinding.IteamVoucherBinding;
import com.example.hn_2025_online_shop.databinding.LayoutIteamOptionProductBinding;
import com.example.hn_2025_online_shop.model.Voucher;

import java.util.List;

public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.VoucherViewHolder> {
    List<Voucher> list;
    Context context;
    private IteamVoucherBinding binding;

    public StoreAdapter(List<Voucher> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void setListVoucher(List<Voucher> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IteamVoucherBinding binding = IteamVoucherBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new VoucherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreAdapter.VoucherViewHolder holder, int position) {
        Voucher voucher = list.get(position);
        holder.binding.sale.setText(voucher.getSale());
        holder.binding.name.setText(voucher.getNameVoucher());
        holder.binding.price.setText(voucher.getPrice());
        holder.binding.date.setText("Còn: " + voucher.getDate());
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return  list.size();
        }
        return 0;
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder {
        private IteamVoucherBinding binding;
       public VoucherViewHolder(IteamVoucherBinding binding) {
           super(binding.getRoot());
           this.binding = binding;
       }
   }
}

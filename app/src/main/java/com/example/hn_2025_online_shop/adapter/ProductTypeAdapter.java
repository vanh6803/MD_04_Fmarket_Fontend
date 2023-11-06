package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.databinding.ItemProductTypeBinding;
import com.example.hn_2025_online_shop.model.ProductType;

import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder> {
    List<ProductType> list;
    Context context;
    public void setListProductType(List<ProductType> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ProductTypeAdapter(List<ProductType> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductTypeBinding binding = ItemProductTypeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductType producctType = list.get(position);
        holder.binding.titleType.setText(producctType.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemProductTypeBinding binding;
        public ViewHolder(ItemProductTypeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}


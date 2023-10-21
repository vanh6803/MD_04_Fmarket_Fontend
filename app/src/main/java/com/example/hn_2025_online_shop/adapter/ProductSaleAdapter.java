package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ItemProductSaleBinding;
import com.example.hn_2025_online_shop.model.Product_sale;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductSaleAdapter extends RecyclerView.Adapter<ProductSaleAdapter.ViewHolder> {
    List<Product_sale> list;
    Context context;

    public ProductSaleAdapter(List<Product_sale> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductSaleBinding binding = ItemProductSaleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product_sale productSale = list.get(position);
        holder.binding.nameSale.setText(productSale.getName());
        holder.binding.price.setText(productSale.getPrice_sale().toString());
        holder.binding.textsale.setText(productSale.getPrice().toString());
        Picasso.get().load(list.get(position).getImg()).into(holder.binding.imgSale);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{

        private ItemProductSaleBinding binding;
        public ViewHolder(ItemProductSaleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.textsale.setPaintFlags(binding.textsale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}

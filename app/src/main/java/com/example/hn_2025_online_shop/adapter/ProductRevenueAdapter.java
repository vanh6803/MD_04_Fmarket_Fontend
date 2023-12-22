package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutIteamThongkeBinding;
import com.example.hn_2025_online_shop.databinding.LayoutItemProductSaleBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductDetailsSoldQuantity;
import com.example.hn_2025_online_shop.model.ProductRevenue;
import com.example.hn_2025_online_shop.model.ProductWithSoldQuantity;

import java.text.DecimalFormat;
import java.util.List;

public class ProductRevenueAdapter extends RecyclerView.Adapter<ProductRevenueAdapter.ProductRevenueViewHolder> {
    private Context context;
    private List<ProductWithSoldQuantity> list;

    public ProductRevenueAdapter(Context context, List<ProductWithSoldQuantity> list) {
        this.context = context;
        this.list = list;
    }
    public void setListProductRevenue(List<ProductWithSoldQuantity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductRevenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutIteamThongkeBinding binding = LayoutIteamThongkeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductRevenueViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductRevenueViewHolder holder, int position) {
        ProductWithSoldQuantity product = list.get(position);
        Glide.with(context).load(product.getProductDetails().getImage()).error(R.drawable.error).into(holder.binding.imgProduct);
        holder.binding.tvNameProduct.setText(product.getProductDetails().getName());
        holder.binding.tvDoanhThu.setText(product.getTotalSoldQuantity()+"");
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return  list.size();
        }
        return 0;
    }

    public class ProductRevenueViewHolder extends RecyclerView.ViewHolder {
        private LayoutIteamThongkeBinding binding;
        public ProductRevenueViewHolder(LayoutIteamThongkeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

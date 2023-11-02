package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.databinding.ItemProductSaleBinding;
import com.example.hn_2025_online_shop.databinding.LayoutIteamProductStoreBinding;
import com.example.hn_2025_online_shop.model.ProductStore;
import com.example.hn_2025_online_shop.model.Product_sale;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.infor_shop.FragmentStore;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductStoreAdapter extends RecyclerView.Adapter<ProductStoreAdapter.ProductStoreViewHolder> {
    private List<ProductStore> list;
    private Context context;

    public ProductStoreAdapter(List<ProductStore> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutIteamProductStoreBinding binding = LayoutIteamProductStoreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductStoreViewHolder(binding);
    }
    @Override
    public int getItemCount() {
        if(list != null){
            return  list.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductStoreViewHolder holder, int position) {
        ProductStore productStore = list.get(position);
        holder.binding.nameSale.setText(productStore.getName());
        holder.binding.price.setText(productStore.getPrice_sale().toString());
        holder.binding.textsale.setText(productStore.getPrice().toString());
        Glide.with(context).load(productStore.getImg()).into(holder.binding.imgSale);
        holder.binding.tvDaBan.setText(productStore.getQuanity_sold() + "");

    }

    public class ProductStoreViewHolder extends RecyclerView.ViewHolder {
        private LayoutIteamProductStoreBinding binding;

        public ProductStoreViewHolder(LayoutIteamProductStoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.textsale.setPaintFlags(binding.textsale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}

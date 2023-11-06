package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.databinding.LayoutIteamProductBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;


    public ProductAdapter(Context context, List<Product> productList) {
        this.productList = productList;
        this.context = context;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutIteamProductBinding binding = LayoutIteamProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }
    @Override
    public int getItemCount() {
        if(productList != null){
            return  productList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.binding.nameProduct.setText(product.getName());
        holder.binding.price.setText(product.getPrice() + "");
        Glide.with(context).load(product.getImage()).into(holder.binding.imgProduct);

        holder.binding.lnProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailProduct.class);
                context.startActivity(intent);
            }
        });
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private LayoutIteamProductBinding binding;

        public ProductViewHolder(LayoutIteamProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
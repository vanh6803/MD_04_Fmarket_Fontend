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
import com.example.hn_2025_online_shop.model.ProductRevenue;

import java.text.DecimalFormat;
import java.util.List;

public class ProductRevenueAdapter extends RecyclerView.Adapter<ProductRevenueAdapter.ProductRevenueViewHolder> {
    private Context context;
    private List<ProductRevenue> list;

    public ProductRevenueAdapter(Context context, List<ProductRevenue> list) {
        this.context = context;
        this.list = list;
    }
    public void setListProductRevenue(List<ProductRevenue> list) {
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
        ProductRevenue product = list.get(position);
        Glide.with(context)
                .load(product.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.binding.imgProduct);
        holder.binding.tvNameProduct.setText(product.getNameProduct());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.binding.tvDoanhThu.setText(df.format(product.getRevenue()) + " đ");
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

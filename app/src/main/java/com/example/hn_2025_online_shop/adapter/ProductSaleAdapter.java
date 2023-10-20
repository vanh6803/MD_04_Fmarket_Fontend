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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_sale,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product_sale productSale = list.get(position);
        holder.name.setText(productSale.getName());
        holder.priceSale.setText(productSale.getPrice_sale().toString());
        holder.price.setText(productSale.getPrice().toString());
        Picasso.get().load(list.get(position).getImg()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        TextView price, priceSale, name;
        ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            img = itemView.findViewById(R.id.imgSale);
            priceSale = itemView.findViewById(R.id.textsale);
            name = itemView.findViewById(R.id.nameSale);
            priceSale.setPaintFlags(priceSale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }
}

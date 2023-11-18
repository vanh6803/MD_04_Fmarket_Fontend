package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutIteamOrderBinding;
import com.example.hn_2025_online_shop.model.Order;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>{
    private List<Order> list;
    private Context context;

    public OrderAdapter( Context context, List<Order> list) {
        this.context = context;
        this.list = list;
    }

    public void setListOrder(List<Order> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutIteamOrderBinding binding = LayoutIteamOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = list.get(position);
        Glide.with(context).load(order.getImage()).error(R.drawable.error).into(holder.binding.imgProduct);
        holder.binding.tvNameProduct.setText(order.getNameProduct());
        holder.binding.tvNameUser.setText(order.getNameUser());
        holder.binding.date.setText(order.getDate());
        holder.binding.tvquantity.setText(order.getQuantity());
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return  list.size();
        }
        return 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        LayoutIteamOrderBinding binding;
        public OrderViewHolder(LayoutIteamOrderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

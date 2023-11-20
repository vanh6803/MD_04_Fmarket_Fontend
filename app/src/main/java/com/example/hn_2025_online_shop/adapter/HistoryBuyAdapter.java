package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.databinding.ItemHistoryBuyBinding;
import com.example.hn_2025_online_shop.model.HistoryBuy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HistoryBuyAdapter extends RecyclerView.Adapter<HistoryBuyAdapter.ViewHolder> {
    List<HistoryBuy> list;
    Context context;


    public HistoryBuyAdapter(List<HistoryBuy> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemHistoryBuyBinding binding = ItemHistoryBuyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HistoryBuy historyBuy = list.get(position);
        holder.binding.txtNamehis.setText(historyBuy.getName());
        holder.binding.txtnametypeHis.setText(historyBuy.getTypeColor());
        holder.binding.txtPriceHis.setText(historyBuy.getPrice());
        Picasso.get().load(list.get(position).getImg()).into(holder.binding.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final ItemHistoryBuyBinding binding;

        public ViewHolder(ItemHistoryBuyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

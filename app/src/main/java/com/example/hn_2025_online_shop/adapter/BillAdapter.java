package com.example.hn_2025_online_shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ItemBillBinding;
import com.example.hn_2025_online_shop.model.response.Bill;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private List<Bill> list;
    private Context context;


    public BillAdapter(List<Bill> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBillBinding binding = ItemBillBinding.inflate(LayoutInflater.from(parent.getContext()), parent , false);
        return new ViewHolder(binding);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Bill bill = list.get(position);
        holder.binding.stt.setText(String.valueOf(position + 1));
        holder.binding.code.setText(bill.getCode());
        holder.binding.content.setText(bill.getNameBill());
        holder.binding.date.setText(bill.getDateTime());
        holder.binding.price.setText(String.valueOf(bill.getPrice()));
        if(position%2 == 0){
            holder.binding.item.setBackgroundColor(0xffffffff);
        }else {
            holder.binding.item.setBackgroundColor(0xff0CC0DF);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        ItemBillBinding binding ;

        public ViewHolder(ItemBillBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

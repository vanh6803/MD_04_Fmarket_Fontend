package com.example.hn_2025_online_shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.databinding.ItemBillBinding;
import com.example.hn_2025_online_shop.model.Order;
import com.example.hn_2025_online_shop.model.response.store.ProductOrder;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private List<Order> list;
    private Context context;
    private ObjectUtil objectUtil;



    public BillAdapter(List<Order> list, Context context, ObjectUtil objectUtil) {
        this.list = list;
        this.context = context;
        this.objectUtil = objectUtil;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBillBinding binding = ItemBillBinding.inflate(LayoutInflater.from(parent.getContext()), parent , false);
        return new ViewHolder(binding);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order bill = list.get(position);
        holder.binding.stt.setText(String.valueOf(position + 1));
        holder.binding.code.setText("#"+bill.getUser().getId());
        holder.binding.content.setText(bill.getProductsOrder().get(position).getOptionProduct().getProduct().getName().toString().substring(0,20) +
                "màu" +bill.getProductsOrder().get(position).getOptionProduct().getNameColor() +"...");
//        holder.binding.date.setText(bill.getProductsOrder().get(position).getOptionProduct().getId());
        holder.binding.price.setText(String.valueOf(bill.getTotalPrice()));
        if(position%2 == 0){
            holder.binding.item.setBackgroundColor(0xffffffff);
        }else {
            holder.binding.item.setBackgroundColor(0xffE0FFFF);
        }

        holder.binding.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objectUtil.onclickObject(bill);
            }
        });

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

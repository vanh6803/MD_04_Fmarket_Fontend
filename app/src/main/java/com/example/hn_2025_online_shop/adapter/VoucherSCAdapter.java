package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.model.Store;
import com.example.hn_2025_online_shop.voucher.VoucherScreen;

import java.util.List;

public class VoucherSCAdapter extends RecyclerView.Adapter<VoucherSCAdapter.VoucherViewHolder>{
    private Context context;


    public VoucherSCAdapter(Context context, List<Store> list) {
        this.context = context;
        this.list = list;
    }

    private List<Store> list;




    @NonNull
    @Override
    public VoucherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_voucher,parent,false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherViewHolder holder, int position) {
        Store store = list.get(position);
        holder.tvSale.setText(store.getTextSale());
        holder.tvprice.setText(store.getPrice());
        holder.tvDate.setText(store.getDate());

    }

    @Override
    public int getItemCount() {
        if(list != null){
            return  list.size();
        }
        return 0;
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder {
        TextView tvSale, tvprice, tvDate;


        public VoucherViewHolder(@NonNull View itemView) {
            super(itemView);
            tvprice = itemView.findViewById(R.id.price);
            tvSale = itemView.findViewById(R.id.sale);
            tvDate = itemView.findViewById(R.id.date);
        }
    }
}

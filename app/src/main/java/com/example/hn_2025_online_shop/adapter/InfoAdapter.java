package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.databinding.LayoutItemAddressBinding;
import com.example.hn_2025_online_shop.databinding.LayoutItemProductBinding;
import com.example.hn_2025_online_shop.model.Info;
import com.example.hn_2025_online_shop.model.Product;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {
    private Context context;
    private List<Info> infoList;

    public InfoAdapter(Context context, List<Info> infoList) {
        this.context = context;
        this.infoList = infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemAddressBinding binding = LayoutItemAddressBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new InfoAdapter.InfoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        Info info = infoList.get(position);
        if(info == null) {
            return;
        }
        holder.binding.tvName.setText(info.getName()  + " | ");
        holder.binding.tvAddress.setText(info.getAddress());
        holder.binding.tvPhoneNumber.setText(info.getPhoneNumber());
        if(info.getChecked()) {
            holder.binding.tvDefault.setVisibility(View.VISIBLE);
        } else {
            holder.binding.tvDefault.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        if(infoList != null) {
            return infoList.size();
        }
        return 0;
    }


    public class InfoViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemAddressBinding binding;
        public InfoViewHolder(LayoutItemAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

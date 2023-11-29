package com.example.hn_2025_online_shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.databinding.LayoutIteamUpdateOptionBinding;
import com.example.hn_2025_online_shop.model.OptionProduct;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.util.List;

public class UpdateOptionAdapter extends RecyclerView.Adapter<UpdateOptionAdapter.UpdateOptionViewHolder>{
    private Context context;
    private List<OptionProduct> list;
    private ObjectUtil objectUtil;


    public UpdateOptionAdapter(Context context, List<OptionProduct> list, ObjectUtil objectUtil) {
        this.context = context;
        this.list = list;
        this.objectUtil = objectUtil;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setDataListOptionProduct(List<OptionProduct> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UpdateOptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutIteamUpdateOptionBinding binding = LayoutIteamUpdateOptionBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new UpdateOptionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UpdateOptionViewHolder holder, int position) {
        OptionProduct optionProduct = list.get(position);
        holder.binding.tvColorOption.setText(optionProduct.getNameColor());
        Glide.with(context).load(optionProduct.getImage()).into(holder.binding.imgIteamOption);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectUtil.onclickObject(optionProduct);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(list != null){
            return  list.size();
        }
        return 0;
    }

    public class UpdateOptionViewHolder extends RecyclerView.ViewHolder {
        private LayoutIteamUpdateOptionBinding binding;
        public UpdateOptionViewHolder(LayoutIteamUpdateOptionBinding binding) {
            super(binding.getRoot());
            this.binding =binding;
        }
    }
}

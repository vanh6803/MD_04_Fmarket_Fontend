package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.databinding.LayoutIteamOptionProductBinding;
import com.example.hn_2025_online_shop.databinding.LayoutItemProductBinding;
import com.example.hn_2025_online_shop.model.OptionProduct;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.util.List;

public class OptionAdapter extends RecyclerView.Adapter<OptionAdapter.OptionViewHolder>{
    private Context context;
    private List<OptionProduct> list;
    private ObjectUtil objectUtil;


    public OptionAdapter(Context context, List<OptionProduct> list, ObjectUtil objectUtil) {
        this.context = context;
        this.list = list;
        this.objectUtil = objectUtil;
    }
    public void setDataListOptionProduct(List<OptionProduct> list){
        this.list=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutIteamOptionProductBinding binding = LayoutIteamOptionProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OptionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OptionViewHolder holder, int position) {
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

    public  class OptionViewHolder extends RecyclerView.ViewHolder {
        private LayoutIteamOptionProductBinding binding;
        public OptionViewHolder(LayoutIteamOptionProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

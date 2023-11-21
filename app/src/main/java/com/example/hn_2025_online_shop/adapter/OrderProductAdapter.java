package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutItemOrderProductBinding;
import com.example.hn_2025_online_shop.model.OptionAndQuantity;

import java.text.DecimalFormat;
import java.util.List;


public class OrderProductAdapter extends RecyclerView.Adapter<OrderProductAdapter.OrderProductViewHolder> {
    private Context context;
    private List<OptionAndQuantity> listOption;


    public OrderProductAdapter(Context context, List<OptionAndQuantity> listOption) {
        this.context = context;
        this.listOption = listOption;
    }

    public void setListOption(List<OptionAndQuantity> listOption) {
        this.listOption = listOption;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemOrderProductBinding binding = LayoutItemOrderProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductViewHolder holder, int position) {
        OptionAndQuantity option = listOption.get(position);
        if(option == null) {
            return;
        }
        holder.binding.tvNameProduct.setText(option.getOptionProduct().getProduct().getName());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.binding.tvQuantityPrice.setText(option.getQuantity() +  " x " + df.format(option.getOptionProduct().getPrice()));
        holder.binding.tvNameColor.setText("Loại: " + option.getOptionProduct().getNameColor());
        Glide.with(context)
                .load(option.getOptionProduct().getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.binding.imgProduct);
    }

    @Override
    public int getItemCount() {
        if(listOption != null) {
            return listOption.size();
        }
        return 0;
    }

    public class OrderProductViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemOrderProductBinding binding;
        public OrderProductViewHolder(LayoutItemOrderProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

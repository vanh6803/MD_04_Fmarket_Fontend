package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.databinding.LayoutItemCartPayBinding;
import com.example.hn_2025_online_shop.model.OptionAndQuantity;

import java.text.DecimalFormat;
import java.util.List;

public class CartPayAdapter extends RecyclerView.Adapter<CartPayAdapter.CartPayViewHolder> {
    private Context context;
    private List<OptionAndQuantity> cartList;

    public CartPayAdapter(Context context, List<OptionAndQuantity> cartList) {
        this.cartList = cartList;
        this.context = context;
    }


    public void setCartList(List<OptionAndQuantity> cartList) {
        this.cartList = cartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartPayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCartPayBinding binding = LayoutItemCartPayBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CartPayViewHolder(binding);
    }
    @Override
    public int getItemCount() {
        if(cartList != null){
            return  cartList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull CartPayViewHolder holder, int position) {
        OptionAndQuantity cart = cartList.get(position);
        holder.binding.tvProductName.setText(cart.getOptionProduct().getProduct().getName() + "");
        holder.binding.tvNameColor.setText("Phân loại: " + cart.getOptionProduct().getNameColor());
        holder.binding.tvQuantity.setText("Số lương: x" + cart.getQuantity());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.binding.tvPrice.setText(df.format(cart.getOptionProduct().getPrice()) + " đ");
        Glide.with(context).load(cart.getOptionProduct().getImage()).into(holder.binding.imgProduct);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public class CartPayViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemCartPayBinding binding;
        public CartPayViewHolder(LayoutItemCartPayBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

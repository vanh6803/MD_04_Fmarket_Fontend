package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.model.CartOfList;
import com.example.hn_2025_online_shop.ultil.CartInterface;
import com.example.hn_2025_online_shop.ultil.CartUtil;

import java.text.DecimalFormat;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<CartOfList> listCart;
    private CartInterface cartInterface;

    public CartAdapter(Context context, List<CartOfList> listCart, CartInterface cartInterface) {
        this.context = context;
        this.listCart = listCart;
        this.cartInterface = cartInterface;
    }

    public void setListCart(List<CartOfList> listCart) {
        this.listCart = listCart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_iteam_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartOfList cart = listCart.get(position);
        if(cart == null) {
            return;
        }
        holder.tvName.setText(cart.getOptionProduct().getProduct().getName());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.tvPrice.setText(df.format(cart.getOptionProduct().getPrice()) + "đ");
        holder.tvQuantity.setText(cart.getQuantity() + "");
        Glide.with(context)
                .load(cart.getOptionProduct().getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.imgProduct);

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartInterface.onclickMinus(cart, position);
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartInterface.onclickPlus(cart, position);
            }
        });

        holder.chkPurchase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true) {
                    CartUtil.listCartCheck.add(cart);
                    cartInterface.setTotalPrice();
                } else {
                    CartUtil.listCartCheck.remove(cart);
                    cartInterface.setTotalPrice();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listCart != null) {
            return listCart.size();
        }
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private CheckBox chkPurchase;
        private ImageView imgProduct;
        private TextView tvName;
        private TextView tvPrice;
        private TextView tvQuantity;
        private TextView btnMinus;
        private TextView btnPlus;

        public LinearLayout layoutForeground;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            chkPurchase = itemView.findViewById(R.id.chkPurchase);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            layoutForeground = itemView.findViewById(R.id.layoutForeground);
        }
    }

    public void removeItem(int index) {
        listCart.remove(index);
        notifyItemRemoved(index);
    }

    public void undoItem(CartOfList cart, int index) {
        listCart.add(index, cart);
        notifyItemInserted(index);
    }
}

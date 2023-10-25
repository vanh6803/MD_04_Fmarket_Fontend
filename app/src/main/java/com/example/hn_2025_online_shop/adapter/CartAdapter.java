package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.model.Cart;
import com.example.hn_2025_online_shop.ultil.CartInterface;
import com.example.hn_2025_online_shop.ultil.CartUltil;

import java.text.DecimalFormat;
import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private List<Cart> listCart;
    private CartInterface cartInterface;

    public CartAdapter(Context context, List<Cart> listCart, CartInterface cartInterface) {
        this.context = context;
        this.listCart = listCart;
        this.cartInterface = cartInterface;
    }

    public void setListCart(List<Cart> listCart) {
        this.listCart = listCart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_iteam_giohang, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = listCart.get(position);
        if(cart == null) {
            return;
        }
        holder.tvName.setText(cart.getName());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.tvPrice.setText(df.format(cart.getPrice()) + " đ");
        holder.tvQuantity.setText(cart.getQuantity() + "");
        Glide.with(context)
                .load(cart.getImage())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgProduct);



        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = cart.getQuantity();
                if(quantity > 1) {
                    quantity -= 1;
                    CartUltil.listCart.get(position).setQuantity(quantity);
                    cartInterface.setTotalPrice();
                    notifyDataSetChanged();
                }
            }
        });

        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantity = cart.getQuantity();
                if(quantity < 10) {
                    quantity += 1;
                    CartUltil.listCart.get(position).setQuantity(quantity);
                    cartInterface.setTotalPrice();
                    notifyDataSetChanged();
                }
            }
        });

        holder.chkPurchase.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true) {
                    CartUltil.listBuyCart.add(cart);
                    cartInterface.setTotalPrice();
                } else {
                    CartUltil.listBuyCart.remove(cart);
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

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            chkPurchase = itemView.findViewById(R.id.chkPurchase);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvName = itemView.findViewById(R.id.tvName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQuantity = itemView.findViewById(R.id.tvQuantity);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
        }
    }
}

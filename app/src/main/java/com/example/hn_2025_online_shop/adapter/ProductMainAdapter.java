package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.model.Product_main;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;

import java.util.List;

public class ProductMainAdapter extends RecyclerView.Adapter<ProductMainAdapter.ProductMainViewHolder> {
    List<Product_main> list;
    private Context context;


    public ProductMainAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Product_main> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid,parent,false);
        return new ProductMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductMainViewHolder holder, int position) {
        Product_main product_main = new Product_main();
        Glide.with(context).load(product_main.getImage()).into(holder.img);
        holder.tvName.setText(product_main.getName());
        Log.d("TAG", "onBindViewHolder: "+ product_main.toString());

    }

    @Override
    public int getItemCount() {
        if(list != null){
            return  list.size();
        }
        return 0;
    }
    public class ProductMainViewHolder  extends RecyclerView.ViewHolder {
        TextView tvName;
        ImageView img;
        LinearLayout item_product;
        public ProductMainViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txtname);
            img = itemView.findViewById(R.id.img);
            item_product = itemView.findViewById(R.id.item_product);
            item_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailProduct.class);
                }
            });
        }
    }
}

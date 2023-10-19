package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.model.Producct_type;
import com.example.hn_2025_online_shop.model.Product_main;

import java.util.ArrayList;
import java.util.List;

public class ProductTypeAdapter extends RecyclerView.Adapter<ProductTypeAdapter.ViewHolder> {
    List<Producct_type> list;
    Context context;

    public ProductTypeAdapter(List<Producct_type> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_type,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Producct_type producctType = list.get(position);
        holder.txttitle.setText(producctType.getTitle());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttitle;
        GridView gridView;
        List<Product_main> productMainList;
        ProductMainAdapter productAdapter;
        LinearLayout sale;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle = itemView.findViewById(R.id.titleType);
            gridView = itemView.findViewById(R.id.grid_product);
            sale= itemView.findViewById(R.id.sale);
            productMainList= new ArrayList<>();
            for (int i = 0; i<6; i++){
                productMainList.add(new Product_main("https://www.digitaltrends.com/wp-content/uploads/2021/11/macbook-pro-2021-01.jpg?resize=625%2C417&p=1", "ốp"+ i));
            }
            productAdapter = new ProductMainAdapter(itemView.getContext(), productMainList);
            gridView.setAdapter(productAdapter);



        }
    }
}


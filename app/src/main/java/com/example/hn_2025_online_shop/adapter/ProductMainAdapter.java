package com.example.hn_2025_online_shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.model.Product_main;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductMainAdapter extends BaseAdapter {
    Context context;
    List<Product_main> list;
    TextView name;
    ImageView imageView;

    public ProductMainAdapter(Context context, List<Product_main> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
     @SuppressLint("ViewHolder") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        name = view.findViewById(R.id.txtname);
        imageView = view.findViewById(R.id.img);
        name.setText( list.get(position).getName());
        Picasso.get().load(list.get(position).getUrl()).into(imageView);
        return view;
    }
}

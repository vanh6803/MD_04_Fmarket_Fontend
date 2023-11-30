package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.model.ProductType;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.util.List;

public class SpinnerCategoryAdapter extends ArrayAdapter<ProductType> {


    public SpinnerCategoryAdapter(@NonNull Context context, int resource, @NonNull List<ProductType> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_selected, parent, false);
        }
        TextView tv_selected = convertView.findViewById(R.id.tv_selected);
        ImageView img = convertView.findViewById(R.id.imgDropDown);
        ProductType productType = this.getItem(position);
        if (productType!= null){
            tv_selected.setText(productType.getName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_category, parent, false);
        }
        TextView tvCategory = convertView.findViewById(R.id.tv_category);
        ProductType productType = this.getItem(position);
        if (productType!= null){
            tvCategory.setText(productType.getName());
        }
        return convertView;
    }
}

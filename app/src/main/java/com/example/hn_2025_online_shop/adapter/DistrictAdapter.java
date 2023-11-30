package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.model.City;
import com.example.hn_2025_online_shop.model.District;

import java.util.List;

public class DistrictAdapter extends ArrayAdapter<District> {

    public DistrictAdapter(@NonNull Context context, int resource, @NonNull List<District> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_spinner_selected, parent,false);
        TextView tvSeleted = convertView.findViewById(R.id.tvSeleted);
        District district = this.getItem(position);
        if(district != null) {
            tvSeleted.setText(district.getDistrictName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_spinner, parent,false);
        TextView tvSpinner = convertView.findViewById(R.id.tvSpinner);
        District district = this.getItem(position);
        if(district != null) {
            tvSpinner.setText(district.getDistrictName());
        }
        return convertView;
    }
}

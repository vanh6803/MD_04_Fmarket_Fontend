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
import com.example.hn_2025_online_shop.model.Ward;

import java.util.List;

public class WardAdapter extends ArrayAdapter<Ward> {

    public WardAdapter(@NonNull Context context, int resource, @NonNull List<Ward> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_spinner_selected, parent,false);
        TextView tvSeleted = convertView.findViewById(R.id.tvSeleted);
        Ward ward = this.getItem(position);
        if(ward != null) {
            tvSeleted.setText(ward.getWardName());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_spinner, parent,false);
        TextView tvSpinner = convertView.findViewById(R.id.tvSpinner);
        Ward ward = this.getItem(position);
        if(ward != null) {
            tvSpinner.setText(ward.getWardName());
        }
        return convertView;
    }
}

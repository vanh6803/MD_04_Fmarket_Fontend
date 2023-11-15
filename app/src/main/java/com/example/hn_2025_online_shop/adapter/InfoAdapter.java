package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.databinding.LayoutItemAddressBinding;
import com.example.hn_2025_online_shop.databinding.LayoutItemProductBinding;
import com.example.hn_2025_online_shop.model.Info;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {
    private Context context;
    private List<Info> infoList;
    private ObjectUtil objectUtil;

    public InfoAdapter(Context context, List<Info> infoList, ObjectUtil objectUtil) {
        this.context = context;
        this.infoList = infoList;
        this.objectUtil = objectUtil;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemAddressBinding binding = LayoutItemAddressBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new InfoAdapter.InfoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoViewHolder holder, int position) {
        Info info = infoList.get(position);
        if(info == null) {
            return;
        }
        holder.binding.tvName.setText(info.getName()  + " | ");
        holder.binding.tvAddress.setText(info.getAddress());
        holder.binding.tvPhoneNumber.setText(info.getPhoneNumber());
        if(info.getChecked()) {
            holder.binding.tvDefault.setVisibility(View.VISIBLE);
            holder.binding.chkChooseInfo.setChecked(true);
        } else {
            holder.binding.tvDefault.setVisibility(View.GONE);
            holder.binding.chkChooseInfo.setChecked(false);
        }
        holder.binding.chkChooseInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked) {
                    objectUtil.onclickObject(info);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(infoList != null) {
            return infoList.size();
        }
        return 0;
    }


    public class InfoViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemAddressBinding binding;
        public InfoViewHolder(LayoutItemAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

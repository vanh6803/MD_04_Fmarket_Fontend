package com.example.hn_2025_online_shop.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hn_2025_online_shop.databinding.LayoutItemAddressBinding;
import com.example.hn_2025_online_shop.model.Info;
import com.example.hn_2025_online_shop.ultil.InfoInterface;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> {
    private final Context context;
    private List<Info> infoList;
    private final InfoInterface infoInterface;

    public InfoAdapter(Context context, List<Info> infoList, InfoInterface infoInterface) {
        this.context = context;
        this.infoList = infoList;
        this.infoInterface = infoInterface;
    }

    @SuppressLint("NotifyDataSetChanged")
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

    @SuppressLint("SetTextI18n")
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
                    infoInterface.onclickObject(info);
                }
            }
        });

        holder.binding.tvUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoInterface.updateObject(info);
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
        private final LayoutItemAddressBinding binding;
        public InfoViewHolder(LayoutItemAddressBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.hn_2025_online_shop.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ItemNotificationBinding;
import com.example.hn_2025_online_shop.model.Notifi;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private Context context;

    private List<Notifi> notifiList;

    public NotificationAdapter(Context context, List<Notifi> notifiList) {
        this.context = context;
        this.notifiList = notifiList;
    }

    public void setNotifiList(List<Notifi> notifiList) {
        this.notifiList = notifiList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNotificationBinding binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return  new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notifi notification = notifiList.get(position);
        holder.binding.tvContent.setText(notification.getContent());

        switch (notification.getType()) {
            case "msg":
                holder.binding.tvTitle.setText("Bạn có tin nhắn mới từ " + notification.getSender().getUsername());
                break;
            case "cmt":
                holder.binding.tvTitle.setText("Bạn có comment mới từ " + notification.getSender().getUsername());
                break;
            case "wfc":
                holder.binding.tvTitle.setText("Đơn hàng đang chờ xác nhận");
                break;
            case "wfd":
                holder.binding.tvTitle.setText("Đơn hàng đang chờ đc giao");
                break;
            case "delivered":
                holder.binding.tvTitle.setText("Giao hàng thành công");
                break;
            case "canceled":
                holder.binding.tvTitle.setText("Đã hủy đơn hàng");
                break;
            default:holder.binding.tvTitle.setText("Bạn có thông báo mới");
                break;
        }

        // image
        Glide.with(context)
                .load(notification.getSender().getAvatar())
                .placeholder(R.drawable.loading)
                .error(R.drawable.avatar1)
                .into(holder.binding.imgAvartar);

        // date
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss  dd-MM-yyyy");
        Date date;
        try {
            date = inputFormat.parse(notification.getCreatedAt());
        } catch (Exception e) {
            date = new Date();
        }
        holder.binding.tvDate.setText(outputFormat.format(date));



        if(position == notifiList.size() -1){
            holder.binding.line.setVisibility(View.GONE);
        }else {
            holder.binding.line.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        if(notifiList != null) {
            return notifiList.size();
        }
        return 0;
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        private ItemNotificationBinding binding;

        public ViewHolder(ItemNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

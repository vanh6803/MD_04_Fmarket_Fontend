package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutItemReceiveMessageBinding;
import com.example.hn_2025_online_shop.databinding.LayoutItemSendMessageBinding;
import com.example.hn_2025_online_shop.model.Message;
import com.example.hn_2025_online_shop.model.Store;
import com.example.hn_2025_online_shop.ultil.AccountUltil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Message> messageList;
    private Store store;
    private static final int TYPE_SEND = 1;
    private static final int TYPE_RECEIVE = 2;

    public MessageAdapter(Context context, List<Message> messageList, Store store) {
        this.context = context;
        this.messageList = messageList;
        this.store = store;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_SEND) {
            LayoutItemSendMessageBinding binding = LayoutItemSendMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new SendMessViewHolder(binding);
        } else {
            LayoutItemReceiveMessageBinding binding = LayoutItemReceiveMessageBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ReceivedViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if(getItemViewType(position) == TYPE_SEND) {
            SendMessViewHolder holderSend = (SendMessViewHolder) holder;
            holderSend.binding.tvMessage.setText(message.getContent());
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");
                Date date = inputFormat.parse(message.getUpdatedAt());
                holderSend.binding.tvDatetime.setText(outputFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            ReceivedViewHolder holderReceive = (ReceivedViewHolder) holder;
            holderReceive.binding.tvMessage.setText(message.getContent());
            Glide.with(context)
                    .load(store.getAvatar())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.avatar1)
                    .into(holderReceive.binding.imgAvatar);
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");
                Date date = inputFormat.parse(message.getUpdatedAt());
                holderReceive.binding.tvDatetime.setText(outputFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemCount() {
        if(messageList != null) {
            return messageList.size();
        }
        return 0;
    }

    // Chọn lại hiển thị item
    @Override
    public int getItemViewType(int position) {
        // Kiểm tra nếu sendId Bằng với id đăng nhập thì là Type_send
        if(messageList.get(position).getSenderId().equals(AccountUltil.USER.getId())) {
            return TYPE_SEND;
        } else {
            return TYPE_RECEIVE;
        }
    }

    public class SendMessViewHolder extends RecyclerView.ViewHolder {
        LayoutItemSendMessageBinding binding;
        public SendMessViewHolder( LayoutItemSendMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class ReceivedViewHolder extends RecyclerView.ViewHolder {
        LayoutItemReceiveMessageBinding binding;
        public ReceivedViewHolder(LayoutItemReceiveMessageBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

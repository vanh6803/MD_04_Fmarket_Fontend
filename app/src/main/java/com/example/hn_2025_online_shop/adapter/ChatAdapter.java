package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutItemChatBinding;
import com.example.hn_2025_online_shop.model.Chat;
import com.example.hn_2025_online_shop.model.PeopleMsg;
import com.example.hn_2025_online_shop.model.User;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{
    private List<PeopleMsg> chatList;
    private Context context;
    private ObjectUtil objectUtil;

    public ChatAdapter(Context context, List<PeopleMsg> chatList, ObjectUtil objectUtil) {
        this.context = context;
        this.chatList = chatList;
        this.objectUtil = objectUtil;
    }

    public void setListChat(List<PeopleMsg> chatList) {
        this.chatList = chatList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemChatBinding binding = LayoutItemChatBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ChatViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        PeopleMsg chat = chatList.get(position);
        holder.binding.tvMessage.setText(chat.getLatestMessage().getContent());
        Log.d("aaaaaa", "store" + chat.getStore());
        Log.d("aaaaaa", "store-iamge" + chat.getStore().getAvatar());
        if(chat.getStore().isIs_active()) {
            holder.binding.tvUsername.setText(chat.getStore().getName());
            Glide.with(context)
                    .load(chat.getStore().getAvatar())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.avatar1)
                    .into(holder.binding.imgAvatar);
        } else {
            holder.binding.tvUsername.setText(chat.getAccount().getUsername());
            Glide.with(context)
                    .load(chat.getAccount().getAvatar())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.avatar1)
                    .into(holder.binding.imgAvatar);
        }

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm");
            Date date = inputFormat.parse(chat.getLatestMessage().getUpdatedAt());
            holder.binding.tvDate.setText(outputFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectUtil.onclickObject(chat);

            }
        });
    }

    @Override
    public int getItemCount() {
        if(chatList != null){
            return  chatList.size();
        }
        return 0;
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        LayoutItemChatBinding binding;
        public ChatViewHolder(LayoutItemChatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

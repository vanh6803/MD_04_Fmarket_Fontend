package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.databinding.LayoutItemChatBinding;
import com.example.hn_2025_online_shop.model.Chat;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder>{
    private List<Chat> chatList;
    private Context context;
    private ObjectUtil objectUtil;

    public ChatAdapter(Context context, List<Chat> chatList, ObjectUtil objectUtil) {
        this.context = context;
        this.chatList = chatList;
        this.objectUtil = objectUtil;
    }

    public void setListChat(List<Chat> chatList) {
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
        Chat chat = chatList.get(position);
        holder.binding.tvUsername.setText(chat.getUsername());
        holder.binding.tvMessage.setText(chat.getMessage());

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

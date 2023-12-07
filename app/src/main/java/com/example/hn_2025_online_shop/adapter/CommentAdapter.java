package com.example.hn_2025_online_shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutItemCommentBinding;
import com.example.hn_2025_online_shop.databinding.LayoutItemProductBinding;
import com.example.hn_2025_online_shop.model.Comment;
import com.example.hn_2025_online_shop.model.CommentAccount;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private Context context;
    private List<CommentAccount> commentList;

    public CommentAdapter(Context context, List<CommentAccount> commentList) {
        this.commentList = commentList;
        this.context = context;
    }

    public void setCommentList(List<CommentAccount> commentList) {
        this.commentList = commentList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemCommentBinding binding = LayoutItemCommentBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CommentViewHolder(binding);
    }
    @Override
    public int getItemCount() {
        if(commentList != null){
            return  commentList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentAccount comment = commentList.get(position);
        if(comment == null) {
            return;
        }
        holder.binding.tvComment.setText(comment.getComment().getContent());
        holder.binding.tvUsername.setText(comment.getAccount().getUsername());
        Glide.with(context)
                .load(comment.getAccount().getAvatar())
                .placeholder(R.drawable.loading)
                .error(R.drawable.avatar1)
                .into(holder.binding.imgAvatar);
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("hh:mm a");
        Date date;
        try {
             date = inputFormat.parse(comment.getComment().getUpdatedAt());
        } catch (Exception e) {
            date = new Date();
        }
        holder.binding.tvDatetime.setText(outputFormat.format(date));
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemCommentBinding binding;
        public CommentViewHolder(LayoutItemCommentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package com.example.hn_2025_online_shop.view.chat_message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ChatAdapter;
import com.example.hn_2025_online_shop.databinding.ActivityChatBinding;
import com.example.hn_2025_online_shop.model.Chat;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements ObjectUtil {
    private ActivityChatBinding binding;
    private ChatAdapter chatAdapter;
    private List<Chat> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initController();
    }

    private void initController() {
        binding.imgBack.setOnClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
        });
    }

    private void initView() {
        chatList = new ArrayList<>();
        chatList.add(new Chat("Nguyễn Chí Thuận", "Mua cho t cái áo !"));
        chatList.add(new Chat("Nguyễn Minh Phượng", "Đẹp quá shop ơi"));
        chatList.add(new Chat("Nguyễn Văn Hải", "Đẹp đấy!"));

        chatAdapter = new ChatAdapter(this, chatList, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rcvChat.setLayoutManager(layoutManager);
        binding.rcvChat.setAdapter(chatAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }

    @Override
    public void onclickObject(Object object) {
        Intent intent = new Intent(this, MessageActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
    }
}
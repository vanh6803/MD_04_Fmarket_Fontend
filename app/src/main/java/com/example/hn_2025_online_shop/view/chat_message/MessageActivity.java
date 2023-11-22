package com.example.hn_2025_online_shop.view.chat_message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ChatAdapter;
import com.example.hn_2025_online_shop.adapter.MessageAdapter;
import com.example.hn_2025_online_shop.databinding.ActivityMessageBinding;
import com.example.hn_2025_online_shop.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {
    private ActivityMessageBinding binding;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessageBinding.inflate(getLayoutInflater());
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
        messageList = new ArrayList<>();
        messageList.add(new Message("1324342", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));
        messageList.add(new Message("1324342", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));
        messageList.add(new Message("1324342", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));
        messageList.add(new Message("1324342", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));
        messageList.add(new Message("1324342", "Chào bạn"));
        messageList.add(new Message("1324342", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));
        messageList.add(new Message("1324342", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));
        messageList.add(new Message("1324342", "Chào bạn"));
        messageList.add(new Message("654ca5874944f00aacf686b1", "Chào bạn"));

        messageAdapter = new MessageAdapter(this, messageList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rcvMesssage.setLayoutManager(layoutManager);
        binding.rcvMesssage.setAdapter(messageAdapter);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}
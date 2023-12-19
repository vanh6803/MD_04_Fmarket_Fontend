package com.example.hn_2025_online_shop.view.chat_message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ChatAdapter;
import com.example.hn_2025_online_shop.adapter.MessageAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityMessageBinding;
import com.example.hn_2025_online_shop.model.Info;
import com.example.hn_2025_online_shop.model.Message;
import com.example.hn_2025_online_shop.model.Store;
import com.example.hn_2025_online_shop.model.User;
import com.example.hn_2025_online_shop.model.response.ListChatResponse;
import com.example.hn_2025_online_shop.model.response.ListMessageResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.MyApplication;
import com.example.hn_2025_online_shop.ultil.NotificationUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MessageActivity extends AppCompatActivity {
    private ActivityMessageBinding binding;
    private MessageAdapter messageAdapter;
    private List<Message> messageList;
    private User userReceiver;
    private Store store;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

    // socket để thao tác
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://" + BaseApi.LOCALHOT + ":3000");
        } catch (URISyntaxException e) {}
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initController();
        getListMessage();
        initSocket();
    }

    private void initSocket() {
        mSocket.connect();
        mSocket.on("new_message", onNewMessage);
        binding.btnSend.setOnClickListener(v -> {
            attemptSend();
        });
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject data = (JSONObject) args[0];
                        String message = data.getString("content");
                        String sender_id = data.getString("sender_id");
                        String receiver_id = data.getString("receiver_id");
                        messageList.add(new Message(sender_id, receiver_id, message, sdf.format(new Date())));
                        messageAdapter.notifyItemRangeInserted(messageList.size(), messageList.size());
                        binding.rcvMesssage.smoothScrollToPosition(messageList.size() - 1);
                        if(!sender_id.equals(AccountUltil.USER.getId())) {
                            NotificationUtil.sendNotification(MessageActivity.this, message);
                        }
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    };



    private void attemptSend() {
        mSocket.connect();
        String message = binding.edSendMessage.getText().toString().trim();

        JSONObject data = new JSONObject();
        try {
            data.put("sender_id", AccountUltil.USER.getId());
            data.put("receiver_id", userReceiver.getId());
            data.put("content", message);
            data.put("image", "link image");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        if (TextUtils.isEmpty(message)) {
            return;
        }

        binding.edSendMessage.setText("");
        mSocket.emit("data", data);
    }

    private void getListMessage() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        String userId = AccountUltil.USER.getId();
        binding.progressBar.setVisibility(View.VISIBLE);

        BaseApi.API.getListMessage(token, userId, userReceiver.getId()).enqueue(new Callback<ListMessageResponse>() {
            @Override
            public void onResponse(Call<ListMessageResponse> call, Response<ListMessageResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ListMessageResponse listMessageResponse = response.body();
                    Log.d(TAG.toString, "onResponse-getListMessage: " + listMessageResponse.toString());
                    if(listMessageResponse.getCode() == 200) {
                        messageList = listMessageResponse.getResult();
                        messageAdapter.setMessageList(messageList);
                        binding.rcvMesssage.smoothScrollToPosition(messageList.size() - 1);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getListMessage: " + errorMessage);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ListMessageResponse> call, Throwable t) {
                Toast.makeText(MessageActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getListMessage: " + t.toString());
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initController() {
        binding.imgBack.setOnClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
        });
    }

    private void initView() {
        // Lấy Intent từ Activity hiện tại
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) { // Kiểm tra xem Bundle có tồn tại hay không
            userReceiver = (User) bundle.getSerializable("receiver_object"); // Ép kiểu đối tượng từ Bundle
            store = (Store) bundle.getSerializable("store");
        }
        if(store.isIs_active()) {
            Glide.with(this)
                    .load(store.getAvatar())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.avatar1)
                    .into(binding.imgAvatar);
            binding.tvUserName.setText(store.getName());
        } else {
            Glide.with(this)
                    .load(userReceiver.getAvatar())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.avatar1)
                    .into(binding.imgAvatar);

            binding.tvUserName.setText(userReceiver.getUsername());
        }

        // rcy
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, messageList, store);
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
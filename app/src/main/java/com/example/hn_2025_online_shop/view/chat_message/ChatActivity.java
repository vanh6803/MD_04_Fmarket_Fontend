package com.example.hn_2025_online_shop.view.chat_message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ChatAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityChatBinding;
import com.example.hn_2025_online_shop.model.Chat;
import com.example.hn_2025_online_shop.model.PeopleMsg;
import com.example.hn_2025_online_shop.model.User;
import com.example.hn_2025_online_shop.model.response.ListChatResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.login.Register;
import com.example.hn_2025_online_shop.view.login.VerifiPassWord;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity implements ObjectUtil {
    private ActivityChatBinding binding;
    private ChatAdapter chatAdapter;
    private List<PeopleMsg> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initController();
        getListPeopleChat();
    }

    private void getListPeopleChat() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        String idUser = AccountUltil.USER.getId();
        Log.d("ChatActivity_uid",  AccountUltil.USER.getId());
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getListPeopleChat(token, idUser).enqueue(new Callback<ListChatResponse>() {
            @Override
            public void onResponse(Call<ListChatResponse> call, Response<ListChatResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ListChatResponse listChatResponse = response.body();
                    Log.d(TAG.toString, "onResponse-getListPeopleChat: " + listChatResponse.toString());
                    if(listChatResponse.getCode() == 200) {
                        chatList = listChatResponse.getResult();
                        chatAdapter.setListChat(chatList);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getListPeopleChat: " + errorMessage);
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
            public void onFailure(Call<ListChatResponse> call, Throwable t) {
                Toast.makeText(ChatActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getListPeopleChat: " + t.toString());
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
        chatList = new ArrayList<>();
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
        PeopleMsg peopleMsg = (PeopleMsg) object;
        Intent intent = new Intent(this, MessageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("receiver_object",  peopleMsg.getAccount());
        bundle.putSerializable("store",  peopleMsg.getStore());
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
    }
}
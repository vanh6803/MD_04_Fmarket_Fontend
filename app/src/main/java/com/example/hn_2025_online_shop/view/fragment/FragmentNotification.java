package com.example.hn_2025_online_shop.view.fragment;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.NotificationAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentNotificationBinding;
import com.example.hn_2025_online_shop.model.Notifi;
import com.example.hn_2025_online_shop.model.response.ListNotifiReponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.MyApplication;
import com.example.hn_2025_online_shop.ultil.NotificationUtil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.chat_message.ChatActivity;
import com.example.hn_2025_online_shop.view.chat_message.MessageActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNotification extends Fragment {
    private FragmentNotificationBinding binding;

    private NotificationAdapter notifiAdapter;
    private List<Notifi> notifiList;

    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

    // socket để thao tác
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://" + BaseApi.LOCALHOT +":3000");
        } catch (URISyntaxException e) {}
    }



    public static FragmentNotification newInstance() {
        FragmentNotification fragment = new FragmentNotification();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initController();
        getListNotify();
        initSocket();
    }

    private void initSocket() {
        mSocket.connect();
        mSocket.on("new_notification", onNewNotification);
    }

    private Emitter.Listener onNewNotification = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            if (getActivity() == null) {
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject data = (JSONObject) args[0];
                        String content = data.getString("content");
                        NotificationUtil.sendNotification(getActivity(), content);
                        getListNotify();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    };

    private void getListNotify() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        String userId = AccountUltil.USER.getId();
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getNotifiList(token, userId).enqueue(new Callback<ListNotifiReponse>() {
            @Override
            public void onResponse(Call<ListNotifiReponse> call, Response<ListNotifiReponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ListNotifiReponse listNotifiReponse = response.body();
                    Log.d(TAG.toString, "onResponse-getNotifiList: " + listNotifiReponse.toString());
                    if(listNotifiReponse.getCode() == 200 || listNotifiReponse.getCode() == 201) {
                        notifiList = listNotifiReponse.getResult();
                        notifiAdapter.setNotifiList(notifiList);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getNotifiList: " + errorMessage);
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ListNotifiReponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getNotifiList: " + t.toString());
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initController() {
        binding.imgChat.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
        });
    }

    public  void initView(){
        notifiList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rcvNofity.setLayoutManager(layoutManager);
        notifiAdapter = new NotificationAdapter(getActivity(), notifiList);
        binding.rcvNofity.setAdapter(notifiAdapter);
    }
}
package com.example.hn_2025_online_shop.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.NotificationAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentNotificationBinding;
import com.example.hn_2025_online_shop.databinding.FragmentProductBinding;
import com.example.hn_2025_online_shop.model.NotificationModel;
import com.example.hn_2025_online_shop.view.chat_message.ChatActivity;

import java.util.ArrayList;
import java.util.List;

public class FragmentNotification extends Fragment {
    private FragmentNotificationBinding binding;

    NotificationAdapter adapter;
    List<NotificationModel> list;


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
    }

    private void initController() {
        binding.imgChat.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
        });
    }

    public  void initView(){
        list = new ArrayList<>();
        for(int i = 0; i< 10; i++){
            list.add(new NotificationModel("Đã đặt hàng"+i,"Bạn đã đặt mua sản phẩm thành công", "20/11/2023 11:11:10"));
        }
        adapter = new NotificationAdapter(getContext(), list);
        binding.recyAction.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
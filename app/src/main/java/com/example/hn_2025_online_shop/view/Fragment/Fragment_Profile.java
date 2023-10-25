package com.example.hn_2025_online_shop.view.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.FragmentProfileBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialogLogoutBinding;
import com.example.hn_2025_online_shop.model.HistoryBuy;
import com.example.hn_2025_online_shop.view.profile_screen.ChatScreen;
import com.example.hn_2025_online_shop.view.profile_screen.HistoryBuyScreen;

public class Fragment_Profile extends Fragment {
    private FragmentProfileBinding binding;
    public static Fragment_Profile newInstance() {
        Fragment_Profile fragment = new Fragment_Profile();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    //init view và các action vào đây
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        followDon();//theo dõi đơn hàng
        chat(); // chat với khách hàng
        logOut();//đăng xuất
    }
    private void followDon() {
        binding.layoutFollowDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HistoryBuyScreen.class));
            }
        });
    }
    private void chat() {
        binding.layoutQA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChatScreen.class));
            }
        });
    }
    private void logOut() {
        binding.layoutLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutDialogLogoutBinding bindingLogout = LayoutDialogLogoutBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(bindingLogout.getRoot());
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                bindingLogout.btnCancelLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                bindingLogout.btnLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), HistoryBuyScreen.class));
                        getActivity().finish();
                    }
                });
                dialog.show();
            }
        });
    }
}
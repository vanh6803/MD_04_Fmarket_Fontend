package com.example.hn_2025_online_shop.view.my_store;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentHomeStoreBinding;
import com.example.hn_2025_online_shop.model.response.StoreIdResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.StoreUltil;
import com.example.hn_2025_online_shop.ultil.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHomeStore extends Fragment {
    private FragmentHomeStoreBinding binding;

    public FragmentHomeStore() {
    }

    public static FragmentHomeStore newInstance() {
        FragmentHomeStore fragment = new FragmentHomeStore();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentHomeStoreBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    public void initView(){
        binding.btnAllPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentProductWarehouse newFragment = new FragmentProductWarehouse(); // Tạo một instance của Fragment mới
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container2, newFragment); // Thay thế Fragment hiện tại bằng Fragment mới
                fragmentTransaction.addToBackStack(null); // (Tùy chọn) Cho phép người dùng quay lại Fragment trước đó bằng nút Back
                fragmentTransaction.commit();


            }
        });

    }
}
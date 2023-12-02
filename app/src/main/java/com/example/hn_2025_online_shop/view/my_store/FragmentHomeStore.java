package com.example.hn_2025_online_shop.view.my_store;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentHomeStoreBinding;
import com.example.hn_2025_online_shop.model.response.StoreIdResponse;
import com.example.hn_2025_online_shop.model.response.store.InfoStore;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.StoreUltil;
import com.example.hn_2025_online_shop.ultil.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Objects;

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
        getProfileStore();
    }

    private void getProfileStore() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        BaseApi.API.getInfoStore(token).enqueue(new Callback<InfoStore>() {
            @Override
            public void onResponse(@NonNull Call<InfoStore> call, @NonNull Response<InfoStore> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    InfoStore infoStore = response.body();
                    if(infoStore.getCode() == 200 || infoStore.getCode() == 201) {
                        setDataInforStore(infoStore);
                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-createProduct: " + errorMessage);
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<InfoStore> call, @NonNull Throwable t) {

            }
        });

    }

    private void setDataInforStore(InfoStore infoStore) {
        binding.tvName.setText(infoStore.getData().getName());
        Glide.with(this)
                .load(infoStore.getData().getAvatar())
                .error(R.drawable.error)
                .into(binding.imgAvartar);
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
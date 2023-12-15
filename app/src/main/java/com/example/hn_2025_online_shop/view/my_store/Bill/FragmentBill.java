package com.example.hn_2025_online_shop.view.my_store.Bill;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hn_2025_online_shop.adapter.BillAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentBillBinding;
import com.example.hn_2025_online_shop.model.Order;
import com.example.hn_2025_online_shop.model.response.OrderResponse;
import com.example.hn_2025_online_shop.model.response.store.ProductOder;
import com.example.hn_2025_online_shop.model.response.store.ResponseBill;
import com.example.hn_2025_online_shop.model.response.store.Result;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBill extends Fragment implements ObjectUtil {
    FragmentBillBinding binding;

    BillAdapter adapter;
    private  List<Result> billList;
    private  String id;



    public FragmentBill() {
        // Required empty public constructor
    }

    public static FragmentBill newInstance() {
        FragmentBill fragment = new FragmentBill();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentBillBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        billList = new ArrayList<>();
        getListBill();
        adapter = new BillAdapter(billList, getContext(), this::onclickObject);
        binding.recycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onclickObject(Object object) {
        for(int i = 0; i< billList.size(); i++){
            id = billList.get(i).getIdPro();
        }
        Intent intent = new Intent(getContext(), DetailBill.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    private void getListBill() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getListBill(token).enqueue(new Callback<ResponseBill>() {
            @Override
            public void onResponse(Call<ResponseBill> call, Response<ResponseBill> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ResponseBill orderResponse = response.body();
                    Log.d(TAG.toString, "onResponse-getListOrder: " + orderResponse.toString());
                    if(orderResponse.getCode() == 200 || orderResponse.getCode() == 201) {
                        billList = orderResponse.getResult();
                        adapter.setListBill(billList);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getListOrder: " + errorMessage);
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
            public void onFailure(Call<ResponseBill> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getListOrder: " + t.toString());
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }
}
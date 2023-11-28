package com.example.hn_2025_online_shop.view.my_store.OrderStore.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hn_2025_online_shop.adapter.OrderAdapter;
import com.example.hn_2025_online_shop.adapter.OrderStoreAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentPageDeliveredBinding;
import com.example.hn_2025_online_shop.databinding.FragmentPageDeliveredStoreBinding;
import com.example.hn_2025_online_shop.model.Order;
import com.example.hn_2025_online_shop.model.response.OrderResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPageDeliveredStore extends Fragment implements ObjectUtil {
    private FragmentPageDeliveredStoreBinding binding;
    private List<Order> orderList;
    private OrderStoreAdapter orderStoreAdapter;

    public static FragmentPageDeliveredStore newInstance(String param1, String param2) {
        FragmentPageDeliveredStore fragment = new FragmentPageDeliveredStore();
        return fragment;
    }

    public static Fragment newInstance() {
        FragmentPageDeliveredStore fragment = new FragmentPageDeliveredStore();
            return fragment;
        }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPageDeliveredStoreBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
        urlListOrder();
    }

    private void initView() {
        orderList = new ArrayList<>();
        orderStoreAdapter = new OrderStoreAdapter(getActivity(), orderList, this, 3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rcvOrder.setLayoutManager(layoutManager);
        binding.rcvOrder.setAdapter(orderStoreAdapter);
    }

    private void urlListOrder() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getListOrderStore(token, TAG.DELIVERED).enqueue(new Callback<OrderResponse>() {
            @Override
            public void onResponse(Call<OrderResponse> call, Response<OrderResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    OrderResponse orderResponse = response.body();
                    Log.d(TAG.toString, "onResponse-getListOrder: " + orderResponse.toString());
                    if(orderResponse.getCode() == 200 || orderResponse.getCode() == 201) {
                        orderList = orderResponse.getResult();
                        orderStoreAdapter.setListOrder(orderList);
                        if(orderList.size() == 0) {
                            binding.layoutDrum.setVisibility(View.VISIBLE);
                        } else {
                            binding.layoutDrum.setVisibility(View.GONE);
                        }
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
            public void onFailure(Call<OrderResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getListOrder: " + t.toString());
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onclickObject(Object object) {

    }
}
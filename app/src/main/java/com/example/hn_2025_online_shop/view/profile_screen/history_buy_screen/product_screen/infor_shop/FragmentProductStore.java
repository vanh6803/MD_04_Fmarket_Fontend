package com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.infor_shop;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.hn_2025_online_shop.adapter.ProductAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentProductStoreBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProductStore extends Fragment {
    private FragmentProductStoreBinding binding;
    private DetailProduct detailProduct;
    private List<Product> productList;
    ProductAdapter productAdapter;
    ProgressLoadingDialog dialog;



    public FragmentProductStore() {
    }
    public static FragmentProductStore newInstance(String param1, String param2) {
        FragmentProductStore fragment = new FragmentProductStore();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentProductStoreBinding.inflate(getLayoutInflater());        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new ProgressLoadingDialog(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.rcvProductStore.setLayoutManager(layoutManager);
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(getActivity(), productList);
        binding.rcvProductStore.setAdapter(productAdapter);
        setDataProductStore();
    }
    private void setDataProductStore(){
        dialog.show();
        BaseApi.API.getDataProductStore(DetailProduct.storeId).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse response1 = response.body();
                    if (response1.getCode() == 200){
                        productAdapter.setProductList(response1.getResult());
                        binding.rcvProductStore.setAdapter(productAdapter);
                        Toast.makeText(getContext(), response1.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        // Parse and display the error message
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(detailProduct, "Không Call đươc API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
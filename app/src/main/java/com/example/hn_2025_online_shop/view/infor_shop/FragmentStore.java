package com.example.hn_2025_online_shop.view.infor_shop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductAdapter;


import com.example.hn_2025_online_shop.adapter.StoreAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentStoreBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.Voucher;

import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.view.product_screen.DetailProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentStore extends Fragment implements ObjectUtil {

    private FragmentStoreBinding binding;
    public Context context;
    List<Product> productList;
    ProductAdapter productAdapter;
    StoreAdapter adapter;
    List<Voucher> list;


    public FragmentStore() {
    }

    public static FragmentStore newInstance(String param1, String param2) {
        FragmentStore fragment = new FragmentStore();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoreBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        list.add(new Voucher("Giảm Giá", "100k", "5 Ngày", "12%"));
        list.add(new Voucher("Giảm Giá", "500k", "5 Ngày", "12%"));
        list.add(new Voucher("Giảm Giá", "200k", "5 Ngày", "12%"));
        list.add(new Voucher("Giảm Giá", "300k", "5 Ngày", "12%"));
        adapter = new StoreAdapter(list,getContext());
        binding.rcvVoucher.setAdapter(adapter);
        productList = new ArrayList<>();
        initView();
        callApiGetListAllProducts();
    }
    private void initView() {
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(getContext(), productList, this);
        productAdapter.setProductList(productList);
        binding.recyStore.setAdapter(productAdapter);
    }
    public void callApiGetListAllProducts(){
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getListAllProduct(true, AccountUltil.TOKEN).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    productAdapter.setProductList(productResponse.getResult());
                    binding.recyStore.setAdapter(productAdapter);
                } else {
                    Toast.makeText(getActivity(), "Call API  Products Error", Toast.LENGTH_SHORT).show();
                }
                binding.progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onclickObject(Object object) {
        Product product = (Product) object;
        String id = product.getId();
        Intent intent = new Intent(getActivity(), DetailProduct.class);
        intent.putExtra("id_product", id);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left); 
    }
}
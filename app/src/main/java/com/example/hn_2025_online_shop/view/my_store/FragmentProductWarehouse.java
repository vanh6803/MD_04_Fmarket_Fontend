package com.example.hn_2025_online_shop.view.my_store;

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

import com.example.hn_2025_online_shop.adapter.ProductAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentProductWarehouseBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.StoreUltil;
import com.example.hn_2025_online_shop.ultil.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentProductWarehouse extends Fragment implements ObjectUtil {
    private List<Product> list;
    private ProductAdapter adapter;
    private FragmentProductWarehouseBinding binding;
    private ProgressLoadingDialog dialog;
    public FragmentProductWarehouse() {

    }



    public static FragmentProductWarehouse newInstance() {
        FragmentProductWarehouse fragment = new FragmentProductWarehouse();

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
        binding = FragmentProductWarehouseBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new ProgressLoadingDialog(getContext());
        list = new ArrayList<>();
        adapter = new ProductAdapter(getContext(), list, this);
        binding.recycleView.setAdapter(adapter);
        callApiShowLishProductMyStore();
    }

    private void callApiShowLishProductMyStore() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("storeId", Context.MODE_PRIVATE);
        String  storeId = sharedPreferences.getString("storeId", "storeId" );
        Log.d("storeId", "callApiShowLishProductMyStore: " + storeId);
        dialog.show();
        Log.d(TAG.toString, "callApiShowLishProductMyStore: "+ StoreUltil.store);
        BaseApi.API.getDataProductStore(storeId).enqueue(new Callback<ProductResponse>() {

            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse response1 = response.body();
                    if (response1.getCode() == 200){
                        adapter.setProductList(response1.getResult());
                        binding.recycleView.setAdapter(adapter);
                        Log.d("cccc", "onResponse: " + response1.getResult());
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
                Toast.makeText(getContext(), "Không Call đươc API", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

    }


    @Override
    public void onclickObject(Object object) {
        Product product = (Product) object;
        String id = product.getId();
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("id_product", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("id_product", id);
//        editor.apply();
        Intent intent = new Intent(getContext(), UpdateProductActivity.class);
        intent.putExtra("id_product", id);
        Log.d("aaaaa", "onclickObject: "+ id);
        startActivity(intent);

    }
}
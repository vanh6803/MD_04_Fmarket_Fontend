package com.example.hn_2025_online_shop.view.fragment.fragment_home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductSaleAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentPageDiscountBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductByCategory;
import com.example.hn_2025_online_shop.model.response.ProductByCategoryReponse;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentPageDiscount extends Fragment implements ObjectUtil {
    private List<Product> list;
    private ProductSaleAdapter adapter;
    private FragmentPageDiscountBinding binding;
    public static FragmentPageDiscount newInstance(String param1, String param2) {
        FragmentPageDiscount fragment = new FragmentPageDiscount();;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentPageDiscountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = new ArrayList<>();
        adapter = new ProductSaleAdapter(getContext(), list, this);
        binding.recyProSale.setAdapter(adapter);
        ShowListProductDiscouted();
    }

    private void ShowListProductDiscouted() {
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getAllProductDiscouted(true, AccountUltil.TOKEN).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) { // chỉ nhận đầu status 200
                    ProductResponse reponse = response.body();
                    Log.d(TAG.toString, "onResponse-ListProductByCategory: " + reponse.toString());
                    if (reponse.getCode() == 200) {
                        adapter.setListProductSale(reponse.getResult());
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-register: " + errorMessage);
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
            public void onFailure(Call<ProductResponse> call, Throwable t) {
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
package com.example.hn_2025_online_shop.view.fragment.fragment_home;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductByCategoryAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentFragementPageSellingBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductByCategory;
import com.example.hn_2025_online_shop.model.response.ProductByCategoryReponse;
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

public class FragementPageSelling extends Fragment implements ObjectUtil {

    private ProductByCategoryAdapter productAdapter;
    private List<ProductByCategory> productList;
    private FragmentFragementPageSellingBinding binding;

    public FragementPageSelling() {
        // Required empty public constructor
    }

    public static FragementPageSelling newInstance(String param1, String param2) {
        FragementPageSelling fragment = new FragementPageSelling();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFragementPageSellingBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initController();
        callApiProductByCategory();

    }

    private void initController() {
    }

    private void initView() {
        productList = new ArrayList<>();
        productAdapter = new ProductByCategoryAdapter(getActivity(), productList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.recycleProductMain.setLayoutManager(linearLayoutManager);
        binding.recycleProductMain.setAdapter(productAdapter);
    }

    private void callApiProductByCategory(){
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getListProductByCategory(AccountUltil.TOKEN).enqueue(new Callback<ProductByCategoryReponse>() {
            @Override
            public void onResponse(Call<ProductByCategoryReponse> call, Response<ProductByCategoryReponse> response) {
                if (response.isSuccessful()) { // chỉ nhận đầu status 200
                    ProductByCategoryReponse reponse = response.body();
                    Log.d(TAG.toString, "onResponse-ListProductByCategory: " + reponse.toString());
                    if (reponse.getCode() == 200) {
                        for (ProductByCategory productByCategory : reponse.getResult()) {
                            if (productByCategory.getProduct().size() > 0) {
                                productList.add(productByCategory);
                            }
                        }
                        productAdapter.setListProductType(productList);
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
            public void onFailure(Call<ProductByCategoryReponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG.toString, "onResume: ");
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

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG.toString, "onStart: ");
    }
}
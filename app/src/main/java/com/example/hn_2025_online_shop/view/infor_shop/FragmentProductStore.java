package com.example.hn_2025_online_shop.view.infor_shop;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentProductStoreBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProductPaginationScrollListener;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.StoreUltil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.my_store.FragmentCreateProductMyStore;
import com.example.hn_2025_online_shop.view.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProductStore extends Fragment implements ObjectUtil {
    private FragmentProductStoreBinding binding;
    private List<Product> productList;
    ProductAdapter productAdapter;
    private int currentPage = 1;
    private boolean isLoading;
    private boolean isLastPage;
    private GridLayoutManager gridLayoutManager;

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
        initView();
        initController();
        setDataProductStore(currentPage);
    }



    private void initView() {
        gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.rcvProductStore.setLayoutManager(gridLayoutManager);
        productList = new ArrayList<>();
        Log.d(TAG.toString, "onViewCreated: "+ StoreUltil.store.getId());
        productAdapter = new ProductAdapter(getActivity(), productList, this);
        binding.rcvProductStore.setAdapter(productAdapter);
    }

    private void initController() {
        binding.rcvProductStore.addOnScrollListener(new ProductPaginationScrollListener(gridLayoutManager) {
            @Override
            public void loadMoreItems() {
                isLoading = true;
                currentPage++;
                loadNextPage(currentPage);
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }
        });
    }

    private void loadNextPage(int currentPage) {
        setDataProductStore(currentPage);
    }

    public void setDataProductStore(int currentP){
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getDataProductStore(StoreUltil.store.getId(), currentP, AccountUltil.TOKEN).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse response1 = response.body();
                    if (response1.getCode() == 200){
                        List<Product> listProductReponse = response1.getResult();
                        productList.addAll(listProductReponse);
                        productAdapter.setProductList(productList);
                        binding.rcvProductStore.setAdapter(productAdapter);
                        isLoading = false;
                        // Kiểm tra nếu hết dữ liệu mà nhỏ hơn 10 thì tức là hết trang
                        // mỗi page có 10 data
                        if (listProductReponse.size() < 10) {
                            isLastPage = true;
                        }
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
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Không Call đươc API", Toast.LENGTH_SHORT).show();
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
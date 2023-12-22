package com.example.hn_2025_online_shop.view.my_store;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import com.example.hn_2025_online_shop.databinding.FragmentCreateProductMyStoreBinding;
import com.example.hn_2025_online_shop.databinding.FragmentProductWarehouseBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProductPaginationScrollListener;
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
    private List<Product> productList;
    private ProductAdapter adapter;
    private FragmentProductWarehouseBinding binding;
    private ProgressLoadingDialog dialog;
    private int currentPage = 1;
    private boolean isLoading;
    private boolean isLastPage;
    private GridLayoutManager gridLayoutManager;
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
        View view = binding.getRoot();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initController();
    }


    private void initController() {
        binding.imgCreateProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentCreateProductMyStore productMyStore = new FragmentCreateProductMyStore();
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack("FragmentA");
                transaction.replace(R.id.content_frame, FragmentCreateProductMyStore.newInstance());
                transaction.commit();

            }
        });

        binding.recycleView.addOnScrollListener(new ProductPaginationScrollListener(gridLayoutManager) {
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

    private void initView() {
        dialog = new ProgressLoadingDialog(getContext());
        productList = new ArrayList<>();
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recycleView.setLayoutManager(gridLayoutManager);
        adapter = new ProductAdapter(getContext(), productList, this);
        binding.recycleView.setAdapter(adapter);
        callApiShowLishProductMyStore(currentPage);
    }

    private void loadNextPage(int currentPage) {
        callApiShowLishProductMyStore(currentPage);
    }


    private void callApiShowLishProductMyStore(int currentP) {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("storeId", Context.MODE_PRIVATE);
        String  storeId = sharedPreferences.getString("storeId", "storeId" );
        Log.d("storeId", "callApiShowLishProductMyStore: " + storeId);
        dialog.show();
        Log.d(TAG.toString, "callApiShowLishProductMyStore: "+ StoreUltil.store);
        BaseApi.API.getDataProductStore(storeId, currentP, AccountUltil.TOKEN).enqueue(new Callback<ProductResponse>() {

            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse response1 = response.body();
                    if (response1.getCode() == 200){
                        List<Product> listProductReponse = response1.getResult();
                        productList.addAll(listProductReponse);
                        adapter.setProductList(productList);
                        binding.recycleView.setAdapter(adapter);
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
        getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
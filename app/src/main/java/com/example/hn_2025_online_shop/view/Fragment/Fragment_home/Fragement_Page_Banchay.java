package com.example.hn_2025_online_shop.view.Fragment.Fragment_home;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductTypeAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentFragementPageBanchayBinding;
import com.example.hn_2025_online_shop.model.Producct_type;
import com.example.hn_2025_online_shop.model.Product_main;
import com.example.hn_2025_online_shop.response.ProductTypeResponse;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragement_Page_Banchay extends Fragment {
    List<Producct_type> producct_types;

    ProductTypeAdapter homeAdapter;
    ProgressLoadingDialog loadingDialog;

    private FragmentFragementPageBanchayBinding binding;

    public Fragement_Page_Banchay() {
        // Required empty public constructor
    }

    public static Fragement_Page_Banchay newInstance(String param1, String param2) {
        Fragement_Page_Banchay fragment = new Fragement_Page_Banchay();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFragementPageBanchayBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        homeAdapter= new ProductTypeAdapter(producct_types, getContext());
        binding.recycleProductMain.setAdapter(homeAdapter);
        callApiProductType();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadingDialog = new ProgressLoadingDialog(getContext());
        producct_types= new ArrayList<>();
//        producct_types.add(new Producct_type("1","Điện thoại / Lap top ", "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg" ));
//        producct_types.add(new Producct_type("2","Điện thoại / Lap top ", "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg" ));
//        producct_types.add(new Producct_type("3","Điện thoại / Lap top ", "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg" ));
//        producct_types.add(new Producct_type("4","Điện thoại / Lap top ", "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg" ));

       homeAdapter= new ProductTypeAdapter(producct_types, getContext());
       binding.recycleProductMain.setAdapter(homeAdapter);
       callApiProductType();
    }
    private void callApiProductType(){
        loadingDialog.show();
        BaseApi.API.getListTypeProduct().enqueue(new Callback<ProductTypeResponse>() {
            @Override
            public void onResponse(Call<ProductTypeResponse> call, Response<ProductTypeResponse> response) {
                if(response.isSuccessful()){
                    ProductTypeResponse productTypeResponse =response.body();
                    homeAdapter.setListProductType(productTypeResponse.getData());
                    binding.recycleProductMain.setAdapter(homeAdapter);
                }else{
                    Toast.makeText(getActivity(), "Get Product Type Error", Toast.LENGTH_SHORT).show();
                }
                loadingDialog.dismiss();
            }
            @Override
            public void onFailure(Call<ProductTypeResponse> call, Throwable t) {
                loadingDialog.dismiss();
            }
        });
    }
}
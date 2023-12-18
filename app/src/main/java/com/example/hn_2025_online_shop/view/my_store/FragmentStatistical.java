package com.example.hn_2025_online_shop.view.my_store;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductRevenueAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentStatisticalBinding;
import com.example.hn_2025_online_shop.model.ProductRevenue;
import com.example.hn_2025_online_shop.model.ProductWithSoldQuantity;
import com.example.hn_2025_online_shop.model.response.statistical.RevenueByMonthResponse;
import com.example.hn_2025_online_shop.model.response.statistical.SoldQuantityProductResponse;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentStatistical extends Fragment  {
    private FragmentStatisticalBinding binding;
    private List<String> monthList = new ArrayList<>();
    private List<ProductWithSoldQuantity> list;
    private ProductRevenueAdapter adapter;
    private ProgressLoadingDialog dialog;
    private DecimalFormat format;

    // TODO: Rename parameter arguments, choose names that match

    public FragmentStatistical() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static FragmentStatistical newInstance() {
        FragmentStatistical fragment = new FragmentStatistical();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        monthList.add("Tất cả doanh thu");
        for(int i=1;i<=12;i++){
            monthList.add("Tháng "+ i);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStatisticalBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        format = new DecimalFormat("###,###,###,### VNĐ");
        list = new ArrayList<>();
        adapter = new ProductRevenueAdapter(getContext(), list);
        binding.rcvProductRevenue.setAdapter(adapter);
        ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, monthList);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerMonth.setAdapter(adapterMonth);
        dialog = new ProgressLoadingDialog(getContext());
        revenueByMonth();
        soldQuantityProduct();
    }

    private void soldQuantityProduct() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("storeId", Context.MODE_PRIVATE);
        String storeId = sharedPreferences.getString("storeId",null);
        dialog.show();
        BaseApi.API.soldQuantityProduct(storeId).enqueue(new Callback<SoldQuantityProductResponse>() {
            @Override
            public void onResponse(Call<SoldQuantityProductResponse> call, Response<SoldQuantityProductResponse> response) {
                if (response.isSuccessful()){
                    SoldQuantityProductResponse responseModel = response.body();
                    if (responseModel.getCode() == 200){
                        adapter.setListProductRevenue(responseModel.getData());
                        binding.rcvProductRevenue.setAdapter(adapter);
                        dialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<SoldQuantityProductResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Không kết nối được server", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void revenueByMonth() {

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("storeId", Context.MODE_PRIVATE);
        String storeId = sharedPreferences.getString("storeId",null);

        binding.spinerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dialog.show();
                if (i == 0) {
                    BaseApi.API.revenueAll(storeId).enqueue(new Callback<RevenueByMonthResponse>() {
                        @Override
                        public void onResponse(Call<RevenueByMonthResponse> call, Response<RevenueByMonthResponse> response) {
                            if (response.isSuccessful()) {
                                RevenueByMonthResponse response1 = response.body();
                                if (response1.getCode() == 200) {
                                    binding.tvRevenue.setText(format.format(response1.getData()));
                                }
                            } else {
                                try {
                                    String errorBody = response.errorBody().string();
                                    // Parse and display the error message
                                    JSONObject errorJson = new JSONObject(errorBody);
                                    String errorMessage = errorJson.getString("message");
                                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<RevenueByMonthResponse> call, Throwable t) {
                            dialog.dismiss();

                        }
                    });
                } else {
                    BaseApi.API.revenueByMonth(storeId, i).enqueue(new Callback<RevenueByMonthResponse>() {
                        @Override
                        public void onResponse(Call<RevenueByMonthResponse> call, Response<RevenueByMonthResponse> response) {
                            if (response.isSuccessful()) {
                                RevenueByMonthResponse response1 = response.body();
                                if (response1.getCode() == 200) {
                                    binding.tvRevenue.setText(format.format(response1.getData()));
                                }
                            } else {
                                try {
                                    String errorBody = response.errorBody().string();
                                    // Parse and display the error message
                                    JSONObject errorJson = new JSONObject(errorBody);
                                    String errorMessage = errorJson.getString("message");
                                    Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            dialog.dismiss();
                        }

                        @Override
                        public void onFailure(Call<RevenueByMonthResponse> call, Throwable t) {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("binh-er", "onFailure: " + t.getMessage());
                            dialog.dismiss();
                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

//    public void doanhThuAll(){
//        BaseApi.API.getListAllProduct().enqueue(new Callback<ProductResponse>() {
//            @Override
//            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
//                if(response.isSuccessful()){
//                    ProductResponse productResponse = response.body();
//                    productAdapter.setProductList(productResponse.getResult());
//                    binding.recyStore.setAdapter(productAdapter);
//                }else {
//                    Toast.makeText(getActivity(), "Call API  Products Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ProductResponse> call, Throwable t) {
//                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//    }
}
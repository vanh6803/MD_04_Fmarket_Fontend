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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductRevenueAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentStatisticalBinding;
import com.example.hn_2025_online_shop.model.ProductRevenue;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.model.response.RevenueByMonthResponse;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.StoreUltil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentStatistical extends Fragment  {
    private FragmentStatisticalBinding binding;
    private List<String> monthList = new ArrayList<>();
    private List<ProductRevenue> list;
    private ProductRevenueAdapter adapter;
    private ProgressLoadingDialog dialog;

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
        list = new ArrayList<>();
        list.add(new ProductRevenue("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQKl-dEvOQOCZCMc6CgX9nda0eY9VbuelCqsQx4Qtg2ix_JRvrEJIZ9jky4piAl_9StgFY&usqp=CAU", "Galaxy Z Fold 5", 800000));
        list.add(new ProductRevenue("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT2Pfx1Gwz7xanO0_X3VDrstawGhXZ_eKHtGw&usqp=CAU", "Galaxy Z Fold 5 ", 700000));
        list.add(new ProductRevenue("data:image/jpeg;base64,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" +
                "mlKdNT/Cir6hFMAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCEAhCED/2Q==", "Ipad Pro 2021", 600000));
        list.add(new ProductRevenue("data:image/jpeg;base64,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" +
                "sgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAH//Z", "Samsung S23 Ultra", 500000));
        list.add(new ProductRevenue("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRNztYv9VFuWG3Ze_w70YuRtfr0NFBl4gceGA&usqp=CAU", "Macbook Air 15-inch", 400000));
        adapter = new ProductRevenueAdapter(getContext(), list);
        binding.rcvProductRevenue.setAdapter(adapter);

        ArrayAdapter<String> adapterMonth = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, monthList);
        adapterMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinerMonth.setAdapter(adapterMonth);
        dialog = new ProgressLoadingDialog(getContext());
        revenueByMonth();
    }

    private void revenueByMonth() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("storeId", Context.MODE_PRIVATE);
        String storeId = sharedPreferences.getString("storeId",null);

        binding.spinerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dialog.show();
                BaseApi.API.revenueByMonth(storeId,i +1).enqueue(new Callback<RevenueByMonthResponse>() {
                    @Override
                    public void onResponse(Call<RevenueByMonthResponse> call, Response<RevenueByMonthResponse> response) {
                        if(response.isSuccessful()){
                            RevenueByMonthResponse response1 = response.body();
                            if (response1.getCode() == 200){
                                binding.tvRevenue.setText(""+response1.getData());
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
                    public void onFailure(Call<RevenueByMonthResponse> call, Throwable t) {
                        Toast.makeText(getContext(), "Không Call đươc API", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
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
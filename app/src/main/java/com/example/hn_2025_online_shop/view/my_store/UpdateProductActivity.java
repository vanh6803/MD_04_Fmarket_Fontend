package com.example.hn_2025_online_shop.view.my_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductByCategoryAdapter;
import com.example.hn_2025_online_shop.adapter.UpdateOptionAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityUpdateProductBinding;
import com.example.hn_2025_online_shop.model.OptionProduct;
import com.example.hn_2025_online_shop.model.ProductByCategory;
import com.example.hn_2025_online_shop.model.ProductDetail;
import com.example.hn_2025_online_shop.model.ProductType;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.model.response.ProductByCategoryReponse;
import com.example.hn_2025_online_shop.model.response.ProductTypeResponse;
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

public class UpdateProductActivity extends AppCompatActivity implements ObjectUtil {
    private ActivityUpdateProductBinding binding;
    private ProgressLoadingDialog dialog;
    private List<OptionProduct> listOption;
    private UpdateOptionAdapter updateOptionAdapter;
    private ProductDetail productDetail;
    private ProductType productType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressLoadingDialog(this);
        listOption = new ArrayList<>();
        updateOptionAdapter = new UpdateOptionAdapter(this, listOption, this);
        initView();
    }

    private void initView() {
        callApiUpdateDetailProduct();
    }




    private void callApiUpdateDetailProduct() {
        dialog.show();
        Intent intent = getIntent();
        String id_product = intent.getStringExtra("id_product");
        BaseApi.API.getDetailProduct(id_product).enqueue(new Callback<DetailProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<DetailProductResponse> call, @NonNull Response<DetailProductResponse> response) {
                if(response.isSuccessful()){
                    DetailProductResponse detailProductResponse = response.body();
                    assert detailProductResponse != null;
                    if (detailProductResponse.getCode() == 200){
                        productDetail = detailProductResponse.getResult();
                        binding.edtNameCategory.setText(productDetail.getCategory_id().getName());
                        updateOptionAdapter.setDataListOptionProduct(productDetail.getOption());
                        binding.rcvOptionProduct.setAdapter(updateOptionAdapter);
                        Log.d("gggg", "onResponse: " + detailProductResponse.getResult());
                        setDataUi(detailProductResponse);
                    }
                } else {
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        // Parse and display the error message
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure(@NonNull Call<DetailProductResponse> call, @NonNull Throwable t) {
                Toast.makeText(UpdateProductActivity.this, "Error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void setDataUi(DetailProductResponse detailProductResponse) {
        if ((detailProductResponse != null)){
            if(detailProductResponse.getResult().getName()!= null){
                binding.edtName.setText(detailProductResponse.getResult().getName());
            }else {
                binding.edtName.setText("No data");
            }
            if(detailProductResponse.getResult().getStatus()!= null){
                binding.edtTinhTrang.setText(detailProductResponse.getResult().getStatus());
            }else {
                binding.edtTinhTrang.setText("No data");
            }
            if(detailProductResponse.getResult().getDescription()!= null){
                binding.edtMota.setText(detailProductResponse.getResult().getDescription());
            }else {
                binding.edtMota.setText("No data");
            }
            if(detailProductResponse.getResult().getScreen()!= null){
                binding.edtSc.setText(detailProductResponse.getResult().getScreen());
            }else {
                binding.edtSc.setText("No data");
            }
            if(detailProductResponse.getResult().getCamera()!= null){
                binding.edtCamera.setText(detailProductResponse.getResult().getCamera());
            }else {
                binding.edtCamera.setText("No data");
            }
            if(detailProductResponse.getResult().getChipset()!= null){
                binding.edtChipset.setText(detailProductResponse.getResult().getChipset());
            }else {
                binding.edtChipset.setText("No data");
            }
            if(detailProductResponse.getResult().getCpu()!= null){
                binding.edtCpu.setText(detailProductResponse.getResult().getCpu());
            }else {
                binding.edtCpu.setText("No data");
            }
            if(detailProductResponse.getResult().getGpu()!= null){
                binding.edtGpu.setText(detailProductResponse.getResult().getCpu());
            }else {
                binding.edtGpu.setText("No data");
            }
            if(detailProductResponse.getResult().getRam() != 0){
                binding.edtRam.setText(detailProductResponse.getResult().getRam() + "");
            }else {
                binding.edtRam.setText("No data");
            }
            if(detailProductResponse.getResult().getRom() != 0){
                binding.edtRom.setText(detailProductResponse.getResult().getRam() + "");
            }else {
                binding.edtRom.setText("No data");
            }
            if(detailProductResponse.getResult().getOperatingSystem()!= null){
                binding.edtHeDieuHanh.setText(detailProductResponse.getResult().getOperatingSystem());
            }else {
                binding.edtHeDieuHanh.setText("No data");
            }
            
            if(detailProductResponse.getResult().getBattery()!= null){
                binding.edtBatrery.setText(detailProductResponse.getResult().getBattery());
            }else{
                binding.edtBatrery.setText("No data");
            }
            if(detailProductResponse.getResult().getWeight() != 0){
                binding.edtWeight.setText(detailProductResponse.getResult().getWeight() + "");
            }else {
                binding.edtWeight.setText("No data");
            }
            if(detailProductResponse.getResult().getConnection()!= null){
                binding.edtConnection.setText(detailProductResponse.getResult().getConnection());
            }else {
                binding.edtConnection.setText("No data");
            }
            if(detailProductResponse.getResult().getSpecialFeature()!= null){
                binding.edtSpecialFeature.setText(detailProductResponse.getResult().getSpecialFeature());
            }else{
                binding.edtSpecialFeature.setText("No data");
            }
            if(detailProductResponse.getResult().getManufacturer()!= null){
                binding.edtManufacturer.setText(detailProductResponse.getResult().getManufacturer());
            }else {
                binding.edtManufacturer.setText("No data");
            }
            if(detailProductResponse.getResult().getOther()!= null){
                binding.edtOther.setText(detailProductResponse.getResult().getOther());
            }else {
                binding.edtOther.setText("No data");
            }
            
        }

    }


    @Override
    public void onclickObject(Object object) {

    }
}
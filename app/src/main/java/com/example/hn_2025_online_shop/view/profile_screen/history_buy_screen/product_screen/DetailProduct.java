package com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductAdapter;
import com.example.hn_2025_online_shop.adapter.VoucherAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.DetailProductBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialogDetailProductBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductDetail;
import com.example.hn_2025_online_shop.model.ProductType;
import com.example.hn_2025_online_shop.model.Voucher;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.model.response.LoginResponse;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.home_screen.MainActivity;
import com.example.hn_2025_online_shop.view.login.Login;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.infor_shop.InforShop;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct extends AppCompatActivity {
    public DetailProductBinding binding;
    List<Product> productList;
    List<Voucher> voucherList;
    ProductAdapter productAdapter;
    VoucherAdapter voucherAdapter;
    ProgressLoadingDialog dialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressLoadingDialog(this);
        binding.textsale.setPaintFlags(binding.textsale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.backDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        productList = new ArrayList<>();
        voucherList = new ArrayList<>();
        for (int i = 1 ; i< 4; i++){
            voucherList.add(new Voucher("Giảm"+i+"%", "1234", ""));
        }
        productAdapter = new ProductAdapter(this, productList);
        voucherAdapter = new VoucherAdapter(this, voucherList);
        binding.recyProductSimilar.setAdapter(productAdapter);
        binding.recyVoucher.setAdapter(voucherAdapter);

        binding.showshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailProduct.this, InforShop.class));
            }
        });
        callApiDetailProduct();
        setDataSimilarProduct();

    }
    public void callApiDetailProduct(){
        dialog.show();
        Intent intent = getIntent();
        String id_product = intent.getStringExtra("id_product");
        BaseApi.API.getDetailProduct(id_product).enqueue(new Callback<DetailProductResponse>() {
            @Override
            public void onResponse(Call<DetailProductResponse> call, Response<DetailProductResponse> response) {
                if(response.isSuccessful()){
                    DetailProductResponse response1 = response.body();
                    if (response1.getCode() == 200){
                        setDataUi(response1);
                        showDetailProductDialog(response1);
                        Toast.makeText(DetailProduct.this, response1.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
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
            public void onFailure(Call<DetailProductResponse> call, Throwable t) {
                Toast.makeText(DetailProduct.this, "Error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
    private void setDataUi(DetailProductResponse response1) {

        if(response1 != null){
            List<SlideModel> listImg = new ArrayList<>();
            for(int i = 0; i< response1.getResult().getImage().size(); i++){
                String linkImg = response1.getResult().getImage().get(i);
                listImg.add(new SlideModel(linkImg, ScaleTypes.FIT));
            }
            Log.d(TAG.toString, "setDataUi: " + response1.getResult().getOption());
            if(response1.getResult().getOption().size() != 0){
                binding.tvPrice.setText(response1.getResult().getOption().get(0).getPrice()+ "" + "đ");
            }else{
                binding.tvPrice.setText("Không có dữ liệu trả về");
                Toast.makeText(this, response1.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(response1.getResult().getImage() != null){
                binding.imgProduct.setImageList(listImg, ScaleTypes.FIT);
            }else {
                Glide.with(DetailProduct.this).load(R.drawable.error);
            }

            if(response1.getResult().getName() != null){
                binding.tvName.setText(response1.getResult().getName());
            }else{
                binding.tvName.setText("Không có dữ liệu trả về");
            }

            if (response1.getResult().getStore_id().getName() != null) {
                binding.tvShop.setText(response1.getResult().getStore_id().getName());
            }else{
                binding.tvShop.setText("Không có dữ liệu trả về");
            }

            if(response1.getResult().getStore_id().getAddress() != null){
                binding.diachiShop.setText(response1.getResult().getStore_id().getAddress());
            }else {
                binding.diachiShop.setText("Không có dữ liệu trả về");
            }

            if(response1.getResult().getStore_id().getAvatar() != null){
                Glide.with(this)
                        .load(response1.getResult().getStore_id().getAvatar())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        .into(binding.imgShop);
            }else {
                Glide.with(this).load(R.drawable.error);
            }

        }else{
            Toast.makeText(this, "Không tìm thấy thông tin sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
    public void showDetailProductDialog(DetailProductResponse response1){
        binding.btnShowDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailProduct.this);
                LayoutDialogDetailProductBinding binding = LayoutDialogDetailProductBinding.inflate(getLayoutInflater());;
                builder.setView(binding.getRoot());
                binding.description.setText(response1.getResult().getDescription());
                binding.screen.setText("Screen: "+response1.getResult().getScreen());
                binding.camera.setText(response1.getResult().getCamera());
                binding.chipset.setText("Chipset: " + response1.getResult().getChipset());
                binding.cpu.setText("Cpu: "+response1.getResult().getCpu());
                binding.gpu.setText("Gpu: "+response1.getResult().getGpu());
                binding.operatingSystem.setText("OperatingSystem: "+response1.getResult().getOperatingSystem());
                binding.battery.setText("Battery: " + response1.getResult().getBattery());
                binding.weight.setText("Weight: " + response1.getResult().getWeight());
                binding.connection.setText("Connection: "+response1.getResult().getConnection());
                binding.specialFeature.setText("SpecialFeature: "+ response1.getResult().getSpecialFeature());
                binding.manufacturer.setText("Manufacturer: "+response1.getResult().getManufacturer());
                binding.other.setText("Other: "+response1.getResult().getOther());
                binding.btnbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    public void setDataSimilarProduct(){
        dialog.show();
        Intent intent = getIntent();
        String id_product = intent.getStringExtra("id_product");
        BaseApi.API.getDataSimilarlProduct(id_product).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse productResponse = response.body();
                    productAdapter.setProductList(productResponse.getResult());
                    binding.recyProductSimilar.setAdapter(productAdapter);
                }else {
                    Toast.makeText(DetailProduct.this, "Get Data Product Similar Error", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(DetailProduct.this, "call api err", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

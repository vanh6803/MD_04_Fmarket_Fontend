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
            productList.add(new Product("1", "sản phẩm", true, "https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", 11100000, 5.0, 1 ));
            voucherList.add(new Voucher("Giảm"+i+"%", "1234", ""));
        }
        productAdapter = new ProductAdapter(this, productList);
        voucherAdapter = new VoucherAdapter(this, voucherList);
        binding.recyProductSame.setAdapter(productAdapter);
        binding.recyVoucher.setAdapter(voucherAdapter);

        binding.showshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailProduct.this, InforShop.class));
            }
        });
        callApiDetailProduct();



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
            if(response1.getResult().getOption() != null){
                binding.tvPrice.setText(response1.getResult().getOption().get(0).getPrice()+ "" + "đ");
            }else{
                Toast.makeText(this, response1.getMessage(), Toast.LENGTH_SHORT).show();
            }
            binding.imgProduct.setImageList(listImg, ScaleTypes.FIT);
            binding.tvName.setText(response1.getResult().getName());

            binding.tvShop.setText(response1.getResult().getStore_id().getName());
            binding.diachiShop.setText(response1.getResult().getStore_id().getAddress());
            Glide.with(this)
                    .load(response1.getResult().getStore_id().getAvatar())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(binding.imgShop);
        }else{
            Toast.makeText(this, "Không tìm thấy thông tin sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
    public void showDetailProductDialog(DetailProductResponse response1){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        binding.btnShowDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutDialogDetailProductBinding binding = LayoutDialogDetailProductBinding.inflate(getLayoutInflater());
                LayoutInflater inflater = getLayoutInflater();
                View dialogview = inflater.inflate(R.layout.layout_dialog_detail_product, null);
                builder.setView(dialogview);
                binding.description.setText(response1.getResult().getDescription() + "");
                binding.screen.setText(response1.getResult().getScreen()+ "");
                binding.camera.setText(response1.getResult().getCamera()+ "");
                binding.chipset.setText(response1.getResult().getDescription()+ "");
                binding.cpu.setText(response1.getResult().getCpu()+ "");
                binding.gpu.setText(response1.getResult().getGpu()+ "");
                binding.operatingSystem.setText(response1.getResult().getOperatingSystem()+ "");
                binding.battery.setText(response1.getResult().getBattery()+ "");
                binding.weight.setText(response1.getResult().getWeight()+ "");
                binding.connection.setText(response1.getResult().getConnection()+ "");
                binding.specialFeature.setText(response1.getResult().getSpecialFeature()+ "");
                binding.manufacturer.setText(response1.getResult().getManufacturer()+ "");
                binding.other.setText(response1.getResult().getOther()+ "");
                builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                binding.btnDong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}

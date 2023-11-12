package com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.OptionAdapter;
import com.example.hn_2025_online_shop.adapter.ProductAdapter;
import com.example.hn_2025_online_shop.adapter.VoucherAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.DetailProductBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialigOptionProductBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialogDetailProductBinding;
import com.example.hn_2025_online_shop.model.OptionProduct;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductDetail;
import com.example.hn_2025_online_shop.model.Voucher;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.StoreUltil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.infor_shop.InforShop;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct extends AppCompatActivity {

    private DetailProductBinding binding;
    private List<Product> productList;
    private List<Voucher> voucherList;
    private ProductAdapter productAdapter;
    private VoucherAdapter voucherAdapter;
    private ProgressLoadingDialog dialog;
    private ProductDetail productDetail;
    private List<OptionProduct> optionProductList;
    private OptionAdapter optionAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initController();
        getVoucher();
        callApiDetailProduct();
        setDataSimilarProduct();
    }

    private void getVoucher() {
        productList = new ArrayList<>();
        voucherList = new ArrayList<>();
        for (int i = 1 ; i< 4; i++){
            voucherList.add(new Voucher("Giảm"+i+"%", "1234", ""));
        }
        productAdapter = new ProductAdapter(this, productList);
        voucherAdapter = new VoucherAdapter(this, voucherList);
        binding.recyProductSimilar.setAdapter(productAdapter);
        binding.recyVoucher.setAdapter(voucherAdapter);
    }

    public void callApiDetailProduct(){
        dialog.show();
        Intent intent = getIntent();
        String id_product = intent.getStringExtra("id_product");
        BaseApi.API.getDetailProduct(id_product).enqueue(new Callback<DetailProductResponse>() {
            @Override
            public void onResponse(Call<DetailProductResponse> call, Response<DetailProductResponse> response) {
                if(response.isSuccessful()){
                    DetailProductResponse detailProductResponse = response.body();
                    if (detailProductResponse.getCode() == 200){
                        productDetail = detailProductResponse.getResult();
                        setDataUi(detailProductResponse);
                        Toast.makeText(DetailProduct.this, detailProductResponse.getMessage(), Toast.LENGTH_SHORT).show();
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
    private void setDataUi(DetailProductResponse detailProductResponse) {

        if(detailProductResponse != null){
            List<SlideModel> listImg = new ArrayList<>();
            for(int i = 0; i< detailProductResponse.getResult().getImage().size(); i++){
                String linkImg = detailProductResponse.getResult().getImage().get(i);
                listImg.add(new SlideModel(linkImg, ScaleTypes.FIT));
            }
            Log.d(TAG.toString, "setDataUi: " + detailProductResponse.getResult().getOption());
            if(detailProductResponse.getResult().getOption().size() != 0){
                binding.tvPrice.setText(detailProductResponse.getResult().getOption().get(0).getPrice()+ "" + "đ");
            }else{
                binding.tvPrice.setText("Không có dữ liệu trả về");
                Toast.makeText(this, detailProductResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(detailProductResponse.getResult().getImage().size() != 0){
                binding.imgProduct.setImageList(listImg, ScaleTypes.FIT);
            }else {
                Glide.with(DetailProduct.this).load(R.drawable.error);
            }

            if(detailProductResponse.getResult().getName().length() != 0){
                binding.tvName.setText(detailProductResponse.getResult().getName());
            }else{
                binding.tvName.setText("Không có dữ liệu trả về");
            }

            if (detailProductResponse.getResult().getStore_id().getName().length() != 0) {
                binding.tvShop.setText(detailProductResponse.getResult().getStore_id().getName());
            }else{
                binding.tvShop.setText("Không có dữ liệu trả về");
            }

            if(detailProductResponse.getResult().getStore_id().getAddress().length() != 0){
                binding.diachiShop.setText(detailProductResponse.getResult().getStore_id().getAddress());
            }else {
                binding.diachiShop.setText("Không có dữ liệu trả về");
            }

            if(detailProductResponse.getResult().getStore_id().getAvatar().length() != 0){
                Glide.with(this)
                        .load(detailProductResponse.getResult().getStore_id().getAvatar())
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

    private void showDialogDetail() {
        if(productDetail == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailProduct.this);
        LayoutDialogDetailProductBinding binding = LayoutDialogDetailProductBinding.inflate(getLayoutInflater());;
        builder.setView(binding.getRoot());
        binding.description.setText(productDetail.getDescription());
        binding.screen.setText("Screen: "+productDetail.getScreen());
        binding.camera.setText("Cammera: " + productDetail.getCamera());
        binding.chipset.setText("Chipset: " + productDetail.getChipset());
        binding.cpu.setText("Cpu: "+productDetail.getCpu());
        binding.gpu.setText("Gpu: "+productDetail.getGpu());
        binding.operatingSystem.setText("OperatingSystem: "+productDetail.getOperatingSystem());
        binding.battery.setText("Battery: " + productDetail.getBattery());
        binding.weight.setText("Weight: " + productDetail.getWeight());
        binding.connection.setText("Connection: "+productDetail.getConnection());
        binding.specialFeature.setText("SpecialFeature: "+ productDetail.getSpecialFeature());
        binding.manufacturer.setText("Manufacturer: "+productDetail.getManufacturer());
        binding.other.setText("Other: "+productDetail.getOther());
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

    private void showBottomSheetDialog(Boolean isCheck) {
        if(productDetail == null) {
            return;
        }

        BottomSheetDialog dialog1 = new BottomSheetDialog(DetailProduct.this);
        LayoutDialigOptionProductBinding bindingoption = LayoutDialigOptionProductBinding.inflate(getLayoutInflater());
        dialog1.setContentView(bindingoption.getRoot());
        Window window = dialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setDataBottomSheetDialog(isCheck, bindingoption); // Set data cho bottom sheet
        optionProductList = new ArrayList<>();
        optionAdapter = new OptionAdapter(DetailProduct.this, optionProductList);
        optionAdapter.setDataListOptionProduct(productDetail.getOption());
        bindingoption.rcvOptionProduct.setAdapter(optionAdapter);
        dialog1.show();
    }

    private void setDataBottomSheetDialog(Boolean isCheck, LayoutDialigOptionProductBinding bindingoption) {
        if(isCheck) {
            bindingoption.btnSave.setText("Mua ngay");
        } else {
            bindingoption.btnSave.setText("Thêm vào giỏ hàng");
        }
        if(productDetail.getOption().size() != 0){
            Glide.with(DetailProduct.this).load(productDetail.getOption().get(0).getImage()).into(bindingoption.imageView);
        }else {
            Glide.with(DetailProduct.this).load(R.drawable.error).into(bindingoption.imageView);
        }
        if(productDetail.getOption().size() != 0){
            bindingoption.textView.setText(productDetail.getOption().get(0).getPrice() + "" + "đ");
        }else {
            bindingoption.textView.setText("No data");
        }
        if(productDetail.getOption().size() != 0){
            bindingoption.textView2.setText(productDetail.getOption().get(0).getSoldQuantity() + "");
        }else {
            bindingoption.textView2.setText("No data");
        }
    }

    private void initController() {
        binding.backDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.showshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productDetail!= null) {
                    String storeId = productDetail.getStore_id().toString();
                    Intent intent = new Intent(DetailProduct.this, InforShop.class);
                    intent.putExtra("storeId",storeId);
                    StoreUltil.store = productDetail.getStore_id();
                    startActivity(intent);
                }
            }
        });

        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(false);
            }
        });

        binding.btnBuyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(true);
            }
        });

        binding.btnShowDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDetail();
            }
        });
    }



    private void initView() {
        dialog = new ProgressLoadingDialog(this);
        binding.textsale.setPaintFlags(binding.textsale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

}

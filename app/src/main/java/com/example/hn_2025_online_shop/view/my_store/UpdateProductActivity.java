package com.example.hn_2025_online_shop.view.my_store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.OptionAdapter;
import com.example.hn_2025_online_shop.adapter.ProductByCategoryAdapter;
import com.example.hn_2025_online_shop.adapter.UpdateOptionAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityUpdateProductBinding;
import com.example.hn_2025_online_shop.databinding.DialogCreateOptionProductBinding;
import com.example.hn_2025_online_shop.databinding.DialogUpdateOptionProductBinding;
import com.example.hn_2025_online_shop.model.OptionProduct;
import com.example.hn_2025_online_shop.model.ProductByCategory;
import com.example.hn_2025_online_shop.model.ProductDetail;
import com.example.hn_2025_online_shop.model.ProductType;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.model.response.ProductByCategoryReponse;
import com.example.hn_2025_online_shop.model.response.ProductTypeResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ApiUtil;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.OptionUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.buy_product.AddressActivity;
import com.example.hn_2025_online_shop.view.buy_product.UpdateAddressActivity;
import com.example.hn_2025_online_shop.view.product_screen.DetailProduct;
import com.example.hn_2025_online_shop.view.profile_screen.ProfileUserScreen;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProductActivity extends AppCompatActivity implements ObjectUtil {
    private ActivityUpdateProductBinding binding;
    private ProgressLoadingDialog dialog;
    public static String optionId;
    private List<OptionProduct> listOption;
    private OptionAdapter optionAdapter;
    private ProductDetail productDetail;
    private ProductType productType;
    private MultipartBody.Part fileImgAvatar;
    private int isCheckImage = 0; // 1 là avatar
    private boolean isCamera = false; // kiểm tra xem avatar có dữ liệu hay chưa

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initController();
    }

    private void initController() {
        binding.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edtName.getText().toString();
                String description = binding.edtMota.getText().toString();
                String tinhTrang = binding.edtTinhTrang.getText().toString();
                String screen = binding.edtSc.getText().toString();
                String camera = binding.edtCamera.getText().toString();
                String chipset = binding.edtChipset.getText().toString();
                String cpu = binding.edtCpu.getText().toString();
                String gpu = binding.edtGpu.getText().toString();
                String inputRam = binding.edtRam.getText().toString();
                String inputRom = binding.edtRom.getText().toString();
                int ram = inputRam.isEmpty() ?0 :Integer.parseInt(inputRam)  ;
                int rom = inputRom.isEmpty() ?0: Integer.parseInt(inputRom);
                String operatingSystem = binding.edtHeDieuHanh.getText().toString();
                String battery = binding.edtBatrery.getText().toString();
                int weight = Integer.parseInt(binding.edtWeight.getText().toString());
                String connection = binding.edtConnection.getText().toString();
                String specialFeature = binding.edtSpecialFeature.getText().toString();
                String manufacturer = binding.edtManufacturer.getText().toString();
                String other = binding.edtOther.getText().toString();
                updateProductMyStore(name, description, tinhTrang, screen,camera,chipset,cpu,gpu,ram,rom,operatingSystem,battery,
                        weight, connection, specialFeature, manufacturer,other);
            }
        });
        binding.btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void updateProductMyStore(String name, String description, String tinhTrang, String screen, String camera, String chipset,
                                      String cpu, String gpu, int ram, int rom, String operatingSystem, String battery, int weight,
                                      String connection, String specialFeature, String manufacturer, String other) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        Intent intent = getIntent();
        String id_product = intent.getStringExtra("id_product");

        dialog.show();
        BaseApi.API.updateProduct(token, id_product, name, description, tinhTrang, screen, camera, chipset, cpu, gpu, ram, rom,
                operatingSystem, battery, weight, connection, specialFeature, manufacturer, other).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    Log.d(TAG.toString, "onResponse-editInfo: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(getApplicationContext(), "Update Product Successfully ! ", Toast.LENGTH_SHORT).show();
                        onBackPressed();

                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-editInfo: " + errorMessage);
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
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-deleteInfo: " + t.toString());
                dialog.dismiss();
            }
        });
    }


    private void initView() {
        dialog = new ProgressLoadingDialog(this);
        listOption = new ArrayList<>();
        optionAdapter = new OptionAdapter(this, listOption, this);
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
                        optionAdapter.setDataListOptionProduct(productDetail.getOption());
                        binding.rcvOptionProduct.setAdapter(optionAdapter);
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
                binding.lnRam.setVisibility(View.GONE);
                binding.edtRam.setVisibility(View.GONE);
            }
            if(detailProductResponse.getResult().getRom() != 0){
                binding.edtRom.setText(detailProductResponse.getResult().getRam() + "");
            }else {
                binding.lnRom.setVisibility(View.GONE);
                binding.edtRom.setVisibility(View.GONE);
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
        OptionProduct optionProduct= (OptionProduct) object;
         optionId = optionProduct.getId();
        Log.d("optionId", "onclickObject: " + optionId);
        showDiaLogUpdateOption();
    }


    private void showDiaLogUpdateOption() {
        BottomSheetDialog dialog1 = new BottomSheetDialog(UpdateProductActivity.this);
        binding1 = DialogUpdateOptionProductBinding.inflate(getLayoutInflater());
        dialog1.setContentView(binding1.getRoot());
        Window window = dialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
//      initData();

        binding1.imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with((UpdateProductActivity.this))
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
                isCheckImage = 1;
            }
        });
        binding1.btnDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog1.dismiss();
            }
        });
        binding1.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding1.edtNameColor.getText().toString();
                int price = Integer.parseInt(binding1.edtPrice.getText().toString());
                int discount = Integer.parseInt(binding1.edtDiscountValue.getText().toString());
                int quantity = Integer.parseInt(binding1.edtQuantity.getText().toString());
                UpdateOptionProduct(name, price, discount, quantity);
            }
        });
        dialog1.show();
    }

    private void initData() {
        binding1.edtNameColor.setText(OptionUltil.OPTION.getNameColor());
        binding1.edtPrice.setText(OptionUltil.OPTION.getPrice());
        binding1.edtQuantity.setText(OptionUltil.OPTION.getQuantity());
        binding1.edtDiscountValue.setText(OptionUltil.OPTION.getDiscountValue());
        Glide.with(this).load(OptionUltil.OPTION.getImage()).error(R.drawable.error).into(binding1.imgAvartar);
    }

    private void UpdateOptionProduct(String name, int price, int discount, int quantity) {
        dialog.show();
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        BaseApi.API.updateOption(token, optionId, name, price, discount, quantity ).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    Log.d(TAG.toString, "onResponse-UpdateOptionProduct: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(getApplicationContext(), "Update Option Product Successfully", Toast.LENGTH_SHORT).show();
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-UpdateOptionProduct: " + errorMessage);
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
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-UpdateOptionProduct: " + t.toString());
                dialog.dismiss();

            }
        });

    }

    private String getPath(Uri uri){
        String result;
        Cursor cursor = getContentResolver()
                .query(uri, null,null,null,null);
        if (cursor == null){
            result = uri.getPath();
        }else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(index);
            cursor.close();
        }
        return result;
    }
    private DialogUpdateOptionProductBinding binding1;
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            File file = new File(getPath(uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            if(isCheckImage == 1) {
                isCamera = true;
                binding1.imgAvartar.setImageURI(uri);
                fileImgAvatar = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
              updateImageOption(fileImgAvatar);
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getApplicationContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            isCamera = false;

        } else {
            Toast.makeText(getApplicationContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
            isCamera = false;
        }
    }

    private void updateImageOption(MultipartBody.Part fileImgAvatar) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        dialog.show();
        BaseApi.API.updateImageOption(token, optionId, fileImgAvatar).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    assert serverResponse != null;
                    Log.d(TAG.toString, "onResponse-uploadAvatar: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(UpdateProductActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-uploadAvatar: " + errorMessage);
                        Toast.makeText(UpdateProductActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Toast.makeText(UpdateProductActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-uploadAvatar: " + t.toString());
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        callApiUpdateDetailProduct();
    }
}
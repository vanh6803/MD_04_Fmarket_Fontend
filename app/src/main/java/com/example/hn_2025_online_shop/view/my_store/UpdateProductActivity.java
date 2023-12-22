package com.example.hn_2025_online_shop.view.my_store;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.OptionAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityUpdateProductBinding;
import com.example.hn_2025_online_shop.databinding.DialogCreateOptionProductBinding;
import com.example.hn_2025_online_shop.databinding.DialogUpdateOptionProductBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialogDeleteOptionBinding;
import com.example.hn_2025_online_shop.model.OptionProduct;
import com.example.hn_2025_online_shop.model.ProductDetail;
import com.example.hn_2025_online_shop.model.ProductType;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.OptionUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
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

public class UpdateProductActivity extends AppCompatActivity implements OptionUtil {
    private ActivityUpdateProductBinding binding;
    private ProgressLoadingDialog dialog;
    public static String optionId;
    private List<OptionProduct> listOption;
    private OptionAdapter optionAdapter;
    private ProductDetail productDetail;
    private ProductType productType;
    private MultipartBody.Part fileImgAvatar;
    private MultipartBody.Part fileImgProduct;
    private int isCheckImage = 0; // 1 là avatar
    private boolean isCamera = false; // kiểm tra xem avatar có dữ liệu hay chưa
    private boolean isImgProduct = false;

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
        binding.imgCreateOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDiaLogCreateOptionProduct();
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });

    }

    private void showDiaLogCreateOptionProduct() {
        BottomSheetDialog dialog2 = new BottomSheetDialog(this);
        binding2 = DialogCreateOptionProductBinding.inflate(getLayoutInflater());
        dialog2.setContentView(binding2.getRoot());
        Window window = dialog2.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        binding2.imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with((UpdateProductActivity.this))
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
                isCheckImage = 2;
            }
        });
        binding2.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog2.dismiss();
            }
        });
        binding2.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding2.edtNameColor.getText().toString();
                String inputprice = binding2.edtPrice.getText().toString();
                String inputdiscount = binding2.edtDiscountValue.getText().toString();
                String inputquantity = binding2.edtQuantity.getText().toString();
                int price = inputprice.isEmpty() ?0 :Integer.parseInt(inputprice)  ;
                int discount = inputdiscount.isEmpty() ?0: Integer.parseInt(inputdiscount);
                int quantity = inputquantity.isEmpty() ?0: Integer.parseInt(inputquantity);
                Boolean checkHotOption = binding2.chkHotOption.isChecked();
                CreateOptionProduct(name, price, discount, quantity, checkHotOption);
            }
        });
        dialog2.show();
    }

    private void CreateOptionProduct(String name, int price, int discount, int quantity, Boolean checkHotOption) {
        if (checkValidateOptionProduct(name, price, discount, quantity)){
            dialog.show();
            Intent intent = getIntent();
            String id_product = intent.getStringExtra("id_product");
            String token = AccountUltil.BEARER + AccountUltil.TOKEN;
            RequestBody requestBodyProductId = RequestBody.create(MediaType.parse("multipart/form-data"), id_product);
            RequestBody requestBodyName = RequestBody.create(MediaType.parse("multipart/form-data"), name);
            RequestBody requestBodyPrice = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(price));
            RequestBody requestBodyDisscount = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(discount));
            RequestBody requestBodyquantity = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(quantity));
            RequestBody requestBodyHotOption = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(checkHotOption));
            BaseApi.API.createOption(token, requestBodyProductId, requestBodyName, fileImgProduct, requestBodyPrice, requestBodyDisscount, requestBodyquantity, requestBodyHotOption).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.isSuccessful()){ // chỉ nhận đầu status 200
                        ServerResponse serverResponse = response.body();
                        Log.d(TAG.toString, "onResponse-CreateOptionProduct: " + serverResponse.toString());
                        if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                            Toast.makeText(getApplicationContext(), "Create Option Product Successfully", Toast.LENGTH_SHORT).show();
                            callApiUpdateDetailProduct();
                        }
                    } else { // nhận các đầu status #200
                        try {
                            String errorBody = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Log.d(TAG.toString, "onResponse-CreateOptionProduct: " + errorMessage);
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
                    Log.d(TAG.toString, "onFailure-CreateOptionProduct: " + t.toString());
                    dialog.dismiss();

                }
            });

        }

    }
    private boolean checkValidateOptionProduct(String name, int price, int discount, int quantity ) {
        if(TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Mời nhập màu sản phẩm", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!isImgProduct) {
            Toast.makeText(this, "Hãy chọn Image", Toast.LENGTH_SHORT).show();
            return false;
        } else if(price == 0) {
            Toast.makeText(this, "Mời nhập giá sp", Toast.LENGTH_SHORT).show();
            return false;
        } else if(quantity == 0) {
            Toast.makeText(this, "Mời nhập số lượng sản phẩm", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
        optionAdapter = new OptionAdapter(this, listOption);
        optionAdapter.setOptionUtil(this);
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
                binding.edtTinhTrang.setText("mới");
            }
            if(detailProductResponse.getResult().getDescription()!= null){
                binding.edtMota.setText(detailProductResponse.getResult().getDescription());
            }else {
                binding.edtMota.setText("No data");
            }
            if(detailProductResponse.getResult().getScreen()!= null){
                binding.edtSc.setText(detailProductResponse.getResult().getScreen());
            }else {
                binding.lnSc.setVisibility(View.GONE);
                binding.edtSc.setVisibility(View.GONE);
            }
            if(detailProductResponse.getResult().getCamera()!= null){
                binding.edtCamera.setText(detailProductResponse.getResult().getCamera());
            }else {
                binding.edtCamera.setVisibility(View.GONE);
                binding.lnCamera.setVisibility(View.GONE);
            }
            if(detailProductResponse.getResult().getChipset()!= null){
                binding.edtChipset.setText(detailProductResponse.getResult().getChipset());
            }else {
                binding.edtChipset.setVisibility(View.GONE);
                binding.lnChipset.setVisibility(View.GONE);
            }
            if(detailProductResponse.getResult().getCpu()!= null){
                binding.edtCpu.setText(detailProductResponse.getResult().getCpu());
            }else {
                binding.edtCpu.setVisibility(View.GONE);
                binding.lnCpu.setVisibility(View.GONE);
            }
            if(detailProductResponse.getResult().getGpu()!= null){
                binding.edtGpu.setText(detailProductResponse.getResult().getCpu());
            }else {
                binding.edtGpu.setVisibility(View.GONE);
                binding.lnGpu.setVisibility(View.GONE);
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
                binding.edtHeDieuHanh.setVisibility(View.GONE);
                binding.lnHeDieuHanh.setVisibility(View.GONE);
            }
            
            if(detailProductResponse.getResult().getBattery()!= null){
                binding.edtBatrery.setText(detailProductResponse.getResult().getBattery());
            }else{
                binding.edtBatrery.setVisibility(View.GONE);
                binding.lnBattery.setVisibility(View.GONE);
            }
            if(detailProductResponse.getResult().getWeight() != 0){
                binding.edtWeight.setText(detailProductResponse.getResult().getWeight() + "");
            }else {
                binding.edtWeight.setVisibility(View.GONE);
                binding.lnWeight.setVisibility(View.GONE);
            }
            if(detailProductResponse.getResult().getConnection()!= null){
                binding.edtConnection.setText(detailProductResponse.getResult().getConnection());
            }else {
                binding.edtConnection.setVisibility(View.GONE);
                binding.lnConnection.setVisibility(View.GONE);
            }
            if(detailProductResponse.getResult().getSpecialFeature()!= null){
                binding.edtSpecialFeature.setText(detailProductResponse.getResult().getSpecialFeature());
            }else{
                binding.edtSpecialFeature.setText("Mời nhập");
            }
            if(detailProductResponse.getResult().getManufacturer()!= null){
                binding.edtManufacturer.setText(detailProductResponse.getResult().getManufacturer());
            }else {
                binding.edtManufacturer.setText("");
            }
            if(detailProductResponse.getResult().getOther()!= null){
                binding.edtOther.setText(detailProductResponse.getResult().getOther());
            }else {
                binding.edtOther.setText("");
            }
            
        }

    }

    private void showDiaLogUpdateOption(OptionProduct optionProduct) {
        BottomSheetDialog dialog1 = new BottomSheetDialog(UpdateProductActivity.this);
        binding1 = DialogUpdateOptionProductBinding.inflate(getLayoutInflater());
        dialog1.setContentView(binding1.getRoot());
        Window window = dialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        binding1.edtNameColor.setText(optionProduct.getNameColor());
        binding1.edtPrice.setText(optionProduct.getPrice() + "");
        binding1.edtQuantity.setText(optionProduct.getQuantity() + "");
        binding1.edtDiscountValue.setText(optionProduct.getDiscountValue() + "");
        binding1.chkHotOption.setChecked(optionProduct.isHot_option());
        Glide.with(this)
                .load(optionProduct.getImage())
                .error(R.drawable.error)
                .into(binding1.imgAvartar);


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
        binding1.btnHuy.setOnClickListener(new View.OnClickListener() {
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
                boolean chkHotOption = binding1.chkHotOption.isChecked();
                UpdateOptionProduct(name, price, discount, quantity, chkHotOption, dialog1);
            }
        });
        dialog1.show();
    }

    private void UpdateOptionProduct(String name, int price, int discount, int quantity, boolean chkHotOption, BottomSheetDialog dialog1) {
        dialog.show();
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        BaseApi.API.updateOption(token, optionId, name, price, discount, quantity, chkHotOption).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    Log.d(TAG.toString, "onResponse-UpdateOptionProduct: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(getApplicationContext(), "Update Option Product Successfully", Toast.LENGTH_SHORT).show();
                        dialog1.dismiss();
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
    private DialogCreateOptionProductBinding binding2;
    private DialogUpdateOptionProductBinding binding1;
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            File file = new File(getPath(uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            if(isCheckImage == 1) { // update option product
                isCamera = true;
                binding1.imgAvartar.setImageURI(uri);
                fileImgAvatar = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                updateImageOption(fileImgAvatar);
            } else if (isCheckImage == 2) { // create option product
                isImgProduct = true;
                binding2.imgAvartar.setImageURI(uri);
                fileImgProduct = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getApplicationContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            isCamera = false;
            isImgProduct= false;

        } else {
            Toast.makeText(getApplicationContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
            isCamera = false;
            isImgProduct= false;
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }

    @Override
    public void onclickOption(OptionProduct optionProduct) {
        optionId = optionProduct.getId();
        Log.d("optionId", "onclickObject: " + optionId);
        showDiaLogUpdateOption(optionProduct);
    }

    @Override
    public void deleteOption(OptionProduct optionProduct) {
        LayoutDialogDeleteOptionBinding bindingDelete = LayoutDialogDeleteOptionBinding.inflate(getLayoutInflater());
        Dialog dialog = new Dialog(this);
        dialog.setContentView(bindingDelete.getRoot());
        Window window = dialog.getWindow();
        assert window != null;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Glide.with(this)
                .load(optionProduct.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(bindingDelete.imgOptionProduct);
        bindingDelete.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        bindingDelete.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteOptionProduct(optionProduct);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void deleteOptionProduct(OptionProduct optionProduct) {
        Toast.makeText(this, "You don't option", Toast.LENGTH_SHORT).show();
//        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
//        BaseApi.API.deleteOption(token, optionProduct.getId()).enqueue(new Callback<ServerResponse>() {
//            @Override
//            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
//                if(response.isSuccessful()){ // chỉ nhận đầu status 200
//                    ServerResponse serverResponse = response.body();
//                    assert serverResponse != null;
//                    Log.d(TAG.toString, "onResponse-deleteOption: " + serverResponse.toString());
//                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
//                        Toast.makeText(UpdateProductActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                } else { // nhận các đầu status #200
//                    try {
//                        assert response.errorBody() != null;
//                        String errorBody = response.errorBody().string();
//                        JSONObject errorJson = new JSONObject(errorBody);
//                        String errorMessage = errorJson.getString("message");
//                        Log.d(TAG.toString, "onResponse-deleteOption: " + errorMessage);
//                        Toast.makeText(UpdateProductActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<ServerResponse> call, Throwable t) {
//                Toast.makeText(UpdateProductActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
//                Log.d(TAG.toString, "onFailure-deleteOption: " + t.toString());
//                dialog.dismiss();
//            }
//        });
    }
}
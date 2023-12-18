package com.example.hn_2025_online_shop.view.my_store;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.SpinnerCategoryAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.DialogCreateOptionProductBinding;
import com.example.hn_2025_online_shop.databinding.FragmentCreateProductMyStoreBinding;
import com.example.hn_2025_online_shop.model.ProductType;
import com.example.hn_2025_online_shop.model.response.CreateProductResponse;
import com.example.hn_2025_online_shop.model.response.ProductTypeResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.buy_product.UpdateAddressActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class FragmentCreateProductMyStore extends Fragment{
    private SpinnerCategoryAdapter spinnerCategoryAdapter;
    public static String categoryId;
    public static String productId;
    private MultipartBody.Part fileImgAvatar;
    private int isCheckImage = 0; // 1 là avatar
    private boolean isCamera = false; // kiểm tra xem avatar có dữ liệu hay chưa
    private ProgressLoadingDialog dialog;
    private FragmentCreateProductMyStoreBinding binding;



    public FragmentCreateProductMyStore() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentCreateProductMyStore newInstance() {
        FragmentCreateProductMyStore fragment = new FragmentCreateProductMyStore();
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
        binding = FragmentCreateProductMyStoreBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initController();
        getListCategory();
    }
    private void updateCategoryList(List<ProductType> list) {
        spinnerCategoryAdapter = new SpinnerCategoryAdapter(getContext(),R.layout.iteam_selected, list);
        binding.spinnerCategory.setAdapter(spinnerCategoryAdapter);

        binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ProductType productType = (ProductType) spinnerCategoryAdapter.getItem(i);
                categoryId  = productType.getId();
                Log.d(TAG.toString, "onclickObject: "+ categoryId);
                clickCategory(productType.getName());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getListCategory() {
        dialog.show();
        BaseApi.API.getListTypeProduct().enqueue(new Callback<ProductTypeResponse>() {
            @Override
            public void onResponse(Call<ProductTypeResponse> call, Response<ProductTypeResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ProductTypeResponse reponse = response.body();
                    Log.d(TAG.toString, "onResponse-getListProductType: " + reponse.toString());
                    if(reponse.getCode() == 200) {
                        List<ProductType> list = reponse.getData();
                        updateCategoryList(list);

                        Log.d(TAG.toString, "onResponse: " + list);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getListProductType: " + errorMessage);
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ProductTypeResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();

            }
        });
    }


    public void createProductMyStore(String name, String description, String tinhTrang, String screen,
                                     String camera, String chipset,String cpu, String gpu, int ram, int rom,
                                     String operatingSystem, String battery,
                                     int weight,String connection, String specialFeature, String manufacture,
                                     String other){
        if(checkValidateCreateProduct(name, description, tinhTrang, screen, camera, chipset, cpu, gpu, ram, rom,
                operatingSystem, battery, weight,
                connection, specialFeature, manufacture, other)){
            String token = AccountUltil.BEARER + AccountUltil.TOKEN;
            dialog.show();
            BaseApi.API.createProductMyStore(
                    token, categoryId, name, description,
                    tinhTrang, screen, camera,
                    chipset, cpu, gpu,ram, rom, operatingSystem,
                    battery,weight, connection, specialFeature, manufacture,
                    other).enqueue(new Callback<CreateProductResponse>() {
                @Override
                public void onResponse(Call<CreateProductResponse> call, Response<CreateProductResponse> response) {
                    if(response.isSuccessful()){ // chỉ nhận đầu status 200
                        CreateProductResponse productResponse = response.body();
                        Log.d(TAG.toString, "onResponse-createProduct: " + productResponse.toString());
                        if(productResponse.getCode() == 200 || productResponse.getCode() == 201) {
                            Toast.makeText(getContext(), "Create Product Is Successfully!", Toast.LENGTH_SHORT).show();
                            productId = productResponse.getResult().getId();
                            showDiaLogCreateOptionProduct();
                        }

                    } else { // nhận các đầu status #200
                        try {
                            String errorBody = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Log.d(TAG.toString, "onResponse-createProduct: " + errorMessage);
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
                public void onFailure(Call<CreateProductResponse> call, Throwable t) {
                    Toast.makeText(getContext(), "Call api Error", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
    private boolean checkValidateCreateProduct(String name, String description, String tinhTrang, String screen,
                                               String camera, String chipset,String cpu, String gpu, int ram, int rom, String operatingSystem, String battery,
                                               int weight,String connection, String specialFeature, String manufacture,
                                               String other){
        if(TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "Mời nhập tên sản phẩm", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(tinhTrang)) {
            Toast.makeText(getContext(), "Mời nhập mới sản phẩm", Toast.LENGTH_SHORT).show();
            return false;

        }else if (TextUtils.isEmpty(description)) {
            Toast.makeText(getContext(), "Mời nhập mô tả sản phẩm", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showDiaLogCreateOptionProduct() {
        BottomSheetDialog dialog1 = new BottomSheetDialog(getContext());
        binding1 = DialogCreateOptionProductBinding.inflate(getLayoutInflater());
        dialog1.setContentView(binding1.getRoot());
        Window window = dialog1.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        binding1.imgCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with((FragmentCreateProductMyStore.this))
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
                String inputprice = binding1.edtPrice.getText().toString();
                String inputdiscount = binding1.edtDiscountValue.getText().toString();
                String inputquantity = binding1.edtQuantity.getText().toString();
                int price = inputprice.isEmpty() ?0 :Integer.parseInt(inputprice)  ;
                int discount = inputdiscount.isEmpty() ?0: Integer.parseInt(inputdiscount);
                int quantity = inputquantity.isEmpty() ?0: Integer.parseInt(inputquantity);
                Boolean checkHotOption = binding1.chkHotOption.isChecked();
                CreateOptionProduct(name, price, discount, quantity, checkHotOption);
            }
        });

        dialog1.show();
    }


    private void CreateOptionProduct(String name, int price, int discount, int quantity, Boolean checkHotOption) {
        if (checkValidateOptionProduct(name, price, discount, quantity)){
            dialog.show();
            String token = AccountUltil.BEARER + AccountUltil.TOKEN;
            RequestBody requestBodyProductId = RequestBody.create(MediaType.parse("multipart/form-data"), productId);
            RequestBody requestBodyName = RequestBody.create(MediaType.parse("multipart/form-data"), name);
            RequestBody requestBodyPrice = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(price));
            RequestBody requestBodyDisscount = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(discount));
            RequestBody requestBodyquantity = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(quantity));
            RequestBody requestBodyHotOption = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(checkHotOption));
            BaseApi.API.createOption(token, requestBodyProductId, requestBodyName, fileImgAvatar, requestBodyPrice, requestBodyDisscount, requestBodyquantity, requestBodyHotOption).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.isSuccessful()){ // chỉ nhận đầu status 200
                        ServerResponse serverResponse = response.body();
                        Log.d(TAG.toString, "onResponse-CreateOptionProduct: " + serverResponse.toString());
                        if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                            Toast.makeText(getContext(), "Create Option Product Successfully", Toast.LENGTH_SHORT).show();
                        }
                    } else { // nhận các đầu status #200
                        try {
                            String errorBody = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Log.d(TAG.toString, "onResponse-CreateOptionProduct: " + errorMessage);
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
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG.toString, "onFailure-CreateOptionProduct: " + t.toString());
                    dialog.dismiss();

                }
            });

        }

    }
    private boolean checkValidateOptionProduct(String name, int price, int discount, int quantity ) {
        if(TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "Mời nhập màu sản phẩm", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!isCamera) {
            Toast.makeText(getContext(), "Hãy chọn Image", Toast.LENGTH_SHORT).show();
            return false;
        } else if(price == 0) {
            Toast.makeText(getContext(), "Mời nhập giá sp", Toast.LENGTH_SHORT).show();
            return false;
        } else if(quantity == 0) {
            Toast.makeText(getContext(), "Mời nhập số lượng sản phẩm", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }




    private DialogCreateOptionProductBinding binding1;
    @Override
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
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            isCamera = false;

        } else {
            Toast.makeText(getContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
            isCamera = false;
        }
    }
    private String getPath(Uri uri){
        String result;
        Cursor cursor = getContext().getContentResolver()
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

    private void clickCategory(String name) {
        if(name.equals("Tai Nghe")){
            binding.edtSC.setVisibility(View.GONE);
            binding.tvSc.setVisibility(View.GONE);
            binding.edtCamera.setVisibility(View.GONE);
            binding.tvCamera.setVisibility(View.GONE);
            binding.edtChipset.setVisibility(View.GONE);
            binding.tvChipset.setVisibility(View.GONE);
            binding.edtCpu.setVisibility(View.GONE);
            binding.tvCpu.setVisibility(View.GONE);
            binding.edtGpu.setVisibility(View.GONE);
            binding.tvGpu.setVisibility(View.GONE);
            binding.edtRam.setVisibility(View.GONE);
            binding.tvRam.setVisibility(View.GONE);
            binding.edtRom.setVisibility(View.GONE);
            binding.tvRom.setVisibility(View.GONE);
            binding.edtHeDieuHanh.setVisibility(View.GONE);
            binding.tvHeDieuHanh.setVisibility(View.GONE);
        } else if (name.equals("Bàn phím")) {
            binding.edtSC.setVisibility(View.GONE);
            binding.tvSc.setVisibility(View.GONE);
            binding.edtCamera.setVisibility(View.GONE);
            binding.tvCamera.setVisibility(View.GONE);
            binding.edtChipset.setVisibility(View.GONE);
            binding.tvChipset.setVisibility(View.GONE);
            binding.edtCpu.setVisibility(View.GONE);
            binding.tvCpu.setVisibility(View.GONE);
            binding.edtGpu.setVisibility(View.GONE);
            binding.tvGpu.setVisibility(View.GONE);
            binding.edtRam.setVisibility(View.GONE);
            binding.tvRam.setVisibility(View.GONE);
            binding.edtRom.setVisibility(View.GONE);
            binding.tvRom.setVisibility(View.GONE);
            binding.edtHeDieuHanh.setVisibility(View.GONE);
            binding.tvHeDieuHanh.setVisibility(View.GONE);
        } else if (name.equals("Camera an ninh")) {
            binding.edtSC.setVisibility(View.GONE);
            binding.tvSc.setVisibility(View.GONE);
            binding.edtChipset.setVisibility(View.GONE);
            binding.tvChipset.setVisibility(View.GONE);
            binding.edtCpu.setVisibility(View.GONE);
            binding.tvCpu.setVisibility(View.GONE);
            binding.edtGpu.setVisibility(View.GONE);
            binding.tvGpu.setVisibility(View.GONE);
            binding.edtRam.setVisibility(View.GONE);
            binding.tvRam.setVisibility(View.GONE);
            binding.edtRom.setVisibility(View.GONE);
            binding.tvRom.setVisibility(View.GONE);
            binding.edtHeDieuHanh.setVisibility(View.GONE);
            binding.tvHeDieuHanh.setVisibility(View.GONE);
        } else if (name.equals("Đồ hồ thông minh")) {
            binding.edtSC.setVisibility(View.VISIBLE);
            binding.tvSc.setVisibility(View.VISIBLE);
            binding.edtCamera.setVisibility(View.GONE);
            binding.tvCamera.setVisibility(View.GONE);
            binding.edtChipset.setVisibility(View.GONE);
            binding.tvChipset.setVisibility(View.GONE);
            binding.edtCpu.setVisibility(View.GONE);
            binding.tvCpu.setVisibility(View.GONE);
            binding.edtGpu.setVisibility(View.GONE);
            binding.tvGpu.setVisibility(View.GONE);
            binding.edtRam.setVisibility(View.GONE);
            binding.tvRam.setVisibility(View.GONE);
            binding.edtRom.setVisibility(View.GONE);
            binding.tvRom.setVisibility(View.GONE);
            binding.edtHeDieuHanh.setVisibility(View.GONE);
            binding.tvHeDieuHanh.setVisibility(View.GONE);
        } else if (name.equals("Điện Thoại")) {
            binding.edtSC.setVisibility(View.VISIBLE);
            binding.tvSc.setVisibility(View.VISIBLE);
            binding.edtCamera.setVisibility(View.VISIBLE);
            binding.tvCamera.setVisibility(View.VISIBLE);
            binding.edtChipset.setVisibility(View.VISIBLE);
            binding.tvChipset.setVisibility(View.VISIBLE);
            binding.edtCpu.setVisibility(View.VISIBLE);
            binding.tvCpu.setVisibility(View.VISIBLE);
            binding.edtGpu.setVisibility(View.VISIBLE);
            binding.tvGpu.setVisibility(View.VISIBLE);
            binding.edtRam.setVisibility(View.VISIBLE);
            binding.tvRam.setVisibility(View.VISIBLE);
            binding.edtRom.setVisibility(View.VISIBLE);
            binding.tvRom.setVisibility(View.VISIBLE);
            binding.edtHeDieuHanh.setVisibility(View.VISIBLE);
            binding.tvHeDieuHanh.setVisibility(View.VISIBLE);
        } else if (name.equals("Laptop")) {
            binding.edtSC.setVisibility(View.VISIBLE);
            binding.tvSc.setVisibility(View.VISIBLE);
            binding.edtCamera.setVisibility(View.VISIBLE);
            binding.tvCamera.setVisibility(View.VISIBLE);
            binding.edtChipset.setVisibility(View.VISIBLE);
            binding.tvChipset.setVisibility(View.VISIBLE);
            binding.edtCpu.setVisibility(View.VISIBLE);
            binding.tvCpu.setVisibility(View.VISIBLE);
            binding.edtGpu.setVisibility(View.VISIBLE);
            binding.tvGpu.setVisibility(View.VISIBLE);
            binding.edtRam.setVisibility(View.VISIBLE);
            binding.tvRam.setVisibility(View.VISIBLE);
            binding.edtRom.setVisibility(View.VISIBLE);
            binding.tvRom.setVisibility(View.VISIBLE);
            binding.edtHeDieuHanh.setVisibility(View.VISIBLE);
            binding.tvHeDieuHanh.setVisibility(View.VISIBLE);
        } else if (name.equals("Pc")) {
            binding.edtSC.setVisibility(View.VISIBLE);
            binding.tvSc.setVisibility(View.VISIBLE);
            binding.edtCamera.setVisibility(View.VISIBLE);
            binding.tvCamera.setVisibility(View.VISIBLE);
            binding.edtChipset.setVisibility(View.VISIBLE);
            binding.tvChipset.setVisibility(View.VISIBLE);
            binding.edtCpu.setVisibility(View.VISIBLE);
            binding.tvCpu.setVisibility(View.VISIBLE);
            binding.edtGpu.setVisibility(View.VISIBLE);
            binding.tvGpu.setVisibility(View.VISIBLE);
            binding.edtRam.setVisibility(View.VISIBLE);
            binding.tvRam.setVisibility(View.VISIBLE);
            binding.edtRom.setVisibility(View.VISIBLE);
            binding.tvRom.setVisibility(View.VISIBLE);
            binding.edtHeDieuHanh.setVisibility(View.VISIBLE);
            binding.tvHeDieuHanh.setVisibility(View.VISIBLE);
        }else if (name.equals("Màn Hình")){
            binding.edtSC.setVisibility(View.VISIBLE);
            binding.tvSc.setVisibility(View.VISIBLE);
            binding.edtCamera.setVisibility(View.GONE);
            binding.tvCamera.setVisibility(View.GONE);
            binding.edtChipset.setVisibility(View.GONE);
            binding.tvChipset.setVisibility(View.GONE);
            binding.edtCpu.setVisibility(View.GONE);
            binding.tvCpu.setVisibility(View.GONE);
            binding.edtGpu.setVisibility(View.GONE);
            binding.tvGpu.setVisibility(View.GONE);
            binding.edtRam.setVisibility(View.GONE);
            binding.tvRam.setVisibility(View.GONE);
            binding.edtRom.setVisibility(View.GONE);
            binding.tvRom.setVisibility(View.GONE);
            binding.edtHeDieuHanh.setVisibility(View.VISIBLE);
            binding.tvHeDieuHanh.setVisibility(View.VISIBLE);
        }
    }


    private void initController() {
        binding.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.addToBackStack("FragmentA");
                transaction.replace(R.id.content_frame, FragmentProductWarehouse.newInstance());
                transaction.commit();
            }
        });

        binding.btnLuu.setOnClickListener(new View.OnClickListener() {
            private DialogCreateOptionProductBinding binding1;

            @Override
            public void onClick(View view) {
                String name = binding.edtNameProduct.getText().toString();
                String description = binding.edtMota.getText().toString();
                String tinhTrang = binding.edtTinhTrang.getText().toString();
                String screen = binding.edtSC.getText().toString();
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
                String inputWeight = binding.edtWeight.getText().toString();
                int weight = inputWeight.isEmpty() ? 0: Integer.parseInt(inputWeight);
                String connection = binding.edtConnection.getText().toString();
                String specialFeature = binding.edtSpecialFeature.getText().toString();
                String manufacturer = binding.edtManufacturer.getText().toString();
                String other = binding.edtOther.getText().toString();
                createProductMyStore(name, description, tinhTrang, screen, camera, chipset,cpu,gpu, ram, rom, operatingSystem, battery,
                        weight, connection, specialFeature, manufacturer, other);
            }
        });
    }

    private void initView() {
        dialog = new ProgressLoadingDialog(getContext());
    }


}
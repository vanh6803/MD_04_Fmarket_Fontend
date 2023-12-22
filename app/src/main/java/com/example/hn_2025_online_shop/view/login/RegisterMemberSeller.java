package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.CityAdapter;
import com.example.hn_2025_online_shop.adapter.DistrictAdapter;
import com.example.hn_2025_online_shop.adapter.WardAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.api.PositionApi;
import com.example.hn_2025_online_shop.databinding.RegisterMemberSellerBinding;
import com.example.hn_2025_online_shop.model.City;
import com.example.hn_2025_online_shop.model.District;
import com.example.hn_2025_online_shop.model.Ward;
import com.example.hn_2025_online_shop.model.response.CityResponse;
import com.example.hn_2025_online_shop.model.response.DistrictResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.model.response.WardResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.success_screen.CreateStoreSuccessActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;

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

public class RegisterMemberSeller extends AppCompatActivity {
    private RegisterMemberSellerBinding binding;
    private ProgressLoadingDialog loadingDialog;
    private MultipartBody.Part fileImgAvatar;
    private MultipartBody.Part fileImgBanner;
    private int isCheckImage = 0; // 1 là avatar, 2 là banner dùng để set dữ liệu
    private boolean isAvatar = false; // kiểm tra xem avatar có dữ liệu hay chưa
    private boolean isBanner = false;
    private List<City> cityList;
    private List<District> districtList;
    private List<Ward> wardList;
    private CityAdapter cityAdapter;
    private DistrictAdapter districtAdapter;
    private WardAdapter wardAdapter;
    private String strCity = "";
    private String strDistrict = "";
    private String strWard = "";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegisterMemberSellerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initController();
        initView();
        getListCity();
    }

    // -------------------------- spinner -----------------------------------

    private void getListCity() {
        PositionApi.API.getListCity().enqueue(new Callback<CityResponse>() {
            @Override
            public void onResponse(Call<CityResponse> call, Response<CityResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    CityResponse cityResponse = response.body();
                    Log.d(TAG.toString, "onResponse-getListCity: " + cityResponse.toString());
                    cityList.clear();
                    cityList.add(new City("--Chọn Tỉnh/Thành phố--"));
                    cityList.addAll(cityResponse.getResults());
                    spinnerCity(cityList);
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getListCity: " + errorMessage);

                        Toast.makeText(RegisterMemberSeller.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Toast.makeText(RegisterMemberSeller.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getListCity: " + t.toString());
            }
        });
    }



    private void getListDistrict(String provinceId) {
        PositionApi.API.getListDistrict(provinceId).enqueue(new Callback<DistrictResponse>() {
            @Override
            public void onResponse(Call<DistrictResponse> call, Response<DistrictResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    DistrictResponse districtResponse = response.body();
                    districtList.clear();
                    districtList.add(new District("--Chọn Quận/Huyện--"));
                    districtList.addAll(districtResponse.getResults());
                    spinnerDistrict(districtList);
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-districtResponse: " + errorMessage);
                        Toast.makeText(RegisterMemberSeller.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
                Toast.makeText(RegisterMemberSeller.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-districtResponse: " + t.toString());
            }
        });

    }

    private void getListWard(String districtId) {
        PositionApi.API.getListWard(districtId).enqueue(new Callback<WardResponse>() {
            @Override
            public void onResponse(Call<WardResponse> call, Response<WardResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    WardResponse wardResponse = response.body();
                    wardList.clear();
                    wardList.add(new Ward("--Chọn Phường/Xã--"));
                    wardList.addAll(wardResponse.getResults());
                    spinnerWard(wardList);
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getListWard: " + errorMessage);
                        Toast.makeText(RegisterMemberSeller.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<WardResponse> call, Throwable t) {
                Toast.makeText(RegisterMemberSeller.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getListWard: " + t.toString());
            }
        });
    }

    private void spinnerCity(List<City> cityList) {
        cityAdapter = new CityAdapter(this, R.layout.layout_item_spinner_selected, cityList);
        binding.spnCity.setAdapter(cityAdapter);
        binding.spnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG.toString, "onItemSelected-city: " + cityAdapter.getItem(i));
                if(cityAdapter.getItem(i).getProvinceId() != null) {
                    getListDistrict(cityAdapter.getItem(i).getProvinceId());
                    strCity = "";
                    strCity = cityAdapter.getItem(i).getProvinceName();
                    binding.edtDiachi.setText(strCity);
                } else {
                    districtList.clear();
                    districtList.add(new District("--Chọn Quận/Huyện--"));
                    districtAdapter = new DistrictAdapter(RegisterMemberSeller.this, R.layout.layout_item_spinner_selected, districtList);
                    binding.spnDistrict.setAdapter(districtAdapter);

                    wardList.clear();
                    wardList.add(new Ward("--Chọn Phường/Xã--"));
                    wardAdapter = new WardAdapter(RegisterMemberSeller.this, R.layout.layout_item_spinner_selected, wardList);
                    binding.spnWard.setAdapter(wardAdapter);
                    strCity = "";
                    binding.edtDiachi.setText(strCity);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void spinnerDistrict(List<District> districtList) {
        districtAdapter = new DistrictAdapter(this, R.layout.layout_item_spinner_selected, districtList);
        binding.spnDistrict.setAdapter(districtAdapter);

        binding.spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG.toString, "onItemSelected-district: " + districtAdapter.getItem(i));
                if(districtAdapter.getItem(i).getDistrictId() != null) {
                    getListWard(districtAdapter.getItem(i).getDistrictId());
                    strDistrict = "";
                    strDistrict = districtAdapter.getItem(i).getDistrictName();
                    binding.edtDiachi.setText(strCity + strDistrict);
                } else {
                    wardList.clear();
                    wardList.add(new Ward("--Chọn Phường/Xã--"));
                    wardAdapter = new WardAdapter(RegisterMemberSeller.this, R.layout.layout_item_spinner_selected, wardList);
                    binding.spnWard.setAdapter(wardAdapter);
                    strDistrict = "";
                    binding.edtDiachi.setText(strCity + ", " + strDistrict);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void spinnerWard(List<Ward> wardList) {
        wardAdapter = new WardAdapter(this, R.layout.layout_item_spinner_selected, wardList);
        binding.spnWard.setAdapter(wardAdapter);

        binding.spnWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG.toString, "onItemSelected-ward: " + wardAdapter.getItem(i));
                if(wardAdapter.getItem(i).getWardId() != null) {
                    strWard = "";
                    strWard = wardAdapter.getItem(i).getWardName();
                    binding.edtDiachi.setText(strCity + ", " + strDistrict + ", " + strWard);
                } else {
                    strWard = "";
                    binding.edtDiachi.setText(strCity + ", " + strDistrict + ", " + strWard);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // -----------------------------------------------------------------------

    private void initController() {
        binding.btnRegisMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edtNameShop.getText().toString().trim();
                String address = binding.edtDiachi.getText().toString().trim();
                registerMemberSeller(name, address);
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });

        binding.imgAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(RegisterMemberSeller.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
                isCheckImage = 1;
            }
        });

        binding.imgBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(RegisterMemberSeller.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
                isCheckImage = 2;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            File file = new File(getPath(uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            if(isCheckImage == 1) {
                isAvatar = true;
                binding.imgAvartar.setImageURI(uri);
                fileImgAvatar = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);
            } else if(isCheckImage == 2) {
                isBanner = true;
                binding.imgBanner.setImageURI(uri);
                fileImgBanner =  MultipartBody.Part.createFormData("banner", file.getName(), requestBody);
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            isAvatar = false;
            isBanner = false;
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
            isAvatar = false;
            isBanner = false;
        }
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


    private void registerMemberSeller(String name, String address) {
        if(checkRegister(name,address)) {
            loadingDialog.show();
            String token = AccountUltil.BEARER + AccountUltil.TOKEN;
            RequestBody requestBodyName = RequestBody.create(MediaType.parse("multipart/form-data"), name);
            RequestBody requestBodyAddress = RequestBody.create(MediaType.parse("multipart/form-data"), address);
            BaseApi.API.registerMemberSeller(token, fileImgAvatar, fileImgBanner, requestBodyName, requestBodyAddress).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.isSuccessful()){ // chỉ nhận đầu status 200
                        ServerResponse serverResponse = response.body();
                        Log.d(TAG.toString, "onResponse-registerMemberSeller: " + serverResponse.toString());
                        if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                            Intent intent = new Intent(RegisterMemberSeller.this, CreateStoreSuccessActivity.class);
                            startActivity(intent);
                        }
                    } else { // nhận các đầu status #200
                        try {
                            String errorBody = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Log.d(TAG.toString, "onResponse-registerMemberSeller: " + errorMessage);
                            Toast.makeText(RegisterMemberSeller.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }catch (IOException e){
                            e.printStackTrace();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    loadingDialog.dismiss();
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Toast.makeText(RegisterMemberSeller.this, t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG.toString, "onFailure-registerMemberSeller: " + t.toString());
                    loadingDialog.dismiss();
                }
            });
        }
    }

    private boolean checkRegister(String name, String address) {
       if(TextUtils.isEmpty(name)) {
           Toast.makeText(this, "Mời nhập tên shop", Toast.LENGTH_SHORT).show();
            return false;
       } else if(!isAvatar) {
           Toast.makeText(this, "Hãy chọn Avatar", Toast.LENGTH_SHORT).show();
           return false;
       } else if(strWard.length() == 0) {
           Toast.makeText(this, "Mời chọn đầy đủ địa chỉ", Toast.LENGTH_SHORT).show();
           return false;
       } else if(!isBanner) {
           Toast.makeText(this, "Hãy chọn Banner", Toast.LENGTH_SHORT).show();
           return false;
       }
        return true;
    }

    private void initView() {
        loadingDialog = new ProgressLoadingDialog(this);

        cityList = new ArrayList<>();
        cityList.add(new City("--Chọn Tỉnh/Thành phố--"));
        cityAdapter = new CityAdapter(this, R.layout.layout_item_spinner_selected, cityList);
        binding.spnCity.setAdapter(cityAdapter);

        districtList = new ArrayList<>();
        districtList.add(new District("--Chọn Quận/Huyện--"));
        districtAdapter = new DistrictAdapter(this, R.layout.layout_item_spinner_selected, districtList);
        binding.spnDistrict.setAdapter(districtAdapter);

        wardList = new ArrayList<>();
        wardList.add(new Ward("--Chọn Phường/Xã--"));
        wardAdapter = new WardAdapter(this, R.layout.layout_item_spinner_selected, wardList);
        binding.spnWard.setAdapter(wardAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}

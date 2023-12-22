package com.example.hn_2025_online_shop.view.buy_product;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.CityAdapter;
import com.example.hn_2025_online_shop.adapter.DistrictAdapter;
import com.example.hn_2025_online_shop.adapter.WardAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.api.PositionApi;
import com.example.hn_2025_online_shop.databinding.DialogDeleteAddressBinding;
import com.example.hn_2025_online_shop.databinding.LayoutEditAddressBinding;
import com.example.hn_2025_online_shop.model.City;
import com.example.hn_2025_online_shop.model.District;
import com.example.hn_2025_online_shop.model.Info;
import com.example.hn_2025_online_shop.model.Ward;
import com.example.hn_2025_online_shop.model.response.CityResponse;
import com.example.hn_2025_online_shop.model.response.DistrictResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.model.response.WardResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateAddressActivity extends AppCompatActivity {
    private LayoutEditAddressBinding binding;
    private Info info;
    private ProgressLoadingDialog loadingDialog;
    private List<City> cityList;
    private List<District> districtList;
    private List<Ward> wardList;
    private CityAdapter cityAdapter;
    private DistrictAdapter districtAdapter;
    private WardAdapter wardAdapter;
    private String strCity = "";
    private String strDistrict = "";
    private String strWard = "";
    private String[] arrayAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutEditAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initController();
        getListCity();
        setData();
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

                        Toast.makeText(UpdateAddressActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Toast.makeText(UpdateAddressActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(UpdateAddressActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
                Toast.makeText(UpdateAddressActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(UpdateAddressActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<WardResponse> call, Throwable t) {
                Toast.makeText(UpdateAddressActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getListWard: " + t.toString());
            }
        });
    }

    private void spinnerCity(List<City> cityList) {
        cityAdapter = new CityAdapter(this, R.layout.layout_item_spinner_selected, cityList);
        binding.spnCity.setAdapter(cityAdapter);

        for (int i = 0; i < cityList.size(); i++) {
            if(cityList.get(i).getProvinceName().equals(arrayAddress[0])) {
                binding.spnCity.setSelection(i);
                break;
            }
        }
        binding.spnCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG.toString, "onItemSelected-city: " + cityAdapter.getItem(i));
                if(cityAdapter.getItem(i).getProvinceId() != null) {
                    getListDistrict(cityAdapter.getItem(i).getProvinceId());
                    strCity = "";
                    strCity = cityAdapter.getItem(i).getProvinceName();
                    binding.edtAddress.setText(strCity);
                } else {
                    districtList.clear();
                    districtList.add(new District("--Chọn Quận/Huyện--"));
                    districtAdapter = new DistrictAdapter(UpdateAddressActivity.this, R.layout.layout_item_spinner_selected, districtList);
                    binding.spnDistrict.setAdapter(districtAdapter);

                    wardList.clear();
                    wardList.add(new Ward("--Chọn Phường/Xã--"));
                    wardAdapter = new WardAdapter(UpdateAddressActivity.this, R.layout.layout_item_spinner_selected, wardList);
                    binding.spnWard.setAdapter(wardAdapter);
                    strCity = "";
                    binding.edtAddress.setText(strCity);
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

        for (int i = 0; i < districtList.size(); i++) {
            if(districtList.get(i).getDistrictName().equals(arrayAddress[1])) {
                binding.spnDistrict.setSelection(i);
                break;
            }
        }

        binding.spnDistrict.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG.toString, "onItemSelected-district: " + districtAdapter.getItem(i));
                if(districtAdapter.getItem(i).getDistrictId() != null) {
                    getListWard(districtAdapter.getItem(i).getDistrictId());
                    strDistrict = "";
                    strDistrict = districtAdapter.getItem(i).getDistrictName();
                    binding.edtAddress.setText(strCity + strDistrict);
                } else {
                    wardList.clear();
                    wardList.add(new Ward("--Chọn Phường/Xã--"));
                    wardAdapter = new WardAdapter(UpdateAddressActivity.this, R.layout.layout_item_spinner_selected, wardList);
                    binding.spnWard.setAdapter(wardAdapter);
                    strDistrict = "";
                    binding.edtAddress.setText(strCity + ", " + strDistrict);
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

        for (int i = 0; i < wardList.size(); i++) {
            if(wardList.get(i).getWardName().equals(arrayAddress[2])) {
                binding.spnWard.setSelection(i);
                break;
            }
        }

        binding.spnWard.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG.toString, "onItemSelected-ward: " + wardAdapter.getItem(i));
                if(wardAdapter.getItem(i).getWardId() != null) {
                    strWard = "";
                    strWard = wardAdapter.getItem(i).getWardName();
                    binding.edtAddress.setText(strCity + ", " + strDistrict + ", " + strWard);
                } else {
                    strWard = "";
                    binding.edtAddress.setText(strCity + ", " + strDistrict + ", " + strWard);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    // -----------------------------------------------------------------------

    private void initController() {
        binding.tvXoaDiaChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDeleteAddressBinding binding1 = DialogDeleteAddressBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(UpdateAddressActivity.this);
                dialog.setContentView(binding1.getRoot());
                Window window = dialog.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                binding1.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        urlDeleteInfo();
                        dialog.dismiss();
                    }
                });

                binding1.btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
        
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = binding.edtAddress.getText().toString().trim();
                String phoneNumber = binding.edtPhoneNumber.getText().toString().trim();
                String name = binding.edtUsername.getText().toString().trim();
                if(validateInfo(address, phoneNumber, name)) {
                    urlEditInfo(address, phoneNumber, name);
                }
            }
        });

        binding.imgBack.setOnClickListener(view -> {
            finish();
            overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
        });
    }

    private boolean validateInfo(String address, String phoneNumber, String name) {
        if(strWard.length() == 0) {
            Toast.makeText(this, "Nhập dữ liệu địa chỉ đầy đủ", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "Nhập dữ liệu số điện thoại đầy đủ", Toast.LENGTH_SHORT).show();
            return false;
        } else if(TextUtils.isEmpty(name)) {
            Toast.makeText(this, "Nhập dữ liệu họ và tên đầy đủ", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void urlEditInfo(String address, String phoneNumber, String name) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        boolean checked = binding.chkChooseDefault.isChecked();
        loadingDialog.show();
        BaseApi.API.editInfo(token, info.getId(), name, address, phoneNumber, checked).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    Log.d(TAG.toString, "onResponse-editInfo: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(UpdateAddressActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        // khi quay trở lại có thể load lại url
                        Intent intent = new Intent(UpdateAddressActivity.this, AddressActivity.class);
                        setResult(RESULT_OK, intent);
                        finish();
                        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
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
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-deleteInfo: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    private void urlDeleteInfo() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        loadingDialog.show();
        BaseApi.API.deleteInfo(token, info.getId()).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    Log.d(TAG.toString, "onResponse-deleteInfo: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(UpdateAddressActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        // khi quay trở lại có thể load lại url
                        Intent intent = new Intent(UpdateAddressActivity.this, AddressActivity.class);
                        setResult(RESULT_OK, intent);
                        finish();
                        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-deleteInfo: " + errorMessage);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-deleteInfo: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    private void setData() {
        binding.edtUsername.setText(info.getName());
        binding.edtPhoneNumber.setText(info.getPhoneNumber());
        binding.edtAddress.setText(info.getAddress());
        binding.chkChooseDefault.setChecked(info.getChecked());
    }

    private void initView() {
        loadingDialog = new ProgressLoadingDialog(this);
        Intent intent = getIntent(); // Lấy Intent từ Activity hiện tại
        Bundle bundle = intent.getExtras();
        if (bundle != null) { // Kiểm tra xem Bundle có tồn tại hay không
            info = (Info) bundle.getSerializable("info"); // Ép kiểu đối tượng từ Bundle
            arrayAddress = info.getAddress().split(", ");
        }

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
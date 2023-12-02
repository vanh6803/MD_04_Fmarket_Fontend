package com.example.hn_2025_online_shop.view.my_store;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.CityAdapter;
import com.example.hn_2025_online_shop.adapter.DistrictAdapter;
import com.example.hn_2025_online_shop.adapter.WardAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.api.PositionApi;
import com.example.hn_2025_online_shop.databinding.FragmentCreateProductMyStoreBinding;
import com.example.hn_2025_online_shop.databinding.FragmentUpdateInforStoreBinding;
import com.example.hn_2025_online_shop.model.City;
import com.example.hn_2025_online_shop.model.District;
import com.example.hn_2025_online_shop.model.Ward;
import com.example.hn_2025_online_shop.model.response.CityResponse;
import com.example.hn_2025_online_shop.model.response.DistrictResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.model.response.WardResponse;
import com.example.hn_2025_online_shop.model.response.store.InfoStore;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.login.RegisterMemberSeller;
import com.example.hn_2025_online_shop.view.success_screen.CreateStoreSuccessActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateInforStoreFragment extends Fragment {
    FragmentUpdateInforStoreBinding binding;
    private int isCheckImage = 0; // 1 là avatar, 2 là banner dùng để set dữ liệu
    private boolean isAvatar = false; // kiểm tra xem avatar có dữ liệu hay chưa
    private boolean isBanner = false;
    private ProgressLoadingDialog dialog;
    private MultipartBody.Part fileImgAvatar;
    private MultipartBody.Part fileImgBanner;
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


    public UpdateInforStoreFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UpdateInforStoreFragment newInstance() {
        UpdateInforStoreFragment fragment = new UpdateInforStoreFragment();
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
        binding = FragmentUpdateInforStoreBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dialog = new ProgressLoadingDialog(getContext());
        getDetailInforStore();
        initView();
        initController();
    }

    private void initView() {


        cityList = new ArrayList<>();
        cityList.add(new City("--Chọn Tỉnh/Thành phố--"));
        cityAdapter = new CityAdapter(getContext(), R.layout.layout_item_spinner_selected, cityList);
        binding.spnCity.setAdapter(cityAdapter);

        districtList = new ArrayList<>();
        districtList.add(new District("--Chọn Quận/Huyện--"));
        districtAdapter = new DistrictAdapter(getContext(), R.layout.layout_item_spinner_selected, districtList);
        binding.spnDistrict.setAdapter(districtAdapter);

        wardList = new ArrayList<>();
        wardList.add(new Ward("--Chọn Phường/Xã--"));
        wardAdapter = new WardAdapter(getContext(), R.layout.layout_item_spinner_selected, wardList);
        binding.spnWard.setAdapter(wardAdapter);
    }

    private void initController() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
        binding.btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.edtNameShop.getText().toString().trim();
                String address = binding.edtDiachi.getText().toString().trim();
                updateStore(name, address);

            }
        });

        binding.imgAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(UpdateInforStoreFragment.this)
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
                ImagePicker.with(UpdateInforStoreFragment.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
                isCheckImage = 2;
            }
        });
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

                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<CityResponse> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<DistrictResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<WardResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getListWard: " + t.toString());
            }
        });
    }

    private void spinnerCity(List<City> cityList) {
        cityAdapter = new CityAdapter(getActivity(), R.layout.layout_item_spinner_selected, cityList);
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
                    binding.edtDiachi.setText(strCity);
                } else {
                    districtList.clear();
                    districtList.add(new District("--Chọn Quận/Huyện--"));
                    districtAdapter = new DistrictAdapter(getActivity(), R.layout.layout_item_spinner_selected, districtList);
                    binding.spnDistrict.setAdapter(districtAdapter);

                    wardList.clear();
                    wardList.add(new Ward("--Chọn Phường/Xã--"));
                    wardAdapter = new WardAdapter(getActivity(), R.layout.layout_item_spinner_selected, wardList);
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
        districtAdapter = new DistrictAdapter(getActivity(), R.layout.layout_item_spinner_selected, districtList);
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
                    binding.edtDiachi.setText(strCity + strDistrict);
                } else {
                    wardList.clear();
                    wardList.add(new Ward("--Chọn Phường/Xã--"));
                    wardAdapter = new WardAdapter(getActivity(), R.layout.layout_item_spinner_selected, wardList);
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
        wardAdapter = new WardAdapter(getActivity(), R.layout.layout_item_spinner_selected, wardList);
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
    private void updateStore(String name, String address) {
        if(validateUpdateStore(name, address)){
            dialog.show();
            String token = AccountUltil.BEARER + AccountUltil.TOKEN;

            BaseApi.API.updateStore(token, name, address ).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.isSuccessful()){ // chỉ nhận đầu status 200
                        ServerResponse serverResponse = response.body();
                        Log.d(TAG.toString, "onResponse-registerMemberSeller: " + serverResponse.toString());
                        if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                            Toast.makeText(getContext(), "Update InforStore Succecssfully", Toast.LENGTH_SHORT).show();


                        }
                    } else { // nhận các đầu status #200
                        try {
                            String errorBody = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Log.d(TAG.toString, "onResponse-registerMemberSeller: " + errorMessage);
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
                    Log.d(TAG.toString, "onFailure-registerMemberSeller: " + t.toString());
                    dialog.dismiss();
                }
            });
        }

    }
    private boolean validateUpdateStore(String name, String address) {
        if(TextUtils.isEmpty(name)) {
            Toast.makeText(getContext(), "Mời nhập tên shop", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void getDetailInforStore() {
        dialog.show();
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        BaseApi.API.getInfoStore(token).enqueue(new Callback<InfoStore>() {
            @Override
            public void onResponse(@NonNull Call<InfoStore> call, @NonNull Response<InfoStore> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    InfoStore storeIdResponse = response.body();
                    assert storeIdResponse != null;
                    if(storeIdResponse.getCode() == 200 || storeIdResponse.getCode() == 201) {
                        setDataInforStore(storeIdResponse);
                        getListCity();
                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-createProduct: " + errorMessage);
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<InfoStore> call, @NonNull Throwable t) {

            }
        });
    }

    private void setDataInforStore(InfoStore storeIdResponse) {
        binding.edtNameShop.setText(storeIdResponse.getData().getName());
        Glide.with(getContext()).load(storeIdResponse.getData().getAvatar()).error(R.drawable.error).into(binding.imgAvartar);
        binding.edtDiachi.setText(storeIdResponse.getData().getAddress());
        Glide.with(getContext()).load(storeIdResponse.getData().getBanner()).error(R.drawable.error).into(binding.imgBanner);
        arrayAddress = storeIdResponse.getData().getAddress().split(", ");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            File file = new File(getPath(uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            if(isCheckImage == 1) {
                isAvatar = true;
                binding.imgAvartar.setImageURI(uri);
                fileImgAvatar = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);
                updateAvartarStore(fileImgAvatar);
            } else if(isCheckImage == 2) {
                isBanner = true;
                binding.imgBanner.setImageURI(uri);
                fileImgBanner =  MultipartBody.Part.createFormData("banner", file.getName(), requestBody);
                updateBannerStore(fileImgBanner);
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            isAvatar = false;
            isBanner = false;
        } else {
            Toast.makeText(getContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
            isAvatar = false;
            isBanner = false;
        }
    }

    private void updateBannerStore(MultipartBody.Part fileImgBanner) {
        dialog.show();
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        BaseApi.API.updateBannerStore(token, fileImgBanner).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    assert serverResponse != null;
                    Log.d(TAG.toString, "onResponse-uploadBanner: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(getContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-uploadBanner: " + errorMessage);
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
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-uploadBanner: " + t.toString());
                dialog.dismiss();
            }
        });
    }

    private void updateAvartarStore(MultipartBody.Part fileImgAvatar) {
        dialog.show();
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;

        BaseApi.API.updateAvartarStore(token, fileImgAvatar).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    assert serverResponse != null;
                    Log.d(TAG.toString, "onResponse-uploadAvatar: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(getContext(), serverResponse.getMessage(), Toast.LENGTH_SHORT).show();


                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-uploadAvatar: " + errorMessage);
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
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-uploadAvatar: " + t.toString());
                dialog.dismiss();
            }
        });
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
}
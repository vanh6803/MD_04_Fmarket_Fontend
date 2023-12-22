package com.example.hn_2025_online_shop.view.profile_screen;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.LayoutProfileUserBinding;
import com.example.hn_2025_online_shop.model.Info;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ApiUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.buy_product.AddAddressActivity;
import com.example.hn_2025_online_shop.view.home_screen.MainActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUserScreen extends AppCompatActivity {
    private LayoutProfileUserBinding binding;
    private ProgressLoadingDialog loadingDialog;
    private SimpleDateFormat formatter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LayoutProfileUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initController();
    }



    private void initController() {
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileUserScreen.this, MainActivity.class);
                setResult(RESULT_OK, intent);
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });

        binding.imgAvartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(ProfileUserScreen.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });

        binding.updatePro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.edtUserName.setEnabled(true);
                binding.edtBirthday.setEnabled(true);
                binding.linearCalander.setClickable(true);
                binding.btnSave.setEnabled(true);
            }
        });

        binding.linearCalander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCalander();
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.edtUserName.getText().toString().trim();
                String birthday = binding.edtBirthday.getText().toString().trim();
                binding.edtUserName.setEnabled(false);
                binding.edtBirthday.setEnabled(false);
                binding.linearCalander.setClickable(false);
                apiEditProfile(username, birthday);
            }
        });
    }

    private void openCalander() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                // set thời gian cho calendar
                calendar.set(year, month, dayOfMonth);
                binding.edtBirthday.setText(formatter.format(calendar.getTime()));
            }
        }, year, month, day);

        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK) {
            assert data != null;
            Uri uri = data.getData();
            File file = new File(getPath(uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            binding.imgAvartar.setImageURI(uri);
            MultipartBody.Part fileImgAvatar = MultipartBody.Part.createFormData("avatar", file.getName(), requestBody);
            apiUploadAvatar(fileImgAvatar);

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
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

    private void apiUploadAvatar(MultipartBody.Part fileImgAvatar) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        String idUser = AccountUltil.USER.getId();
        loadingDialog.show();
        BaseApi.API.uploadAvatar(token, idUser, fileImgAvatar).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    assert serverResponse != null;
                    Log.d(TAG.toString, "onResponse-uploadAvatar: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(ProfileUserScreen.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        // Khởi tạo mỗi khi mình có dữ liệu hoặc thay đổi thì nó update AccountUser
                        ApiUtil.getDetailUser(ProfileUserScreen.this, loadingDialog);
                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-uploadAvatar: " + errorMessage);
                        Toast.makeText(ProfileUserScreen.this, errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Toast.makeText(ProfileUserScreen.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-uploadAvatar: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    private void apiEditProfile(String username, String birthday) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        String idUser = AccountUltil.USER.getId();

        if(validateValue(username, birthday)) {
            loadingDialog.show();
            BaseApi.API.editProfile(token, idUser, username, birthday).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                    if(response.isSuccessful()){ // chỉ nhận đầu status 200
                        ServerResponse serverResponse = response.body();
                        assert serverResponse != null;
                        Log.d(TAG.toString, "onResponse-editProfile: " + serverResponse.toString());
                        if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                            Toast.makeText(ProfileUserScreen.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            // Khởi tạo mỗi khi mình có dữ liệu hoặc thay đổi thì nó update AccountUser
                            ApiUtil.getDetailUser(ProfileUserScreen.this, loadingDialog);
                        }
                    } else { // nhận các đầu status #200
                        try {

                            assert response.errorBody() != null;
                            String errorBody = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Log.d(TAG.toString, "onResponse-editProfile: " + errorMessage);
                            Toast.makeText(ProfileUserScreen.this, errorMessage, Toast.LENGTH_SHORT).show();
                        }catch (IOException e){
                            e.printStackTrace();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    loadingDialog.dismiss();
                }

                @Override
                public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                    Toast.makeText(ProfileUserScreen.this, t.toString(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG.toString, "onFailure-editProfile: " + t.toString());
                    loadingDialog.dismiss();
                }
            });
        }

    }

    private boolean validateValue(String username, String birthday) {
        if(TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            formatter.parse(birthday);
        } catch (Exception e) {
            Toast.makeText(this, "Nhập đúng định dạng ngày tháng dd/MM/yyyy", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @SuppressLint({"SimpleDateFormat", "ResourceAsColor"})
    private void initData() {
        formatter = new SimpleDateFormat("dd/MM/yyyy");
        loadingDialog = new ProgressLoadingDialog(this);
        binding.edtUserName.setText(AccountUltil.USER.getUsername());
        binding.edtBirthday.setText(AccountUltil.USER.getBirthday());
        binding.email.setText(AccountUltil.USER.getEmail());
        Glide.with(this)
                .load(AccountUltil.USER.getAvatar())
                .placeholder(R.drawable.loading)
                .error(R.drawable.avatar1)
                .into(binding.imgAvartar);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileUserScreen.this, MainActivity.class);
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}
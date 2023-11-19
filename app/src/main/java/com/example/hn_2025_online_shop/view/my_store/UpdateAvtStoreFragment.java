package com.example.hn_2025_online_shop.view.my_store;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentUpdateAvtStoreBinding;
import com.example.hn_2025_online_shop.model.HistoryBuy;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ApiUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.StoreUltil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.profile_screen.ProfileUserScreen;
import com.github.dhaval2404.imagepicker.ImagePicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UpdateAvtStoreFragment extends Fragment {
    FragmentUpdateAvtStoreBinding binding;

    private ProgressLoadingDialog loadingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_update_avt_store, container, false);
//        binding.imgAvtStore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ImagePicker.with(HistoryBuy)
//                        .crop()                    //Crop image(Optional), Check Customization for more option
//                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
//                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
//                        .start();
//            }
//        });
    }

    private void apiUploadAvatarStore(MultipartBody.Part fileImgAvatar) {
        String token = StoreUltil.BEARER + StoreUltil.TOKEN;
        String idStore = StoreUltil.STORE.getId();
        loadingDialog.show();
        BaseApi.API.updateAvatarStore(token, idStore, fileImgAvatar).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if (response.isSuccessful()) { // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    assert serverResponse != null;
                    Log.d(TAG.toString, "onResponse-uploadAvatar: " + serverResponse.toString());
                    if (serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
//                        Toast.makeText(ProfileUserScreen.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
//                        ApiUtil.getDetailUser(ProfileUserScreen.this, loadingDialog);
                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-uploadAvatar: " + errorMessage);
//                        Toast.makeText(ProfileUserScreen.this, errorMessage, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Log.d(TAG.toString, "onFailure-uploadAvatar: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }
}
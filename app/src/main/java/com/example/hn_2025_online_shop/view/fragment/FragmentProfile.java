package com.example.hn_2025_online_shop.view.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentProfileBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialogFeedbackBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialogLogoutBinding;
import com.example.hn_2025_online_shop.model.Store;
import com.example.hn_2025_online_shop.model.response.CheckStoreResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.model.response.StoreIdResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.StoreUltil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.login.Login;
import com.example.hn_2025_online_shop.view.login.RegisterMemberSeller;
import com.example.hn_2025_online_shop.view.my_store.MyStoreScreen;
import com.example.hn_2025_online_shop.view.login.ResetPassWord;
import com.example.hn_2025_online_shop.view.profile_screen.OrderProductScreen;
import com.example.hn_2025_online_shop.view.profile_screen.ProfileUserScreen;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfile extends Fragment {
    private FragmentProfileBinding binding;
    private ProgressLoadingDialog loadingDialog;
    private SharedPreferences sharedPreferences;
    private GoogleSignInClient mGoogleSignInClient;
    public static FragmentProfile newInstance() {
        FragmentProfile fragment = new FragmentProfile();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    //init view và các action vào đây
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGoogleSignInClient = createGoogleSignInClient();
        initView();
        initController();
        setData();
    }

    private GoogleSignInClient createGoogleSignInClient() {
        // Initialize GoogleSignInOptions with the appropriate configuration
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                // Add any additional options as needed
                .build();

        // Initialize the GoogleSignInClient
        return GoogleSignIn.getClient(requireContext(), gso);
    }

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == getActivity().RESULT_OK) {
                        setData();
                    }
                }
            });

    private void setData() {
        binding.tvUserName.setText(AccountUltil.USER.getUsername());
        Glide.with(requireActivity())
                .load(AccountUltil.USER.getAvatar())
                .placeholder(R.drawable.loading)
                .error(R.drawable.avatar1)
                .into(binding.imgAvartar);
        if(TextUtils.isEmpty(AccountUltil.USER.getPhone())) {
            binding.tvPhone.setText("099.999.999");
        } else {
            binding.tvPhone.setText(AccountUltil.USER.getPhone());
        }
    }

    private void initController() {
        HistoryDon();//theo dõi đơn hàng
        phanHoiKhieuNai();//phan hoi khieu nai
        resetPass();
        profile();
        myStore();
        logOut();//đăng xuất
    }


    private void myStore() {
        binding.layoutMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadingDialog.show();
                String token = AccountUltil.BEARER + AccountUltil.TOKEN;
//
                BaseApi.API.checkStoreExiting(token).enqueue(new Callback<CheckStoreResponse>() {
                    @Override
                    public void onResponse(@NonNull Call<CheckStoreResponse> call, @NonNull Response<CheckStoreResponse> response) {
                        if(response.isSuccessful()){ // chỉ nhận đầu status 200
                            CheckStoreResponse response1 = response.body();
                            assert response1 != null;
                            Log.d(TAG.toString, "onResponse-logout: " + response1.toString());
                            if(response1.getCode() == 200) {
                                startActivity(new Intent(getActivity(), RegisterMemberSeller.class));
                                getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
                            }
                        } else { // nhận các đầu status #200
                            try {
                                assert response.errorBody() != null;
                                String errorBody = response.errorBody().string();
                                JSONObject errorJson = new JSONObject(errorBody);
                                String errorMessage = errorJson.getString("message");
                                startActivity(new Intent(getActivity(), MyStoreScreen.class));
                                getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
                                Log.d(TAG.toString, "onResponse-logout: " + errorMessage);
                            }catch (IOException e){
                                e.printStackTrace();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        loadingDialog.dismiss();
                    }
                    @Override
                    public void onFailure(@NonNull Call<CheckStoreResponse> call, @NonNull Throwable t) {
                        Log.d("aaa", "onFailure: " + t.getMessage());

                    }
                });
            }
        });
    }


    private void profile() {
        binding.layoutProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProfileUserScreen.class);
                mActivityResultLauncher.launch(intent);
                requireActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });
    }

    private void resetPass() {
        binding.layoutResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ResetPassWord.class));
                requireActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });
    }
    private void HistoryDon() {
        binding.layoutHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), OrderProductScreen.class));
                requireActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });
    }

    private void phanHoiKhieuNai() {
        binding.layoutPhanhoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutDialogFeedbackBinding bindingLogout = LayoutDialogFeedbackBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(bindingLogout.getRoot());
                Window window = dialog.getWindow();
                assert window != null;
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

                bindingLogout.btnPhanHoi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getActivity(), OrderProductScreen.class));
                        requireActivity().finish();
                    }
                });
                dialog.show();
            }
        });
    }
    private void logOut() {
         sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
        String tokenGG = sharedPreferences.getString("TOKENGG",null);

        binding.btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutDialogLogoutBinding bindingLogout = LayoutDialogLogoutBinding.inflate(getLayoutInflater());
                Dialog dialog = new Dialog(requireContext());
                dialog.setContentView(bindingLogout.getRoot());
                Window window = dialog.getWindow();
                assert window != null;
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                bindingLogout.btnCancelLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                bindingLogout.btnLogout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logoutAccount();
                        if(tokenGG != null){
                            mGoogleSignInClient.signOut();
                        }
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    private void logoutAccount() {
        loadingDialog.show();
        BaseApi.API.logout(AccountUltil.BEARER + AccountUltil.TOKEN).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    assert serverResponse != null;
                    Log.d(TAG.toString, "onResponse-logout: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200) {
                        startActivity(new Intent(getActivity(), Login.class));
                        requireActivity().finishAffinity();
                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-logout: " + errorMessage);
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-logout: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    private void initView() {
        loadingDialog = new ProgressLoadingDialog(requireContext());
    }
}
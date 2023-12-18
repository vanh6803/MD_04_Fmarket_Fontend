package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.LoginBinding;
import com.example.hn_2025_online_shop.model.response.LoginResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ApiUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.ultil.Validator;
import com.example.hn_2025_online_shop.view.home_screen.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    private LoginBinding binding;
    private ProgressLoadingDialog loadingDialog;

    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;
    private SharedPreferences sharedPreferences;

    final int RC_SIGN_IN = 2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initController();
        initLoginGoogle();
    }

    private void initController() {
        binding.txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });
        binding.txtfogotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Login.this, ForgotPassWord.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.edtEmail.getText().toString().trim();
                String pass = binding.edtPass.getText().toString().trim();
                loginAccount(email, pass);
            }
        });

        binding.btnLoginGoogle.setOnClickListener(view -> {
            signIn();
        });
    }

    private void loginAccount(String email, String pass) {
        if (validateLogin(email, pass)) {
            loadingDialog.show();
            BaseApi.API.login(email, pass).enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        LoginResponse loginResponse = response.body();
                        if (loginResponse.getCode() == 200) {
                            AccountUltil.TOKEN = loginResponse.getToken();
                            Log.d(TAG.toString, "onResponse-Token: " + AccountUltil.TOKEN);
                            // Đăng nhập thành công thì lấy ra detail user rồi cho vào 1 biến để có thể tái sử dụng
                            ApiUtil.getDetailUser(Login.this, loadingDialog);
                            // Lấy danh sách cart
                            ApiUtil.getAllCart(Login.this, null);
                            Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                            screenSwitch(Login.this, MainActivity.class);
                            finishAffinity();
                        }
                    } else {
                        try {
                            String errorBody = response.errorBody().string();
                            // Parse and display the error message
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    loadingDialog.dismiss();
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    Toast.makeText(Login.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    loadingDialog.dismiss();
                }
            });
        }

    }


    private boolean validateLogin(String email, String pass) {
        if (areEditTextsEmpty(binding.edtEmail, binding.edtPass)) {
            Toast.makeText(Login.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!Validator.isValidEmail(email)) {
            Toast.makeText(Login.this, "Nhập đúng định dạnh email!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        loadingDialog = new ProgressLoadingDialog(this);
    }

    private void screenSwitch(Context packageContext, Class<?> cls) {
        Intent intent = new Intent(packageContext, cls);
        startActivity(intent);
    }

    private boolean areEditTextsEmpty(EditText editText1, EditText editText2) {
        String text1 = editText1.getText().toString().trim();
        String text2 = editText2.getText().toString().trim();
        return TextUtils.isEmpty(text1) || TextUtils.isEmpty(text2);
    }

    // --------------------- Login google -------------------------

    private void initLoginGoogle() {
        sharedPreferences = getSharedPreferences("USER_FILE", MODE_PRIVATE);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Lấy token của người dùng
            String idToken = account.getIdToken();
            String email = account.getEmail();


            // Kiểm tra xem token có null không
            if (idToken != null) {
                // Điều hướng người dùng đến màn hình chính (MainActivity) và chuyển token qua


                BaseApi.API.loginGoogle(idToken).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            LoginResponse loginResponse = response.body();
                            if (loginResponse.getCode() == 200) {
                                AccountUltil.TOKEN = loginResponse.getToken();
                                Log.d(TAG.toString, "onResponse-Token: " + AccountUltil.TOKEN);
                                // Đăng nhập thành công thì lấy ra detail user rồi cho vào 1 biến để có thể tái sử dụng
                                ApiUtil.getDetailUser(Login.this, loadingDialog);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("TOKENGG", idToken);
                                editor.commit();
                                // Lấy danh sách cart
                                ApiUtil.getAllCart(Login.this, null);
                                Toast.makeText(Login.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                screenSwitch(Login.this, MainActivity.class);
                                finishAffinity();
                            }
                        } else {
                            try {
                                String errorBody = response.errorBody().string();
                                // Parse and display the error message
                                JSONObject errorJson = new JSONObject(errorBody);
                                String errorMessage = errorJson.getString("message");
                                Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        loadingDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
            } else {
                // Xử lý token null
                Log.e("Token", "Token is null");
            }

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ApiException", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Đăng nhập bằng google thất bại!", Toast.LENGTH_SHORT).show();
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        Log.d("account-onStart", "onStart: " + account);
//        if (account != null) {
//            Intent intent = new Intent(Login.this, MainActivity.class);
//
//            startActivity(intent);
//        }
//    }
}

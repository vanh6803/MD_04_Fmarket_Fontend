package com.example.hn_2025_online_shop.view.login;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.LoginBinding;
import com.example.hn_2025_online_shop.model.response.LoginResponse;
import com.example.hn_2025_online_shop.ultil.Validator;
import com.example.hn_2025_online_shop.view.home_screen.MainActivity;


import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;


public class Login extends AppCompatActivity{
    private LoginBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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
                if (!areEditTextsEmpty(binding.edtEmail, binding.edtPass)){
                    if (Validator.isValidEmail(email)){
                        BaseApi.API.login(email,pass).enqueue(
                                new Callback<LoginResponse>() {
                                    @Override
                                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                        if(response.isSuccessful()){
                                            LoginResponse loginResponse = response.body();
                                            if (loginResponse.getCode() == 201){
                                                Toast.makeText(Login.this
                                                        ,"Bạn đã đăng nhập thành công!"
                                                        ,Toast.LENGTH_SHORT).show();
                                                screenSwitch(Login.this, MainActivity.class);
                                            }
                                        }else {
                                            try {
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
                                    }


                                    @Override
                                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                                        Toast.makeText(Login.this
                                                ,t.getMessage()
                                                ,Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }else {
                        Toast.makeText(Login.this
                                ,"Bạn hãy nhập đúng định dạnh email!"
                                , Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Login.this
                            ,"Bạn đừng để trống chỗ nhập nhé!"
                            , Toast.LENGTH_SHORT).show();
                }
            }
        });
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
}


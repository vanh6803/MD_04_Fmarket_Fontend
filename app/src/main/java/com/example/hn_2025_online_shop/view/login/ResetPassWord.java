package com.example.hn_2025_online_shop.view.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ResetPasswordBinding;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.Validator;

public class ResetPassWord extends AppCompatActivity {
    private ResetPasswordBinding binding;
    private ProgressLoadingDialog loadingDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initController();
    }

    private void initController() {
        binding.btnRepassSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strOldPassword = binding.edtOldPassword.getText().toString().trim();
                String strNewPassword = binding.edtNewPassword.getText().toString().trim();
                String strReNewPassword = binding.edtReNewPassword.getText().toString().trim();
                repassAccount(strOldPassword, strNewPassword, strReNewPassword);
            }
        });

        binding.backResetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });
    }

    private void repassAccount(String strOldPassword, String strNewPassword, String strReNewPassword) {
        if(validateRepass(strOldPassword, strNewPassword, strReNewPassword)) {

        }
    }

    private boolean validateRepass(String strOldPassword, String strNewPassword, String strReNewPassword) {
        if (TextUtils.isEmpty(strOldPassword) || TextUtils.isEmpty(strNewPassword) || TextUtils.isEmpty(strReNewPassword)){
            Toast.makeText(ResetPassWord.this,"Bạn đừng để trống chỗ nhập nhé!", Toast.LENGTH_SHORT).show();
            return false;
        } else if(!strNewPassword.equals(strReNewPassword)) {
            Toast.makeText(ResetPassWord.this,"NewPassword và ReNewPassword không khớp nhau!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        loadingDialog = new ProgressLoadingDialog(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}

package com.example.hn_2025_online_shop.view.buy_product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.CartPayAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityPayBinding;
import com.example.hn_2025_online_shop.model.Info;
import com.example.hn_2025_online_shop.model.body.PurchaseBody;
import com.example.hn_2025_online_shop.model.response.InfoResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.CartUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.cart_screen.CartActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PayActivity extends AppCompatActivity {
    private ActivityPayBinding binding;
    private ProgressLoadingDialog loadingDialog;
    private List<Info> infoList;
    private Info info;
    private CartPayAdapter cartPayAdapter;
    private int totalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initController();
        urlGetAllInfo();
    }

    private void urlGetAllInfo() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        loadingDialog.show();
        BaseApi.API.getInfo(token).enqueue(new Callback<InfoResponse>() {
            @Override
            public void onResponse(Call<InfoResponse> call, Response<InfoResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    InfoResponse infoResponse = response.body();
                    Log.d(TAG.toString, "onResponse-getInfo: " + infoResponse.toString());
                    if(infoResponse.getCode() == 200 || infoResponse.getCode() == 201) {
                        infoList = infoResponse.getResult();
                        // setDataAddress
                        setDataInfo();
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getInfo: " + errorMessage);
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
            public void onFailure(Call<InfoResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getInfo: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    private void setDataInfo() {
        if(infoList.size() == 0) {
            binding.tvInfoUser.setText("Chưa có địa chỉ mời bạn tạo");
            return;
        }
        info = infoList.get(0);
        for (int i = 0; i < infoList.size(); i++) {
            if(infoList.get(i).getChecked()) {
                info = infoList.get(i);
                break;
            }
        }
        if(info != null) {
            binding.tvInfoUser.setText(info.getName() + " | " + info.getPhoneNumber() + " | " + info.getAddress());
        } else {
            binding.tvInfoUser.setText("Chưa có địa chỉ mời bạn tạo");
        }
    }

    private void initController() {
        binding.layoutInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayActivity.this, AddressActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });

        binding.btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CartUtil.listCartCheck.size() > 0) {
                    urlCreateOrder();
                } else {
                    Toast.makeText(PayActivity.this, "Chưa có sản phẩm nào", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void urlCreateOrder() {
        if(validatePurchare()) {
            String token = AccountUltil.BEARER + AccountUltil.TOKEN;
            PurchaseBody purchaseBody = new PurchaseBody();
            purchaseBody.setInfoId(info.getId());
            purchaseBody.setUserId(AccountUltil.USER.getId());
            purchaseBody.setProductsOrder(CartUtil.listCartCheck);
            loadingDialog.show();
            BaseApi.API.createOrder(token, purchaseBody).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if(response.isSuccessful()){ // chỉ nhận đầu status 200
                        ServerResponse serverResponse = response.body();
                        Log.d(TAG.toString, "onResponse-createOrder: " + serverResponse.toString());

                        if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                            Toast.makeText(PayActivity.this, serverResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            CartUtil.listCartCheck.clear();
                        }
                    } else { // nhận các đầu status #200
                        try {
                            String errorBody = response.errorBody().string();
                            JSONObject errorJson = new JSONObject(errorBody);
                            String errorMessage = errorJson.getString("message");
                            Log.d(TAG.toString, "onResponse-createOrder: " + errorMessage);
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
                    Log.d(TAG.toString, "onFailure-createOrder: " + t.toString());
                    loadingDialog.dismiss();
                }
            });
        }
    }

    private boolean validatePurchare() {
        if(info == null) {
            Toast.makeText(this, "Mời nhập địa chỉ!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        totalPrice = getIntent().getIntExtra("totalPrice", 0);
        DecimalFormat df = new DecimalFormat("###,###,###");
        binding.tvTotalPrice.setText(df.format(totalPrice) + "đ");

        loadingDialog = new ProgressLoadingDialog(this);
        infoList = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rcvProduct.setLayoutManager(linearLayoutManager);
        cartPayAdapter = new CartPayAdapter(this, CartUtil.listCartCheck);
        binding.rcvProduct.setAdapter(cartPayAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}
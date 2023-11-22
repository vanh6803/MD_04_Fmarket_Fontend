package com.example.hn_2025_online_shop.view.find_product;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.FindAdapter;
import com.example.hn_2025_online_shop.adapter.ProductByCategoryAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FindProductBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductByCategory;
import com.example.hn_2025_online_shop.model.ProductFind;
import com.example.hn_2025_online_shop.model.response.ProductByCategoryReponse;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FindProduct extends AppCompatActivity implements ObjectUtil {
    private FindProductBinding binding;
    private ProgressLoadingDialog loadingDialog;
    private ProductByCategoryAdapter productAdapter;
    private List<ProductByCategory> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FindProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initController();
        callApiProductByCategory();
    }

    private void initController() {

        //icon deleteText
        binding.deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etdFind.setText("");
            }
        });


        //icon back
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //icon find
        binding.find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.find.setVisibility(View.GONE);
                binding.filter.setVisibility(View.VISIBLE);
            }
        });




        binding.etdFind.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    productAdapter.filterItem(s.toString());
                    binding.deleteText.setVisibility(View.VISIBLE);
                    binding.find.setVisibility(View.VISIBLE);
                    binding.filter.setVisibility(View.GONE);
                }else {
                    productAdapter.filterItem(s.toString());
                    binding.deleteText.setVisibility(View.GONE);
                    binding.find.setVisibility(View.VISIBLE);
                    binding.filter.setVisibility(View.GONE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        binding.etdFind.addTextChangedListener(textWatcher);
        binding.recycleView.setAdapter(productAdapter);



    }
    private void performSearch() {
        String searchTerm = binding.etdFind.getText().toString();
        productAdapter.filterItem(searchTerm);
    }


    @SuppressLint("NotifyDataSetChanged")
    private  void initView(){
        loadingDialog = new ProgressLoadingDialog(this);
        productList = new ArrayList<>();
        productAdapter = new ProductByCategoryAdapter(this, productList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recycleView.setLayoutManager(linearLayoutManager);
        binding.recycleView.setAdapter(productAdapter);
    }






    private void callApiProductByCategory(){
        loadingDialog.show();
        BaseApi.API.getListProductByCategory().enqueue(new Callback<ProductByCategoryReponse>() {
            @Override
            public void onResponse(Call<ProductByCategoryReponse> call, Response<ProductByCategoryReponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ProductByCategoryReponse reponse = response.body();
                    Log.d(TAG.toString, "onResponse-ListProductByCategory: " + reponse.toString());
                    if(reponse.getCode() == 200) {
                        productAdapter.setListProductType(reponse.getResult());
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-register: " + errorMessage);

                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ProductByCategoryReponse> call, Throwable t) {
                loadingDialog.dismiss();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG.toString, "onResume: ");
    }



    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG.toString, "onStart: ");
    }

    @Override
    public void onclickObject(Object object) {
        Product product = (Product) object;
        String id = product.getId();
        Intent intent = new Intent(this, DetailProduct.class);
        intent.putExtra("id_product", id);
        this.startActivity(intent);
        this.overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
    }
}

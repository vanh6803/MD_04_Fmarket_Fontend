package com.example.hn_2025_online_shop.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentProductBinding;
import com.example.hn_2025_online_shop.model.Banner;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.response.BannerReponse;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.CartUtil;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.cart_screen.CartActivity;
import com.example.hn_2025_online_shop.view.chat_message.ChatActivity;
import com.example.hn_2025_online_shop.view.find_product.FindProduct;
import com.example.hn_2025_online_shop.view.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProduct extends Fragment implements ObjectUtil {

    private List<Product> listProdcut;
    private ProductAdapter productAdapter;
    private FragmentProductBinding binding;
    public static FragmentProduct newInstance() {
        FragmentProduct fragment = new FragmentProduct();
        return fragment;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //init view và các action vào đây
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initController();
        callApiBanner();
        callApiGetListAllProducts();
        setNumberCart();
    }

    private void setNumberCart() {
        // Lấy danh sách cart
        binding.tvQuantityCart.setText(CartUtil.listCart.size() + "");
    }

    private void callApiBanner() {
        BaseApi.API.getListBanner().enqueue(new Callback<BannerReponse>() {
            @Override
            public void onResponse(Call<BannerReponse> call, Response<BannerReponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    BannerReponse reponse = response.body();
                    Log.d(TAG.toString, "onResponse-getListBanner: " + reponse.toString());
                    if(reponse.getCode() == 200) {
                        setDataBanner(reponse.getData());
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getListBanner: " + errorMessage);
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            @Override
            public void onFailure(Call<BannerReponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDataBanner(List<Banner> data) {
        ArrayList<SlideModel> list  = new ArrayList<>();
        List<String> tabTitles = new ArrayList<>();
        for (Banner banner: data) {
            list.add(new SlideModel(banner.getImage() , ScaleTypes.FIT));
            tabTitles.add(banner.getNote());
        }
        binding.sliderProduct.setImageList(list, ScaleTypes.FIT);
    }

    private void initController() {
        binding.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });

        binding.imgChat.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    productAdapter.filterItem(s.toString());

                }else {
                    productAdapter.filterItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
//        binding.find.addTextChangedListener(textWatcher);
//        binding.recycleProduct.setAdapter(productAdapter);
        binding.find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FindProduct.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.recycleProduct.setLayoutManager(layoutManager);
        listProdcut = new ArrayList<>();
        productAdapter = new ProductAdapter(getContext(), listProdcut, this);
        productAdapter.setProductList(listProdcut);
        binding.recycleProduct.setAdapter(productAdapter);
    }

    public void callApiGetListAllProducts(){
        binding.progressBar.setVisibility(View.VISIBLE);
        BaseApi.API.getListAllProduct(true, AccountUltil.TOKEN).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    ProductResponse productResponse = response.body();
                    productAdapter.setProductList(productResponse.getResult());
                    binding.recycleProduct.setAdapter(productAdapter);
                } else {
                    Toast.makeText(getActivity(), "call list all products err", Toast.LENGTH_SHORT).show();
                }
                binding.progressBar.setVisibility(View.GONE);
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Err", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onclickObject(Object object) {
        Product product = (Product) object;
        String id = product.getId();
        Intent intent = new Intent(getActivity(), DetailProduct.class);
        intent.putExtra("id_product", id);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
    }

}
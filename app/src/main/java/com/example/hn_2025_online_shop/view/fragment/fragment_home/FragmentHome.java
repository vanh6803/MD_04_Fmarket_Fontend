package com.example.hn_2025_online_shop.view.fragment.fragment_home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.page_view.ViewPageHomeAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.FragmentHomeBinding;
import com.example.hn_2025_online_shop.model.Banner;
import com.example.hn_2025_online_shop.model.response.BannerReponse;
import com.example.hn_2025_online_shop.ultil.ApiUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.cart_screen.CartActivity;
import com.example.hn_2025_online_shop.view.chat_message.ChatActivity;
import com.example.hn_2025_online_shop.view.find_product.FindProduct;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentHome extends Fragment {

    private FragmentHomeBinding binding;
    private ViewPageHomeAdapter viewPageHomeAdapter;

    public FragmentHome() {
    }

    public static FragmentHome newInstance() {
        FragmentHome fragment = new FragmentHome();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Lấy detail user rồi gắn vào lớp utils để đâu cũng có thể gọi
        initView();
        initController();
        callApiBanner();
        setTab();
        ApiUtil.setTitleQuantityCart(getActivity(), binding.tvQuantityCart);
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

        for (Banner banner: data) {
            list.add(new SlideModel(banner.getImage() , ScaleTypes.FIT));

        }
        binding.sliderHome.setImageList(list, ScaleTypes.FIT);
    }

    private void setTab() {
        viewPageHomeAdapter = new ViewPageHomeAdapter(getActivity());
        binding.viewPagerHome.setAdapter(viewPageHomeAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(binding.tabHome, binding.viewPagerHome, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Sản phẩm");
                        break;
                    case 1:
                        tab.setText("Giảm giá");
                        break;
                    case 2:
                        tab.setText("Bán Chạy");
                        break;
                    default:
                        tab.setText("Liên quan");
                }
            }
        });

        mediator.attach();
    }

    private void initController() {
        binding.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                mActivityResultLauncher.launch(intent);
                getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });



        binding.findPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FindProduct.class);
                mActivityResultLauncher.launch(intent);
                getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });

        binding.imgChat.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), ChatActivity.class);
            startActivity(intent);
            getActivity().overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
        });
    }

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == getActivity().RESULT_OK) {
                        Intent intent = result.getData();
                        int cartSize = intent.getIntExtra("data_cart_size", 0);
                        binding.tvQuantityCart.setText(cartSize + "");
                    }
                }
            });

    private void initView() {
    }

    private void setTabTitles(List<String> tabTitles) {
        for (int i = 0; i < tabTitles.size(); i++) {
            TabLayout.Tab tab = binding.tabHome.getTabAt(i);
            if (tab != null) {
                tab.setText(tabTitles.get(i));
            }
        }
    }
}
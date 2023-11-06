package com.example.hn_2025_online_shop.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

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
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProduct extends Fragment {

    private List<Product> listProdcut;
    private ProductAdapter productAdapter;
    ProgressLoadingDialog dialog;
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
        dialog = new ProgressLoadingDialog(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.recycleProduct.setLayoutManager(layoutManager);
        listProdcut = new ArrayList<>();
//        listProdcut.add(new Product_main("1", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));
//        listProdcut.add(new Product_main("2", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));
//        listProdcut.add(new Product_main("3", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));
//        listProdcut.add(new Product_main("4", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));
//        listProdcut.add(new Product_main("5", "Laptop Az", "https://www.techone.vn/wp-content/uploads/2023/09/iphone-15-pro-max_2__5.webp", "aaa"));
        productAdapter = new ProductAdapter(getContext(), listProdcut);
        productAdapter.setProductList(listProdcut);
        binding.recycleProduct.setAdapter(productAdapter);
        ArrayList<SlideModel> list  = new ArrayList<>();
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        list.add(new SlideModel(R.drawable.banner2 , ScaleTypes.FIT));
        binding.sliderProduct.setImageList(list, ScaleTypes.FIT);
        callApiGetListAllProducts();
    }
    public void callApiGetListAllProducts(){
        dialog.show();
        BaseApi.API.getListAllProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse productResponse = response.body();
                    productAdapter.setProductList(productResponse.getResult());
                    binding.recycleProduct.setAdapter(productAdapter);
                }else {
                    Toast.makeText(getActivity(), "call list all products err", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Err", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
}
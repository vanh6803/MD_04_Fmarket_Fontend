package com.example.hn_2025_online_shop.view.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.ProductAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityShowAllProductByCategoryBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductByCategory;
import com.example.hn_2025_online_shop.model.response.ProductByCategoryReponse;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowAllProductByCategoryActivity extends AppCompatActivity implements ObjectUtil {
    ActivityShowAllProductByCategoryBinding binding;
    List<Product> list;
    ProductAdapter adapter;
    private ProgressLoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowAllProductByCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressLoadingDialog(this);
        list = new ArrayList<>();;
        binding.tvNameCategory.setText("Giá : 499k");
//        list.add(new Product("1321", "Điện Thoại đểu", false, "https://gaixinhdep.net/wp-content/uploads/2023/10/anh-gai-xinh-lo-num-vu.jpg", 1, 2, 1));
//        list.add(new Product("1321", "Điện Thoại đểu", false, "https://gaixinhdep.net/wp-content/uploads/2023/10/anh-gai-xinh-lo-num-vu.jpg", 1, 2, 1));
//        list.add(new Product("1321", "Điện Thoại đểu", false, "https://gaixinhdep.net/wp-content/uploads/2023/10/anh-gai-xinh-lo-num-vu.jpg", 1, 2, 1));
//        list.add(new Product("1321", "Điện Thoại đểu", false, "https://gaixinhdep.net/wp-content/uploads/2023/10/anh-gai-xinh-lo-num-vu.jpg", 1, 2, 1));
//        list.add(new Product("1321", "Điện Thoại đểu", false, "https://gaixinhdep.net/wp-content/uploads/2023/10/anh-gai-xinh-lo-num-vu.jpg", 1, 2, 1));
//        list.add(new Product("1321", "Điện Thoại đểu", false, "https://gaixinhdep.net/wp-content/uploads/2023/10/anh-gai-xinh-lo-num-vu.jpg", 1, 2, 1));
//        list.add(new Product("1321", "Điện Thoại đểu", false, "https://gaixinhdep.net/wp-content/uploads/2023/10/anh-gai-xinh-lo-num-vu.jpg", 1, 2, 1));
        adapter = new ProductAdapter(this, list, this);
        binding.rcvAllProductByCategory.setAdapter(adapter);
        ShowListAllProductByCategory();
    }

    private void ShowListAllProductByCategory() {
        dialog.show();
        Intent intent = getIntent();
        ProductByCategory categoryId = (ProductByCategory) intent.getSerializableExtra("categoryId");
        Log.d("categoryId", "ShowListAllProductByCategory: "+ categoryId);
        binding.tvNameCategory.setText(categoryId.getNameCategory());
        adapter.setProductList(categoryId.getProduct());
        binding.rcvAllProductByCategory.setAdapter(adapter);
        dialog.dismiss();

    }

    private void setdaUi(ProductResponse reponse) {

    }


    @Override
    public void onclickObject(Object object) {
        Product product = (Product) object;
        String id = product.getId();
        Intent intent = new Intent(this, DetailProduct.class);
        intent.putExtra("id_product", id);
        startActivity(intent);
        overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);

    }
}
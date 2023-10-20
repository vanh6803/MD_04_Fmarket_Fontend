package com.example.hn_2025_online_shop.view.product_screen;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.adapter.ProductSaleAdapter;
import com.example.hn_2025_online_shop.adapter.VoucherAdapter;
import com.example.hn_2025_online_shop.databinding.DetailProductBinding;
import com.example.hn_2025_online_shop.model.Product_sale;
import com.example.hn_2025_online_shop.model.Voucher;

import java.util.ArrayList;
import java.util.List;

public class DetailProduct extends AppCompatActivity {
    private DetailProductBinding binding;
    List<Product_sale> productSaleList;
    List<Voucher> voucherList;
    ProductSaleAdapter productSaleAdapter;
    VoucherAdapter voucherAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textsale.setPaintFlags(binding.textsale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        binding.backDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        productSaleList = new ArrayList<>();
        voucherList = new ArrayList<>();
        for (int i = 1 ; i< 4; i++){
            productSaleList.add(new Product_sale("sản phẩm "+ i,12341,1233,"https://cdn11.dienmaycholon.vn/filewebdmclnew/public/userupload/files/news/di-dong/co-the-apple-se-trang-bi-man-hinh-phu-cho-iphone-15-pro.jpg"));
            voucherList.add(new Voucher("Giảm"+i+"%"));
        }
        productSaleAdapter = new ProductSaleAdapter(productSaleList, this);
        voucherAdapter = new VoucherAdapter(this, voucherList);
        binding.recyProductSame.setAdapter(productSaleAdapter);
        binding.recyVoucher.setAdapter(voucherAdapter);

    }
}

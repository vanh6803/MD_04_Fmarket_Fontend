package com.example.hn_2025_online_shop.adapter;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ItemProductByCategoryBinding;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductByCategory;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.view.fragment.ShowAllProductByCategoryActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductByCategoryAdapter extends RecyclerView.Adapter<ProductByCategoryAdapter.ViewHolder> {
    private final Context context;
    private List<ProductByCategory> productByCategoryList;
    private List<ProductByCategory> filteredItems;
    private ProductAdapter productAdapter;
    private ObjectUtil objectUtil;

    @SuppressLint("NotifyDataSetChanged")
    public void setListProductType(List<ProductByCategory> productByCategoryList) {
        this.productByCategoryList = productByCategoryList;
        this.filteredItems = new ArrayList<>(productByCategoryList);
        notifyDataSetChanged();

    }

    public ProductByCategoryAdapter(Context context, List<ProductByCategory> productByCategoryList, ObjectUtil objectUtil) {
        this.context = context;
        this.productByCategoryList = productByCategoryList;
        this.objectUtil = objectUtil;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductByCategoryBinding binding = ItemProductByCategoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductByCategory productByCategory = productByCategoryList.get(position);
        if(productByCategory == null) {
            return;
        }


        holder.binding.titleType.setText(productByCategory.getNameCategory());
        setDataRcvProduct(productByCategory.getProduct(), holder.binding);
        holder.binding.tvXemTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("productByCategory", "onClick: " + productByCategory);
                String categoryId = productByCategory.getId();
                Log.d("categoryId", "onBindViewHolder: " + categoryId);
                Intent intent = new Intent(context, ShowAllProductByCategoryActivity.class);
                intent.putExtra("categoryId", productByCategory);
                context.startActivity(intent);
            }
        });
    }

    private void setDataRcvProduct(List<Product> productList, ItemProductByCategoryBinding binding) {
        productAdapter = new ProductAdapter(context, productList, objectUtil);
        GridLayoutManager gridLayout = new GridLayoutManager(context, 2);
        binding.rcvProduct.setLayoutManager(gridLayout);
        binding.rcvProduct.setAdapter(productAdapter);

    }

    @Override
    public int getItemCount() {
        if(productByCategoryList != null) {
            return productByCategoryList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemProductByCategoryBinding binding;
        public ViewHolder(ItemProductByCategoryBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    public void filterItem(String query) {
        productByCategoryList.clear();
        if (query.isEmpty()) {
            productByCategoryList.addAll(filteredItems);

        } else {
            for (ProductByCategory item : filteredItems) {
                if (item.getNameCategory().toLowerCase().contains(query.toLowerCase())) {
                    productByCategoryList.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }
}
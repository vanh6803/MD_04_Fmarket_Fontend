package com.example.hn_2025_online_shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutIteamProductBestsellerBinding;

import com.example.hn_2025_online_shop.model.OptionProductBestSeller;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductBestSellerAdapter extends RecyclerView.Adapter<ProductBestSellerAdapter.ProductViewHolder> {
    private Context context;
    private List<OptionProductBestSeller> productList;
    private List<OptionProductBestSeller> filteredItems;
    private ObjectUtil objectUtil;

    public ProductBestSellerAdapter(Context context, List<OptionProductBestSeller> productList, ObjectUtil objectUtil) {
        this.productList = productList;
        this.context = context;
        this.objectUtil = objectUtil;
    }

    public void setListProductBestSeller(List<OptionProductBestSeller> productList) {
        this.productList = productList;
        this.filteredItems = new ArrayList<>(productList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutIteamProductBestsellerBinding binding = LayoutIteamProductBestsellerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductViewHolder(binding);
    }
    @Override
    public int getItemCount() {
        if(productList != null){
            return  productList.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        OptionProductBestSeller product = productList.get(position);
        holder.binding.tvName.setText(product.getProductId().getName() + "-" +  product.getNameColor());
        DecimalFormat df = new DecimalFormat("###,###,###");
        holder.binding.tvPrice.setText(df.format(product.getPrice()) + " đ");
        Glide.with(context)
                .load(product.getImage())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.binding.imgProduct);
        holder.binding.ratingBar.setRating((float) product.getProductId().getAverageRate());
        holder.binding.tvReview.setText("Đã bán " + product.getSoldQuantity());


        holder.binding.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectUtil.onclickObject(product);
            }
        });

    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private LayoutIteamProductBestsellerBinding binding;
        public ProductViewHolder(LayoutIteamProductBestsellerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


    @SuppressLint("NotifyDataSetChanged")
    public void filterItem(String query) {
        productList.clear();
        if (query.isEmpty()) {
            productList.addAll(filteredItems);

        } else {
            for (OptionProductBestSeller item : filteredItems) {
                if (item.getProductId().getName().toLowerCase().contains(query.toLowerCase())) {
                    productList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}


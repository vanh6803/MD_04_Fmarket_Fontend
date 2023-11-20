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
import com.example.hn_2025_online_shop.databinding.ItemProductFindBinding;
import com.example.hn_2025_online_shop.model.ProductFind;
import java.util.ArrayList;
import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {
    private final Context context;
    private final List<ProductFind> productList;
    private final List<ProductFind> filteredItems;

    public FindAdapter(Context context, List<ProductFind> productList) {
        this.context = context;
        this.productList = productList;
        this.filteredItems = new ArrayList<>(productList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProductFindBinding binding = ItemProductFindBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FindAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductFind product = productList.get(position);
        Glide.with(context)
                .load(product.getImg())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.binding.imgProduct);
        holder.binding.tvProductName.setText(product.getName());

        holder.binding.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        private final ItemProductFindBinding binding;

        public ViewHolder(ItemProductFindBinding binding) {
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
            for (ProductFind item : filteredItems) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    productList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


}

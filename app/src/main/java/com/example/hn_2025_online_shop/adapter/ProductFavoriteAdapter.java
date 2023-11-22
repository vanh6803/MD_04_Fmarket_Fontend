package com.example.hn_2025_online_shop.adapter;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ItemFavoritesBinding;
import com.example.hn_2025_online_shop.model.ProductFind;
import java.util.List;

public class ProductFavoriteAdapter extends RecyclerView.Adapter<ProductFavoriteAdapter.ViewHolder> {
    private Context context;
    private List<ProductFind> productList;

    public ProductFavoriteAdapter(Context context, List<ProductFind> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFavoritesBinding binding = ItemFavoritesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductFind product = productList.get(position);
        Glide.with(context)
                .load(product.getImg())
                .placeholder(R.drawable.loading)
                .error(R.drawable.error)
                .into(holder.binding.imgProduct);
        holder.binding.tvName.setText(product.getName());
        holder.binding.txtCount.setText("10");
        holder.binding.txtSale.setText("200.000đ");
        holder.binding.txtSale.setPaintFlags(holder.binding.txtSale.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder{
        private ItemFavoritesBinding binding;
        public ViewHolder(ItemFavoritesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

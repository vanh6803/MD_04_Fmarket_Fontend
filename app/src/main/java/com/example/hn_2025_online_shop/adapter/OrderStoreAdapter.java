package com.example.hn_2025_online_shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.databinding.LayoutItemOrderBinding;
import com.example.hn_2025_online_shop.databinding.LayoutItemOrderStoreBinding;
import com.example.hn_2025_online_shop.model.Order;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderStoreAdapter extends RecyclerView.Adapter<OrderStoreAdapter.OrderStoreViewHolder>{
    private List<Order> orderList;
    private Context context;
    private OrderProductAdapter orderProductAdapter;
    private ObjectUtil objectUtil;

    public OrderStoreAdapter(Context context, List<Order> orderList, ObjectUtil objectUtil) {
        this.context = context;
        this.orderList = orderList;
        this.objectUtil = objectUtil;
    }

    public void setListOrder(List<Order> orderList) {
        this.orderList = orderList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrderStoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemOrderStoreBinding binding = LayoutItemOrderStoreBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new OrderStoreViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderStoreViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.binding.tvStatus.setText(order.getStatus());
        holder.binding.tvQuantityTypeProduct.setText(order.getProductsOrder().size() + " loại sản phẩm");
        holder.binding.tvNameUser.setText("Người mua: " + order.getUser().getUsername());

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = inputFormat.parse(order.getCreatedAt());
            holder.binding.tvDate.setText("Ngày đặt: " + outputFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                objectUtil.onclickObject(order);
            }
        });

        setRcvProduct(holder.binding, order);
    }

    private void setRcvProduct(LayoutItemOrderStoreBinding binding, Order order) {
        orderProductAdapter = new OrderProductAdapter(context, order.getProductsOrder());
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        binding.rcvOrderDetail.setLayoutManager(layoutManager);
        binding.rcvOrderDetail.setAdapter(orderProductAdapter);
    }

    @Override
    public int getItemCount() {
        if(orderList != null){
            return  orderList.size();
        }
        return 0;
    }

    public class OrderStoreViewHolder extends RecyclerView.ViewHolder {
        LayoutItemOrderStoreBinding binding;
        public OrderStoreViewHolder(LayoutItemOrderStoreBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

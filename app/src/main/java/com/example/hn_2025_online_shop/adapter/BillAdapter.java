package com.example.hn_2025_online_shop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hn_2025_online_shop.databinding.ItemBillBinding;
import com.example.hn_2025_online_shop.model.Order;
import com.example.hn_2025_online_shop.model.response.store.ProductOder;
import com.example.hn_2025_online_shop.model.response.store.Result;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.view.my_store.Bill.DetailBill;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {

    private List<Result> list;
    private Context context;
    private ObjectUtil objectUtil;



    public BillAdapter(List<Result> list, Context context, ObjectUtil objectUtil) {
        this.list = list;
        this.context = context;
        this.objectUtil = objectUtil;
    }

    public void setListBill(List<Result> resultList) {
        this.list = resultList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBillBinding binding = ItemBillBinding.inflate(LayoutInflater.from(parent.getContext()), parent , false);
        return new ViewHolder(binding);
    }

    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result bill = list.get(position);
        holder.binding.stt.setText(String.valueOf(position + 1));
        holder.binding.code.setText("#"+bill.getIdPro());
      for (int i = 0; i< bill.getProductsOrder().size(); i++){
          holder.binding.date.setText(convertDateFormat(bill.getProductsOrder().get(i).getOption_id().getCreatedAt()));
         if( bill.getProductsOrder().get(i).getOption_id().getProduct()!= null){
             holder.binding.content.setText(
                     bill.getProductsOrder().get(i).getOption_id().getProduct().getName() +
                             "màu" +bill.getProductsOrder().get(i).getOption_id().getNameColor() +"...");
         } else{
             holder.binding.content.setText("");
         }

      }
        holder.binding.price.setText(String.valueOf(bill.getTotal_price()));
        if(position%2 == 0){
            holder.binding.item.setBackgroundColor(0xffffffff);
        }else {
            holder.binding.item.setBackgroundColor(0xffE0FFFF);
        }

        holder.binding.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailBill.class);
                intent.putExtra("id", bill.getIdPro());
               context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends  RecyclerView.ViewHolder{
        ItemBillBinding binding ;

        public ViewHolder(ItemBillBinding binding ) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    private String convertDateFormat(String inputDate) {
        String outputDate = "";

        try {
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");

            Date date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return outputDate;
    }
}

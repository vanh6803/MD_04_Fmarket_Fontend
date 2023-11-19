package com.example.hn_2025_online_shop.view.find_product;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hn_2025_online_shop.adapter.FindAdapter;
import com.example.hn_2025_online_shop.databinding.FindProductBinding;
import com.example.hn_2025_online_shop.model.ProductFind;

import java.util.ArrayList;
import java.util.List;


public class FindProduct extends AppCompatActivity  {
    private FindProductBinding binding;
    FindAdapter adapter;
    List<ProductFind> product;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FindProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initController();
        initView();
    }

    private void initController() {

        //icon deleteText
        binding.deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.etdFind.setText("");
            }
        });


        //icon back
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //icon find
        binding.find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.find.setVisibility(View.GONE);
                binding.filter.setVisibility(View.VISIBLE);
            }
        });




        binding.etdFind.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch();
                    return true;
                }
                return false;
            }
        });
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() > 0){
                    adapter.filterItem(s.toString());
                    binding.deleteText.setVisibility(View.VISIBLE);
                    binding.find.setVisibility(View.VISIBLE);
                    binding.filter.setVisibility(View.GONE);
                }else {
                    adapter.filterItem(s.toString());
                    binding.deleteText.setVisibility(View.GONE);
                    binding.find.setVisibility(View.VISIBLE);
                    binding.filter.setVisibility(View.GONE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        binding.etdFind.addTextChangedListener(textWatcher);
        binding.recycleView.setAdapter(adapter);



    }
    private void performSearch() {
        String searchTerm = binding.etdFind.getText().toString();
        adapter.filterItem(searchTerm);
    }


    @SuppressLint("NotifyDataSetChanged")
    private  void initView(){
        product = new ArrayList<>();
        for (int i = 0 ; i< 10; i++){
            product.add(new ProductFind("Điện thoại "+ i, "https://res.cloudinary.com/dwxavjnvc/image/upload/v1699708633/Product/csddbopt4d1xeeivy8bc.png"));
        }

        adapter = new FindAdapter(this, product);
        binding.recycleView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}

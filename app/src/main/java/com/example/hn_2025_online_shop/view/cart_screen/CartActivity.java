package com.example.hn_2025_online_shop.view.cart_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.CartAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityCartBinding;
import com.example.hn_2025_online_shop.model.CartOfList;
import com.example.hn_2025_online_shop.model.response.CartReponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.CartInterface;
import com.example.hn_2025_online_shop.ultil.CartUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartInterface{

    private ActivityCartBinding binding;
    private CartAdapter cartAdapter;
    private List<CartOfList> listCart;
    private int totalPrice = 0;
    private ProgressLoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initController();
        getListCart();
    }


    private void getListCart() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        loadingDialog.show();
        BaseApi.API.allCartUser(token).enqueue(new Callback<CartReponse>() {
            @Override
            public void onResponse(Call<CartReponse> call, Response<CartReponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    CartReponse cartReponse = response.body();
                    Log.d(TAG.toString, "onResponse-allCartUser: " + cartReponse.toString());
                    if(cartReponse.getCode() == 200) {
                        listCart = cartReponse.getData();
                        cartAdapter.setListCart(listCart);
                        setTotalPrice();
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-allCartUser: " + errorMessage);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                loadingDialog.dismiss();
            }

            @Override
            public void onFailure(Call<CartReponse> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-allCartUser: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }

    private void initController() {
        binding.arrowBackDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });
    }

    private void initView() {
        loadingDialog = new ProgressLoadingDialog(this);
        cartAdapter = new CartAdapter(this, listCart, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rcvCart.setLayoutManager(layoutManager);
        binding.rcvCart.setAdapter(cartAdapter);
    }

    @Override
    public void setTotalPrice() {
        totalPrice = 0;
        for (int i = 0; i < CartUtil.listCartCheck.size(); i++) {
            int price = CartUtil.listCartCheck.get(i).getOptionProduct().getPrice();
            int quantity = CartUtil.listCartCheck.get(i).getQuantity();
            totalPrice += price * quantity;
        }
        DecimalFormat df = new DecimalFormat("###,###,###");
        binding.tvTotalPrice.setText(df.format(totalPrice) + " đ");
    }

    @Override
    public void onclickMinus(Object object, int position) {
        CartOfList cart = (CartOfList) object;
        int quantity = cart.getQuantity();
        if(quantity > 1) {
            quantity -= 1;
            listCart.get(position).setQuantity(quantity);
            cartAdapter.setListCart(listCart);
            setTotalPrice();
        }
    }

    @Override
    public void onclickPlus(Object object, int position) {
        CartOfList cart = (CartOfList) object;
        int quantity = cart.getQuantity();
        quantity += 1;
        listCart.get(position).setQuantity(quantity);
        cartAdapter.setListCart(listCart);
        setTotalPrice();
    }
}
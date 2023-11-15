package com.example.hn_2025_online_shop.view.cart_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.CartAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.ActivityCartBinding;
import com.example.hn_2025_online_shop.model.CartOfList;
import com.example.hn_2025_online_shop.model.response.CartReponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ApiUtil;
import com.example.hn_2025_online_shop.ultil.CartInterface;
import com.example.hn_2025_online_shop.ultil.CartUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.ultil.swipe.ItemTouchHelperListener;
import com.example.hn_2025_online_shop.ultil.swipe.RecycleViewItemTouchHelper;
import com.example.hn_2025_online_shop.view.buy_product.PayActivity;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements CartInterface, ItemTouchHelperListener {

    private ActivityCartBinding binding;
    private CartAdapter cartAdapter;
    private int totalPrice = 0;
    private ProgressLoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initView();
        initController();
        // Hàm này có sẵn ở đâu cx gọi đc
        ApiUtil.getAllCart(this, loadingDialog, cartAdapter);
    }

    private void initController() {
        binding.arrowBackDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
            }
        });

        binding.btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CartUtil.listCartCheck.size() > 0) {
                    Intent intent = new Intent(CartActivity.this, PayActivity.class);
                    intent.putExtra("totalPrice" , totalPrice);
                    startActivity(intent);
                } else {
                    Toast.makeText(CartActivity.this, "Mời bạn chọn sản phẩm trong giỏ hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        // Xóa toàn bộ list đc chọn cũ
        CartUtil.listCartCheck.clear();

        // recycleView
        loadingDialog = new ProgressLoadingDialog(this);
        cartAdapter = new CartAdapter(this, CartUtil.listCart, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rcvCart.setLayoutManager(layoutManager);
        binding.rcvCart.setAdapter(cartAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        binding.rcvCart.addItemDecoration(itemDecoration);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecycleViewItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.rcvCart);
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
            CartUtil.listCart.get(position).setQuantity(quantity);
            cartAdapter.setListCart(CartUtil.listCart);
            setTotalPrice();
        }
    }

    @Override
    public void onclickPlus(Object object, int position) {
        CartOfList cart = (CartOfList) object;
        int quantity = cart.getQuantity();
        quantity += 1;
        CartUtil.listCart.get(position).setQuantity(quantity);
        cartAdapter.setListCart(CartUtil.listCart);
        setTotalPrice();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder) {
        if(viewHolder instanceof CartAdapter.CartViewHolder) {
            CartOfList cart = CartUtil.listCart.get(viewHolder.getAdapterPosition());
            int indexDelete = viewHolder.getAdapterPosition();
            deleteCart(cart, indexDelete);
        }
    }

    private void deleteCart(CartOfList cart, int indexDelete) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        String cartId = cart.getId();
        loadingDialog.show();
        BaseApi.API.deleteCartItem(token, cartId).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    Log.d(TAG.toString, "onResponse-deleteCartItem: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200) {
                        cartAdapter.removeItem(indexDelete);
                        setTotalPrice();
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-deleteCartItem: " + errorMessage);
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
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Toast.makeText(CartActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-deleteCartItem: " + t.toString());
                loadingDialog.dismiss();
            }
        });
    }
}
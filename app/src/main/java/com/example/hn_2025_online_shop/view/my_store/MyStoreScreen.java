package com.example.hn_2025_online_shop.view.my_store;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.LayoutHeaderNavBinding;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.view.my_store.Bill.FragmentBill;
import com.example.hn_2025_online_shop.view.my_store.OrderStore.FragmentOrder;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.model.response.store.InfoStore;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.google.android.material.navigation.NavigationView;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.Objects;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyStoreScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int Fragment_home = 0;
    private static final int Fragment_khosanpham = 1;
    private static final int Fragment_dondathang = 2;
    private static final int Fragment_xuatkho = 3;
    private static final int Fragment_taoVoucher = 4;
    private static final int Fragment_baoCaoDoanhThu = 5;
    private static final int Fragment_CreateProductMyStore = 6;
    private static final int Fragment_UpdateInforStore = 7;
    private static final int Fragment_HoaDon = 8;
    private int mcurrentFrg = Fragment_home;
    private DrawerLayout drawerLayout;
    private ProgressLoadingDialog dialog;
    private String nameStore;
    LayoutHeaderNavBinding binding;

    private View view;

    private View mHeaderView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_store_screen);
        dialog = new ProgressLoadingDialog(this);
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigation_View);
        navigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new FragmentHomeStore());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        binding = LayoutHeaderNavBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        getProfileStore(token);
        mHeaderView = navigationView.getHeaderView(0);
    }






    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(mcurrentFrg != Fragment_home){
                replaceFragment(new FragmentHomeStore());
                mcurrentFrg = Fragment_home;
            }

        }else if(id == R.id.khosanpham){
            if(mcurrentFrg != Fragment_khosanpham){
                replaceFragment(new FragmentProductWarehouse());
                mcurrentFrg = Fragment_khosanpham;
            }

        }

        else if(id == R.id.dondathang){
            if(mcurrentFrg != Fragment_dondathang){
                replaceFragment(new FragmentOrder());
                mcurrentFrg = Fragment_dondathang;
            }

        }
//        else if(id == R.id.taovoucher){
//            if(mcurrentFrg != Fragment_taoVoucher){
//                replaceFragment(new CreateVoucherFragment());
//                mcurrentFrg = Fragment_taoVoucher;
//            }
//        }
        else if(id == R.id.inforStore){
            if(mcurrentFrg != Fragment_UpdateInforStore){
                replaceFragment(new UpdateInforStoreFragment());
                mcurrentFrg = Fragment_UpdateInforStore;
            }

        }
        else if(id == R.id.dangsanpham){
            if(mcurrentFrg != Fragment_CreateProductMyStore){
                replaceFragment(new FragmentCreateProductMyStore());
                mcurrentFrg = Fragment_CreateProductMyStore;
            }


        }
//        else if(id == R.id.xuatkho){
//            if(mcurrentFrg != Fragment_xuatkho){
//                replaceFragment(new FragmentWarehouse());
//                mcurrentFrg = Fragment_xuatkho;
//            }
//
//        }
        else if(id == R.id.hoadon){
            if(mcurrentFrg != Fragment_HoaDon){
                replaceFragment(new FragmentBill());
                mcurrentFrg = Fragment_HoaDon;
            }

        }
        else if(id == R.id.doanhthu){
            if(mcurrentFrg != Fragment_baoCaoDoanhThu){
                replaceFragment(new FragmentStatistical());
                mcurrentFrg = Fragment_baoCaoDoanhThu;
            }
        }else if(id == R.id.quayve){
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
            overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }


    private void getProfileStore(String token) {
        BaseApi.API.getInfoStore(token).enqueue(new Callback<InfoStore>() {
            @Override
            public void onResponse(@NonNull Call<InfoStore> call, @NonNull Response<InfoStore> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    InfoStore storeIdResponse = response.body();
                    assert storeIdResponse != null;
                    if(storeIdResponse.getCode() == 200 || storeIdResponse.getCode() == 201) {
                        nameStore = storeIdResponse.getData().getName();
                        Objects.requireNonNull(getSupportActionBar()).setTitle(nameStore);
                        setDataInforStore(storeIdResponse);
                        String storeId = storeIdResponse.getData().get_id();
                        SharedPreferences sharedPreferences = getSharedPreferences("storeId", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("storeId", storeId);
                        editor.commit();

                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-createProduct: " + errorMessage);
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<InfoStore> call, @NonNull Throwable t) {

            }
        });

    }

    private void setDataInforStore(InfoStore storeIdResponse) {
        TextView tvNameStore = mHeaderView.findViewById(R.id.tvNameStore);
        tvNameStore.setText(storeIdResponse.getData().getName());
        TextView tvEmail = mHeaderView.findViewById(R.id.tvEmail);
        tvEmail.setText(AccountUltil.USER.getEmail());
        ImageView imgAvartarStore = mHeaderView.findViewById(R.id.imgAvartarStore);
        Glide.with(this)
                .load(storeIdResponse.getData().getAvatar())
                .error(R.drawable.error)
                .into(imgAvartarStore);

        Log.d("showdata", "setDataInforStore: " + storeIdResponse.getData().getName() );
    }
}
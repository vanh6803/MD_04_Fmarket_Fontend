package com.example.hn_2025_online_shop.view.my_store;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hn_2025_online_shop.R;
import com.google.android.material.navigation.NavigationView;

public class MyStoreScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private static final int Fragment_home = 0;
    private static final int Fragment_khosanpham = 1;
    private static final int Fragment_dondathang = 2;
    private static final int Fragment_xuatkho = 3;
    private int mcurrentFrg = Fragment_home;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_store_screen);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_View);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new Fragment_Home());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);


    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(mcurrentFrg != Fragment_home){
                replaceFragment(new Fragment_Home());
                mcurrentFrg = Fragment_home;
            }

        }else if(id == R.id.khosanpham){
            if(mcurrentFrg != Fragment_khosanpham){
                replaceFragment(new Fragment_KhoSanPham());
                mcurrentFrg = Fragment_khosanpham;
            }

        }

        else if(id == R.id.dondathang){
            if(mcurrentFrg != Fragment_dondathang){
                replaceFragment(new Fragment_DonDatHang());
                mcurrentFrg = Fragment_dondathang;
            }

        }
        else if(id == R.id.taovoucher){

        }
        else if(id == R.id.taobanner){

        }
        else if(id == R.id.dangsanpham){

        }
        else if(id == R.id.xuatkho){
            if(mcurrentFrg != Fragment_xuatkho){
                replaceFragment(new FragmentXuatKho());
                mcurrentFrg = Fragment_xuatkho;
            }

        }
        else if(id == R.id.hoadon){

        }
        else if(id == R.id.doanhthu){

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
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}
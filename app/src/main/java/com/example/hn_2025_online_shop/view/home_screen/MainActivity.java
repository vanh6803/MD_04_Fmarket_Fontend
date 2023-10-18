package com.example.hn_2025_online_shop.view.home_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_Favorite;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_Home;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_Notification;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_Product;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_Profile;
import com.example.hn_2025_online_shop.view.login.ForgotPassWord;
import com.example.hn_2025_online_shop.view.login.Login;
import com.example.hn_2025_online_shop.view.login.Register;
import com.example.hn_2025_online_shop.view.login.VerifiPassWord;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment_Home fragment = new Fragment_Home();
        transaction.add(R.id.framelayout, fragment);
        transaction.commit();
        bottomNavigationView = findViewById(R.id.bottomnavigator);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                int index;

//                switch (item.getItemId()){
//                    case (R.id.homePage):
//                        fragment = new Fragment_Home();
//                        loadFragment(fragment);
//                        return true;
//                    case (R.id.product):
//                        fragment = new Fragment_Home();
//                        loadFragment(fragment);
//                        return true;
//                    case (R.id.favorites):
//                        fragment = new Fragment_Home();
//                        loadFragment(fragment);
//                        return true;
//                    default:
//                        fragment = new Fragment_Home();
//                        loadFragment(fragment);
//                        return true;
//                }


                if(item.getItemId() == R.id.homePage){
                    fragment = new Fragment_Home();
                    loadFragment(fragment);
                    return true;
                } else if (item.getItemId() == R.id.product) {
                    fragment = new Fragment_Product();
                    loadFragment(fragment);
                    return true;
                } else if (item.getItemId() == R.id.favorites) {
                    fragment = new Fragment_Favorite();
                    loadFragment(fragment);
                    return true;
                } else if (item.getItemId() == R.id.notification) {
                    fragment = new Fragment_Notification();
                    loadFragment(fragment);
                    return true;
                } else if (item.getItemId() == R.id.profile) {
                    fragment = new Fragment_Profile();
                    loadFragment(fragment);
                    return true;
                }else {
                    fragment = new Fragment_Home();
                    loadFragment(fragment);
                    return true;
                }
            }
        });


    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
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
import android.widget.FrameLayout;

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

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    FrameLayout framelayout;
    Fragment_Home fragment1 = new Fragment_Home();
    Fragment_Product fragment2 = new Fragment_Product();
    Fragment_Favorite fragment3 = new Fragment_Favorite();
    Fragment_Notification fragment4 = new Fragment_Notification();
    Fragment_Profile fragment5 = new Fragment_Profile();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        framelayout= findViewById(R.id.framelayout);


        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        Fragment_Home fragment = new Fragment_Home();
        transaction.add(R.id.framelayout, fragment);
        transaction.commit();
        bottomNavigationView = findViewById(R.id.bottomnavigator);
        bottomNavigationView.setSelectedItemId(R.id.homePage);

//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragment;
//                switch (item.getItemId()){
//                    case (R.id.product):
//                        fragment = new Fragment_Product();
//                        loadFragment(fragment);
//                        return true;
//                    case (R.id.favorites):
//                        fragment = new Fragment_Favorite();
//                        loadFragment(fragment);
//                        return true;
//                    case (R.id.notification):
//                        fragment = new Fragment_Notification();
//                        loadFragment(fragment);
//                        return true;
//                    case (R.id.profile):
//                        fragment = new Fragment_Profile();
//                        loadFragment(fragment);
//                        return true;
//                    default:
//                        fragment = new Fragment_Home();
//                        loadFragment(fragment);
//                        return true;
//                }
//            }
//        });

    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        switch (item.getItemId()){
            case (R.id.product):
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout, fragment2)
                        .commit();
                return true;
            case (R.id.favorites):
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout, fragment3)
                        .commit();
                return true;
            case (R.id.notification):
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout, fragment4)
                        .commit();
                return true;
            case (R.id.profile):
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout, fragment5)
                        .commit();
                return true;
            default:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.framelayout, fragment1)
                        .commit();
                return true;
        }
    }
}
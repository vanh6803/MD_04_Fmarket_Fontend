package com.example.hn_2025_online_shop.view.home_screen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ActivityMainBinding;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_Favorite;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_home.Fragment_Home;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_Notification;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_Product;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_Profile;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadFragment(Fragment_Home.newInstance());
        binding.bottomnavigator.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.homePage) {
                    loadFragment(Fragment_Home.newInstance());
                } else if (id == R.id.product) {
                    loadFragment(Fragment_Product.newInstance());
                } else if (id == R.id.favorites) {
                    loadFragment(Fragment_Favorite.newInstance());
                } else if (id == R.id.notification) {
                    loadFragment(Fragment_Notification.newInstance());
                } else if (id == R.id.product) {
                    loadFragment(Fragment_Profile.newInstance());
                }

                return true;
            }
        });


    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.framelayout, fragment);
        transaction.commit();
    }
}
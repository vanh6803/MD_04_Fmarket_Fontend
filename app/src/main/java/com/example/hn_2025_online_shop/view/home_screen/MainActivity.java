package com.example.hn_2025_online_shop.view.home_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.ActivityMainBinding;
import com.example.hn_2025_online_shop.view.fragment.FragmentFavorite;
import com.example.hn_2025_online_shop.view.fragment.fragment_home.FragmentHome;
import com.example.hn_2025_online_shop.view.fragment.FragmentNotification;
import com.example.hn_2025_online_shop.view.fragment.FragmentProduct;
import com.example.hn_2025_online_shop.view.fragment.FragmentProfile;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onClickBottomNav();
    }

    private void onClickBottomNav() {
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.homepage));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.product_24));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.yeuthich));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.thongbao));
        binding.bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.profile));
        binding.bottomNavigation.show(1, true);
        loadFragment(FragmentHome.newInstance());

        binding.bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        loadFragment(FragmentHome.newInstance());
                        break;
                    case 2:
                        loadFragment(FragmentProduct.newInstance());
                        break;
                    case 3:
                        loadFragment(FragmentFavorite.newInstance());
                        break;
                    case 4:
                        loadFragment(FragmentNotification.newInstance());
                        break;
                    case 5:
                        loadFragment(FragmentProfile.newInstance());
                        break;
                }
                return null;
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
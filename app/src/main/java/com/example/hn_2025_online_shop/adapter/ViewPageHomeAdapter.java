package com.example.hn_2025_online_shop.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hn_2025_online_shop.view.Fragment.Fragment_home.Fragement_Page_Banchay;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_home.Fragment_Page_Danggiamgia;
import com.example.hn_2025_online_shop.view.Fragment.Fragment_home.Fragment_Page_Noibat;

import java.util.List;

public class ViewPageHomeAdapter extends FragmentPagerAdapter {
    private List<String> tabTitles;

    public ViewPageHomeAdapter(@NonNull FragmentManager fm ,List<String> tabTitles ) {
        super(fm);
        this.tabTitles = tabTitles;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Fragement_Page_Banchay();
            case 1:
                return new Fragment_Page_Danggiamgia();
            case 2:
                return new Fragment_Page_Noibat();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles.get(position);
    }
}

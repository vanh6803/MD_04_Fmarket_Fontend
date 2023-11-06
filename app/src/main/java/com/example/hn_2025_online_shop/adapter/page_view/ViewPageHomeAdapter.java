package com.example.hn_2025_online_shop.adapter.page_view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hn_2025_online_shop.view.fragment.fragment_home.FragementPageSelling;
import com.example.hn_2025_online_shop.view.fragment.fragment_home.FragmentPageDiscount;
import com.example.hn_2025_online_shop.view.fragment.fragment_home.FragmentPageOutstanding;

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
                return new FragementPageSelling();
            case 1:
                return new FragmentPageDiscount();
            case 2:
                return new FragmentPageOutstanding();
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

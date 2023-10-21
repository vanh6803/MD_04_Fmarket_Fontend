package com.example.hn_2025_online_shop.adapter.page_view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageDanggiao;

import java.util.List;

public class ViewPageHistoryAdapter extends FragmentPagerAdapter {

    List<String> listTitle;
    public ViewPageHistoryAdapter(@NonNull FragmentManager fm, List<String> listTitle) {
        super(fm);
        this.listTitle = listTitle;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FragmentPageDanggiao();
            case 1:
                return new FragmentPageDanggiao();
            case 2:
                return new FragmentPageDanggiao();
            case 3:
                return new FragmentPageDanggiao();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return listTitle.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
}

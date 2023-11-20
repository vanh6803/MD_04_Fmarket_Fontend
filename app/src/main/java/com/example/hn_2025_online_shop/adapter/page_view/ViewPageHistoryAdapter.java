package com.example.hn_2025_online_shop.adapter.page_view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageCancleOrder;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageDelivered;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageDelivering;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageHandle;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageSuccessfulDelivery;

import java.util.List;

public class ViewPageHistoryAdapter extends FragmentStateAdapter {
    public ViewPageHistoryAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentPageHandle();
            case 1:
                return new FragmentPageDelivering();
            case 2:
                return new FragmentPageDelivered();
            case 3:
                return new FragmentPageSuccessfulDelivery();
            case 4:
                return new FragmentPageCancleOrder();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

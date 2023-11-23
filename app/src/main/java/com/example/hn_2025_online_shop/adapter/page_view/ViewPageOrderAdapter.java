package com.example.hn_2025_online_shop.adapter.page_view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageCancelled;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageWaitingDelivery;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageWaitConfirm;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.FragmentPageDelivered;

public class ViewPageOrderAdapter extends FragmentStateAdapter {
    public ViewPageOrderAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new FragmentPageWaitConfirm();
            case 1:
                return new FragmentPageWaitingDelivery();
            case 2:
                return new FragmentPageDelivered();
            case 3:
                return new FragmentPageCancelled();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}

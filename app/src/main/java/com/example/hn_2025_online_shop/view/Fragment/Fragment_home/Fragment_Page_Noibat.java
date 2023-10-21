
package com.example.hn_2025_online_shop.view.Fragment.Fragment_home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
public class Fragment_Page_Noibat extends Fragment {

    public Fragment_Page_Noibat() {
    }

    public static Fragment_Page_Noibat newInstance(String param1, String param2) {
        Fragment_Page_Noibat fragment = new Fragment_Page_Noibat();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment__page__noibat, container, false);
    }
}
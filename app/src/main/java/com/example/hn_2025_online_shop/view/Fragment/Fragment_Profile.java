package com.example.hn_2025_online_shop.view.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.FragmentProfileBinding;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class Fragment_Profile extends Fragment {

    private FragmentProfileBinding binding;
    private GoogleSignInClient mGoogleSignInClient;
    private SharedPreferences sharedPreferences;

    public static Fragment_Profile newInstance() {
        Fragment_Profile fragment = new Fragment_Profile();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    //init view và các action vào đây
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String token = sharedPreferences.getString("TOKEN", null);

//        todo: cho vào trong btn logout
//        if (token != null){
//            mGoogleSignInClient.signOut();
//        }

    }
}
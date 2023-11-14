package com.example.hn_2025_online_shop.view.my_store;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.FragmentCreateProductMyStoreBinding;


public class FragmentCreateProductMyStore extends Fragment {
    private FragmentCreateProductMyStoreBinding binding;


    public FragmentCreateProductMyStore() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragmentCreateProductMyStore newInstance() {
        FragmentCreateProductMyStore fragment = new FragmentCreateProductMyStore();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreateProductMyStoreBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String[] optionDiscount = {"Có", "Không"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, optionDiscount);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerDiscount.setAdapter(adapter);
        String[] optionTinhTrang = {"mới", "cũ"};
        ArrayAdapter<String> adapterTinhTrang = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, optionTinhTrang);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerTinhTrang.setAdapter(adapterTinhTrang);
        String[] optionCategory = {"Điện Thoai", "Laptop", "Tai Nghe", "Bàn Phím", "Pc"};
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, optionCategory);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerCategory.setAdapter(adapterCategory);

        binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedOption = optionCategory[i];
                if(selectedOption.equals("Tai Nghe")){
                    binding.tvSc.setVisibility(View.GONE);
                    binding.lnSc.setVisibility(View.GONE);
                    binding.tvCamera.setVisibility(View.GONE);
                    binding.lnCamera.setVisibility(View.GONE);
                    binding.tvChipset.setVisibility(View.GONE);
                    binding.lnChipset.setVisibility(View.GONE);
                    binding.tvCpu.setVisibility(View.GONE);
                    binding.lnCpu.setVisibility(View.GONE);
                    binding.tvGpu.setVisibility(View.GONE);
                    binding.lnGpu.setVisibility(View.GONE);
                } else if (selectedOption.equals("Điện Thoại")) {
                    binding.tvSc.setVisibility(View.VISIBLE);
                    binding.lnSc.setVisibility(View.VISIBLE);
                    binding.tvCamera.setVisibility(View.VISIBLE);
                    binding.lnCamera.setVisibility(View.VISIBLE);
                    binding.tvCpu.setVisibility(View.VISIBLE);
                    binding.lnCpu.setVisibility(View.VISIBLE);
                    binding.tvGpu.setVisibility(View.VISIBLE);
                    binding.lnGpu.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
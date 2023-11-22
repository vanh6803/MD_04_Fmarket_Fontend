package com.example.hn_2025_online_shop.view.my_store;
import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hn_2025_online_shop.adapter.OrderAdapter;
import com.example.hn_2025_online_shop.databinding.FragmentOrderBinding;


public class FragmentOrder extends Fragment {
//    private List<Order1> list;
    private OrderAdapter adapter;
    private FragmentOrderBinding binding;

    public FragmentOrder() {
        // Required empty public constructor
    }

    public static FragmentOrder newInstance() {
        FragmentOrder fragment = new FragmentOrder();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        list = new ArrayList<>();
//        list.add(new Order1("https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", "Iphone 13 ProMax", "ngocntph26008", "20/10/2003", 1));
//        list.add(new Order1("https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", "Iphone 13 ProMax", "ngocntph26008", "20/10/2003", 1));
//        list.add(new Order1("https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", "Iphone 13 ProMax", "ngocntph26008", "20/10/2003", 1));
//        list.add(new Order1("https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", "Iphone 13 ProMax", "ngocntph26008", "20/10/2003", 1));
//        list.add(new Order1("https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", "Iphone 13 ProMax", "ngocntph26008", "20/10/2003", 1));
//        list.add(new Order1("https://vtv1.mediacdn.vn/2019/10/10/photo-1-15706463929181755249740.jpg", "Iphone 13 ProMax", "ngocntph26008", "20/10/2003", 1));
//        adapter = new OrderAdapter(getContext(), list);
//        adapter.setListOrder(list);
//        binding.rcvListOrder.setAdapter(adapter);
    }
}
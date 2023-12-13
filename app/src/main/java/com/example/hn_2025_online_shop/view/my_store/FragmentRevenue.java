package com.example.hn_2025_online_shop.view.my_store;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.hn_2025_online_shop.databinding.FragmentRevenueBinding;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;

public class  FragmentRevenue extends Fragment {
    private FragmentRevenueBinding binding;

    public FragmentRevenue() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding= FragmentRevenueBinding.inflate(getLayoutInflater());
        binding.combinedChart.getDescription().setText("Combined Chart Example");
        // Thiết lập độ dời ngang để cho phép cuộn ngang
        binding.combinedChart.setDrawBarShadow(false);
        binding.combinedChart.setHorizontalScrollBarEnabled(true);
        binding.combinedChart.setData(generateCombinedData());
        binding.combinedChart.setScrollBarSize(12);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private CombinedData generateCombinedData() {
        // Tạo dữ liệu cho LineChart
        LineData lineData = generateLineData();

        // Tạo dữ liệu cho BarChart
        BarData barData = generateBarData();

        // Kết hợp dữ liệu của LineChart và BarChart vào CombinedData
        CombinedData combinedData = new CombinedData();
        combinedData.setData(lineData);
        combinedData.setData(barData);

        return combinedData;
    }

    private LineData generateLineData() {
        // Tạo danh sách các điểm dữ liệu cho LineChart
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(1, 8));
        entries.add(new Entry(2, 6));
        entries.add(new Entry(3, 2));
        entries.add(new Entry(4, 7));
        entries.add(new Entry(5, 3));
        entries.add(new Entry(6, 1));
        entries.add(new Entry(8, 3));
        entries.add(new Entry(9, 5));
        entries.add(new Entry(10,3));
        entries.add(new Entry(11,2));
        entries.add(new Entry(12,4));

        // Tạo LineDataSet từ danh sách các điểm dữ liệu
        LineDataSet lineDataSet = new LineDataSet(entries, "Thu nhập");
        // Cấu hình LineDataSet
        // ...
        lineDataSet.setCircleColor(Color.RED);
        lineDataSet.setHighLightColor(Color.RED);
        lineDataSet.setColor(Color.RED);

        // Tạo LineData từ LineDataSet
        LineData lineData = new LineData(lineDataSet);
        return lineData;
    }

    private BarData generateBarData() {
        // Tạo danh sách các điểm dữ liệu cho BarChart
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1, 2));
        entries.add(new BarEntry(2, 4));
        entries.add(new BarEntry(3, 6));
        entries.add(new BarEntry(4, 3));
        entries.add(new BarEntry(5, 4));
        entries.add(new BarEntry(6, 1));
        entries.add(new BarEntry(7, 3));
        entries.add(new BarEntry(8, 1));
        entries.add(new BarEntry(9, 2));
        entries.add(new BarEntry(10,3));
        entries.add(new BarEntry(11,5));
        entries.add(new BarEntry(12,7));

        // Tạo BarDataSet từ danh sách các điểm dữ liệu
        BarDataSet barDataSet = new BarDataSet(entries, "Chi phí");
     
        // Tạo BarData từ BarDataSet
        BarData barData = new BarData(barDataSet);
        return barData;
    }

}
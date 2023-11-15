package com.example.hn_2025_online_shop.view.my_store;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.databinding.DialogCreateOptionProductBinding;
import com.example.hn_2025_online_shop.databinding.FragmentCreateProductMyStoreBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialigOptionProductBinding;
import com.example.hn_2025_online_shop.view.buy_product.UpdateAddressActivity;
import com.example.hn_2025_online_shop.view.login.RegisterMemberSeller;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class FragmentCreateProductMyStore extends Fragment {
    private MultipartBody.Part fileImgAvatar;

    private int isCheckImage = 0; // 1 là avatar
    private boolean isCamera = false; // kiểm tra xem avatar có dữ liệu hay chưa


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
        binding.tvHeDieuHanh.setVisibility(View.GONE);
        binding.lnHeDieuHanh.setVisibility(View.GONE);
        binding.spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedOption = optionCategory[i];
                if(selectedOption.equals("Điện Thoại")){
                    binding.tvSc.setVisibility(View.VISIBLE);
                    binding.lnSc.setVisibility(View.VISIBLE);
                    binding.tvCamera.setVisibility(View.VISIBLE);
                    binding.lnCamera.setVisibility(View.VISIBLE);
                    binding.tvCpu.setVisibility(View.VISIBLE);
                    binding.lnCpu.setVisibility(View.VISIBLE);
                    binding.tvGpu.setVisibility(View.VISIBLE);
                    binding.lnGpu.setVisibility(View.VISIBLE);

                } else if (selectedOption.equals("Laptop")) {
                    binding.tvSc.setVisibility(View.VISIBLE);
                    binding.lnSc.setVisibility(View.VISIBLE);
                    binding.tvCamera.setVisibility(View.VISIBLE);
                    binding.lnCamera.setVisibility(View.VISIBLE);
                    binding.tvCpu.setVisibility(View.VISIBLE);
                    binding.lnCpu.setVisibility(View.VISIBLE);
                    binding.tvGpu.setVisibility(View.VISIBLE);
                    binding.lnGpu.setVisibility(View.VISIBLE);
                } else if (selectedOption.equals("Tai Nghe")) {
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
                    binding.tvHeDieuHanh.setVisibility(View.GONE);
                    binding.lnHeDieuHanh.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UpdateAddressActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLuu.setOnClickListener(new View.OnClickListener() {
            private DialogCreateOptionProductBinding binding1;

            @Override
            public void onClick(View view) {
                BottomSheetDialog dialog1 = new BottomSheetDialog(getContext());
                binding1 = DialogCreateOptionProductBinding.inflate(getLayoutInflater());
                dialog1.setContentView(binding1.getRoot());
                Window window = dialog1.getWindow();
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                binding1.imgCamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ImagePicker.with((FragmentCreateProductMyStore.this))
                                .crop()	    			//Crop image(Optional), Check Customization for more option
                                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                                .start();
                        isCheckImage = 1;
                    }
                });


                binding1.btnDong.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });

                dialog1.show();


            }
        });

    }
    DialogCreateOptionProductBinding binding1;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            File file = new File(getPath(uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            if(isCheckImage == 1) {
                isCamera = true;
                binding1.imgCamera.setImageURI(uri);
                fileImgAvatar = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            }

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(getContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT).show();
            isCamera = false;

        } else {
            Toast.makeText(getContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
            isCamera = false;

        }


    }
    private String getPath(Uri uri){
        String result;
        Cursor cursor = getContext().getContentResolver()
                .query(uri, null,null,null,null);
        if (cursor == null){
            result = uri.getPath();
        }else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(index);
            cursor.close();
        }
        return result;
    }
}
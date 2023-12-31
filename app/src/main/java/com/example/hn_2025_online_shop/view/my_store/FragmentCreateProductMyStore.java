package com.example.hn_2025_online_shop.view.my_store;

import static com.example.hn_2025_online_shop.ultil.StoreUltil.idStore;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.DialogCreateOptionProductBinding;
import com.example.hn_2025_online_shop.databinding.FragmentCreateProductMyStoreBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialigOptionProductBinding;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.StoreUltil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.buy_product.UpdateAddressActivity;
import com.example.hn_2025_online_shop.view.login.RegisterMemberSeller;
import com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen.DetailProduct;
import com.example.hn_2025_online_shop.view.success_screen.CreateStoreSuccessActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentCreateProductMyStore extends Fragment {
    private MultipartBody.Part fileImgAvatar;

    private int isCheckImage = 0; // 1 là avatar
    private boolean isCamera = false; // kiểm tra xem avatar có dữ liệu hay chưa
    private ProgressLoadingDialog dialog;


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
        initView();
        initController();
    }

    private void initController() {
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
//                createProductMyStore();
                showDiaLogCreateOptionProduct();
            }
        });
    }

    private void initView() {
        dialog = new ProgressLoadingDialog(getContext());
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

        String[] optionIsAvtive = {"Có", "Không"};
        ArrayAdapter<String> adapterIsAvtive = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, optionIsAvtive);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerIsActive.setAdapter(adapterIsAvtive);

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
            String categoryIdDienThoai = "654df46fde2dd24f7fc38944";
            String categoryIdLaptop = "654df48cde2dd24f7fc38947";
            String categoryIdTaiNghe = "654df49ade2dd24f7fc3894a";
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
                    selectedOption = categoryIdDienThoai;
                } else if (selectedOption.equals("Laptop")) {
                    binding.tvSc.setVisibility(View.VISIBLE);
                    binding.lnSc.setVisibility(View.VISIBLE);
                    binding.tvCamera.setVisibility(View.VISIBLE);
                    binding.lnCamera.setVisibility(View.VISIBLE);
                    binding.tvCpu.setVisibility(View.VISIBLE);
                    binding.lnCpu.setVisibility(View.VISIBLE);
                    binding.tvGpu.setVisibility(View.VISIBLE);
                    binding.lnGpu.setVisibility(View.VISIBLE);
                    selectedOption = categoryIdLaptop;
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
                    selectedOption = categoryIdTaiNghe;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spinnerIsActive.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedIsActive = optionIsAvtive[i];
                boolean isActive= false;
                if((selectedIsActive.equals("Có"))){
                    isActive = true;
                } else if (selectedIsActive.equals("Không")) {
                    isActive = false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void showDiaLogCreateOptionProduct() {
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

    public void createProductMyStore(){
        String store_Id = idStore;
        String categoryId = binding.spinnerCategory.getSelectedItem().toString();
        String name = binding.edtNameProduct.toString();
        String description = binding.edtMota.toString();
        String status = binding.spinnerTinhTrang.getSelectedItem().toString();
        boolean discounted = (boolean) binding.spinnerDiscount.getSelectedItem();
        boolean is_active = (boolean) binding.spinnerIsActive.getSelectedItem();
        String screen = binding.edtSC.toString();
        String camera = binding.edtCamera.toString();
        String chipset = binding.edtChipset.toString();
        String cpu = binding.edtCpu.toString();
        String gpu = binding.edtGpu.toString();
        String operatingSystem = binding.edtHeDieuHanh.toString();
        String battery = binding.edtBatrery.toString();
        int weight = Integer.parseInt(binding.edtNameProduct.toString());
        String connection = binding.edtConnection.toString();
        String specialFeature = binding.edtSpecialFeature.toString();
        String manufacturer = binding.edtManufacturer.toString();
        String other = binding.edtOther.toString();
        dialog.show();
        BaseApi.API.createProductMyStore(
                store_Id, categoryId, name, description,
                status, discounted, is_active, screen, camera,
                chipset, cpu, gpu, operatingSystem,
                battery, weight, connection, specialFeature,
                manufacturer, other).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ProductResponse productResponse = response.body();
                    Log.d(TAG.toString, "onResponse-createProduct: " + productResponse.toString());
                    if(productResponse.getCode() == 200 || productResponse.getCode() == 201) {
                        Toast.makeText(getContext(), "Create Product Is Successfully!", Toast.LENGTH_SHORT).show();
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-createProduct: " + errorMessage);
                        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Call api Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
    private DialogCreateOptionProductBinding binding1;
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            File file = new File(getPath(uri));
            RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
            if(isCheckImage == 1) {
                isCamera = true;
                binding1.imgAvartar.setImageURI(uri);
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
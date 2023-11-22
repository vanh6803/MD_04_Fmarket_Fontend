package com.example.hn_2025_online_shop.view.profile_screen.history_buy_screen.product_screen;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.OptionAdapter;
import com.example.hn_2025_online_shop.adapter.ProductAdapter;
import com.example.hn_2025_online_shop.adapter.VoucherAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.DetailProductBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialigOptionProductBinding;
import com.example.hn_2025_online_shop.databinding.LayoutDialogDetailProductBinding;
import com.example.hn_2025_online_shop.model.OptionProduct;
import com.example.hn_2025_online_shop.model.Product;
import com.example.hn_2025_online_shop.model.ProductDetail;
import com.example.hn_2025_online_shop.model.Voucher;
import com.example.hn_2025_online_shop.model.response.DetailProductResponse;
import com.example.hn_2025_online_shop.model.response.ProductResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ApiUtil;
import com.example.hn_2025_online_shop.ultil.CartUtil;
import com.example.hn_2025_online_shop.ultil.ObjectUtil;
import com.example.hn_2025_online_shop.ultil.ProgressLoadingDialog;
import com.example.hn_2025_online_shop.ultil.StoreUltil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.buy_product.PayActivity;
import com.example.hn_2025_online_shop.view.cart_screen.CartActivity;
import com.example.hn_2025_online_shop.view.home_screen.MainActivity;
import com.example.hn_2025_online_shop.view.infor_shop.InforShop;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct extends AppCompatActivity implements ObjectUtil {

    private DetailProductBinding binding;
    private List<Product> productList;
    private List<Voucher> voucherList;
    private ProductAdapter productAdapter;
    private VoucherAdapter voucherAdapter;
    private ProgressLoadingDialog dialog;
    private ProductDetail productDetail;
    private List<OptionProduct> optionProductList;
    private OptionAdapter optionAdapter;
    private int quantityProduct = 1;
    private OptionProduct optionProduct;
    private LayoutDialigOptionProductBinding bindingOption;
    private final int totalPrice = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
        initController();
        getVoucher();
        callApiDetailProduct();
        setDataSimilarProduct();
        setNumberCart();
    }


    @SuppressLint("SetTextI18n")
    private void setNumberCart() {
        // Lấy danh sách cart
        binding.tvQuantityCart.setText(CartUtil.listCart.size() + "");
    }

    private void getVoucher() {
        voucherList = new ArrayList<>();
        for (int i = 1 ; i< 6; i++){
            voucherList.add(new Voucher("Giảm"+ i +"% đối với đơn hàng trên 100k","Giảm"+i+"%", "1234", ""));
        }
        binding.count.setText(voucherList.size() + " mã giảm giá");
        productAdapter = new ProductAdapter(this, productList, this);
        voucherAdapter = new VoucherAdapter(this, voucherList);
        binding.recyProductSimilar.setAdapter(productAdapter);
        binding.recyVoucher.setAdapter(voucherAdapter);
    }

    public void callApiDetailProduct(){
        dialog.show();
        Intent intent = getIntent();
        String id_product = intent.getStringExtra("id_product");
        BaseApi.API.getDetailProduct(id_product).enqueue(new Callback<DetailProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<DetailProductResponse> call, @NonNull Response<DetailProductResponse> response) {
                if(response.isSuccessful()){
                    DetailProductResponse detailProductResponse = response.body();
                    assert detailProductResponse != null;
                    if (detailProductResponse.getCode() == 200){
                        productDetail = detailProductResponse.getResult();
                        Log.d("gggg", "onResponse: " + detailProductResponse.getResult());
                        setDataUi(detailProductResponse);
                    }
                } else {
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        // Parse and display the error message
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure(@NonNull Call<DetailProductResponse> call, @NonNull Throwable t) {
                Toast.makeText(DetailProduct.this, "Error", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }
    @SuppressLint({"SetTextI18n", "CheckResult"})
    private void setDataUi(DetailProductResponse detailProductResponse) {

        if(detailProductResponse != null){
            List<SlideModel> listImg = new ArrayList<>();
            for(int i = 0; i< detailProductResponse.getResult().getImage().size(); i++){
                String linkImg = detailProductResponse.getResult().getImage().get(i);
                listImg.add(new SlideModel(linkImg, ScaleTypes.FIT));
            }
            Log.d(TAG.toString, "setDataUi: " + detailProductResponse.getResult().getOption());
            if(detailProductResponse.getResult().getOption().size() != 0){
                DecimalFormat df = new DecimalFormat("###,###,###");
                binding.tvPrice.setText(df.format(detailProductResponse.getResult().getOption().get(0).getPrice())+" đ");
            }else{
                binding.tvPrice.setText("Không có dữ liệu trả về");
                Toast.makeText(this, detailProductResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }

            if(detailProductResponse.getResult().getImage().size() != 0){
                binding.imgProduct.setImageList(listImg, ScaleTypes.FIT);
            }else {
                Glide.with(DetailProduct.this).load(R.drawable.error);
            }

            if(detailProductResponse.getResult().getName().length() != 0){
                binding.tvName.setText(detailProductResponse.getResult().getName());
            }else{
                binding.tvName.setText("Không có dữ liệu trả về");
            }

            if (detailProductResponse.getResult().getStore_id().getName().length() != 0 || detailProductResponse.getResult().getStore_id().getName() != null) {
                binding.tvShop.setText(detailProductResponse.getResult().getStore_id().getName());
            }else{
                binding.tvShop.setText("Không có dữ liệu trả về");
            }

            if(detailProductResponse.getResult().getStore_id().getAddress().length() != 0){
                binding.diachiShop.setText(detailProductResponse.getResult().getStore_id().getAddress());
            }else {
                binding.diachiShop.setText("Không có dữ liệu trả về");
            }

            if(detailProductResponse.getResult().getStore_id().getAvatar().length() != 0){
                Glide.with(this)
                        .load(detailProductResponse.getResult().getStore_id().getAvatar())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.error)
                        .into(binding.imgShop);
            }else {
                Glide.with(this).load(R.drawable.error);
            }

        }else{
            Toast.makeText(this, "Không tìm thấy thông tin sản phẩm", Toast.LENGTH_SHORT).show();
        }
    }
    public void showDetailProductDialog(DetailProductResponse response1){
        binding.btnShowDetailProduct.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailProduct.this);
                LayoutDialogDetailProductBinding binding = LayoutDialogDetailProductBinding.inflate(getLayoutInflater());;
                builder.setView(binding.getRoot());
                binding.description.setText(response1.getResult().getDescription());
                binding.screen.setText("Screen: "+response1.getResult().getScreen());
                binding.camera.setText(response1.getResult().getCamera());
                binding.chipset.setText("Chipset: " + response1.getResult().getChipset());
                binding.cpu.setText("Cpu: "+response1.getResult().getCpu());
                binding.gpu.setText("Gpu: "+response1.getResult().getGpu());
                binding.ram.setText("Ram: "+response1.getResult().getRam() +"GB");
                binding.rom.setText("Rom: "+response1.getResult().getRom()+"GB");
                binding.operatingSystem.setText("OperatingSystem: "+response1.getResult().getOperatingSystem());
                binding.battery.setText("Battery: " + response1.getResult().getBattery());
                binding.weight.setText("Weight: " + response1.getResult().getWeight());
                binding.connection.setText("Connection: "+response1.getResult().getConnection());
                binding.specialFeature.setText("SpecialFeature: "+ response1.getResult().getSpecialFeature());
                binding.manufacturer.setText("Manufacturer: "+response1.getResult().getManufacturer());
                binding.other.setText("Other: "+response1.getResult().getOther());
                builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    public void setDataSimilarProduct(){
        dialog.show();
        Intent intent = getIntent();
        String id_product = intent.getStringExtra("id_product");
        BaseApi.API.getDataSimilarlProduct(id_product).enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProductResponse> call, @NonNull Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    ProductResponse productResponse = response.body();
                    assert productResponse != null;
                    Log.d(TAG.toString, "onResponse-setDataSimilarProduct: " + productResponse.getResult().toString());
                    productAdapter.setProductList(productResponse.getResult());
                    binding.recyProductSimilar.setAdapter(productAdapter);
                }else {
                    Toast.makeText(DetailProduct.this, "Get Data Product Similar Error", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
            @Override
            public void onFailure(@NonNull Call<ProductResponse> call, @NonNull Throwable t) {
                Toast.makeText(DetailProduct.this, "call api err", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void showDialogDetail() {
        if(productDetail == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailProduct.this);
        LayoutDialogDetailProductBinding binding = LayoutDialogDetailProductBinding.inflate(getLayoutInflater());;
        builder.setView(binding.getRoot());
        binding.description.setText(productDetail.getDescription());
        binding.screen.setText("Screen: "+productDetail.getScreen());
        binding.camera.setText("Cammera: " + productDetail.getCamera());
        binding.chipset.setText("Chipset: " + productDetail.getChipset());
        binding.cpu.setText("Cpu: "+productDetail.getCpu());
        binding.gpu.setText("Gpu: "+productDetail.getGpu());
        binding.ram.setText("Ram: "+productDetail.getRam() +"GB");
        binding.rom.setText("Rom: "+productDetail.getRom()+"GB");
        binding.operatingSystem.setText("OperatingSystem: "+productDetail.getOperatingSystem());
        binding.battery.setText("Battery: " + productDetail.getBattery());
        binding.weight.setText("Weight: " + productDetail.getWeight());
        binding.connection.setText("Connection: "+productDetail.getConnection());
        binding.specialFeature.setText("SpecialFeature: "+ productDetail.getSpecialFeature());
        binding.manufacturer.setText("Manufacturer: "+productDetail.getManufacturer());
        binding.other.setText("Other: "+productDetail.getOther());

        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void initController() {
        binding.backDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackActivity();
            }
        });

        binding.showshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(productDetail!= null) {
                    String productId = productDetail.getId();
                    String storeId = productDetail.getStore_id().toString();
                    Intent intent = new Intent(DetailProduct.this, InforShop.class);
                    intent.putExtra("storeId",storeId);
                    intent.putExtra("id_product",productId);
                    StoreUltil.store = productDetail.getStore_id();
                    startActivity(intent);
                }
            }
        });

        binding.btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(false);
            }
        });

        binding.btnBuyDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomSheetDialog(true);
            }
        });

        binding.btnShowDetailProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogDetail();
            }
        });

        binding.imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProduct.this, CartActivity.class);
                mActivityResultLauncher.launch(intent);
                overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
            }
        });
    }

    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        assert intent != null;
                        int cartSize = intent.getIntExtra("data_cart_size", 0);
                        binding.tvQuantityCart.setText(cartSize + "");
                }
            }
    });

    private void initView() {
        dialog = new ProgressLoadingDialog(this);
        binding.textsale.setPaintFlags(binding.textsale.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        productList = new ArrayList<>();
        productAdapter = new ProductAdapter(this, productList, this);
    }

    // --------------------------------- BottomSheetDialog --------------------------------------------

    private void showBottomSheetDialog(Boolean isCheck) {
        if(productDetail == null) {
            return;
        }

        BottomSheetDialog dialog1 = new BottomSheetDialog(DetailProduct.this);
        bindingOption = LayoutDialigOptionProductBinding.inflate(getLayoutInflater());
        dialog1.setContentView(bindingOption.getRoot());
        Window window = dialog1.getWindow();
        assert window != null;
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        setDataBottomSheetDialog(isCheck, bindingOption); // Set data cho bottom sheet
        optionProductList = new ArrayList<>();
        optionAdapter = new OptionAdapter(DetailProduct.this, optionProductList, this);
        optionAdapter.setDataListOptionProduct(productDetail.getOption());
        bindingOption.rcvOptionProduct.setAdapter(optionAdapter);
        dialog1.show();

        setOnclickBottomDialog(isCheck, bindingOption);
    }

    private void setOnclickBottomDialog(Boolean isCheck, LayoutDialigOptionProductBinding bindingOption) {
        bindingOption.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(optionProduct != null) {
                    if(isCheck) {
                        // set dữ liệu vào CartUtil.listCartCheck  để có thể mua ngay
//                        CartOfList cartOfList = new CartOfList();
//                        cartOfList.setQuantity(quantityProduct);
//                        cartOfList.setId(optionProduct.getId());
//                        CartUtil.listCartCheck.add(cartOfList);
//
//                        // tính tổng giá tiền
//                        totalPrice = quantityProduct * optionProduct.getPrice();

                        Intent intent = new Intent(DetailProduct.this, PayActivity.class);
                        intent.putExtra("totalPrice" , totalPrice);
                        startActivity(intent);
                    } else {
                        urlCartAdd(bindingOption);
                    }
                    overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
                } else {
                    Toast.makeText(DetailProduct.this, "Mời chọn sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bindingOption.btnPlus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(quantityProduct < 20) {
                    quantityProduct += 1;
                    bindingOption.tvQuantity.setText(quantityProduct + "");
                }
            }
        });

        bindingOption.btnMinus.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if(quantityProduct > 1) {
                    quantityProduct -= 1;
                    bindingOption.tvQuantity.setText(quantityProduct + "");
                }
            }
        });
    }


    private void urlCartAdd(LayoutDialigOptionProductBinding bindingOption) {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        String optionId = optionProduct.getId();
        quantityProduct = Integer.parseInt(bindingOption.tvQuantity.getText().toString());
        dialog.show();
        BaseApi.API.createCartItem(token, optionId, quantityProduct).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(@NonNull Call<ServerResponse> call, @NonNull Response<ServerResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ServerResponse serverResponse = response.body();
                    assert serverResponse != null;
                    Log.d(TAG.toString, "onResponse-cartAdd: " + serverResponse.toString());
                    if(serverResponse.getCode() == 200 || serverResponse.getCode() == 201) {
                        Toast.makeText(DetailProduct.this, "Thêm sản phẩm vào giỏ hàng thành công!", Toast.LENGTH_SHORT).show();
                        ApiUtil.setTitleQuantityCart(DetailProduct.this, dialog, binding.tvQuantityCart);
                    }
                } else { // nhận các đầu status #200
                    try {
                        assert response.errorBody() != null;
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-cartAdd: " + errorMessage);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(@NonNull Call<ServerResponse> call, @NonNull Throwable t) {
                Toast.makeText(DetailProduct.this, t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-cartAdd: " + t.toString());
                dialog.dismiss();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void setDataBottomSheetDialog(Boolean isCheck, LayoutDialigOptionProductBinding bindingoption) {
        if(isCheck) {
            bindingoption.btnSave.setText("Mua ngay");
        } else {
            bindingoption.btnSave.setText("Thêm vào giỏ hàng");
        }
        if(productDetail.getOption().size() != 0){
            Glide.with(DetailProduct.this).load(productDetail.getOption().get(0).getImage()).into(bindingoption.imgProduct);
        }else {
            Glide.with(DetailProduct.this).load(R.drawable.error).into(bindingoption.imgProduct);
        }
        if(productDetail.getOption().size() != 0){
            DecimalFormat df = new DecimalFormat("###,###,###");
            bindingoption.tvPrice.setText(df.format(productDetail.getOption().get(0).getPrice()) + " đ");
        }else {
            bindingoption.tvPrice.setText("No data");
        }
        if(productDetail.getOption().size() != 0){
            bindingoption.tvWarehouseQuantity.setText("Kho: " + productDetail.getOption().get(0).getSoldQuantity());
        }else {
            bindingoption.tvWarehouseQuantity.setText("No data");
        }
    }


    @Override
    public void onclickObject(Object object) {
        // Do dùng trung 1 hàm lên phải kiểm tra xem giá trị trả về là của OptionProduct Hay Product
        if(object instanceof OptionProduct) {
            optionProduct = (OptionProduct) object;
            Glide.with(this)
                    .load(optionProduct.getImage())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.error)
                    .into(bindingOption.imgProduct);
        } else if(object instanceof Product) {
            Product product = (Product) object;
            String id = product.getId();
            Intent intent = new Intent(this, DetailProduct.class);
            intent.putExtra("id_product", id);
            startActivity(intent);
            overridePendingTransition(R.anim.slidle_in_left, R.anim.slidle_out_left);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBackActivity();
    }

    private void onBackActivity() {
        // Đoạn này chưa hiểu lắm nhưng kể cả truyền cho màn hình nào thì tất cả
        // registerForActivityResult onBack đểu đc chạy
        Intent intent = new Intent(DetailProduct.this, MainActivity.class);
        intent.putExtra("data_cart_size", CartUtil.listCart.size());
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(R.anim.slidle_in_right, R.anim.slidle_out_right);
    }
}

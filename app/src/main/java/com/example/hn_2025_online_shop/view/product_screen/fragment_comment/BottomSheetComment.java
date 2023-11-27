package com.example.hn_2025_online_shop.view.product_screen.fragment_comment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Binder;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.CommentAdapter;
import com.example.hn_2025_online_shop.databinding.BottomSheetCommentBinding;
import com.example.hn_2025_online_shop.model.Comment;
import com.example.hn_2025_online_shop.model.ProductDetail;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetComment extends BottomSheetDialogFragment {
    private BottomSheetCommentBinding binding;
    private ProductDetail productDetail;
    private List<Comment> commentList;
    private CommentAdapter commentAdapter;

    public static BottomSheetComment newInstance(ProductDetail productDetail) {
        BottomSheetComment sheetDialog = new BottomSheetComment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("productDetail", productDetail);
        sheetDialog.setArguments(bundle);
        return sheetDialog;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null) {
            productDetail = (ProductDetail) bundle.getSerializable("productDetail");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        binding = BottomSheetCommentBinding.inflate(getLayoutInflater());
        bottomSheetDialog.setContentView(binding.getRoot());
        bottomSheetDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initView();
        return bottomSheetDialog;
    }

    private void initView() {
        commentList = new ArrayList<>();
        commentList.add(new Comment("Điện thoại đẹp quá"));
        commentList.add(new Comment("Bán tôi cái"));
        commentList.add(new Comment("Đẹp quá shop ơi"));
        commentList.add(new Comment("Quá đẹp luôn"));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rcvComment.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(getActivity(), commentList);
        binding.rcvComment.setAdapter(commentAdapter);
    }

}
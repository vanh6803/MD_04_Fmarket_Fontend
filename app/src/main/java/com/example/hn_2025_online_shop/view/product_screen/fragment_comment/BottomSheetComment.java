package com.example.hn_2025_online_shop.view.product_screen.fragment_comment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hn_2025_online_shop.R;
import com.example.hn_2025_online_shop.adapter.CommentAdapter;
import com.example.hn_2025_online_shop.api.BaseApi;
import com.example.hn_2025_online_shop.databinding.BottomSheetCommentBinding;
import com.example.hn_2025_online_shop.model.Comment;
import com.example.hn_2025_online_shop.model.CommentAccount;
import com.example.hn_2025_online_shop.model.Message;
import com.example.hn_2025_online_shop.model.ProductDetail;
import com.example.hn_2025_online_shop.model.User;
import com.example.hn_2025_online_shop.model.response.ListCommentResponse;
import com.example.hn_2025_online_shop.model.response.ServerResponse;
import com.example.hn_2025_online_shop.ultil.AccountUltil;
import com.example.hn_2025_online_shop.ultil.ApiUtil;
import com.example.hn_2025_online_shop.ultil.TAG;
import com.example.hn_2025_online_shop.view.login.Register;
import com.example.hn_2025_online_shop.view.login.VerifiPassWord;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BottomSheetComment extends BottomSheetDialogFragment {
    private BottomSheetCommentBinding binding;
    private ProductDetail productDetail;
    private List<CommentAccount> commentList;
    private CommentAdapter commentAdapter;
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://" + BaseApi.LOCALHOT +":3000");
        } catch (URISyntaxException e) {}
    }

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
        getListComment();
        initSocket();
        return bottomSheetDialog;
    }

    private void initSocket() {
        mSocket.connect();
        mSocket.on("new_comment", onNewComment);
        binding.btnSend.setOnClickListener(v -> {
            attemptSend();
        });
    }

    private Emitter.Listener onNewComment = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            if (getActivity() == null) {
                return;
            }
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        JSONObject data = (JSONObject) args[0];
                        String content = data.getString("content");
                        Log.d(TAG.toString, "run: " + content);
                        String username = AccountUltil.USER.getUsername();
                        String avatar = AccountUltil.USER.getAvatar();
                        String datetime = sdf.format(new Date());
                        Comment comment = new Comment(content, datetime);
                        User user = new User(avatar, username);
                        commentList.add(new CommentAccount(comment, user));
                        commentAdapter.setCommentList(commentList);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    };

    private void attemptSend() {
        mSocket.connect();
        String comment = binding.edComment.getText().toString().trim();

        JSONObject data = new JSONObject();
        try {
            data.put("account_id", AccountUltil.USER.getId());
            data.put("product_id", productDetail.getId());
            data.put("content", comment);
            data.put("image", "link image");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        if (TextUtils.isEmpty(comment)) {
            return;
        }

        binding.edComment.setText("");
        mSocket.emit("new_comment", data);
    }

    private void getListComment() {
        String token = AccountUltil.BEARER + AccountUltil.TOKEN;
        BaseApi.API.getListComment(token, productDetail.getId()).enqueue(new Callback<ListCommentResponse>() {
            @Override
            public void onResponse(Call<ListCommentResponse> call, Response<ListCommentResponse> response) {
                if(response.isSuccessful()){ // chỉ nhận đầu status 200
                    ListCommentResponse listCommentResponse = response.body();
                    Log.d(TAG.toString, "onResponse-getListComment: " + listCommentResponse.toString());
                    if(listCommentResponse.getCode() == 200) {
                        commentList = listCommentResponse.getResult();
                        commentAdapter.setCommentList(commentList);
                    }
                } else { // nhận các đầu status #200
                    try {
                        String errorBody = response.errorBody().string();
                        JSONObject errorJson = new JSONObject(errorBody);
                        String errorMessage = errorJson.getString("message");
                        Log.d(TAG.toString, "onResponse-getListComment: " + errorMessage);
                        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ListCommentResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
                Log.d(TAG.toString, "onFailure-getListComment: " + t.toString());
            }
        });
    }

    private void initView() {
        commentList = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        binding.rcvComment.setLayoutManager(layoutManager);
        commentAdapter = new CommentAdapter(getActivity(), commentList);
        binding.rcvComment.setAdapter(commentAdapter);
    }
}
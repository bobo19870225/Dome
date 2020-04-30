package com.yy.dome.ui.activity.my.wechatimg;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.yy.dome.R;


/**
 *
 * 提示弹窗
 */
public class PictureDialog extends Dialog {

    private onClickListener mListener;
    private LinearLayout mTakePhotoLinearLayout;
    private LinearLayout mAlbumLinearLayout;


    public PictureDialog(@NonNull Context context) {
        super(context, R.style.TransparencyDialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture_dialog);
        setCanceledOnTouchOutside(true);
        //初始化界面
        initView();
        //初始化界面控件的事件
        initEvent();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        View view = findViewById(R.id.picture_dialog_relative_layout);
        mTakePhotoLinearLayout = findViewById(R.id.picture_dialog_take_photo_linear_layout);
        mAlbumLinearLayout = findViewById(R.id.picture_dialog_album_linear_layout);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = view.getResources().getDisplayMetrics().widthPixels;
        view.setLayoutParams(layoutParams);
        if(getWindow()!=null) {
            getWindow().setGravity(Gravity.BOTTOM);
        }
    }

    /**
     * 初始化界面的监听器
     */
    private void initEvent() {
        mTakePhotoLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onCameraClick();
                }
            }
        });
        mAlbumLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onAlbumClick();
                }
            }
        });
    }

    public PictureDialog setListener(onClickListener listener) {
        mListener = listener;
        return this;
    }

    /**
     * 设置确定按钮和取消被点击的接口
     */
    public interface onClickListener {
        void onCameraClick();

        void onAlbumClick();
    }
}

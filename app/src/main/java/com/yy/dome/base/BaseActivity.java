package com.yy.dome.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.yy.dome.R;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.tool.UtilImags;
import com.yy.dome.util.ActivityCollector;

import java.io.IOException;

import butterknife.ButterKnife;
import okhttp3.Cookie;

/**
 * Created by huyongjiang on 2017/2/25.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(setMainLayout());
        ButterKnife.bind(this);
        initView();
        initBeforeData();
        ActivityCollector.addAcitivity(this);
    }
    /**
     * 初始化布局
     */
    protected abstract int setMainLayout();

    /**
     * 初始化先前数据
     */
    protected abstract void initBeforeData();

    /**
     * 初始化事件
     */
    protected abstract void initView();

    /**
     * 弹出toast 显示时长short
     *
     * @param msg
     */
    protected void showToastShort(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        }
    }
    protected void showToastShort(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    /**
     * 弹出toast 显示时长long
     *
     * @param msg
     */
    protected void showToastLong(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
        }
    }

    /***
     * 清除WebView缓存信息
     * @param webView
     */
    protected void clearWebViewCache(WebView webView){
        webView.getSettings().setAppCachePath("");
        webView.clearCache(true);
    }
    /***
     * 设置WebView内核属性
     * @param webSettings
     */
    protected void setWebViewSettings(WebSettings webSettings){
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.supportMultipleWindows();
        webSettings.setAppCacheEnabled(true);
        try {
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);


            webSettings.setAppCachePath(UtilImags.SHOWFILEURL(this));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!NetUtils.isNetworkAvailable(this)) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }

    }





    /***
     * 跳转Activity
     * @param mClass
     */
    protected void openActivity(Class<?> mClass) {
        openIntent(new Intent(this, mClass));
    }

    protected void openIntent(Intent intent) {
        startActivity(intent);
    }

/*    protected void setStatusTextColor(){
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }*/

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
               finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }


}


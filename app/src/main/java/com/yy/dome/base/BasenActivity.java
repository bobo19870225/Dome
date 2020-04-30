package com.yy.dome.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;

import android.view.View;


import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



import com.tencent.smtt.sdk.WebSettings;

import com.tencent.smtt.sdk.WebView;
import com.yy.dome.presenter.BasePresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.tool.UtilImags;
import com.yy.dome.ui.activity.my.LoginActivity;
import com.yy.dome.util.ActivityCollector;

import java.io.IOException;

import butterknife.ButterKnife;

/**
 * Created by huyongjiang on 2017/2/25.
 */

public abstract class BasenActivity<V,T extends BasePresenter<V>> extends AppCompatActivity {

    private ForceOfflineReceiver receiver;
    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("警告");
            builder.setMessage("你已经被强制下线，请重新登录");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ActivityCollector.finishAll();//销毁所有的活动
                    //回到登录界面(LoginActivity)
                    Intent intent = new Intent(context, LoginActivity.class);
                    context.startActivity(intent);
                }
            });
            builder.show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(setMainLayout());
        ButterKnife.bind(this);
        p=createPresenter();
        p.attachView((V)this);
        initView();
        initBeforeData();
        ActivityCollector.addAcitivity(this);
    }


    protected T p;
    protected int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;


    public abstract T createPresenter();
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



    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver,intentFilter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(receiver!=null){
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        p.detachView();
        p=null;
    }


    /****
     * 状态栏颜色修改
     */
    protected void setStatusTextColor(){
        this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

}


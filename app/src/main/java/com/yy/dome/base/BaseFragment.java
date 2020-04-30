package com.yy.dome.base;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yy.dome.tool.NetUtils;
import com.yy.dome.tool.UtilImags;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.PersistentCookieStore;

import java.io.IOException;

import okhttp3.Cookie;

/**
 * Created by huyongjiang on 2017/11/11.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setMainLayout(), container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initBeforeData();
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
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
        }
    }
    protected void showToastShort(int msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 弹出toast 显示时长long
     *
     * @param msg
     */
    protected void showToastLong(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
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
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(false);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.supportMultipleWindows();  //多窗口
        webSettings.setAppCacheMaxSize(1024 * 1024 * 25);//设置缓冲大小，我设的是8M
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setSavePassword(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setSaveFormData(true);
        try {
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setDatabasePath(UtilImags.SHOWFILEURL(getActivity()));
            webSettings.setAppCachePath(UtilImags.SHOWFILEURL(getActivity()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!NetUtils.isNetworkAvailable(getActivity())) {
            webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        } else {
            webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }
    }

    /***
     * Cookie同步WebView中
     */
    protected void setCookieSynchronizationWebView(String url, Context context){
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(context));
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        cookieManager.removeAllCookie();
        for (Cookie cookie : cookieJar.getCookieStore().getCookies()) {
            cookieManager.setCookie(url, cookie.toString());
        }
        CookieSyncManager.getInstance().sync();
    }

    /***
     * 跳转Activity
     *
     * @param mClass
     */
    protected void openActivity(Class<?> mClass) {
        openIntent(new Intent(getActivity(), mClass));
    }

    protected void openIntent(Intent intent) {
        startActivity(intent);
    }

    /****
     * 状态栏颜色修改
     */
    protected void setStatusTextColor(){
        getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }



    protected void showLog(String msg){
        Log.i("aaaaa",msg);
    }

}


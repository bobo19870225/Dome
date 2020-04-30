package com.yy.dome.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.android.tu.loadingdialog.LoadingDialog;
import com.yy.dome.R;
import com.yy.dome.api.IUtilDBRequest;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.js.BridgeWebView;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.ACache;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by 胡勇江 on 2016/11/12.
 */


public class Fragment3 extends BasenFragment<IView, MultiFunctionPresenter> implements IView {

    Intent intent;
    ACache aCache;
    Context context;
    String URL = "http://url/ideal.htm?method=schoolDetailedPage&phone=phone&token=token&schoolId=schoolId";
    @BindView(R.id.webview_onclick)
    LinearLayout webviewOnclick;
    @BindView(R.id.webview_error)
    LinearLayout webviewError;
    @BindView(R.id.webview)
    BridgeWebView webview;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    private String url = IUtilDBRequest.URL + "&method=schoolDetailedPage" + "&phone=" + UtilFileDB.Tel() + "&token=" + UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123) + "&schoolId=";
    String uRL;
    @Override
    protected int setMainLayout() {
        return R.layout.fragment2;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }


    @Override
    protected void initView() {
        aCache = ACache.get(getActivity());
        context = getActivity();
        WebSettings settings = webview.getSettings();
        setWebViewSettings(settings);
        webview.loadUrl(url+UtilFileDB.SCHOOLID());
    }


    @Override
    protected void initBeforeData() {


        Log.e("school",url+UtilFileDB.SCHOOLID());
        init();
    }


    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {

    }

    @Override
    public void onNetWork() {

    }

    @Override
    public void onError() {

    }

    @Override
    public void onLoadContributorStart() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @OnClick({R.id.webview_onclick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.webview_onclick:
                if (!NetUtils.isNetworkAvailable(context)) {
                    showToastShort("网络未连接");
                    return;
                } else {
                    webviewError.setVisibility(View.GONE);
                    webview.loadUrl(url);
                }
                break;

        }
    }

    private void init() {
        // TODO 自动生成的方法存根
        webview.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                view.loadUrl(url);
                return true;
            }
        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根
                if (newProgress == 100) {

                    progressBar1.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar1.setProgress(newProgress);//设置进度值
                }

            }
        });

    }


}

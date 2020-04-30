package com.yy.dome.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;

import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.yy.dome.R;
import com.yy.dome.api.IUtilDBRequest;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.TimeInfo;
import com.yy.dome.entity.home.SchoolInfo;

import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class HomeActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    Intent intent;
    Context context;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.webview_onclick)
    LinearLayout webviewOnclick;
    @BindView(R.id.webview_error)
    LinearLayout webviewError;
    String detailed = "";

    private String url = IUtilDBRequest.URL + "&phone=" + UtilFileDB.Tel() + "&token=" + UtilFileDB.Token() + "&method=idealMain&type=1";

    String  time;

    @Override
    protected int setMainLayout() {
        return R.layout.a;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {

        aCache = ACache.get(this);
        context = this;
        clearWebViewCache(webview);// 清除
        StatusBarUtil.setTransparentForWindow(this);
    }

    @Override
    protected void initBeforeData() {
        WebSettings settings = webview.getSettings();
        setWebViewSettings(settings);
        settings.setJavaScriptEnabled(true);
        webview.loadUrl(url);

/*        try {
            webview.setWebChromeClient(new WebChromeClient() {
                @Override
                public void onCloseWindow(WebView window) {
                    super.onCloseWindow(window);
                }


                @Override
                public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                    return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
                }
                @Override
                public void onProgressChanged(WebView view, int newProgress) {
                    super.onProgressChanged(view, newProgress);
                    if (newProgress == 100) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    rotateHeaderWebViewFrame.refreshComplete();
                                } catch (Exception e) {
                                    onError();
                                }
                            }
                        }, 500);
                    }
                }

                @Override
                public void onReceivedTitle(WebView view, String title) {
                    super.onReceivedTitle(view, title);
                    if (title.contains("404") || title.contains("网页无法打开") || title.contains("500") || title.toLowerCase().contains("error")) {
                        webviewError.setVisibility(View.VISIBLE);

                    }
                }

            });
        } catch (Exception e) {
            webviewError.setVisibility(View.VISIBLE);
        }
        rotateHeaderWebViewFrame.setLastUpdateTimeRelateObject(this);
        rotateHeaderWebViewFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, webview, header);
            }
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                webview.loadUrl(url);
            }
        });
        rotateHeaderWebViewFrame.setResistance(1.7f);
        rotateHeaderWebViewFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        rotateHeaderWebViewFrame.setDurationToClose(200);
        rotateHeaderWebViewFrame.setDurationToCloseHeader(1000);
        rotateHeaderWebViewFrame.setPullToRefresh(false);

        rotateHeaderWebViewFrame.setKeepHeaderWhenRefresh(true);*/

/*        webview.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                detailed = UitlAPi.show(data, mHandler);

            }
        });*/
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        if (position == 1) {
            if (topContributor instanceof String) {
                SchoolInfo infoss = JSON.parseObject(topContributor, SchoolInfo.class);
                TimeInfo Info = JSON.parseObject(topContributor, TimeInfo.class);
                time = topContributor.toString();
            }
        }
    }

    @Override
    public void onNetWork() {
        showToastShort(R.string.no_network_error);
    }

    @Override
    public void onError() {
        showToastShort(R.string.error_no_data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

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


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            try {
                if (resultCode == 2) {
                }
            } catch (Exception e) {
            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
            webview.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}
package com.yy.dome.ui.activity.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.yy.dome.R;
import com.yy.dome.api.IUtilDBRequest;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.js.BridgeWebView;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DisclaimerActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    Intent intent;
    Context context;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.webview)
    WebView webview;

    @BindView(R.id.webview_onclick)
    LinearLayout webviewOnclick;
    @BindView(R.id.webview_error)
    LinearLayout webviewError;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    // private String uRL = "http://192.168.3.200:3958/ideal.htm?&method=articleDetailedPage&phone=18748819480&token=089aea27e6c37ba12476396c50d2f1a4&id=68";

    private String uRL = IUtilDBRequest.URL + "&method=articleDetailedPage" + "&phone=" + UtilFileDB.Tel() + "&token=" + UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123) + "&id=128";

    @Override
    protected int setMainLayout() {
        return R.layout.ab;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        StatusBarUtil.setTransparentForWindow(this);

    }

    @Override
    protected void initBeforeData() {
        WebSettings settings = webview.getSettings();
        setWebViewSettings(settings);
        setWebViewSettings(settings);
        webview.loadUrl(uRL);

      /*  try {
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
                webview.loadUrl(uRL);
            }
        });
        rotateHeaderWebViewFrame.setResistance(1.7f);
        rotateHeaderWebViewFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        rotateHeaderWebViewFrame.setDurationToClose(200);
        rotateHeaderWebViewFrame.setDurationToCloseHeader(1000);
        rotateHeaderWebViewFrame.setPullToRefresh(false);
        rotateHeaderWebViewFrame.setKeepHeaderWhenRefresh(true);*/
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {


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


    @OnClick({R.id.fanhui_lay, R.id.top_right, R.id.webview_onclick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.top_right:
                break;
            case R.id.webview_onclick:
                if (!NetUtils.isNetworkAvailable(context)) {
                    showToastShort("网络未连接");
                    return;
                } else {
                    webviewError.setVisibility(View.GONE);
                    webview.loadUrl(uRL);
                }
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();

    }

}
package com.yy.dome.ui.activity.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.yy.dome.R;
import com.yy.dome.api.IUtilDBRequest;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.js.BridgeHandler;
import com.yy.dome.js.BridgeWebView;
import com.yy.dome.js.CallBackFunction;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.yy.dome.widget.PtrClassicFrameLayout;
import com.yy.dome.widget.PtrDefaultHandler;
import com.yy.dome.widget.PtrFrameLayout;
import com.yy.dome.widget.PtrHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeSchoolDetalis extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
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


    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;

    private String uRL = IUtilDBRequest.URL + "&method=schoolDetailedPage" + "&phone=" + UtilFileDB.Tel() + "&token=" + UtilFileDB.Token() + "&schoolId=";
    ;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_webview;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        top.setText("学校详情");
        uRL += getIntent().getStringExtra("id");

        int length = uRL.length();//得到字符串长度


    }

    @Override
    protected void initBeforeData() {
        StatusBarUtil.setTransparentForWindow(this);
        WebSettings settings = webview.getSettings();
        setWebViewSettings(settings);

        if (uRL.equals("2535")) {
            webview.loadUrl(IUtilDBRequest.URL + "&method=schoolDetailedPage&phone=18185153729&token=token&ids=[112]&title" + 2535);
        }
        if (uRL.equals("2")) {
            webview.loadUrl(IUtilDBRequest.URL + "&method=schoolDetailedPage&phone=18185153729&token=token&ids=[111]&title=" + 2);
        }
        if (uRL.equals("208")) {
            webview.loadUrl(IUtilDBRequest.URL + "&method=schoolDetailedPage&phone=18185153729&token=token&ids=[110]&title=" + 208);
        }
        if (uRL.equals("218")) {
            webview.loadUrl(IUtilDBRequest.URL + "&method=schoolDetailedPage&phone=18185153729&token=token&ids=[109]&title=" + 218);
        }
        if (uRL.equals("2513")) {
            webview.loadUrl(IUtilDBRequest.URL + "&method=schoolDetailedPage&phone=18185153729&token=token&ids=[108]&title=" + 2513);
        }
        if (uRL.equals("273")) {
            webview.loadUrl(IUtilDBRequest.URL + "&method=schoolDetailedPage&phone=18185153729&token=token&ids=[107]&title=" + 273);
        }
        if (uRL.equals("2604")) {
            webview.loadUrl(IUtilDBRequest.URL + "&method=schoolDetailedPage&phone=18185153729&token=token&ids=[108]&title=" + 2604);
        }
        if (uRL.equals("2520")) {
            webview.loadUrl(IUtilDBRequest.URL + "&method=schoolDetailedPage&phone=18185153729&token=token&ids=[107]&title=" + 2520);
        }


/*        webview.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
            }
        });*/

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
        }
    }
}
package com.yy.dome.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yy.dome.R;
import com.yy.dome.api.IUtilDBRequest;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.js.BridgeWebView;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
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
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
   // private String url= IUtilDBRequest.URL+"app/article/batchLinePage";

    private String url = IUtilDBRequest.URL + "&method=batchLinePage" + "&phone=" + UtilFileDB.Tel() + "&token=" + UtilFileDB.Token();
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
    }

    @Override
    protected void initBeforeData() {
        top.setText("贵州批次");
        StatusBarUtil.setTransparentForWindow(this);
        WebSettings settings = webview.getSettings();
        setWebViewSettings(settings);
       // webview.loadData(url, "text/html", "UTF-8");
/*        webview.getSettings().setDefaultTextEncodingName("UTF -8");
       // webview.loadDataWithBaseURL(url, "text/html", "utf-8",null);
        webview.loadData(url, "text/html", "UTF-8");*/
        webview.getSettings().setDefaultTextEncodingName("utf-8") ;
        webview.loadUrl(url);
        init();
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

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webview.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webview.goBack();
                return true;
            } else {//当webview处于第一页面时,直接退出程序
                System.exit(0);
            }


        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                finish();
            }
            if (webview.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webview.goBack();
                return true;
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
                    webview.loadUrl("file:///android_asset/index.html");
                }
                break;
        }
    }

}
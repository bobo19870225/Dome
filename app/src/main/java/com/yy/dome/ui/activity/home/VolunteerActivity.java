package com.yy.dome.ui.activity.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.tencent.tauth.Tencent;
import com.yy.dome.R;
import com.yy.dome.api.IUtilDBRequest;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.js.BridgeHandler;

import com.yy.dome.js.CallBackFunction;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.ACache;
import com.yy.dome.util.EventBusUtil;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UitlAPi;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolunteerActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    Intent intent;
    Context context;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;

    @BindView(R.id.top_lift)
    TextView topLift;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.webview_onclick)
    LinearLayout webviewOnclick;
    @BindView(R.id.webview_error)
    LinearLayout webviewError;
    String detailed = "";
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
/*    @BindView(R.id.rotate_header_web_view_frame)
    PtrClassicFrameLayout rotateHeaderWebViewFrame;*/

    private String url = IUtilDBRequest.URL + "&method=subjectPage" + "&phone=" + UtilFileDB.Tel() + "&token=" + UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123);

    Tencent mTencent;
    String mAppid = "1110107582";
    PopupWindow popupWindow;
    LinearLayout linearLayoutPopupWindow;

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
        top.setText("专业大全");
/*        if (mTencent == null) {
            mTencent = Tencent.createInstance(mAppid, context);
        }*/
    }

    @Override
    protected void initBeforeData() {
        StatusBarUtil.setTransparentForWindow(this);
        WebSettings settings = webview.getSettings();
        setWebViewSettings(settings);
        webview.loadUrl(url);
        try {
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


/*        webview.registerHandler("submitFromWeb", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                detailed = UitlAPi.show(data, mHandler);
            }
        });*/

        init();
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


    //创建一个Hander局部类对象，通过handleMessage()钩子方法来更新UI控件
    Handler mHandler = new Handler() {

        public void handleMessage(Message msg) {
            //得到封装消息的id进行匹配
            if (1 == msg.what) {
                show();
            }
        }

    };

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
                if (webview.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                    webview.goBack();
                    return true;
                } else {
                    finish();
                }
            }

            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @OnClick({R.id.fanhui_lay, R.id.top_right, R.id.webview_onclick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                if (webview.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                    webview.goBack();
                    //topLift.setText("返回上一页");
                } else {
                    finish();
                }
                break;
            case R.id.top_right:
                showStscShare();
                linearLayoutPopupWindow.startAnimation(AnimationUtils.loadAnimation(context, R.anim.activity_translate_in));
                popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
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

    private void show() {
        intent = new Intent(context, VolunteerDetails.class);
        intent.putExtra("id", detailed);

        startActivity(intent);
    }

    /****
     * 分享操作
     *
     * @param
     */
    private void showStscShare() {
        popupWindow = new PopupWindow(context);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popupwindow_shread, null);
        linearLayoutPopupWindow = (LinearLayout) view.findViewById(R.id.ll_popups);
        EventBusUtil.SHOWSTSCSHARE(view, popupWindow, linearLayoutPopupWindow, mTencent, VolunteerActivity.this, url);
    }
}
package com.yy.dome.ui.activity.home;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.api.IUtilDBRequest;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.VRMapInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.MainFragment;
import com.yy.dome.ui.activity.school.VRMapActivity;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.utils.MyMainDialog;
import com.yy.dome.view.IView;
import com.yy.dome.widget.CenterDialog;
import com.yy.dome.wx.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;


public class FindSchool extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    Intent intent;
    Context context;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.webview_onclick)
    LinearLayout webviewOnclick;
    @BindView(R.id.webview_error)
    LinearLayout webviewError;
    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    @BindView(R.id.school_right)
    TextView schoolRight;
    private String url = IUtilDBRequest.URL + "&method=schoolPage" + "&phone=" + UtilFileDB.Tel() + "&token=" + UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123);
    LinearLayout ll_popup;
    public static PopupWindow pop;
    String goodid, schoolid;
    CenterDialog centerDialog;

    private Dialog dialog;
    private IWXAPI api;

    @Override
    protected int setMainLayout() {
        return R.layout.abc;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        top.setText("查找大学");
        aCache = ACache.get(this);
        context = this;
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);
        clearWebViewCache(webview);// 清除
        if (!EventBus.getDefault().isRegistered(this))
            EventBus.getDefault().register(this);
    }

    @Override
    protected void initBeforeData() {
        StatusBarUtil.setTransparentForWindow(this);
        WebSettings settings = webview.getSettings();
        setWebViewSettings(settings);
        settings.setJavaScriptEnabled(true);
        webview.loadUrl(url);

        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);

                Log.e("我是标题", title);
                if (title.contains("goodsId") && title.length() > 0) {
                    try {
                        Map m = new HashMap();
                        String[] fri = title.split(",");
                        for (String ele : fri) {
                            String[] pair = ele.split("=");
                            m.put(pair[0], pair[1]);
                        }
                        Log.e("我是标题1", m.get("schoolId").toString());
                        schoolid = m.get("schoolId").toString();
                        goodid = m.get("goodsId").toString();

                    } catch (Exception E) {

                    }
                    schoolRight.setText("VR地图");
                } else {
                    schoolRight.setText("");
                }
            }
        });
        //重写防止使用浏览器打开
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }


    @Override
    public void onLoadContributorStart() {

    }

    private String vrURL;

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        Log.e("dsajksdlalk", topContributor);
        VRMapInfo info = JSON.parseObject(topContributor, VRMapInfo.class);
        if (info.getRet().equals("0")) {
            vrURL = info.getData().get(0);
        } else if (info.getRet().equals("1")) {
            pop.dismiss();
            showToastShort("该校暂无资源");
        }
//        if (info.getRet().equals("2")) {
//            pop.dismiss();
//            ///支付操作
//            openDialog();
//        }
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
                    schoolRight.setText("");
                    return true;
                } else {
                    finish();
                }
            }

            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @OnClick({R.id.webview_onclick, R.id.fanhui_lay, R.id.school_right})
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
            case R.id.fanhui_lay:
                intent = new Intent(context, MainFragment.class);
                startActivity(intent);
                break;
            case R.id.school_right:
/*                try{
                    p.functionMultiRequests(Api.URLs + "ideal.htm?", context, Api.VRMAP(UtilFileDB.Tel(),
                            UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123),schoolid,goodid), 1);
                }catch (Exception E){
                }*/
                VRMAP();
                ll_popup.startAnimation(AnimationUtils.loadAnimation(
                        context, R.anim.activity_translate_in));
                pop.showAtLocation(view, Gravity.LEFT, 0, 0);
                try {
                    p.functionMultiRequests(Api.URLs + "ideal.htm?", context, Api.VRMAP(UtilFileDB.Tel(),
                            UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123), schoolid, goodid), 1);
                } catch (Exception E) {

                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void VRMAP() {
        pop = new PopupWindow(context);
        View view = getLayoutInflater().inflate(R.layout.item_vrmap_pay,
                null);
        ll_popup = (LinearLayout) view.findViewById(R.id.popup_vrmap);
        pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        LinearLayout parent = (LinearLayout) view.findViewById(R.id.parent_vrmap);
        TextView bt1 = (TextView) view.findViewById(R.id.vrmap_pay);
        TextView bt2 = (TextView) view.findViewById(R.id.vip_pay);
        TextView bt3 = (TextView) view.findViewById(R.id.vrmap_quxiao);
        TextView bt4 = (TextView) view.findViewById(R.id.vrp_determine);

        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ///支付操作
                OkHttpUtils.post().url(Api.URLs + "student.htm?").params(Api.VRMapPay(UtilFileDB.Tel(),
                        schoolid, goodid)).build().execute(new FindSchool.PunchCallback(1));
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });


        bt2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
                intent = new Intent(context, VipActivity.class);
                startActivity(intent);

            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });


        bt4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
                intent = new Intent(context, VRMapActivity.class);
                intent.putExtra("id", vrURL);
                startActivity(intent);

            }
        });
    }

    public class PunchCallback extends StringCallback {

        private int index;

        public PunchCallback(int index) {
            this.index = index;
        }

        @Override
        public void onError(Call call, Exception e, int id) {//加载失败
            Toast.makeText(FindSchool.this, "加载失败", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onResponse(String response, int id) {
            Log.e("支付接口：", response);
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(response);
                if (null != jsonobjs && !jsonobjs.has("retcode")) {
                    String appid = jsonobjs.getString("appid");
                    String partnerId = jsonobjs.getString("mch_id");
                    String prepayId = jsonobjs.getString("prepay_id");
                    String packageValue = jsonobjs.getString("package");
                    String nonceStr = jsonobjs.getString("nonce_str");
                    String timeStamp = jsonobjs.getString("timestamp");
                    String sign = jsonobjs.getString("sign");
                    UtilFileDB.ADDSHAREDDATA("prepayId", prepayId);
                    PayReq req = new PayReq();
                    req.appId = appid;
                    req.partnerId = partnerId;
                    req.prepayId = prepayId;
                    req.packageValue = packageValue;
                    req.nonceStr = nonceStr;
                    req.timeStamp = timeStamp;
                    req.sign = sign;
                    api.sendReq(req);
                    Toast.makeText(FindSchool.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {

            }

        }

        @Override
        public void onAfter(int id) {//加载中
            super.onAfter(id);
        }

        @Override
        public void onBefore(Request request, int id) {
            super.onBefore(request, id);
        }

    }


    public static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                pop.dismiss();
            }

        }

    };
    /**
     * 微信支付回调处理
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(String event) {
        if (event.equals("WXPayCallBack")){
            ll_popup.clearAnimation();
            intent = new Intent(context, VRMapActivity.class);
            intent.putExtra("id", vrURL);
            startActivity(intent);
        }

    }

    private void openDialog() {
        MyMainDialog myMainDialog = new MyMainDialog(this);
        myMainDialog.show();
        myMainDialog.setClickLinstener(new MyMainDialog.ClickListenerInterface() {
            @Override
            public void doTime() {
                Toast.makeText(context, "付费", Toast.LENGTH_SHORT).show();


            }

            @Override
            public void doSinger() {
                Toast.makeText(context, "开通VIP", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doSong() {
                Toast.makeText(context, "取消", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void doAlum() {
                myMainDialog.dismiss();
                Toast.makeText(context, "确定", Toast.LENGTH_SHORT).show();

                intent = new Intent(context, VRMapActivity.class);
                intent.putExtra("id", vrURL);
                startActivity(intent);
            }
        });
    }


}
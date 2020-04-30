package com.yy.dome.wxapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.api.IUtilDBRequest;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.yy.dome.wx.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WxActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    Intent intent;
    Context context;
    @BindView(R.id.button2323223)
    Button button2323223;
    @BindView(R.id.textView2232323)
    Button textView2232323;
    @BindView(R.id.webview_webview)
    WebView webviewWebview;
    private IWXAPI api;
    String appid, partnerId, prepayId, sign;
    Gson gson;
    private String url = IUtilDBRequest.URL + "&method=circularPage" + "&phone=" + UtilFileDB.Tel() + "&token=" + UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123);
    String stringA = "appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";
    String stringSignTemp = stringA + "&key=192006250b4c09247ec02edce69f6a2d";

    @Override
    protected int setMainLayout() {
        return R.layout.wx_api;
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
        StatusBarUtil.setTransparentForWindow(this);
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID, false);
        api.registerApp(Constants.APP_ID);
        gson = new Gson();
        Log.e("sign", UtilTools.MD5(stringSignTemp).toUpperCase());
        Log.e("sing", hmac_sha1("sha256", stringSignTemp, "192006250b4c09247ec02edce69f6a2d").toLowerCase());

        WebSettings settings = webviewWebview.getSettings();
        settings.setJavaScriptEnabled(true);
        webviewWebview.loadUrl("file:///android_asset/circular/home.html?url=" + url);
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        Log.e("payinfo", topContributor);
        JSONObject jsonobjs;
        try {
            jsonobjs = new JSONObject(topContributor);
            if (null != jsonobjs && !jsonobjs.has("retcode")) {
                String appid = jsonobjs.getString("appid");
                String partnerId = jsonobjs.getString("mch_id");
                String prepayId = jsonobjs.getString("prepay_id");
                String packageValue = jsonobjs.getString("package");
                String nonceStr = jsonobjs.getString("nonce_str");
                String timeStamp = jsonobjs.getString("timestamp");
                String sign = jsonobjs.getString("sign");

                String stringAA = "appid=" + appid + "&mch_id=" + partnerId + "&prepay_id=" + prepayId + "&package=" + packageValue +
                        "&nonce_str=" + nonceStr + "&timestamp=" + timeStamp;
                String stringSignTempA = stringAA + "&key=" + Constants.API_KEY;
                Log.i("阿山", UtilTools.MD5(stringSignTempA));
                PayReq req = new PayReq();
                req.appId = jsonobjs.getString("appid");
                req.partnerId = jsonobjs.getString("mch_id");
                req.prepayId = jsonobjs.getString("prepay_id");
                req.packageValue = jsonobjs.getString("package");
                req.nonceStr = jsonobjs.getString("nonce_str");
                req.timeStamp = jsonobjs.getString("timestamp");
                // req.sign = UtilTools.MD5(stringSignTempA).toUpperCase();
                req.sign = sign;
                api.sendReq(req);

                Log.e("appid", appid);
                Log.e("partnerId", partnerId);
                Log.e("prepayId", prepayId);
                Log.e("packageValue", packageValue);
                Log.e("nonceStr", nonceStr);
                Log.e("timeStamp", timeStamp);
                Log.e("时间戳", UtilTools.currentTimeMillis());
                Log.e("sign", sign);
                Toast.makeText(WxActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {

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


    @OnClick({R.id.button2323223, R.id.textView2232323})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button2323223:
                p.functionMultiRequests(Api.URLs + "student.htm?", WxActivity.this, Api.wxapi(UtilFileDB.Tel(),"1"), 1);
                break;
            case R.id.textView2232323:

                break;
        }
    }


    private String hmac_sha1(String ss, String datas, String key) {
        String reString = "";

        try {
            byte[] data = key.getBytes("UTF-8");
            //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
            //生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance("HmacSHA1");
            //用给定密钥初始化 Mac 对象
            mac.init(secretKey);

            byte[] text = datas.getBytes("UTF-8");
            //完成 Mac 操作
            byte[] text1 = mac.doFinal(text);

            reString = Base64.encodeToString(text1, Base64.DEFAULT);

        } catch (Exception e) {
            // TODO: handle exception
        }

        return reString;
    }


}
package com.yy.dome.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.yy.dome.wx.Constants;
import com.yy.dome.wxapi.WxActivity;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VipActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    Intent intent;
    Context context;
    @BindView(R.id.top_text)
    TextView topText;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.ry_ela1)
    RelativeLayout ryEla1;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    @BindView(R.id.pay_layout)
    LinearLayout payLayout;
    private IWXAPI api;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_vip;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        top.setText("VIP会员专享");
        StatusBarUtil.setTransparentForWindow(this);
    }

    @Override
    protected void initBeforeData() {

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        JSONObject jsonobjs;
        try {
            jsonobjs = new JSONObject(topContributor);
            if (null != jsonobjs && !jsonobjs.has("retcode")) {
                String appid = jsonobjs.getString("appid");
                String partnerId = jsonobjs.getString("mch_id");
                String prepayId = jsonobjs.getString("prepay_id");
                String goodid = jsonobjs.getString("goods_Id");
                String packageValue = jsonobjs.getString("package");
                String nonceStr = jsonobjs.getString("nonce_str");
                String timeStamp = jsonobjs.getString("timestamp");
                String sign = jsonobjs.getString("sign");
                UtilFileDB.ADDSHAREDDATA("prepayId",prepayId);
                UtilFileDB.ADDSHAREDDATA("goodid",goodid);
                PayReq req = new PayReq();
                req.appId = appid;
                req.partnerId = partnerId;
                req.prepayId = prepayId;
                req.packageValue = packageValue;
                req.nonceStr = nonceStr;
                req.timeStamp = timeStamp;
                req.sign = sign;
                api.sendReq(req);
                Toast.makeText(context, "正常调起支付", Toast.LENGTH_SHORT).show();
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


    @OnClick({R.id.fanhui_lay, R.id.pay_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.pay_layout:
                p.functionMultiRequests(Api.URLs + "student.htm?", VipActivity.this, Api.wxapi(UtilFileDB.Tel(),"1"), 1);
                break;
        }
    }
}
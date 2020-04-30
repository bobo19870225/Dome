package com.yy.dome.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.home.FindSchool;
import com.yy.dome.ui.activity.school.VRMapActivity;
import com.yy.dome.ui.activity.school.adapter.ExpertVideoActivity;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.wx.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    @BindView(R.id.top_lift)
    TextView topLift;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.altr_top_lay)
    LinearLayout altrTopLay;
    private IWXAPI api;
    private String result;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        ButterKnife.bind(this);

        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }


    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                result = String.valueOf(resp.errCode);//这个参数必须要
                OkHttpUtils.post().url(Api.URLs + "student.htm?").params(Api.pay(UtilFileDB.Tel(), UtilFileDB.prepayId(),
                        UtilTools.currentTimeMillis(), result, UtilFileDB.Goodid(), "",
                        UtilFileDB.expertIds())).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Message message1 = new Message();
                        message1.what = 0;
                        new FindSchool().handler.sendMessage(message1);
                        Message message = new Message();
                        message.what = 1;
                        new ExpertVideoActivity().handler.sendMessage(message);
                        EventBus.getDefault().post("WXPayCallBack");
                        finish();//运行
                    }
                });
                Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
            } else {

                Log.e("java", "onResp: " + resp.errCode);
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }


    @OnClick(R.id.fanhui_lay)
    public void onViewClicked() {
        finish();
    }
}
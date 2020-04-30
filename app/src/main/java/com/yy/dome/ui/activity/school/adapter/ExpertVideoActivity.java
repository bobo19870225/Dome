package com.yy.dome.ui.activity.school.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.VideoView;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BaseActivity;
import com.yy.dome.tool.DataList;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.home.VipActivity;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.wx.Constants;
import com.yy.dome.wxapi.WXPayEntryActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

public class ExpertVideoActivity extends BaseActivity  {


    private static final String THUMB = "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg";
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.altr_top_lay)
    LinearLayout altrTopLay;

    @BindView(R.id.pay_video)
    TextView payVideo;


    @BindView(R.id.video_pay)
    TextView videoPay;

    public  static VideoView expertVideo;

    public static RelativeLayout rlLayout;


    private StandardVideoController controller;

    private IWXAPI api;
    String id, name, URL,goodid;
    private static Context th;
    @Override
    protected int setMainLayout() {
        return R.layout.activity_expertvideo;
    }


    @Override
    protected void initView() {

        expertVideo = findViewById(R.id.expert_video);
        rlLayout = findViewById(R.id.rl_layout);


        th=ExpertVideoActivity.this;
        StatusBarUtil.setTransparentForWindow(this);
        top.setText("专家详解");
        id += getIntent().getStringExtra("id");
        URL += getIntent().getStringExtra("URL");
        name += getIntent().getStringExtra("name");
        goodid+= getIntent().getStringExtra("goodid");
        api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.registerApp(Constants.APP_ID);

        Log.e("视频ID",id.substring(4).trim());

        UtilFileDB.ADDSHAREDDATA("expertIds",id.substring(4).trim());

        Log.e("视频ID12343423234",UtilFileDB.expertIds());
        controller = new StandardVideoController(this);
        controller.setEnableOrientation(true);

        PrepareView prepareView = new PrepareView(this);//准备播放界面
        ImageView thumb = prepareView.findViewById(R.id.thumb);//封面图
        Glide.with(this).load(THUMB).into(thumb);

        controller.addControlComponent(prepareView);

        controller.addControlComponent(new CompleteView(this));//自动完成播放界面

        controller.addControlComponent(new ErrorView(this));//错误界面

        VodControlView vodControlView = new VodControlView(this);
        vodControlView.showBottomProgress(false);
        controller.addControlComponent(vodControlView);




    }

    @Override
    protected void initBeforeData() {

        TitleView titleView = new TitleView(this);//标题栏
        controller.addControlComponent(titleView);
        titleView.setTitle(name.substring(4).trim());
        expertVideo.setVideoController(controller);
        expertVideo.setUrl(Api.URLs + URL.substring(4).trim());
        Log.e("是否可以查看",UtilFileDB.flag());
        if (UtilFileDB.flag().equals("true")) {
            rlLayout.setVisibility(View.GONE);
            //设置标题
            TitleView titleView1 = new TitleView(this);//标题栏
            controller.addControlComponent(titleView1);
            titleView1.setTitle(name.substring(4).trim());
            expertVideo.setVideoController(controller);
            expertVideo.setUrl(Api.URLs + URL.substring(4).trim());
            expertVideo.start();
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        if (expertVideo != null) {
            expertVideo.resume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (expertVideo != null) {
            expertVideo.pause();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (expertVideo != null) {
            expertVideo.release();
        }
    }

    @Override
    public void onBackPressed() {
        if (expertVideo == null || !expertVideo.onBackPressed()) {
            super.onBackPressed();
        }
    }



    /***
     *
     */
    public void showStartVideo() {
        rlLayout.setVisibility(View.GONE);//这里报错
        expertVideo.start();

    }



    @OnClick({R.id.fanhui_my, R.id.pay_video, R.id.video_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_my:
                finish();
                expertVideo.pause();

                break;
            case R.id.pay_video:
                ///支付操作
                if(UtilFileDB.flag().equals("false")){
                    OkHttpUtils.post().url(Api.URLs+ "student.htm?").params(Api.ExpertVideo(UtilFileDB.Tel(),id.substring(4).trim(),
                            goodid.substring(4).trim())).build().execute(new ExpertVideoActivity.PunchCallback(1));
                }else {


                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showStartVideo();
                        }
                    },1000);

                }
                break;
            case R.id.video_pay:
                break;
        }
    }


    public class PunchCallback extends StringCallback {

        private int index;

        public PunchCallback(int index) {
            this.index = index;
        }

        @Override
        public void onError(Call call, Exception e, int id) {//加载失败
            Toast.makeText(ExpertVideoActivity.this, "加载失败", Toast.LENGTH_LONG).show();
        }
        @Override
        public void onResponse(String response, int id) {
            Log.e("支付接口：",response);
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
                    UtilFileDB.ADDSHAREDDATA("prepayId",prepayId);
                    PayReq req = new PayReq();
                    req.appId = appid;
                    req.partnerId = partnerId;
                    req.prepayId = prepayId;
                    req.packageValue = packageValue;
                    req.nonceStr = nonceStr;
                    req.timeStamp = timeStamp;
                    req.sign = sign;
                    api.sendReq(req);
                    Toast.makeText(ExpertVideoActivity.this , "正常调起支付", Toast.LENGTH_SHORT).show();
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



    public static  Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==1)
            {
                rlLayout.setVisibility(View.GONE);//这里报错
                expertVideo.start();
            }
        }
    };



}

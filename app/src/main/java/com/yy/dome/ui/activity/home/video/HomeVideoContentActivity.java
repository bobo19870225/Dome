package com.yy.dome.ui.activity.home.video;


import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dueeeke.videocontroller.StandardVideoController;
import com.dueeeke.videocontroller.component.CompleteView;
import com.dueeeke.videocontroller.component.ErrorView;
import com.dueeeke.videocontroller.component.PrepareView;
import com.dueeeke.videocontroller.component.TitleView;
import com.dueeeke.videocontroller.component.VodControlView;
import com.dueeeke.videoplayer.player.VideoView;
import com.yy.dome.R;
import com.yy.dome.base.BaseActivity;
import com.yy.dome.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyongjiang on 2020/3/24
 * Describe: 视频详情
 */
public class HomeVideoContentActivity extends BaseActivity {


    private static final String THUMB = "https://cms-bucket.nosdn.127.net/eb411c2810f04ffa8aaafc42052b233820180418095416.jpeg";

    @BindView(R.id.player)
    VideoView mVideoView;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.altr_top_lay)
    LinearLayout altrTopLay;
    @BindView(R.id.video_text)
    TextView videoText;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;

    private StandardVideoController controller;
    public static int BtnTag = 0;
    String id, contens, name, URL;

    @Override
    protected int setMainLayout() {
        return R.layout.home_video_content_activity;
    }


    @Override
    protected void initView() {
        StatusBarUtil.setTransparentForWindow(this);
        top.setText("魅力大学");
        id += getIntent().getStringExtra("id");
        URL += getIntent().getStringExtra("URL");
        contens += getIntent().getStringExtra("contens");
        name += getIntent().getStringExtra("name");
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
        mVideoView.setVideoController(controller);
        mVideoView.setUrl(URL.substring(4).trim());

        videoText.setText(contens.substring(4).trim());
        mVideoView.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fanhui_lay)
    public void onViewClicked() {
        finish();
        mVideoView.pause();
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
        if (mVideoView != null) {
            mVideoView.resume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (mVideoView != null) {
            mVideoView.pause();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mVideoView != null) {
            mVideoView.release();
        }
    }

    @Override
    public void onBackPressed() {
        if (mVideoView == null || !mVideoView.onBackPressed()) {
            super.onBackPressed();
        }
    }

}

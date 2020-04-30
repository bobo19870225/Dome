package com.yy.dome.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.yy.dome.R;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpertActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
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
    private IWXAPI api;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_expert;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        top.setText("专家一对一");
        StatusBarUtil.setTransparentForWindow(this);
    }

    @Override
    protected void initBeforeData() {


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


    @OnClick(R.id.fanhui_lay)
    public void onViewClicked() {
        finish();
    }



}
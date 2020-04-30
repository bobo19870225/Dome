package com.yy.dome.ui.activity.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.util.ACache;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCommentActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    private Context context;
    ACache aCache;
    private Intent intent;

    @Override
    protected int setMainLayout() {
        return R.layout.cs;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        top.setText("我的评论");
    }

    @Override
    public void onResume() {

        super.onResume();
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


    @OnClick(R.id.fanhui)
    public void onViewClicked() {
        finish();
    }
}

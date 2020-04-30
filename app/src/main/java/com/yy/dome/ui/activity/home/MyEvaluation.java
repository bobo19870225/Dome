package com.yy.dome.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.ui.activity.answer.AnswerActivity;
import com.yy.dome.ui.activity.answer.OkActivity;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyEvaluation extends BasenActivity<IView, MultiFunctionPresenter> implements IView {


    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.see)
    TextView see;
    private Context context;
    ACache aCache;
    private Intent intent;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_evaluation;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTransparentForWindow(this);
        aCache = ACache.get(this);
        context = this;
        top.setText("自我测评");
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


    @OnClick({R.id.fanhui_my, R.id.start, R.id.see})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_my:
                finish();
                break;
            case R.id.start:
                startActivity(new Intent(context, AnswerActivity.class));
                break;
            case R.id.see:
                if (UtilFileDB.ANSWER()) {
                    intent = new Intent(context, AnswerActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(context, OkActivity.class);
                    intent.putExtra("stringType", UtilFileDB.stringType());
                    startActivity(intent);
                }


                break;
        }
    }


}

package com.yy.dome.ui.activity.school;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.ui.activity.school.form.FormBatchActivityOne;
import com.yy.dome.ui.activity.school.form.FormBatchActivityThree;
import com.yy.dome.ui.activity.school.form.FormBatchActivityTwo;
import com.yy.dome.ui.activity.school.form.FormLookUpInfo;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.view.IView;
import com.yy.dome.widget.PreferenceItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VoluntaryformActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    Intent intent;
    Context context;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    @BindView(R.id.top_lift)
    TextView topLift;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right_texts)
    TextView topRightTexts;
    @BindView(R.id.form_batch1)
    PreferenceItem formBatch1;
    @BindView(R.id.form_batch2)
    PreferenceItem formBatch2;
    @BindView(R.id.my_lay3)
    LinearLayout myLay3;
    List<FormLookUpInfo> list;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_form;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        //arrayListId = getIntent().getStringArrayListExtra("arrayId");//接收集合
        aCache = ACache.get(this);
        context = this;
        StatusBarUtil.setTransparentForWindow(this);
        top.setText("我的志愿表");
        formBatch1.setTitle("本科一批志愿表");
        formBatch1.setImageView(R.mipmap.icon_my_achievement);
        formBatch2.setTitle("本科第二批志愿表");
        formBatch2.setImageView(R.mipmap.icon_my_achievement);
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


    @OnClick({R.id.fanhui_lay, R.id.form_batch1, R.id.form_batch2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.form_batch1:
                intent = new Intent(context, FormBatchActivityOne.class);
                startActivity(intent);
                break;
            case R.id.form_batch2:
                intent = new Intent(context, FormBatchActivityTwo.class);
                startActivity(intent);
                break;
        }
    }
}
package com.yy.dome.ui.activity.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.submission)
    Button submission;
    @BindView(R.id.feedback_edittext)
    EditText feedbackEdittext;
    @BindView(R.id.feedback_tel)
    EditText feedbackTel;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    private Intent intent;
    Context context;


    @Override
    protected int setMainLayout() {
        return R.layout.activity_feedback;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        StatusBarUtil.setTransparentForWindow(this);
    }

    @Override
    protected void initBeforeData() {
        top.setText("意见反馈");
    }

    @Override
    public void onLoadContributorStart() {

    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        if (position == 1) {
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(topContributor);
                String errno = jsonobjs.getString("ret");
                if (errno.equals("0")) {
                    showToastShort("我们已经收到您提的宝贵意见，请等候通知");
                    Log.e("data", topContributor);
                    finish();
                }
            } catch (JSONException e) {

            }
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


    @OnClick({R.id.fanhui_lay, R.id.submission})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.submission:

                p.functionMultiRequests(Api.URLs + "ideal.htm?", context, Api.Feedback(UtilFileDB.Tel(), UtilFileDB.Token(), feedbackEdittext.getText().toString().trim(),
                        feedbackTel.getText().toString().trim()), 1);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
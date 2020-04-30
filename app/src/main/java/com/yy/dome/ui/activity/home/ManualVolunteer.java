package com.yy.dome.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
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
import com.yy.dome.view.IView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManualVolunteer extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
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
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.altr_top_lay)
    LinearLayout altrTopLay;
    @BindView(R.id.homepage_fragment_search_views)
    EditText homepageFragmentSearchViews;
    @BindView(R.id.pent_view)
    LinearLayout pentView;
    @BindView(R.id.main)
    LinearLayout main;
    @BindView(R.id.submission_bnt)
    TextView signBtn;
    @BindView(R.id.query_results)
    TextView queryResults;


    @Override
    protected int setMainLayout() {
        return R.layout.activity_identify;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        StatusBarUtil.setTransparentForWindow(this);
        context = this;
        top.setText("院校鉴别");
    }

    @Override
    protected void initBeforeData() {


    }


    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        Log.i("胡勇江", topContributor);
                if (position == 1) {
                JSONObject jsonobjs;
                try {
                    jsonobjs = new JSONObject(topContributor);
                    String errno = jsonobjs.getString("msg");
                    if (errno.equals("true")) {
                        queryResults.setText("是真的学府");
                    } else {
                        queryResults.setText("这是假的学府");
                    }
                    if (errno.equals("1002")) {
                        showToastShort("学校不少于四个字");
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
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


    @OnClick({R.id.fanhui_my, R.id.submission_bnt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_my:
                finish();
                break;
            case R.id.submission_bnt:
                try {
                    p.functionMultiRequests(Api.URLs + "ideal.htm?", context, Api.SCHOOLIdentify(homepageFragmentSearchViews.getText().toString().trim()), 1);
                } catch (Exception e) {

                }
                break;

        }
    }

}
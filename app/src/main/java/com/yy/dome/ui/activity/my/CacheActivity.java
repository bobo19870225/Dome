package com.yy.dome.ui.activity.my;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.base.BaseActivity;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.DialogHelper;
import com.yy.dome.util.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by huyongjiang on 2017/11/27.
 * 清除缓存
 */
public class CacheActivity extends BaseActivity {


    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.altr_top_lay)
    LinearLayout altrTopLay;
    @BindView(R.id.cache_text)
    TextView cacheText;
    @BindView(R.id.cache_submit)
    TextView cacheSubmit;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;

    @Override
    protected int setMainLayout() {
        return R.layout.cache_layout;
    }

    @Override
    protected void initView() {
        top.setText("清除缓存");
        StatusBarUtil.setTransparentForWindow(this);
        cacheText.setText("缓存大小:" + UtilTools.showRandom() + "MB");
    }

    @Override
    protected void initBeforeData() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fanhui_lay, R.id.cache_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.cache_submit:
                final ProgressDialog progressDialog = DialogHelper.getWaitDialog(this, "清除中...");
                progressDialog.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        cacheText.setText("缓存大小:0MB");
                        progressDialog.dismiss();
                    }
                }, 2000);
                break;
        }
    }

}

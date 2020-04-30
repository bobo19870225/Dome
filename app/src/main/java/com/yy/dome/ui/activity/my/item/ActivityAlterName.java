package com.yy.dome.ui.activity.my.item;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.yy.dome.widget.CommonEditText;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by huyongjiang on 2017/10/19.
 * 修改姓名
 */

public class ActivityAlterName extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right_name)
    TextView topRightName;
    @BindView(R.id.edit_name)
    CommonEditText editName;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;

    private Context context;
    private Intent intent;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_alter_name;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        top.setText("修改姓名");
        topRightName.setText("完成");
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
        JSONObject jsonobjs;
        try {
            jsonobjs = new JSONObject(topContributor);
            String error = jsonobjs.getString("ret");
            if (error.equals("0")) {
                UtilFileDB.ADDSHAREDDATA("zqname", editName.getText().toString());
                showToastShort("修改成功");
                setResult(3, intent);
                finish();
            } else {
                showToastShort(error);
            }

        } catch (JSONException e) {
            onError();
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

    }


    @OnClick({R.id.fanhui_lay, R.id.top_right_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.top_right_name:
                if (editName.getText().toString().length() < 1) {
                    showToastShort("请输入用户名");
                    return;
                }
                try {
                    p.functionMultiRequest(Api.URLs + "ideal.htm?", context, Api.USERNAME(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123),
                            editName.getText().toString()), 1);
                } catch (Exception e) {

                }

                break;
        }
    }
}

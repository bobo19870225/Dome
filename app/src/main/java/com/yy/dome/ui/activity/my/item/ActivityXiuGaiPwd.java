package com.yy.dome.ui.activity.my.item;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.my.LoginActivity;
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
 * Created by huyongjiang on 2020/3/10.
 * 修改密码
 */

public class ActivityXiuGaiPwd extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right_text)
    TextView topRight;
    @BindView(R.id.jiumima)
    CommonEditText jiumima;
    @BindView(R.id.xinmima)
    CommonEditText xinmima;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;

    private Context context;

    @Override
    protected int setMainLayout() {
        return R.layout.vip_xiugaimima;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        top.setText("修改密码");
        topRight.setText("完成");
        StatusBarUtil.setTransparentForWindow(this);
        jiumima.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        xinmima.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        jiumima.setHint("请输入原密码");
        xinmima.setHint("请输入新密码");
    }

    @Override
    protected void initBeforeData() {
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        Log.e("失败", topContributor);
        if (position == 1) {
            Log.e("top", topContributor);
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(topContributor);
                String errno = jsonobjs.getString("ret");
                if (errno.equals("0")) {
                    showToastShort("修改成功");
                    Intent intent=new Intent(context, LoginActivity.class);
                    startActivity(intent);
                }
                if (errno.equals("1")) {
                    showToastShort("原密码输入错误");
                }
                if (errno.equals("3")) {
                    showToastShort("验证码输入错误");
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

    }

    @OnClick({R.id.fanhui_lay, R.id.top_right_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.top_right_text:
                if (jiumima.getText().toString().length() < 6) {
                    showToastShort("请输入原密码");
                    return;
                }
                if (xinmima.getText().toString().length() < 6) {
                    showToastShort("请输入新密码");
                    return;
                }
                p.functionMultiRequest(Api.URLs + "student.htm?", context, Api.USERPWD(UtilFileDB.Tel(), UtilTools.MD5(jiumima.getText().toString()),
                        UtilTools.MD5(xinmima.getText().toString())), 1);
                break;
        }
    }


}
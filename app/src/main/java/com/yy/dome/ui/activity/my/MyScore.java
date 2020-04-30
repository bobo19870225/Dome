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

import androidx.core.content.ContextCompat;

import com.alibaba.fastjson.JSON;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.DemoInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.MainFragment;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyScore extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    @BindView(R.id.target_score)
    EditText targetScore;
    @BindView(R.id.my_rank)
    EditText myRank;
    @BindView(R.id.determine)
    Button determine;
    @BindView(R.id.science)
    TextView science;
    @BindView(R.id.liberal_arts)
    TextView liberalArts;
    @BindView(R.id.wlk)
    TextView wlk;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;

    private Intent intent;
    Context context;
    String score;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_my_score;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        top.setText("添加分数");
        aCache = ACache.get(this);
        context = this;
        score = getIntent().getStringExtra("mcore");
        StatusBarUtil.setTransparentForWindow(this);
    }

    @Override
    protected void initBeforeData() {
        setBottomTextColor(0);
        try {
            p.functionMultiRequest(Api.URLs + "ideal.htm?", context, Api.showUpdateImageData(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)), 2);
        } catch (Exception e) {

        }
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        Log.e("data", topContributor);
        if (position == 1) {
            DemoInfo Info = JSON.parseObject(topContributor, DemoInfo.class);
            if (Info.getRet() == 0) {
                showToastShort("添加成功");
                UtilFileDB.ADDSHAREDDATA("useruid", Info.getData().getId());
                UtilFileDB.ADDSHAREDDATA("zqname", Info.getData().getName());
                UtilFileDB.ADDSHAREDDATA("time", Info.getData().getLoginTime());
                UtilFileDB.ADDSHAREDDATA("tel", Info.getData().getPhone());
                UtilFileDB.ADDSHAREDDATA("score", Info.getData().getScore());
                UtilFileDB.ADDSHAREDDATA("school", Info.getData().getFollowSchool());
                UtilFileDB.ADDSHAREDDATA("rank", Info.getData().getRank());
                UtilFileDB.ADDSHAREDDATA("wlk", Info.getData().getWlk());
                // Log.e("修改成功后", Info.getData());
                Intent intent = new Intent();
                intent.putExtra("username", Info.getData().getName());
                setResult(2, intent);
                UtilFileDB.ADDFile(aCache, "addcart", "1");
                intent = new Intent(context, MainFragment.class);
                startActivity(intent);
            }
            if (Info.getRet() == 2001) {
                openActivity(LoginActivity.class);
            }
        }
        if (position == 2) {
            Log.e("tupian", topContributor);
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(topContributor);
                String errno = jsonobjs.getString("headImg");
                Log.e("errno", errno);
                UtilFileDB.ADDSHAREDDATA("image", errno);
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


    @OnClick({R.id.fanhui_lay, R.id.determine, R.id.science, R.id.liberal_arts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.science:
                setBottomTextColor(0);
                break;
            case R.id.liberal_arts:
                setBottomTextColor(1);

                break;
            case R.id.determine:
                try {
                    String sts = targetScore.getText().toString().trim();
                    int i;
                    i = Integer.valueOf(sts).intValue();
                    if (i < 751 && i > 0) {
                        p.functionMultiRequest(Api.URLs + "ideal.htm?", context, Api.updateScore(UtilFileDB.NAME(), UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123), targetScore.getText().toString().trim(),
                                wlk.getText().toString().trim(), myRank.getText().toString().trim()), 1);
                        return;
                    } else {
                        showToastShort("分数输入有误，请重新输入");
                    }
                } catch (Exception e) {
                    showToastShort("请输入目标分数");
                }



                break;
        }
    }

    private void setBottomTextColor(int position) {

        science.setTextColor(ContextCompat.getColor(context, R.color.colorGray));
        science.setBackgroundResource(R.drawable.border);
        liberalArts.setTextColor(ContextCompat.getColor(context, R.color.colorGray));
        liberalArts.setBackgroundResource(R.drawable.border);
        if (position == 0) {
            science.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            wlk.setText("理科");
            science.setBackgroundResource(R.drawable.selector_green_bg);
        } else {
            liberalArts.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            liberalArts.setBackgroundResource(R.drawable.selector_green_bg);
            wlk.setText("文科");
        }
    }
}
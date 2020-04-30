package com.yy.dome.ui.activity.school.form;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.android.tu.loadingdialog.LoadingDialog;
import com.google.gson.Gson;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.Groupsinfo;
import com.yy.dome.entity.PrestigiousSchoolInfo;
import com.yy.dome.entity.SchoolVolunteerInfo;
import com.yy.dome.entity.SchoolVolunteetInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.school.OnItemClickListenters;
import com.yy.dome.ui.activity.school.adapter.FormAdapterOne;
import com.yy.dome.ui.activity.school.adapter.SlidesAdapter;
import com.yy.dome.ui.activity.school.adapter.VolunteerAdapter;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;

import com.yy.dome.utils.ZQListView;
import com.yy.dome.view.IView;



import java.util.ArrayList;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FormBatchActivityOne extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
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
    @BindView(R.id.form_recyclerview)
    ZQListView formRecyclerview;
    private Intent intent;
    Context context;
    private FormAdapterOne adapter;

    ArrayList<FormLookUpInfo.DataBean.SubsBean> listdata = new ArrayList();//定义接收SUBbean数组的值
    ArrayList<FormLookUpInfo.DataBean> listdataOne = new ArrayList();//定义接收DATA数组的值
    @Override
    protected int setMainLayout() {
        return R.layout.activity_form_list;
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
        top.setText("贵州高考志愿表");
        showMyDialog();
        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", context, Api.LookUpFORMONE(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)), 1);
        } catch (Exception e) {
        }
    }

    @Override
    protected void initBeforeData() {



    }

    @Override
    public void onLoadContributorStart() {

    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        Log.e("志愿表展示接口", topContributor);
        FormLookUpInfo dataList = JSON.parseObject(topContributor,FormLookUpInfo.class);
        if (dataList.getData() != null && dataList.getData().size() >0){
            listdataOne.addAll(dataList.getData());
            adapter = new FormAdapterOne(this,listdataOne);
            formRecyclerview.setAdapter(adapter);
        }
        //好了


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
    protected void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.fanhui_lay)
    public void onViewClicked() {
        finish();
    }

    /**
     * 显示对话框
     *
     * @param
     */
    public void showMyDialog() {

        LoadingDialog.Builder builder = new LoadingDialog.Builder(context)
                .setMessage("加载中...")
                .setCancelable(false);
        final LoadingDialog dialog = builder.create();
        dialog.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                dialog.dismiss();
            }
        }, 1000);

    }
}
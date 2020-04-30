package com.yy.dome.ui.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.yy.dome.R;
import com.yy.dome.adapter.SchoolAdapter;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.home.SchoolInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.my.LoginActivity;
import com.yy.dome.ui.activity.school.SchoolDetails;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.yy.dome.widget.LinearLayoutForListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExpressActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {

    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.listview_find)
    LinearLayoutForListView listviewFind;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    private Context context;
    ACache aCache;
    private Intent intent;
    List<SchoolInfo.Data> list;
    SchoolAdapter adapter;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_find_school;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;
        top.setText("名校展馆");
        StatusBarUtil.setTransparentForWindow(this);
        try {
            p.functionMultiRequest(Api.URLs + "ideal.htm?", context, Api.SCHOOL(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)
            ), 1);
        } catch (Exception E) {
            showToastShort("网络加载失败");
        }

    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    protected void initBeforeData() {
        swiperefresh.setSize(SwipeRefreshLayout.LARGE);
        //设置刷新时动画的颜色，可以设置4个
        swiperefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                p.functionMultiRequest(Api.URLs + "ideal.htm?", context, Api.SCHOOL(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)
                ), 1);
                showToastLong("刷新成功");
            }
        });
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        Log.e("top", topContributor);
        if (position == 1) {
            SchoolInfo info = JSON.parseObject(topContributor, SchoolInfo.class);
            if (info.getRet() == 0) {
                if (info.getData().size() > 0) {
                    showListData(info.getData());
                }
                if (info.getRet() == 2001) {
                    openActivity(LoginActivity.class);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            swiperefresh.setRefreshing(false);
                        } catch (Exception e) {

                        }
                    }
                }, 2500);//刷新时间
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

    private void showListData(final List<SchoolInfo.Data> listview) {

        adapter = new SchoolAdapter(context, listview);

        listviewFind.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(context, SchoolDetails.class);
                intent.putExtra("id", listview.get(position).getId());
                Log.e("id", listview.get(position).getId());
                startActivity(intent);
            }
        });
        listviewFind.setAdapter(adapter);
    }

    @OnClick(R.id.fanhui_lay)
    public void onViewClicked() {
        finish();
    }
}

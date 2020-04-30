package com.yy.dome.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.yy.dome.R;
import com.yy.dome.adapter.OnItemClickListenter;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.home.video.ExpertVideoInfo;
import com.yy.dome.ui.activity.school.adapter.ExpertVideoActivity;
import com.yy.dome.ui.activity.school.adapter.ExpertVideoAdapter;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FundFragment extends BasenFragment<IView, MultiFunctionPresenter> implements IView {

    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.expert_recyclerview)
    RecyclerView expertRecyclerview;
    Intent intent;
    Context context;
    ExpertVideoAdapter adapter;

    String id, goodid;

    ArrayList<ExpertVideoInfo> videolist = new ArrayList();
    @BindView(R.id.ry_ela1)
    RelativeLayout ryEla1;


    @Override
    protected int setMainLayout() {
        return R.layout.fragment_fund;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        context = getContext();
        top.setText("专家咨询");

        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", getActivity(), Api.SCHOOLVIDEO(UtilFileDB.Tel(),
                    UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)), 1);

        }catch (Exception e){

        }

    }

    @Override
    protected void initBeforeData() {



    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {


        if (position == 1) {
            Log.i("视频地址", topContributor);
            List<ExpertVideoInfo> dataList = new Gson().fromJson(topContributor, new TypeToken<List<ExpertVideoInfo>>() {
            }.getType());
            for (int i = 0; i < dataList.size(); i++) {
                videolist.add(dataList.get(i));//给适配添加数据
                id = dataList.get(i).getId();

            }
            schoolvideo();
        }

    }

    @Override
    public void onNetWork() {
        showToastShort("网络未连接");
    }

    @Override
    public void onError() {
    }

    @Override
    public void onLoadContributorStart() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;

    }

    private void schoolvideo() {

        LinearLayoutManager m = new LinearLayoutManager(getActivity());
        m.setOrientation(LinearLayoutManager.VERTICAL);
        expertRecyclerview.setLayoutManager(m);
        expertRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        adapter = new ExpertVideoAdapter(context, videolist);
        expertRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListenter(new OnItemClickListenter() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("是否可以观看", videolist.get(position).getFlag());
                UtilFileDB.ADDSHAREDDATA("flag", videolist.get(position).getFlag());
                intent = new Intent(context, ExpertVideoActivity.class);
                intent.putExtra("id", videolist.get(position).getId());
                intent.putExtra("goodid", videolist.get(position).getGoodsId());
                UtilFileDB.ADDSHAREDDATA("goodid", videolist.get(position).getGoodsId());
                intent.putExtra("URL", videolist.get(position).getUrl());
                intent.putExtra("name", videolist.get(position).getName());
                startActivity(intent);

            }
        });
    }


}
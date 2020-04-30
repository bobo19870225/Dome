package com.yy.dome.ui.fragment;

import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import android.widget.ListView;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.alibaba.fastjson.JSON;
import com.yy.dome.R;
import com.yy.dome.adapter.MyAdapter;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.entity.home.ArticleInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.details.ArticleDetails;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/27.
 */

public class NewsTabFragment extends BasenFragment<IView, MultiFunctionPresenter> implements IView{
    MyAdapter adapter;
    List<ArticleInfo.Data> list;
    String position;
    Handler mHandler;
    @BindView(R.id.tab_listview)
    ListView tabListview;
    Intent intent;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swiperefresh;
    private  View view;


    public static Fragment newInstance(String position) {
        NewsTabFragment fragment = new NewsTabFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    protected int setMainLayout() {
        return R.layout.news_tab_listview;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        view=getView();
        try {
            Bundle bundle = getArguments();
            position = (String) bundle.getSerializable("position");
            p.functionMultiRequest(Api.URLs + "ideal.htm?", getActivity(), Api.ARTICLES(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token()+UtilFileDB.Tel()+123), position), 1);
            mHandler = new Handler();
        } catch (Exception e) {
        }

        StatusBarUtil.setTransparentForWindow(getActivity());

    }
    @Override
    protected void initBeforeData() {
        //设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        // swipeRefreshLayout.setProgressViewOffset(true, 5, 50);
        //设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        swiperefresh.setSize(SwipeRefreshLayout.LARGE);
        //设置刷新时动画的颜色，可以设置4个
        swiperefresh.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                try {
                    p.functionMultiRequest(Api.URLs + "ideal.htm?", getActivity(), Api.ARTICLES(UtilFileDB.Tel(), UtilFileDB.Token(), position), 1);
                    showToastShort("刷新成功");
                }catch (Exception e){

                }
            }
        });
    }

    @Override
    public void onPause() {
        p.detachView();
        super.onPause();
    }


    @Override
    public void onNetWork() {
        showToastShort(R.string.no_network);
    }

    @Override
    public void onError() {
        showToastShort(R.string.error_no_data);
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {

        try {
            ArticleInfo newsListInfo = JSON.parseObject(topContributor, ArticleInfo.class);
            if (newsListInfo.getData().size() > 0) {
                if (position == 1) {
                    list = newsListInfo.getData();
                    show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                swiperefresh.setRefreshing(false);
                            } catch (Exception e) {

                            }
                        }
                    }, 2500);//刷新时间
                } else {
                    list.addAll(newsListInfo.getData());
                    adapter.notifyDataSetChanged();
                }
            } else {
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            onError();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    private void show() {
        adapter = new MyAdapter(list, getActivity());
        tabListview.setAdapter(adapter);
        tabListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    intent = new Intent(getContext(), ArticleDetails.class);
                    intent.putExtra("articleid", list.get(position).getId());
                    startActivity(intent);
                } catch (Exception e) {

                }
            }
        });
    }
}
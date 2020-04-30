package com.yy.dome.ui.fragment;

import android.content.Intent;
import android.graphics.Outline;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson.JSON;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.entity.ArticleINFO;
import com.yy.dome.entity.home.ArticleInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.details.ArticleDetails;
import com.yy.dome.util.GlideImageLoader;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServiceFragment extends BasenFragment<IView, MultiFunctionPresenter> implements IView {

    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.tab_viewpager)
    ViewPager tabViewpager;
    private List<ArticleINFO.Data> list;
    Fragment[] mFragmentArrays;
    private View view;
    Intent intent;


    @Override
    protected int setMainLayout() {
        return R.layout.fragment_service;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        view = getView();
        StatusBarUtil.setTransparentForWindow(getActivity());
        //showPermissions(permissions);
        p.functionMultiRequest(Api.URLs + "ideal.htm?", getActivity(), Api.TOPNEWS(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)), 1);
        p.functionMultiRequest(Api.URLs + "ideal.htm?", getActivity(), Api.lunbo(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123)), 2);


    }

    @Override
    protected void initBeforeData() {


    }

    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }

        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // return "";//b!=null? b.getData().get(position).getName() : DataList.mTabTitles[position]
            return list != null ? list.get(position).getName() : null;
        }

    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        Log.i("top", topContributor);
        if (position == 1) {
            ArticleINFO CatInfo = JSON.parseObject(topContributor, ArticleINFO.class);
            if (CatInfo != null) {
                list = CatInfo.getData();
            }
            try {
                tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);//MODE_FIXED  MODE_SCROLLABLE
                int length = list != null ? list.size() : null;
                mFragmentArrays = new Fragment[length];
                for (int i = 0; i < length; i++) {
                    mFragmentArrays[i] = NewsTabFragment.newInstance(list.get(i).getId());

                }
                PagerAdapter pagerAdapter = new MyViewPagerAdapter(getChildFragmentManager());
                tabViewpager.setAdapter(pagerAdapter);
                tablayout.setupWithViewPager(tabViewpager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (position==2){
            ArticleInfo info = JSON.parseObject(topContributor, ArticleInfo.class);

            final List<ArticleInfo.Data> data = info.getData();
            Banner banner = (Banner) view.findViewById(R.id.banner);
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(data);
            banner.start();

            banner.setOutlineProvider(new ViewOutlineProvider() {
                @Override
                public void getOutline(View view, Outline outline) {
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 12);
                }
            });

            banner.setClipToOutline(true);
            //增加点击事件
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    intent = new Intent(getContext(), ArticleDetails.class);
                    intent.putExtra("articleid", data.get(position).getId());
                    Log.e("aid",data.get(position).getId());
                    startActivity(intent);
                }
            });
        }

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        tabViewpager = null;
        tablayout = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


}

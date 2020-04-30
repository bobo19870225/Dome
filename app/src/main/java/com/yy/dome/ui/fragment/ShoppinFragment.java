package com.yy.dome.ui.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.yy.dome.R;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.ui.activity.answer.AnswerActivity;
import com.yy.dome.ui.activity.answer.AnswerActivitys;
import com.yy.dome.ui.activity.answer.OkActivity;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShoppinFragment extends BasenFragment<IView, MultiFunctionPresenter> implements IView {


    Intent intent;
    ACache aCache;
    Context context;
    @BindView(R.id.start)
    TextView start;
    @BindView(R.id.see)
    TextView see;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.ry_ela1)
    RelativeLayout ryEla1;
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;

    @Override
    protected int setMainLayout() {
        return R.layout.fragment_volunteer;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }


    @Override
    protected void initView() {

        aCache = ACache.get(getActivity());
        StatusBarUtil.setTransparentForWindow(getActivity());
        //StatusBarUtil.setTranslucentForImageViewInFragment(getActivity(), null);
        //setTvTitleBackgroundColor(Color.TRANSPARENT);
        aCache = ACache.get(getActivity());
        context = getActivity();
        top.setText("性格测评");
    }

    @Override
    protected void initBeforeData() {

    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {

    }

    @Override
    public void onNetWork() {

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

    @OnClick({R.id.start, R.id.see})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.start:
                startActivity(new Intent(context, AnswerActivitys.class));
                break;
            case R.id.see:

                if (UtilFileDB.ANSWER()) {
                    intent = new Intent(context, AnswerActivitys.class);
                    startActivity(intent);
                } else {
                    try {
                        int[] numbersnn={Integer.parseInt(UtilFileDB.xsCount())  ,Integer.parseInt(UtilFileDB.yjCount())
                                ,Integer.parseInt(UtilFileDB.ysCount()) ,Integer.parseInt(UtilFileDB.shCount())
                                ,Integer.parseInt(UtilFileDB.qyCount()) ,Integer.parseInt(UtilFileDB.cgCount())
                        };
                        intent = new Intent(context, OkActivity.class);
                        intent.putExtra("stringType", UtilFileDB.stringType());
                        intent.putExtra("nuu",numbersnn);
                        startActivity(intent);
                    }catch (Exception e){

                    }

                }

                break;
        }
    }


}
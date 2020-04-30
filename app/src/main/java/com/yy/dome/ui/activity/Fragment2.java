package com.yy.dome.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.tu.loadingdialog.LoadingDialog;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.entity.BeanInfoS;
import com.yy.dome.entity.PrestigiousSchoolInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.ACache;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 胡勇江 on 2016/11/12.
 */


public class Fragment2 extends BasenFragment<IView, MultiFunctionPresenter> implements IView {

    Intent intent;
    ACache aCache;
    Context context;
    @BindView(R.id.tablelayout)
    TableLayout tablelayout;


    String zgxc, zdxc;
    String enrollNumSurgeVal;//录取人数波动值：
    String batchLineSurgeVal;//批次线波动值：
    String rankSurgeVal;//最低位次波动值：
    float enrollCoefficient;//录取系数：
    String lineDifCoefficient;//线差系数：
    float rankCoefficient;//位次系数：
    @BindView(R.id.text_shu)
    TextView textShu;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_wlk)
    TextView textWlk;
    @BindView(R.id.ry_main1)
    RelativeLayout ryMain1;
    @BindView(R.id.tabrw)
    TableRow tabrw;
    @BindView(R.id.school_admission)
    TextView schoolAdmission;
    @BindView(R.id.luqu_year1)
    TextView luquYear1;
    @BindView(R.id.luqu_year2)
    TextView luquYear2;
    @BindView(R.id.luqu_year3)
    TextView luquYear3;
    @BindView(R.id.view_text)
    TextView viewText;
    @BindView(R.id.school_batch)
    TextView schoolBatch;
    @BindView(R.id.batch_year1)
    TextView batchYear1;
    @BindView(R.id.batch_year2)
    TextView batchYear2;
    @BindView(R.id.batch_year3)
    TextView batchYear3;
    @BindView(R.id.highest_xc)
    TextView highestXc;
    @BindView(R.id.zgxc_year1)
    TextView zgxcYear1;
    @BindView(R.id.zgxc_year2)
    TextView zgxcYear2;
    @BindView(R.id.zgxc_year3)
    TextView zgxcYear3;
    @BindView(R.id.highest_score)
    TextView highestScore;
    @BindView(R.id.zgf_year1)
    TextView zgfYear1;
    @BindView(R.id.zgf_year2)
    TextView zgfYear2;
    @BindView(R.id.zgf_year3)
    TextView zgfYear3;
;
    @BindView(R.id.minimum_score)
    TextView minimumScore;
    @BindView(R.id.minimum_year1)
    TextView minimumYear1;
    @BindView(R.id.minimum_year2)
    TextView minimumYear2;
    @BindView(R.id.minimum_year3)
    TextView minimumYear3;
    @BindView(R.id.minimum_xc)
    TextView minimumXc;
    @BindView(R.id.zdxc_year1)
    TextView zdxcYear1;
    @BindView(R.id.zdxc_year2)
    TextView zdxcYear2;
    @BindView(R.id.zdxc_year3)
    TextView zdxcYear3;
    private ArrayList<BeanInfoS.Data> mItemNameList = new ArrayList<>();
    int a, b;
    String id;

    @Override
    protected int setMainLayout() {
        return R.layout.fragment1;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }


    @Override
    protected void initView() {
        aCache = ACache.get(getActivity());
        context = getActivity();

        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", getActivity(), Api.SCHOOLDATA(UtilFileDB.SCHOOLid(), UtilFileDB.Tel()), 2);
        }catch (Exception e){

        }
    }


    @Override
    protected void initBeforeData() {

    }


    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {

            BeanInfoS Info = JSON.parseObject(topContributor, BeanInfoS.class);
            ArrayList<BeanInfoS.Data> ab = new ArrayList();//2016
            ArrayList<BeanInfoS.Data> abc = new ArrayList();//2017
            ArrayList<BeanInfoS.Data> abcd = new ArrayList();//2018
            //遍历数组
            for (int i = 0; i < Info.getData().size(); i++) {
                if (Info.getData().get(i).getYear().equals("2016")) {
                    ab.add(Info.getData().get(i));
                }
                if (Info.getData().get(i).getYear().equals("2017")) {
                    abc.add(Info.getData().get(i));
                }
                if (Info.getData().get(i).getYear().equals("2018")) {
                    abcd.add(Info.getData().get(i));
                }
                if (Info.getData().get(i).getWlk().equals("1")){
                    textWlk.setText("贵州省理科");
                }else {
                    textWlk.setText("贵州省文科");
                }

            }
            mItemNameList.add(ab.get(0));
            mItemNameList.add(abc.get(0));
            mItemNameList.add(abcd.get(0));
            zgxc = String.valueOf(Info.getData().get(position).getMaxScore() - Info.getData().get(position).getBatchLine());
            zdxc = String.valueOf(Info.getData().get(position).getMinScore() - Info.getData().get(position).getBatchLine());
            enrollNumSurgeVal = String.valueOf(abcd.get(0).getEnrollNumber() - abc.get(0).getEnrollNumber());
            batchLineSurgeVal = String.valueOf(abcd.get(0).getBatchLine() - abc.get(0).getBatchLine());
            rankSurgeVal = String.valueOf(abcd.get(0).getRanking() - abc.get(0).getRanking());
            a = Integer.parseInt(enrollNumSurgeVal);
            b = Integer.parseInt(rankSurgeVal);
            Integer str = new Integer(a);
            Integer strs = new Integer(abcd.get(0).getEnrollNumber());
            int i = str.intValue();
            int j = strs.intValue();
            float rr = (float) i;
            float rrs = (float) j;

            Integer s = new Integer(b);
            Integer ss = new Integer(abcd.get(0).getRanking());
            int g = s.intValue();
            int h = ss.intValue();
            float w = (float) g;
            float q = (float) h;
            enrollCoefficient = rr / rrs;
            rankCoefficient = w / q;

            luquYear1.setText(String.valueOf(abcd.get(0).getEnrollNumber()));
            luquYear2.setText(String.valueOf(abc.get(0).getEnrollNumber()));
            luquYear3.setText(String.valueOf(ab.get(0).getEnrollNumber()));
            batchYear1.setText(String.valueOf(abcd.get(0).getBatchLine()));
            batchYear2.setText(String.valueOf(abc.get(0).getBatchLine()));
            batchYear3.setText(String.valueOf(ab.get(0).getBatchLine()));
            zgxcYear1.setText(String.valueOf(abcd.get(0).getMaxScore() - abcd.get(0).getBatchLine()));
            zgxcYear2.setText(String.valueOf(abc.get(0).getMaxScore() - abc.get(0).getBatchLine()));
            zgxcYear3.setText(String.valueOf(ab.get(0).getMaxScore() - ab.get(0).getBatchLine()));
            zdxcYear1.setText(String.valueOf(abcd.get(0).getMinScore() - abcd.get(0).getBatchLine()));
            zdxcYear2.setText(String.valueOf(abc.get(0).getMinScore() - abc.get(0).getBatchLine()));
            zdxcYear3.setText(String.valueOf(ab.get(0).getMinScore() - ab.get(0).getBatchLine()));
            zgfYear1.setText(String.valueOf(abcd.get(0).getMaxScore()));
            zgfYear2.setText(String.valueOf(abc.get(0).getMaxScore()));
            zgfYear3.setText(String.valueOf(ab.get(0).getMaxScore()));
            minimumYear1.setText(String.valueOf(abcd.get(0).getMinScore()));
            minimumYear2.setText(String.valueOf(abc.get(0).getMinScore()));
            minimumYear3.setText(String.valueOf(ab.get(0).getMinScore()));

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




}

package com.yy.dome.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.tu.loadingdialog.LoadingDialog;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.entity.BeanInfoS;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.util.ACache;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by 胡勇江 on 2016/11/12.
 */

public class Fragment1 extends BasenFragment<IView, MultiFunctionPresenter> implements IView {

    Intent intent;
    ACache aCache;
    Context context;
    String id;

    float enrollCoefficient1;//录取系数：
    float lineDifCoefficient1;//线差系数：
    float rankCoefficient1;//位次系数：

    int a, b, c;
    @BindView(R.id.lq_zg_score)
    TextView lqZgScore;
    @BindView(R.id.lq_zd_score)
    TextView lqZdScore;
    @BindView(R.id.school_enrollratio)
    TextView schoolEnrollratio;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.tabrw)
    TableRow tabrw;
    @BindView(R.id.tablelayout)
    TableLayout tablelayout;
    @BindView(R.id.ry_main1)
    LinearLayout ryMain1;
    @BindView(R.id.highest_xc)
    TextView highestXc;
    @BindView(R.id.highest_score)
    TextView highestScore;
    @BindView(R.id.lqxs_year1)
    TextView lqxsYear1;
    @BindView(R.id.xcfs_year1)
    TextView xcfsYear1;
    @BindView(R.id.wcxs_year1)
    TextView wcxsYear1;
    @BindView(R.id.school_average)
    TextView schoolAverage;
    @BindView(R.id.lqxs_year2)
    TextView lqxsYear2;
    @BindView(R.id.xcfs_year2)
    TextView xcfsYear2;
    @BindView(R.id.wcxs_year2)
    TextView wcxsYear2;
    @BindView(R.id.minimum_score)
    TextView minimumScore;
    @BindView(R.id.lqxs_year3)
    TextView lqxsYear3;
    @BindView(R.id.xcfs_year3)
    TextView xcfsYear3;
    @BindView(R.id.wcxs_year3)
    TextView wcxsYear3;

    private List<BeanInfoS.Data> datas;//总的数据源

    @Override
    protected int setMainLayout() {
        return R.layout.fragment;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }


    @Override
    protected void initView() {
        aCache = ACache.get(getActivity());
        context = getActivity();
    }


    @Override
    protected void initBeforeData() {
        tablelayout.setStretchAllColumns(true);

        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", getActivity(), Api.SCHOOLDATA(UtilFileDB.SCHOOLid(), UtilFileDB.Tel()), 2);
        } catch (Exception e) {

        }

    }


    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        if (position == 2) {
            BeanInfoS Info = JSON.parseObject(topContributor, BeanInfoS.class);
            datas = Info.getData();
            //遍历数组
            boolean flag = true;
            for (int i = 0; i < Info.getData().size(); i++) {

                if (i + 1 < Info.getData().size()) {

                    TableRow tr = new TableRow(getContext());
                    tr.setLayoutParams(new TableRow.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));

                        TextView t = new TextView(getContext());
                        t.setText(Info.getData().get(i).getYear());
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setTextSize(10);
                        tr.addView(t);//年份

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getEnrollNumber()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setTextSize(10);
                        tr.addView(t);//录取人数

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getEnrollNumber() - Info.getData().get(i + 1).getEnrollNumber()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setTextSize(10);
                        tr.addView(t);//录取人数波动值

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getMinScore() - Info.getData().get(i).getBatchLine()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextColor(Color.BLUE);
                        t.setTextSize(10);
                        tr.addView(t);//最低线差

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getBatchLine() - Info.getData().get(i + 1).getBatchLine()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextColor(Color.BLUE);
                        t.setTextSize(10);
                        tr.addView(t);//最低线差波动值

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getRanking()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextColor(Color.BLUE);
                        t.setTextSize(10);
                        tr.addView(t);//最低位次

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getRanking() - Info.getData().get(i + 1).getRanking()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextColor(Color.BLUE);
                        t.setTextSize(10);
                        tr.addView(t);//最低位次波动值


                    a = Integer.parseInt(String.valueOf(Info.getData().get(i).getEnrollNumber() - Info.getData().get(i + 1).getEnrollNumber()));
                    b = Integer.parseInt(String.valueOf(Info.getData().get(i).getRanking() - Info.getData().get(i + 1).getRanking()));
                    c = Integer.parseInt(String.valueOf(Info.getData().get(i).getBatchLine() - Info.getData().get(i + 1).getBatchLine()));

                    Integer str = new Integer(a);
                    Integer strs = new Integer(Info.getData().get(i).getEnrollNumber());
                    int h = str.intValue();
                    int c = strs.intValue();
                    float rr = (float) h;
                    float rrs = (float) c;


                    enrollCoefficient1 = rr / rrs;//录取系数


                    Integer xs = new Integer(b);
                    Integer xss = new Integer(Info.getData().get(i).getRanking());
                    int xg = xs.intValue();
                    int xh = xss.intValue();
                    float w = (float) xg;
                    float q = (float) xh;

                    rankCoefficient1 = w / q;//位次系数

                    Integer sc = new Integer(c);
                    Integer ssc = new Integer(Info.getData().get(i).getRanking());
                    int gc = sc.intValue();
                    int hc = ssc.intValue();
                    float wc = (float) gc;
                    float qc = (float) hc;

                    lineDifCoefficient1 = wc / qc;//线差系数

                    tablelayout.addView(tr);
                    final View vline = new View(getContext());
                    vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
                    vline.setBackgroundColor(Color.BLACK);
                    tablelayout.addView(vline);

                } else {
                    TableRow tr = new TableRow(getContext());
                    tr.setLayoutParams(new TableRow.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));


                        TextView t = new TextView(getContext());
                        t.setText(Info.getData().get(i).getYear());
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextSize(10);
                        tr.addView(t);//年份

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getEnrollNumber()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextSize(10);
                        tr.addView(t);//录取人数

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getEnrollNumber() - Info.getData().get(i).getEnrollNumber()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextColor(Color.BLUE);
                        t.setTextSize(10);
                        tr.addView(t);//录取人数波动值

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getMinScore() - Info.getData().get(i).getBatchLine()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextColor(Color.BLUE);
                        t.setTextSize(10);
                        tr.addView(t);//最低线差

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getBatchLine() - Info.getData().get(i).getBatchLine()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextColor(Color.BLUE);
                        t.setTextSize(10);
                        tr.addView(t);//最低线差波动值

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getRanking()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextColor(Color.BLUE);
                        t.setTextSize(10);
                        tr.addView(t);//最低位次

                        t = new TextView(getContext());
                        t.setText(String.valueOf(Info.getData().get(i).getRanking() - Info.getData().get(i).getRanking()));
                        t.setGravity(Gravity.CENTER_HORIZONTAL);
                        t.setPadding(5, 5, 5, 5);
                        t.setTextColor(Color.BLUE);
                        t.setTextSize(10);
                        tr.addView(t);//最低位次波动值


                        tablelayout.addView(tr);
                        final View vline = new View(getContext());
                        vline.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT));
                        vline.setBackgroundColor(Color.BLACK);

                        tablelayout.addView(vline);
                        break;


                }

            }


            schoolEnrollratio.setText("录取率："+UtilFileDB.Schoolenrollratio()+"%");
            lqZgScore.setText("录取分数：" + UtilFileDB.SchoolMaxScore());
            lqZdScore.setText("~" + UtilFileDB.SchoolMinScore());

/*
            zdxc = String.valueOf(Info.getData().get(position).getMinScore() - Info.getData().get(position).getBatchLine());
            enrollNumSurgeVal = String.valueOf(abcd.get(0).getEnrollNumber() - abc.get(0).getEnrollNumber());
            batchLineSurgeVal = String.valueOf(abcd.get(0).getBatchLine() - abc.get(0).getBatchLine());
            rankSurgeVal = String.valueOf(abcd.get(0).getRanking() - abc.get(0).getRanking());

            a = Integer.parseInt(String.valueOf(abcd.get(0).getEnrollNumber() - abc.get(0).getEnrollNumber()));
            b = Integer.parseInt(String.valueOf(abcd.get(0).getRanking() - abc.get(0).getRanking()));
            c =Integer.parseInt(String.valueOf(abcd.get(0).getBatchLine() - abc.get(0).getBatchLine()));
            Integer str = new Integer(a);
            Integer strs = new Integer(abcd.get(0).getEnrollNumber());
            int i = str.intValue();
            int j = strs.intValue();
            float rr = (float) i;
            float rrs = (float) j;

            aa = Integer.parseInt(String.valueOf(abc.get(0).getEnrollNumber() - ab.get(0).getEnrollNumber()));
            Integer aaa = new Integer(aa);
            Integer aaaa = new Integer(abc.get(0).getEnrollNumber());
            int ii = aaa.intValue();
            int jj = aaaa.intValue();
            float rrr = (float) ii;
            float rrrs = (float) jj;

            bb = Integer.parseInt(String.valueOf(abc.get(0).getRanking() - ab.get(0).getRanking()));
            Integer bbs = new Integer(bb);
            Integer bbss = new Integer(abc.get(0).getRanking());
            int gg = bbs.intValue();
            int hh = bbss.intValue();
            float ww = (float) gg;
            float qq = (float) hh;

            Integer s = new Integer(b);
            Integer ss = new Integer(abcd.get(0).getRanking());
            int g = s.intValue();
            int h = ss.intValue();
            float w = (float) g;
            float q = (float) h;

            Integer sc = new Integer(c);
            Integer ssc = new Integer(abcd.get(0).getRanking());
            int gc = sc.intValue();
            int hc = ssc.intValue();
            float wc = (float) gc;
            float qc = (float) hc;

            Integer sc2 = new Integer(cc);
            Integer ssc2 = new Integer(abcd.get(0).getRanking());
            int gc2 = sc2.intValue();
            int hc2 = ssc2.intValue();
            float wc2 = (float) gc2;
            float qc2 = (float) hc2;

            //录取系数
            enrollCoefficient1 = rr / rrs;
            enrollCoefficient2 = rrr / rrrs;
            //位次系数
            rankCoefficient1 = w / q;
            rankCoefficient2 = ww / qq;
            //线差系数
            lineDifCoefficient1=wc /qc;
            lineDifCoefficient2=wc2 /hc2;

            schoolEnrollratio.setText("录取率："+UtilFileDB.Schoolenrollratio()+"%");
            lqZgScore.setText("录取分数：" + UtilFileDB.SchoolMaxScore());
            lqZdScore.setText("~" + UtilFileDB.SchoolMinScore());

            lqrsYear1.setText(String.valueOf(ab.get(0).getEnrollNumber()));
            lqrsYear2.setText(String.valueOf(abc.get(0).getEnrollNumber()));
            lqrsYear3.setText(String.valueOf(abcd.get(0).getEnrollNumber()));

            zdxcYear1.setText(String.valueOf(ab.get(0).getMinScore() - abcd.get(0).getBatchLine()));
            zdxcYear2.setText(String.valueOf(abc.get(0).getMinScore() - abc.get(0).getBatchLine()));
            zdxcYear3.setText(String.valueOf(abcd.get(0).getMinScore() - ab.get(0).getBatchLine()));



            lqbdYear1.setText("--");
            lqbdYear2.setText(String.valueOf(abc.get(0).getEnrollNumber() - ab.get(0).getEnrollNumber()));
            lqbdYear3.setText(String.valueOf(abcd.get(0).getEnrollNumber() - abc.get(0).getEnrollNumber()));
            zdxcBdYear1.setText("--");
            zdxcBdYear2.setText(String.valueOf(abc.get(0).getBatchLine() - ab.get(0).getBatchLine()));
            zdxcBdYear3.setText(String.valueOf(abcd.get(0).getBatchLine() - abc.get(0).getBatchLine()));
            zdwcBdYear1.setText("--");
            zdwcBdYear2.setText(String.valueOf(abc.get(0).getRanking() - ab.get(0).getRanking()));
            zdwcBdYear3.setText(String.valueOf(abcd.get(0).getRanking() - abc.get(0).getRanking()));

            xcfsYear1.setText("--");
            xcfsYear2.setText(String.valueOf(abc.get(0).getRanking() - ab.get(0).getRanking()));
            xcfsYear3.setText(String.valueOf(abcd.get(0).getRanking() - abc.get(0).getRanking()));

            zdwcYear1.setText(String.valueOf(ab.get(0).getRanking()));
            zdwcYear2.setText(String.valueOf(abc.get(0).getRanking()));
            zdwcYear3.setText(String.valueOf(abcd.get(0).getRanking()));

            zdxcYear1.setText(String.valueOf(ab.get(0).getMinScore() - abcd.get(0).getBatchLine()));
            zdxcYear2.setText(String.valueOf(abc.get(0).getMinScore() - abc.get(0).getBatchLine()));
            zdxcYear3.setText(String.valueOf(abcd.get(0).getMinScore() - ab.get(0).getBatchLine()));

            lqxsYear1.setText("--");
            lqxsYear2.setText(String.valueOf(enrollCoefficient2));
            lqxsYear3.setText(String.valueOf(enrollCoefficient1));





            wcxsYear1.setText("--");
            wcxsYear2.setText(String.valueOf(rankCoefficient2));
            wcxsYear3.setText(String.valueOf(rankCoefficient1));*/

            // Log.e("录取人数波动值",String.valueOf(Info.getData().get(position).getEnrollNumber() - Info.getData().get(position).getEnrollNumber() ));
            // Log.e("录取系数",Info.getData().get(position).getEnrollNumber()/Info.getData().get(position).getEnrollNumber());
        }




/*                    Log.e("最高线差", zgxc);
                    Log.e("最低线差", zdxc);
                    Log.e("录取人数波动值", enrollNumSurgeVal);
                    Log.e("批次线波动值", batchLineSurgeVal);
                    Log.e("最低位次波动值", rankSurgeVal);
                    Log.e("录取系数", String.valueOf(enrollCoefficient1));
                    Log.e("位次系数", String.valueOf(rankCoefficient1));
                    Log.e("线差系数", String.valueOf(lineDifCoefficient1));*/

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

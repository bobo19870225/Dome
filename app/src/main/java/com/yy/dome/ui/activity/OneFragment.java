package com.yy.dome.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.tu.loadingdialog.LoadingDialog;
import com.yy.dome.R;
import com.yy.dome.adapter.ALeftAdapter;
import com.yy.dome.adapter.ARightAdapter;
import com.yy.dome.adapter.ARightTableAdapter;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.entity.BeanInfoS;
import com.yy.dome.entity.Stock;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.ACache;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.yy.dome.view.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OneFragment extends BasenFragment<IView, MultiFunctionPresenter> implements IView {

    Intent intent;
    ACache aCache;
    Context context;
    private String id;
    private float enrollCoefficient1;//录取系数：
    private float lineDifCoefficient1;//线差系数：
    private float rankCoefficient1;//位次系数：
    @BindView(R.id.lq_zg_score)
    TextView lqZgScore;
    @BindView(R.id.lq_zd_score)
    TextView lqZdScore;
    @BindView(R.id.school_enrollratio)
    TextView schoolEnrollratio;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.left_title_container)
    LinearLayout leftTitleContainer;
    @BindView(R.id.right_title_container)
    LinearLayout rightTitleContainer;
    @BindView(R.id.title_horsv)
    MyHorizontalScrollView titleHorsv;
    @BindView(R.id.left_container_listview)
    ListView leftContainerListview;
    @BindView(R.id.left_container)
    LinearLayout leftContainer;
    @BindView(R.id.right_container_listview)
    ListView rightContainerListview;
    @BindView(R.id.right_container)
    LinearLayout rightContainer;
    @BindView(R.id.content_horsv)
    MyHorizontalScrollView contentHorsv;
    @BindView(R.id.table_listviw)
    ListView tableListviw;


    //左侧固定一列数据适配
    private List<String> leftlList;
    //右侧数据适配
    private List<Stock> stockList;
    private String zgxc, zdxc;
    private String enrollNumSurgeVal;//录取人数波动值：
    private String luqurenshu;//录取人数：
    private String zdwc;//最低位次
    private String batchLineSurgeVal;//批次线波动值：
    private String rankSurgeVal;//最低位次波动值：
    private String enrollCoefficient;//录取系数：
    private String lineDifCoefficient;//线差系数：
    private String rankCoefficient;//位次系数：


    private List<StockInfo> dataview;

    @Override
    protected int setMainLayout() {
        return R.layout.table_activity;
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
        } catch (Exception e) {

        }
    }

    @Override
    protected void initBeforeData() {

    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        if (position == 2) {
            BeanInfoS Info = JSON.parseObject(topContributor, BeanInfoS.class);
            //遍历数组
            stockList = new ArrayList<>();
            dataview=new ArrayList<>();

            for (int i = 0; i < Info.getData().size(); i++) {
                if (i + 1 < Info.getData().size()) {
                    Integer str = new Integer(Integer.parseInt(String.valueOf(Info.getData().get(i).getEnrollNumber() - Info.getData().get(i + 1).getEnrollNumber())));
                    Integer strs = new Integer(Info.getData().get(i).getEnrollNumber());
                    int h = str.intValue();
                    int c = strs.intValue();
                    float rr = (float) h;
                    float rrs = (float) c;
                    enrollCoefficient1 = rr / rrs;//录取系数

                    Log.e("录取系数",String.valueOf(enrollCoefficient1));

                    Integer xs = new Integer(Integer.parseInt(String.valueOf(Info.getData().get(i).getRanking() - Info.getData().get(i + 1).getRanking())));
                    Integer xss = new Integer(Info.getData().get(i).getRanking());
                    int xg = xs.intValue();
                    int xh = xss.intValue();
                    float w = (float) xg;
                    float q = (float) xh;
                    rankCoefficient1 = w / q;//位次系数

                    Integer sc = new Integer(Integer.parseInt(String.valueOf(Info.getData().get(i).getBatchLine() - Info.getData().get(i + 1).getBatchLine())));
                    Integer ssc = new Integer(Info.getData().get(i).getRanking());
                    int gc = sc.intValue();
                    int hc = ssc.intValue();
                    float wc = (float) gc;
                    float qc = (float) hc;
                    lineDifCoefficient1 = wc / qc;//线差系数




                    zgxc = String.valueOf(Info.getData().get(i).getMaxScore() - Info.getData().get(i + 1).getBatchLine());//最高线差
                    zdxc = String.valueOf(Info.getData().get(i).getMinScore() - Info.getData().get(i + 1).getBatchLine());//最低线差
                    enrollNumSurgeVal = String.valueOf(Info.getData().get(i).getEnrollNumber() - Info.getData().get(i + 1).getEnrollNumber());//录取人数波动值
                    batchLineSurgeVal = String.valueOf(Info.getData().get(i).getBatchLine() - Info.getData().get(i + 1).getBatchLine());//批次线波动值
                    rankSurgeVal = String.valueOf(Info.getData().get(i).getRanking() - Info.getData().get(i + 1).getRanking());//最低位次波动值
                    luqurenshu = String.valueOf(Info.getData().get(i).getEnrollNumber());
                    zdwc = String.valueOf(Info.getData().get(i).getRanking());

                    try {
                        if (!TextUtils.isEmpty(String.valueOf(enrollCoefficient1)) && String.valueOf(enrollCoefficient1).length()>=6){
                            enrollCoefficient=String.valueOf(enrollCoefficient1).substring(0,6);
                        }
                    }catch (Exception E){

                    }
                    try {
                        if (!TextUtils.isEmpty(String.valueOf(lineDifCoefficient1)) && String.valueOf(lineDifCoefficient1).length()>=6){
                            lineDifCoefficient=String.valueOf(lineDifCoefficient1).substring(0,6);
                        }
                    }catch (Exception E){

                    }
                    try {
                        if (!TextUtils.isEmpty(String.valueOf(rankCoefficient1)) && String.valueOf(rankCoefficient1).length()>=6){
                            rankCoefficient=String.valueOf(rankCoefficient1).substring(0,6);
                        }
                    }catch (Exception E){

                    }

                    dataview.add(new StockInfo(Info.getData().get(i).getYear(),String.valueOf(enrollCoefficient),
                            String.valueOf(lineDifCoefficient),String.valueOf(rankCoefficient)));

                    stockList.add(new Stock(Info.getData().get(i).getYear(), luqurenshu, enrollNumSurgeVal, zdxc, batchLineSurgeVal, zdwc, rankSurgeVal, String.valueOf(Info.getData().get(i).getBatchLine())));

                } else {

                    Integer str = new Integer(String.valueOf(Info.getData().get(i).getEnrollNumber() - Info.getData().get(i).getEnrollNumber()));
                    Integer strs = new Integer(Info.getData().get(i).getEnrollNumber());
                    int h = str.intValue();
                    int c = strs.intValue();
                    float rr = (float) h;
                    float rrs = (float) c;
                    enrollCoefficient1 = rr / rrs;//录取系数
                    Integer xs = new Integer(String.valueOf(Info.getData().get(i).getRanking() - Info.getData().get(i).getRanking()));
                    Integer xss = new Integer(Info.getData().get(i).getRanking());
                    int xg = xs.intValue();
                    int xh = xss.intValue();
                    float w = (float) xg;
                    float q = (float) xh;
                    rankCoefficient1 = w / q;//位次系数
                    Integer sc = new Integer(String.valueOf(Info.getData().get(i).getBatchLine() - Info.getData().get(i).getBatchLine()));
                    Integer ssc = new Integer(Info.getData().get(i).getRanking());
                    int gc = sc.intValue();
                    int hc = ssc.intValue();
                    float wc = (float) gc;
                    float qc = (float) hc;
                    lineDifCoefficient1 = wc / qc;//线差系数


                    zgxc = String.valueOf(Info.getData().get(i).getMaxScore() - Info.getData().get(i).getBatchLine());//最高线差
                    zdxc = String.valueOf(Info.getData().get(i).getMinScore() - Info.getData().get(i).getBatchLine());//最低线差
                    enrollNumSurgeVal = String.valueOf(Info.getData().get(i).getEnrollNumber() - Info.getData().get(i).getEnrollNumber());//录取人数波动值
                    batchLineSurgeVal = String.valueOf(Info.getData().get(i).getBatchLine() - Info.getData().get(i).getBatchLine());//批次线波动值
                    rankSurgeVal = String.valueOf(Info.getData().get(i).getRanking() - Info.getData().get(i).getRanking());//最低位次波动值
                    luqurenshu = String.valueOf(Info.getData().get(i).getEnrollNumber());
                    zdwc = String.valueOf(Info.getData().get(i).getRanking());
                    stockList.add(new Stock(Info.getData().get(i).getYear(), luqurenshu, enrollNumSurgeVal, zdxc, batchLineSurgeVal, zdwc, rankSurgeVal, String.valueOf(Info.getData().get(i).getBatchLine())));
                    dataview.add(new StockInfo(Info.getData().get(i).getYear(),String.valueOf(enrollCoefficient1),
                            String.valueOf(lineDifCoefficient1),String.valueOf(rankCoefficient1)));
                    break;

                }
            }

            schoolEnrollratio.setText("录取率：" + UtilFileDB.Schoolenrollratio() + "%");
            lqZgScore.setText("录取分数：" + UtilFileDB.SchoolMaxScore());
            lqZdScore.setText("~" + UtilFileDB.SchoolMinScore());
            leftlList = new ArrayList<>();
            for (int i = 0; i < stockList.size(); i++) {
                leftlList.add(stockList.get(i).getName());
            }


            showdata();
            showdatasss();
        }

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


    private void showdata() {
        // 设置两个水平控件的联动
        titleHorsv.setScrollView(contentHorsv);
        contentHorsv.setScrollView(titleHorsv);
        //添加左侧数据
        ALeftAdapter adapter = new ALeftAdapter(getContext(), leftlList);
        leftContainerListview.setAdapter(adapter);
        UtilTools.setListViewHeightBasedOnChildren(leftContainerListview);

        // 添加右边内容数据
        ARightAdapter myRightAdapter = new ARightAdapter(getContext(), stockList);
        rightContainerListview.setAdapter(myRightAdapter);
        UtilTools.setListViewHeightBasedOnChildren(rightContainerListview);
        rightContainerListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Stock stock = (Stock) parent.getAdapter().getItem(position);

            }
        });
    }

    private void showdatasss() {

        // 添加数据
        ARightTableAdapter myRightAdapter = new ARightTableAdapter(getContext(), dataview);
        tableListviw.setAdapter(myRightAdapter);
    }

}

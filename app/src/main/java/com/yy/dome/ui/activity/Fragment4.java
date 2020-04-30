package com.yy.dome.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.android.tu.loadingdialog.LoadingDialog;
import com.yy.dome.R;
import com.yy.dome.adapter.SchoolVolunteer;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.entity.SchoolVolunteetInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.util.ACache;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import java.util.ArrayList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Fragment4 extends BasenFragment<IView, MultiFunctionPresenter> implements IView {

    Intent intent;
    ACache aCache;
    Context context;
    @BindView(R.id.listview_shcool_details)
    ListView listviewShcoolDetails;
    LinearLayout ll_popup;
    PopupWindow pop;
    LinearLayout ll_popup1;
    PopupWindow pop1;
    @BindView(R.id.wlk_text)
    TextView wlkText;
    @BindView(R.id.wlk_layout)
    LinearLayout wlkLayout;
    @BindView(R.id.batch_text)
    TextView batchText;
    @BindView(R.id.batch_layout)
    LinearLayout batchLayout;
    int id=0;
    private SchoolVolunteer adapter=null;
    private ArrayList<SchoolVolunteetInfo.AllSubject> allData = new ArrayList();
    private ArrayList<SchoolVolunteetInfo.AllSubject> sciences = new ArrayList();

    String wlk=null;
    String batch=null;
    @Override
    protected int setMainLayout() {
        return R.layout.fragment3;
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
            p.functionMultiRequests(Api.URLs + "ideal.htm?", context, Api.SCHOOLIVOLUNTTEER(UtilFileDB.Tel(), UtilFileDB.code(), UtilFileDB.year()), 1);
        } catch (Exception e) {

        }

    }

    @Override
    protected void initBeforeData() {
        adapter=new SchoolVolunteer(getActivity(),sciences);
        listviewShcoolDetails.setAdapter(adapter);
    }


    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
            SchoolVolunteetInfo info = JSON.parseObject(topContributor, SchoolVolunteetInfo.class);
            sciences.addAll(info.getAllSubject());
            allData.addAll(info.getAllSubject());
            adapter.notifyDataSetChanged();

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

    //筛选---这两个参数就是查询的条件
    private List<SchoolVolunteetInfo.AllSubject> serchList(String wlk, String batch) {
        //全部
        if (wlk == null && batch == null) {
            Log.e("全部",allData.toString());
           return allData;

        }

        //二者皆不为全部
        ArrayList<SchoolVolunteetInfo.AllSubject> alllist = new ArrayList();
        if (wlk != null && batch != null) {
            //查询后的数据
            for (int i=0;i<allData.size();i++){
                SchoolVolunteetInfo.AllSubject item= allData.get(i);
                if (item.getWlk().equals(wlk) && item.getBatch().equals(batch)){
                    alllist.add(item);
                }
            }
            Log.e("二者皆不为全部",alllist.toString());
            return alllist;
        }
        for (int i=0;i<allData.size();i++){
            SchoolVolunteetInfo.AllSubject item= allData.get(i);
            if ((null != wlk && item.getWlk().equals(wlk)) || (batch != null && item.getBatch().equals(batch))){
                alllist.add(item);
            }

        }

        //查询后的数据
        Log.e("查询后的数据",alllist.toString());
        return  alllist;

    }





    /****
     * 文理科
     */
    public void WIK() {
            pop = new PopupWindow(context);
            View view = getLayoutInflater().inflate(R.layout.item_wlk_listview,
                    null);
            ll_popup = (LinearLayout) view.findViewById(R.id.popup);
            pop.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
            pop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            pop.setBackgroundDrawable(new BitmapDrawable());
            pop.setFocusable(true);
            pop.setOutsideTouchable(true);
            pop.setContentView(view);
            RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent1);
            TextView bt1 = (TextView) view.findViewById(R.id.all);
            TextView bt2 = (TextView) view.findViewById(R.id.popup_li);
            TextView bt3 = (TextView) view.findViewById(R.id.popup_wen);
            TextView bt4 = (TextView) view.findViewById(R.id.popup_quxiao);
            TextView bt5 = (TextView) view.findViewById(R.id.popup_determine);

             MyListener listener = new MyListener();
            bt1.setTag(1);
            bt1.setOnClickListener(listener);

            bt2.setTag(2);
            bt2.setOnClickListener(listener);

            bt3.setTag(3);
            bt3.setOnClickListener(listener);

        parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                    ll_popup.clearAnimation();
                }
            });

        bt4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });


        bt5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
    }

    /****
     * 批次线
     */
    public void batch() {
        pop1 = new PopupWindow(context);
        View view = getLayoutInflater().inflate(R.layout.item_batch_view,
                null);
        ll_popup1 = (LinearLayout) view.findViewById(R.id.popup1);
        pop1.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        pop1.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        pop1.setBackgroundDrawable(new BitmapDrawable());
        pop1.setFocusable(true);
        pop1.setOutsideTouchable(true);
        pop1.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent2);
        TextView bt1 = (TextView) view.findViewById(R.id.all1);
        TextView bt2 = (TextView) view.findViewById(R.id.batch_1);
        TextView bt3 = (TextView) view.findViewById(R.id.batch_2);
        TextView bt4 = (TextView) view.findViewById(R.id.batch_determine);
        TextView bt5 = (TextView) view.findViewById(R.id.batch_quxiao);

        MyListener listener = new MyListener();
        bt1.setTag(4);
        bt1.setOnClickListener(listener);

        bt2.setTag(5);
        bt2.setOnClickListener(listener);

        bt3.setTag(6);
        bt3.setOnClickListener(listener);


        parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop1.dismiss();
                ll_popup1.clearAnimation();
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop1.dismiss();
                ll_popup1.clearAnimation();
            }
        });


        bt5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pop1.dismiss();
                ll_popup1.clearAnimation();
            }
        });
    }



    @OnClick({R.id.wlk_layout, R.id.batch_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wlk_layout:
                WIK();
                ll_popup.startAnimation(AnimationUtils.loadAnimation(
                        context, R.anim.activity_translate_in));
                pop.showAtLocation(view, Gravity.LEFT, 0, 0);
                break;
            case R.id.batch_layout:
                batch();
                ll_popup1.startAnimation(AnimationUtils.loadAnimation(
                        context, R.anim.activity_translate_in));
                pop1.showAtLocation(view, Gravity.LEFT, 0, 0);
                break;
        }
    }


    public class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int tag = (Integer) v.getTag();
            switch (tag){
                case 1:
                    wlkText.setText("全部");
                    pop.dismiss();
                    wlk=null;
                    ll_popup.clearAnimation();
                    break;
                case 2:
                    wlkText.setText("理科");
                    pop.dismiss();
                    wlk="1";
                    ll_popup.clearAnimation();

                    break;
                case 3:
                    wlkText.setText("文科");
                    pop.dismiss();
                    wlk="0";
                    ll_popup.clearAnimation();

                    break;
                case 4:
                    batchText.setText("全部");
                    pop1.dismiss();
                    batch=null;
                    ll_popup1.clearAnimation();
                    break;

                case 5:
                    batchText.setText("本科第一批");
                    pop1.dismiss();
                    batch="1";
                    ll_popup1.clearAnimation();
                    System.out.println("button5 click");
                    break;
                case 6:
                    batchText.setText("本科第二批");
                    pop1.dismiss();
                    batch="2";
                    ll_popup1.clearAnimation();

                    break;
            }
                sciences.clear();
                sciences.addAll(serchList(wlk,batch));
                adapter.notifyDataSetChanged();
        }

    }


}

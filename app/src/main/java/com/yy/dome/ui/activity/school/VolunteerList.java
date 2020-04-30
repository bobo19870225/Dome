package com.yy.dome.ui.activity.school;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.android.tu.loadingdialog.LoadingDialog;
import com.yy.dome.R;
import com.yy.dome.adapter.Recycleradatper;

import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.HomeSchoolInfo;
import com.yy.dome.entity.SchoolVolunteerInfo;
import com.yy.dome.entity.SchoolVolunteetInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;


import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.activity.school.adapter.VolunteerAdapter;

import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VolunteerList extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    Intent intent;
    Context context;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    @BindView(R.id.top_lift)
    TextView topLift;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right_texts)
    TextView topRightText;
    @BindView(R.id.volunteer_list)
    RecyclerView volunteerList;

    @BindView(R.id.recycler_one_list)
    RecyclerView recycler_one_list;


    private Recycleradatper recycleradatper;//下面列表
    private List<SchoolVolunteerInfo.AllSubject> recyclerOneList = new ArrayList<>();

    private VolunteerAdapter volunteerAdapter;//上面列表
    private List<SchoolVolunteerInfo.SmartSubject> volunteerAdapterCheck1 = new ArrayList<>();
    private List<SchoolVolunteerInfo.SmartSubject> volunteerAdapterCheck2;


    private int statusPosition = 0;
    private ArrayList<String> listId= new ArrayList();

    @Override
    protected int setMainLayout() {
        return R.layout.activity_volunteer_list;
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
        top.setText("选择专业");
        topRightText.setText("完成(0/6)");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_one_list.setLayoutManager(linearLayoutManager);
        showMyDialog();
        try {
            p.functionMultiRequests(Api.URLs + "ideal.htm?", context,
                    Api.SCHOOLIVOLUNTTEER(UtilFileDB.Tel(), UtilFileDB.code(), UtilFileDB.year()), 1);
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
        if (position==1) {
            SchoolVolunteerInfo info = JSON.parseObject(topContributor, SchoolVolunteerInfo.class);
            recyclerOneList.addAll(info.getAllSubject());
            showListData();//加载下面列表
            volunteerAdapterCheck1.addAll(info.getSmartSubject());

            listData();
        }if (position==2){
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(topContributor);
                String errno = jsonobjs.getString("ret");
                if (errno.equals("0")) {
                    finish();
                    showToastLong("保存成功，请到个人中心查看志愿表！");

                }
            }catch (Exception e){

            }
        }


    }
    private void showListData() {//下面列表
        volunteerList.setLayoutManager(new LinearLayoutManager(this));
        recycleradatper = new Recycleradatper(this, recyclerOneList);
        volunteerList.setAdapter(recycleradatper);
        recycleradatper.setOnItemClickListentersss(new OnItemClickListenters() {
            @Override
            public void onItemClick(View view, int position) {
                if(volunteerAdapterCheck1.size()<6){
                    SchoolVolunteerInfo.SmartSubject smartSubject = new SchoolVolunteerInfo.SmartSubject();
                    smartSubject.setId(recyclerOneList.get(position).getId());
                    smartSubject.setName(recyclerOneList.get(position).getName());
                    smartSubject.setBatch(recyclerOneList.get(position).getBatch());
                    smartSubject.setMoney(recyclerOneList.get(position).getMoney());
                    smartSubject.setPlanNumber(recyclerOneList.get(position).getPlanNumber());
                    smartSubject.setStudyYear(recyclerOneList.get(position).getStudyYear());
                    smartSubject.setSubject(recyclerOneList.get(position).getSubject());
                    volunteerAdapterCheck1.add(smartSubject);
                    volunteerAdapter.notifyDataSetChanged();
                    statusPosition = position;
                    recyclerOneList.remove(position);
                    recycleradatper.notifyItemRemoved(position);
                    topRightText.setText("完成"+volunteerAdapterCheck1.size()+"/6");
                }else{
                  showToastShort("最多只能添加6个专业，请移除一个后再添加");
                }


            }
        });
    }

    private void listData(){
        volunteerAdapter = new VolunteerAdapter(this,volunteerAdapterCheck1);
        recycler_one_list.setAdapter(volunteerAdapter);//上面列表
        topRightText.setText("完成"+volunteerAdapterCheck1.size()+"/6");
        volunteerAdapter.setOnItemClickListentersss(new OnItemClickListenters() {
            @Override
            public void onItemClick(View view, int position) {
                SchoolVolunteerInfo.AllSubject allSubject = new SchoolVolunteerInfo.AllSubject();
                allSubject.setId(volunteerAdapterCheck1.get(position).getId());
                allSubject.setName(volunteerAdapterCheck1.get(position).getName());
                allSubject.setBatch(volunteerAdapterCheck1.get(position).getBatch());
                allSubject.setMoney(volunteerAdapterCheck1.get(position).getMoney());
                allSubject.setPlanNumber(volunteerAdapterCheck1.get(position).getPlanNumber());
                allSubject.setStudyYear(volunteerAdapterCheck1.get(position).getStudyYear());
                allSubject.setSubject(volunteerAdapterCheck1.get(position).getSubject());
                recyclerOneList.add(allSubject);
                recycleradatper.notifyDataSetChanged();
                volunteerAdapterCheck1.remove(position);
                volunteerAdapter.notifyItemRemoved(position);
                topRightText.setText("完成"+volunteerAdapterCheck1.size()+"/6");
            }
        });

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
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                finish();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    @OnClick({R.id.fanhui_lay, R.id.top_right_texts})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                finish();
                break;
            case R.id.top_right_texts:
                for (int i = 0; i < volunteerAdapterCheck1.size(); i++) {
                    listId.add(String.valueOf(volunteerAdapterCheck1.get(i).getId()));
                }
                Log.e("listId",listId.toString());
                try {
                    p.functionMultiRequests(Api.URLs + "ideal.htm?", context,
                            Api.PreservationFORM(UtilFileDB.Tel(),
                                    UtilTools.MD5(UtilFileDB.Token() + UtilFileDB.Tel() + 123),
                                    UtilFileDB.SCHOOLID(),listId.toString(),UtilFileDB.Schoolenrollratio(),UtilFileDB.branch(),UtilFileDB.Schoolname()), 2);
                } catch (Exception e) {

                }


                break;
        }
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
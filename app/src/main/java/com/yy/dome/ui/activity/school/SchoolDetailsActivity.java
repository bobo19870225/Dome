package com.yy.dome.ui.activity.school;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.android.tu.loadingdialog.LoadingDialog;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.util.ACache;
import com.yy.dome.util.GlideRoundTransform;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;
import com.yy.dome.widget.ZQRoundOvalImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SchoolDetailsActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    Intent intent;
    Context context;
    @BindView(R.id.fanhui_my)
    ImageView fanhuiMy;
    @BindView(R.id.top)
    TextView top;
    @BindView(R.id.top_right)
    ImageView topRight;
    @BindView(R.id.school_details_name)
    TextView schoolDetailsName;
    @BindView(R.id.school_details_type)
    TextView schooltype;
    @BindView(R.id.school_details_lx_pm)
    TextView schoolDetailsLxPm;
    @BindView(R.id.school_details_city)
    TextView schoolDetailsCity;

    @BindView(R.id.tablayout_top)
    TabLayout tablayoutTop;
    @BindView(R.id.viewPager_school)
    ViewPager viewPagerSchool;
    @BindView(R.id.fanhui_lay)
    LinearLayout fanhuiLay;
    @BindView(R.id.school_log)
    ZQRoundOvalImageView schoolLog;
    @BindView(R.id.top_lift)
    TextView topLift;
    @BindView(R.id.school_details_ss)
    TextView schoolDetailsSs;
    @BindView(R.id.school_details_year)
    TextView schoolDetailsYear;
    @BindView(R.id.school_details_company)
    TextView schoolDetailsCompany;
    @BindView(R.id.school_details_code)
    TextView schoolDetailsCode;
    @BindView(R.id.school_details_is211)
    TextView schoolDetailsIs211;
    @BindView(R.id.school_details_is985)
    TextView schoolDetailsIs985;
    @BindView(R.id.register_school)
    TextView registerSchool;

    private MyFragmentPagerAdapter myFragmentPagerAdapter;
    String name, id, code, year, city, image, type, is211, is985, createTime, pertain, schoolcode, hotRank;


    @Override
    protected int setMainLayout() {
        return R.layout.activity_school_details;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
        context = this;

        initViews();
        name += getIntent().getStringExtra("name");
        id += getIntent().getStringExtra("id");
        code += getIntent().getStringExtra("code");
        schoolcode += getIntent().getStringExtra("schoolcode");
        year += getIntent().getStringExtra("year");
        city += getIntent().getStringExtra("city");
        image += getIntent().getStringExtra("image");
        type += getIntent().getStringExtra("type");
        is211 += getIntent().getStringExtra("is211");
        is985 += getIntent().getStringExtra("is985");
        hotRank += getIntent().getStringExtra("hotRank");
        createTime += getIntent().getStringExtra("createTime");
        pertain += getIntent().getStringExtra("pertain");


        if (hotRank.equals("null")) {
            schoolDetailsLxPm.setText("综合排名:" + "--");
        } else {

            schoolDetailsLxPm.setText("综合排名:" + hotRank.substring(4).trim());
        }
        schoolDetailsName.setText(name.substring(4).trim());
        schoolDetailsCity.setText(city.substring(4).trim());
        schooltype.setText(type.substring(4).trim());
        schoolDetailsYear.setText(createTime.substring(4).trim());
        schoolDetailsCompany.setText("主管部门:" + pertain.substring(4).trim());
        schoolDetailsCode.setText("院校代码:" + schoolcode.substring(4).trim());
        schoolDetailsIs211.setVisibility(is211.substring(4).trim().equals("true") ? View.VISIBLE : View.GONE);
        schoolDetailsIs985.setVisibility(is985.substring(4).trim().equals("true") ? View.VISIBLE : View.GONE);


        try {
            Glide.with(context).load(Api.URLs + image.substring(4).trim()).thumbnail(0.1f).transform(new GlideRoundTransform(context, 10)).into(schoolLog);
        } catch (Exception e) {
        }
        UtilFileDB.ADDSHAREDDATA("code", code.substring(4).trim());
        UtilFileDB.ADDSHAREDDATA("year", year.substring(4).trim());
        UtilFileDB.ADDSHAREDDATA("id", id.substring(4).trim());
        showMyDialog();

    }

    @Override
    protected void initBeforeData() {
        StatusBarUtil.setTransparentForWindow(this);


    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {

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



    private void initViews() {
        //使用适配器将ViewPager与Fragment绑定在一起
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPagerSchool.setAdapter(myFragmentPagerAdapter);
        //将TabLayout与ViewPager绑定在一起
        tablayoutTop.setupWithViewPager(viewPagerSchool);
    }

    @OnClick({R.id.fanhui_lay, R.id.register_school})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fanhui_lay:
                    finish();
                break;
            case R.id.register_school:
                intent =new Intent(context,VolunteerList.class);
                startActivity(intent);
                break;
        }
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

    /**
     * 显示对话框
     * @param
     */
    public void showMyDialog(){

        LoadingDialog.Builder builder=new LoadingDialog.Builder(context)
                .setMessage("加载中...")
                .setCancelable(false);
        final LoadingDialog dialog=builder.create();
        dialog.show();
        Handler handler =new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                dialog.dismiss();
            }
        },1000);

    }


}
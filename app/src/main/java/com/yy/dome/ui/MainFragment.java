package com.yy.dome.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.yy.dome.R;
import com.yy.dome.base.BaseFragmentActivity;
import com.yy.dome.tool.DataList;
import com.yy.dome.ui.fragment.FundFragment;
import com.yy.dome.ui.fragment.HomeFragment;
import com.yy.dome.ui.fragment.MyFragment;
import com.yy.dome.ui.fragment.ServiceFragment;
import com.yy.dome.ui.fragment.ShoppinFragment;
import com.yy.dome.util.ACache;
import com.yy.dome.util.ActivityCollector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
public class MainFragment extends BaseFragmentActivity {
    FragmentManager mFragmentManager;
    ACache aCache;
    HomeFragment homeFragment;
    ServiceFragment serviceFragment;
    ShoppinFragment shoppinFragment;
    MyFragment myFragment;
    FundFragment fundFragment;
    MediaPlayer music = null;
    @BindView(R.id.main_content)
    FrameLayout mainContent;
    @BindView(R.id.home_img1)
    ImageView homeImg1;
    @BindView(R.id.home_text1)
    TextView homeText1;
    @BindView(R.id.home_tab1)
    LinearLayout homeTab1;
    @BindView(R.id.home_img2)
    ImageView homeImg2;
    @BindView(R.id.home_text2)
    TextView homeText2;
    @BindView(R.id.home_tab2)
    LinearLayout homeTab2;
    /*@BindView(R.id.home_img3)
    ImageView homeImg3;
    @BindView(R.id.home_text3)
    TextView homeText3;
    @BindView(R.id.home_tab3)
    LinearLayout homeTab3;*/
    @BindView(R.id.home_img4)
    ImageView homeImg4;
    @BindView(R.id.home_text4)
    TextView homeText4;
    @BindView(R.id.home_tab4)
    LinearLayout homeTab4;
    @BindView(R.id.home_img5)
    ImageView homeImg5;
    @BindView(R.id.home_text5)
    TextView homeText5;
    @BindView(R.id.home_tab5)
    LinearLayout homeTab5;

    @Override
    protected int setMainLayout() {
        return R.layout.main_fragment;
    }

    @Override
    protected void initView() {
        aCache = ACache.get(this);
    }

    @Override
    protected void initBeforeData() {




        mFragmentManager = getSupportFragmentManager();
        showFragment(0);
        setBottomTextColor(0);
    }

    private void showFragment(int page) {
        FragmentTransaction mFragmentTransaction = mFragmentManager
                .beginTransaction();
        if (homeFragment != null)
            mFragmentTransaction.hide(homeFragment);
        if (serviceFragment != null)
            mFragmentTransaction.hide(serviceFragment);
        if (shoppinFragment != null)
            mFragmentTransaction.hide(shoppinFragment);
        if (fundFragment != null)
            mFragmentTransaction.hide(fundFragment);
        if (myFragment != null)
            mFragmentTransaction.hide(myFragment);
        switch (page) {
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    mFragmentTransaction.add(R.id.main_content, homeFragment);
                } else {
                    mFragmentTransaction.show(homeFragment);
                }
                break;
            case 1:
                if (shoppinFragment == null) {
                    shoppinFragment = new ShoppinFragment();
                    mFragmentTransaction.add(R.id.main_content, shoppinFragment);
                } else {
                    mFragmentTransaction.show(shoppinFragment);
                }
                break;
/*            case 2:
                if (shoppinFragment == null) {
                    shoppinFragment = new ShoppinFragment();
                    mFragmentTransaction.add(R.id.main_content, shoppinFragment);
                } else {
                    mFragmentTransaction.show(shoppinFragment);
                }
                break;*/
            case 3:
                if (fundFragment == null) {
                    fundFragment = new FundFragment();
                    mFragmentTransaction.add(R.id.main_content, fundFragment);
                } else {
                    mFragmentTransaction.show(fundFragment);
                }
                break;
            case 4:
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    mFragmentTransaction.add(R.id.main_content, myFragment);
                } else {
                    mFragmentTransaction.show(myFragment);
                }
                break;
        }
        mFragmentTransaction.commitAllowingStateLoss();
    }

    private void setBottomTextColor(int position) {
        homeText1.setTextColor(getResources().getColor(R.color.colorGray));
        homeText2.setTextColor(getResources().getColor(R.color.colorGray));
        //homeText3.setTextColor(getResources().getColor(R.color.font_hongse));
        homeText4.setTextColor(getResources().getColor(R.color.colorGray));
        homeText5.setTextColor(getResources().getColor(R.color.colorGray));
        homeImg1.setImageResource(DataList.TAB_ICON_NORMAL_IDS[0]);
        homeImg2.setImageResource(DataList.TAB_ICON_NORMAL_IDS[1]);
        //homeImg3.setImageResource(DataList.TAB_ICON_NORMAL_IDS[2]);
        homeImg4.setImageResource(DataList.TAB_ICON_NORMAL_IDS[3]);
        homeImg5.setImageResource(DataList.TAB_ICON_NORMAL_IDS[4]);
        switch (position) {
            case 0:
                homeText1.setTextColor(ContextCompat.getColor(MainFragment.this,R.color.colorPrimary));
                homeImg1.setImageResource(DataList.TAB_ICON_ACTIVE_IDS[0]);
                break;
            case 1:
                homeText2.setTextColor(ContextCompat.getColor(MainFragment.this,R.color.colorPrimary));
                homeImg2.setImageResource(DataList.TAB_ICON_ACTIVE_IDS[1]);
                break;
            case 2:
/*              homeText3.setTextColor(ContextCompat.getColor(MainFragment.this,R.color.colorPrimary));
                homeImg3.setImageResource(DataList.TAB_ICON_ACTIVE_IDS[2]);*/
                break;
            case 3:
                homeText4.setTextColor(ContextCompat.getColor(MainFragment.this,R.color.colorPrimary));
                homeImg4.setImageResource(DataList.TAB_ICON_ACTIVE_IDS[3]);
                break;
            case 4:
                homeText5.setTextColor(ContextCompat.getColor(MainFragment.this,R.color.colorPrimary));
                homeImg5.setImageResource(DataList.TAB_ICON_ACTIVE_IDS[4]);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {
                setResult(1);
                dialogExit();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }


    private void dialogExit() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(MainFragment.this);
        dialog.setMessage("是否确认退出？");
        dialog.setTitle("提示");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAll();//销毁所有的活动
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                System.exit(0);//退出程序
            }
        });
        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.main_content,  R.id.home_tab1,  R.id.home_tab2,  R.id.home_tab4, R.id.home_tab5})
    public void onViewClicked(View view) {
        //PlayMusic(R.raw.qipao);
        switch (view.getId()) {
            case R.id.main_content:
                break;
            case R.id.home_tab1:
                showFragment(0);
                setBottomTextColor(0);
                break;
            case R.id.home_tab2:
                showFragment(1);
                setBottomTextColor(1);
                break;
/*            case R.id.home_tab3:
                showFragment(2);
                setBottomTextColor(2);
                break;*/
            case R.id.home_tab4:
                showFragment(3);
                setBottomTextColor(3);
                break;
            case R.id.home_tab5:
                showFragment(4);
                setBottomTextColor(4);
                break;
        }
    }
    private void PlayMusic(int MusicId) {
        music = MediaPlayer.create(this, MusicId);
        music.start();

    }
}
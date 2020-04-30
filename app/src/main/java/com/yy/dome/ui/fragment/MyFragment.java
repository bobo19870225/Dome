package com.yy.dome.ui.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenFragment;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilImags;
import com.yy.dome.ui.activity.home.VipActivity;
import com.yy.dome.ui.activity.my.AboutUsActivity;
import com.yy.dome.ui.activity.my.CacheActivity;
import com.yy.dome.ui.activity.my.FeedbackActivity;
import com.yy.dome.ui.activity.my.LoginActivity;
import com.yy.dome.ui.activity.my.MyScore;
import com.yy.dome.ui.activity.my.SettingActivity;
import com.yy.dome.ui.activity.school.VoluntaryformActivity;
import com.yy.dome.util.ACache;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.util.Utils;
import com.yy.dome.view.IView;
import com.yy.dome.widget.PreferenceItem;
import com.yy.dome.widget.ZQRoundOvalImageView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFragment extends BasenFragment<IView, MultiFunctionPresenter> implements IView {
    Intent intent;
    @BindView(R.id.my_image)
    ZQRoundOvalImageView myImage;
    @BindView(R.id.my_username)
    TextView myUsername;
    @BindView(R.id.my_setting_data)
    TextView mySettingData;

    @BindView(R.id.my_feedback)
    PreferenceItem myFeedback;
    @BindView(R.id.my_setting)
    PreferenceItem mySetting;
    @BindView(R.id.my_download)
    PreferenceItem myDownload;
    @BindView(R.id.homepage_scrollview)
    ScrollView homepageScrollview;
    @BindView(R.id.my_score)
    PreferenceItem myScore;

    ACache aCache;
    @BindView(R.id.about_us)
    PreferenceItem aboutUs;
    @BindView(R.id.my_wlk)
    TextView myWlk;
    @BindView(R.id.my_score_text)
    TextView myScoreText;
    @BindView(R.id.my_lay_vip)
    LinearLayout myLayVip;
    @BindView(R.id.my_volunteer)
    PreferenceItem myVolunteer;

    @Override
    protected int setMainLayout() {
        return R.layout.fragment_my;
    }


    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {

        aCache = ACache.get(getActivity());
        myDownload.setTitle(R.string.my_dwonload);
        myDownload.setImageView(R.mipmap.icon_my_dwonload);
        myFeedback.setTitle(R.string.my_feedback);
        myFeedback.setImageView(R.mipmap.icon_my_feedback);

        myVolunteer.setTitle("我的志愿");
        myVolunteer.setImageView(R.mipmap.icon_my_setting);

        mySetting.setTitle(R.string.my_setting);
        mySetting.setImageView(R.mipmap.icon_my_setting);
        myScore.setTitle(R.string.my_score);
        myScore.setImageView(R.mipmap.icon_my_achievement);

        aboutUs.setTitle(R.string.about_us);
        aboutUs.setImageView(R.mipmap.icon_my_about);

/*        try {
            p.functionMultiRequest(Api.URLs + "ideal.htm?", getActivity(), Api.showUpdateImageData(UtilFileDB.Tel(), UtilTools.MD5(UtilFileDB.Token()+UtilFileDB.Tel()+123)), 1);
        }catch (Exception e){

        }*/
        try {
            if (UtilFileDB.WLKS().equals("0")) {
                myWlk.setText("文科" + "/");
            } else {
                myWlk.setText("理科" + "/");
            }
            myScoreText.setText(UtilFileDB.SCORE());
            myUsername.setText(UtilFileDB.LOGINNAME());
        }catch (Exception e){

        }

    }

    @Override
    public void onResume() {
        if (UtilFileDB.ISLOGIN()) {
            mySettingData.setText(R.string.my_login_data);
            mySettingData.setBackgroundResource(R.drawable.text_sign_setting_style);
            mySettingData.setTextColor(ContextCompat.getColor(getActivity(), R.color.white));
        } else {
            showUserData();
        }
        super.onResume();
    }

    private void showUserData() {
        try {
            //myUsername.setText(UtilFileDB.LOGINNAMS(aCache));
            UtilImags.getImage(getActivity(), aCache, Api.URLs + "/files/dfHeadImg/defaultHead.jpg", myImage);
            myUsername.setText(UtilFileDB.uname());
        } catch (Exception e) {
        }
    }

/*    private void showUserData() {
        try {
            if (UtilFileDB.ISLOGINIMG(aCache)) {
                myImage.setImageBitmap(UtilFileDB.LOGINIMGBITMAP(aCache));
            } else {
                UtilImags.getImage(getActivity(), aCache, UtilFileDB.LOGINIMAGEURL(), myImage);
            }
            //myUsername.setText(UtilFileDB.LOGINNAMS(aCache));
            myUsername.setText(UtilFileDB.LOGINNAME());
        } catch (Exception e) {

        }
    }*/


    @Override
    protected void initBeforeData() {
        showUserData();
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        if (position == 1) {
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(topContributor);
                String errno = jsonobjs.getString("headImg");
                Log.e("errno", errno);
                myImage.setImageBitmap(byteBitmap(Utils.strToByteArray(errno)));
            } catch (JSONException e) {

            }
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
    public void onDestroyView() {
        super.onDestroyView();
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

    @OnClick({R.id.my_image, R.id.my_score, R.id.my_setting,
            R.id.my_download, R.id.my_feedback, R.id.about_us, R.id.my_username,
            R.id.my_setting_data, R.id.my_lay_vip,R.id.my_volunteer
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_image:
                try {
                    if (UtilFileDB.ISLOGIN()) {
                        intent = new Intent(getActivity(), LoginActivity.class);
                        startActivityForResult(intent, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.my_username:
                try {
                    if (UtilFileDB.ISLOGIN()) {
                        intent = new Intent(getActivity(), LoginActivity.class);
                        startActivityForResult(intent, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.my_setting_data:
                try {
                    if (UtilFileDB.ISLOGIN()) {
                        intent = new Intent(getActivity(), LoginActivity.class);
                        startActivityForResult(intent, 1);
                    } else {
                        intent = new Intent(getActivity(), SettingActivity.class);
                        startActivityForResult(intent, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.my_score:
                intent = new Intent(getActivity(), MyScore.class);
                startActivity(intent);
                break;
            case R.id.my_volunteer:
                intent = new Intent(getActivity(), VoluntaryformActivity.class);
                startActivity(intent);
                break;

            case R.id.my_lay_vip:
                intent = new Intent(getActivity(), VipActivity.class);
                startActivity(intent);
                break;


            case R.id.my_setting:
                try {
                    if (UtilFileDB.ISLOGIN()) {
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivityForResult(intent, 1);
                    } else {
                        intent = new Intent(getActivity(), SettingActivity.class);
                        startActivityForResult(intent, 1);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.my_download:
                openActivity(CacheActivity.class);
                break;
            case R.id.my_feedback:
                openActivity(FeedbackActivity.class);
                break;
            case R.id.about_us:
                openActivity(AboutUsActivity.class);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            try {
                if (resultCode == 2) {
                    mySettingData.setText(R.string.my_edit_data);
                    mySettingData.setBackgroundResource(R.drawable.text_sign_setting_style);
                    mySettingData.setTextColor(getResources().getColor(R.color.white));
                    myUsername.setText(data.getStringExtra("username"));
/*                    myImage.setImageBitmap(UtilFileDB.LOGINIMGBITMAP(aCache));
                    myImage.setImageBitmap(UtilFileDB.LOGINIMGBITMAP(aCache));*/

                    Log.e("username", data.getStringExtra("username"));
                }
                if (resultCode == 3) {
                    myUsername.setText("未登录");
                }
                if (resultCode == 6) {
                    myUsername.setText(UtilFileDB.LOGINNAMS(aCache));
                    Log.e("name", UtilFileDB.LOGINNAMS(aCache));
                }
            } catch (Exception e) {
            }
        }
    }

    private Bitmap byteBitmap(byte[] data) {
        try {
            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);  //生成位图
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "图片转换失败！", Toast.LENGTH_LONG).show();
        }
        return null;
    }
}

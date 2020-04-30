package com.yy.dome.ui.activity.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.api.PubcApplication;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.LoginInfo;
import com.yy.dome.entity.SignInfo;
import com.yy.dome.entity.TimeInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.MainFragment;
import com.yy.dome.util.ACache;
import com.yy.dome.util.AppManager;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

import butterknife.OnClick;

public class Register extends BasenActivity<IView, MultiFunctionPresenter> implements IView {

    @BindView(R.id.img_fanhui)
    ImageView imgFanhui;
    @BindView(R.id.edit_tel)
    EditText editTel;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.sign_btn)
    TextView signBtn;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.edit_pwds)
    EditText editPwds;
    @BindView(R.id.input_layout_name)
    LinearLayout inputLayoutName;
    @BindView(R.id.edit_yzm)
    EditText editYzm;
    @BindView(R.id.yzm_text)
    TextView yzmText;
    @BindView(R.id.register_yzm)
    LinearLayout registerYzm;
    @BindView(R.id.register_pwd)
    LinearLayout registerPwd;
    @BindView(R.id.register_pwds)
    LinearLayout registerPwds;
    @BindView(R.id.lift_text)
    TextView liftText;
    private Context context;
    ACache aCache;
    private Intent intent;
    String Time;
    String password, password2;
    private TimeCount mTiemTimeCount;
    private String code;

    Gson  gson;


    @Override
    protected int setMainLayout() {
        return R.layout.activity_register;
    }

    @Override
    public MultiFunctionPresenter createPresenter() {
        return new MultiFunctionPresenter();
    }

    @Override
    protected void initView() {
        StatusBarUtil.setTransparentForWindow(this);
        aCache = ACache.get(this);
        context = this;
        editPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editPwds.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

    }

    @Override
    protected void initBeforeData() {
        p.functionMultiRequest(Api.URLs + "student.htm?", Register.this, Api.time(), 1);

        gson=new Gson();
        AppManager.getInstance().addActivity(Register.this);
        if (getIntent() != null) {
            initView();
            mTiemTimeCount = new TimeCount(60000, 1000);
        }
        Log.e("time",UtilTools.currentTimeMillis());
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        if (position == 1) {
            TimeInfo Info = JSON.parseObject(topContributor, TimeInfo.class);
            Time = Info.toString();
            Log.e("Time",Time);
        } if (position == 2) {
            Log.e("top",topContributor);
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(topContributor);
                String errno = jsonobjs.getString("ret");
                if (errno.equals("0")) {
                    UtilFileDB.ADDSHAREDDATA("name", editTel.getText().toString());
                    showToastShort("注册成功");
                    intent=new Intent(context,MyScore.class);
                    startActivity(intent);
                } else {
                    showToastShort("注册失败");
                }
                if (errno.equals("1004")) {
                    showToastShort("电话号码已被注册");
                }
                if (errno.equals("1001")) {
                    showToastShort("名字只能是中文");
                }
                if (errno.equals("1003")) {
                    showToastShort("输入错误、请检查后重新输入");
                }
            } catch (JSONException e) {

            }if (position==3) {
                Log.e("wwww", topContributor);
                try {
                    LoginInfo userInfo = JSON.parseObject(topContributor, LoginInfo.class);

                    if (userInfo.getRet() == 0) {
                        UtilFileDB.ADDSHAREDDATA("zqname", userInfo.getData().getName());
                        UtilFileDB.ADDSHAREDDATA("useruid", userInfo.getData().getId());
                        UtilFileDB.ADDSHAREDDATA("name", editTel.getText().toString());
                        UtilFileDB.ADDSHAREDDATA("pwd", editPwd.getText().toString());
                        UtilFileDB.ADDSHAREDDATA("time", userInfo.getData().getLoginTime());
                        UtilFileDB.ADDSHAREDDATA("tel", userInfo.getData().getPhone());
                        UtilFileDB.ADDSHAREDDATA("score", userInfo.getData().getScore());
                        UtilFileDB.ADDSHAREDDATA("token", userInfo.getToken());
                        UtilFileDB.ADDSHAREDDATA("school", userInfo.getData().getFollowSchool());
                        UtilFileDB.ADDSHAREDDATA("followArticle", userInfo.getData().getFollowArticle());
                        UtilFileDB.ADDSHAREDDATA("rank", userInfo.getData().getRank());
                        UtilFileDB.ADDSHAREDDATA("wlk", userInfo.getData().getWlk() + "分");
                        Intent intent = new Intent();
                        intent.putExtra("username", userInfo.getData().getName());
                        Log.e("name",userInfo.getData().getName());
                        setResult(2, intent);
                        intent = new Intent(context, MainFragment.class);
                        startActivity(intent);
                        if (userInfo.getData().getScore().equals("0")) {
                            openActivity(MyScore.class);
                        }
                    } else {
                        showToastShort("用户名或密码错误");

                        return;
                    }
                } catch (Exception e) {
                    showToastShort(R.string.error_no_data);
                }

            }

        }
        if (position==4){
            SignInfo info = JSON.parseObject(topContributor, SignInfo.class);
            Log.e("验证码",topContributor);
            SharedPreferences.Editor editor = PubcApplication.editor;
            editor.putString("phone","18748819480");
            editor.commit();
        }

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            try {
                if (resultCode == 2) {
                }
            } catch (Exception e) {
            }
        }
    }


    @OnClick({R.id.input_layout_name, R.id.yzm_text, R.id.register_yzm, R.id.register_pwd,
            R.id.register_pwds, R.id.lift_text,R.id.img_fanhui, R.id.sign_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.input_layout_name:
                break;
            case R.id.yzm_text:
                if (editTel.getText().toString().trim().equals("")){
                    showToastShort("请输入手机号");
                }else {
                    mTiemTimeCount.start();
                    p.functionMultiRequest(Api.URLs + "student.htm?", Register.this, Api.YZM(editTel.getText().toString().trim()), 4);
                }
                break;
            case R.id.register_yzm:

                break;
            case R.id.register_pwd:
                break;
            case R.id.register_pwds:
                break;
            case R.id.lift_text:
                finish();
                break;
            case R.id.img_fanhui:
                finish();
                break;
            case R.id.sign_btn:
/*                String telRegex = "13\\d{9}|14[57]\\d{8}|15[012356789]\\d{8}|18[01256789]\\d{8}|17[0678]\\d{8}";
                //String telRegex = "[1][358]\\d{9}";
                if (!editTel.getText().toString().trim().matches(telRegex)) {
                showToastShort("请输入手机号码");
                return;
            }*/
                code=editYzm.getText().toString().trim();
                if (editYzm.getText().toString().equals("") || !editYzm.getText().toString().trim().equals(code)) {
                    showToastShort("请输入正确验证码");
                    return;
                }
                password = editPwd.getText().toString().trim();//第一次输入的密码赋值给password
                password2 = editPwds.getText().toString().trim();
                if (password.equals("") || password2.equals("")) {
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (password.equals(password2)) {
                        p.functionMultiRequest(Api.URLs + "student.htm?", Register.this, Api.showRegisterDataInfo(editTel.getText().toString(),editYzm.getText().toString().trim(),
                                UtilTools.MD5(editPwd.getText().toString())
                                , UtilTools.currentTimeMillis(), UtilTools.MD5(UtilTools.MD5(Time) + editTel.getText().toString())), 2);

                        p.functionMultiRequest(Api.URLs + "student.htm?", Register.this, Api.Login(editTel.getText().toString(),
                                UtilTools.MD5(editPwd.getText().toString())
                                , UtilTools.currentTimeMillis(), UtilTools.MD5(UtilTools.MD5(Time) + editTel.getText().toString())), 3);

                    } else {
                        Toast.makeText(getApplication(), "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    showToastShort("地区还未选择");
                }

                break;
        }
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            //yzmText.setBackgroundColor(Color.parseColor("#B6B6D8"));
            yzmText.setClickable(false);
            yzmText.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            yzmText.setText("重新发送");
            yzmText.setClickable(true);
            //yzmText.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }



}




package com.yy.dome.ui.activity.my.item;

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
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.api.PubcApplication;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.SignInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.util.ACache;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.view.IView;
import com.yy.dome.widget.CommonEditText;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

public class ForgetPassword extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;
    @BindView(R.id.img_fanhui)
    ImageView imgFanhui;
    @BindView(R.id.edit_tel_fp)
    EditText editTelFp;
    @BindView(R.id.input_layout_name)
    LinearLayout inputLayoutName;
    @BindView(R.id.edit_yzm_fp)
    EditText editYzmFp;
    @BindView(R.id.yzm_text_fp)
    TextView yzmTextFp;
    @BindView(R.id.register_yzm)
    LinearLayout registerYzm;
    @BindView(R.id.edit_pwd_fp)
    EditText editPwdFp;
    @BindView(R.id.register_pwd)
    LinearLayout registerPwd;
    @BindView(R.id.edit_pwds_fp)
    EditText editPwdsFp;
    @BindView(R.id.register_pwds)
    LinearLayout registerPwds;
    @BindView(R.id.forget_password_submission)
    TextView forgetPasswordSubmission;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    String password, password2;
    private TimeCount mTiemTimeCount;
    private String code;
    private Context context;
    private Intent intent;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_forgetpassword;
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
        editTelFp.setHint("请输入手机号");
        editYzmFp.setHint("请输入验证码");
        editPwdFp.setHint("请输入密码");
        editPwdsFp.setHint("请再次输入密码");
    }

    @Override
    protected void initBeforeData() {
        if (getIntent() != null) {
            initView();
            mTiemTimeCount = new TimeCount(60000, 1000);
        }
        editPwdFp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        editPwdsFp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {
        if (position == 1) {
            Log.e("top", topContributor);
            JSONObject jsonobjs;
            try {
                jsonobjs = new JSONObject(topContributor);
                String errno = jsonobjs.getString("ret");
                if (errno.equals("0")) {
                    showToastShort("找回成功");
                    finish();
                } else {
                    showToastShort("找回失败");
                }
                if (errno.equals("2")) {
                    showToastShort("验证码失效");
                }
                if (errno.equals("3")) {
                    showToastShort("验证码输入错误");
                }
            } catch (JSONException e) {

            }
        }if (position==4){
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

    }


    @OnClick({R.id.img_fanhui, R.id.forget_password_submission,R.id.yzm_text_fp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_fanhui:
                finish();
                break;
            case R.id.yzm_text_fp:
                if (editTelFp.getText().toString().trim().equals("")){
                    showToastShort("请输入手机号");
                }else {
                    mTiemTimeCount.start();
                    p.functionMultiRequest(Api.URLs + "student.htm?", ForgetPassword.this, Api.UPDATAYZM(editTelFp.getText().toString().trim()), 4);
                }
                break;
            case R.id.forget_password_submission:

/*                String telRegex = "13\\d{9}|14[57]\\d{8}|15[012356789]\\d{8}|18[01256789]\\d{8}|17[0678]\\d{8}";
                //String telRegex = "[1][358]\\d{9}";
                if (!editTelFp.getText().toString().trim().matches(telRegex)) {
                    showToastShort("请输入手机号码");
                    return;
                }*/
                code=editYzmFp.getText().toString().trim();
                if (editYzmFp.getText().toString().equals("") || !editYzmFp.getText().toString().trim().equals(code)) {
                    showToastShort("请输入正确验证码");
                    return;
                }
                password = editPwdFp.getText().toString().trim();//第一次输入的密码赋值给password
                password2 = editPwdsFp.getText().toString().trim();
                if (password.equals("") || password2.equals("")) {
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    if (password.equals(password2)) {
                        p.functionMultiRequest(Api.URLs + "student.htm?", ForgetPassword.this, Api.updataPassWORD(editTelFp.getText().toString(),editYzmFp.getText().toString().trim(),
                                UtilTools.MD5(editPwdFp.getText().toString())), 1);
                    } else {
                        Toast.makeText(getApplication(), "密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

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
            yzmTextFp.setClickable(false);
            yzmTextFp.setText("("+millisUntilFinished / 1000 +") 秒后可重新发送");
        }

        @Override
        public void onFinish() {
            yzmTextFp.setText("重新获取验证码");
            yzmTextFp.setClickable(true);
            //yzmText.setBackgroundColor(Color.parseColor("#4EB84A"));

        }
    }
}

package com.yy.dome.ui.activity.my;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.fastjson.JSON;
import com.yy.dome.R;
import com.yy.dome.api.Api;
import com.yy.dome.base.BasenActivity;
import com.yy.dome.entity.LoginInfo;
import com.yy.dome.entity.TimeInfo;
import com.yy.dome.presenter.MultiFunctionPresenter;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.tool.UtilTools;
import com.yy.dome.ui.MainFragment;
import com.yy.dome.ui.activity.my.item.ForgetPassword;
import com.yy.dome.util.ACache;
import com.yy.dome.util.JellyInterpolator;
import com.yy.dome.util.StatusBarUtil;
import com.yy.dome.util.UtilFileDB;
import com.yy.dome.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BasenActivity<IView, MultiFunctionPresenter> implements IView {
    ACache aCache;

    String str1, str2;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.name_tel)
    EditText nameTel;
    @BindView(R.id.input_layout_name)
    LinearLayout inputLayoutName;
    @BindView(R.id.pwd)
    EditText pwd;
    @BindView(R.id.input_layout_psw)
    LinearLayout inputLayoutPsw;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    @BindView(R.id.sign)
    TextView sign;
    @BindView(R.id.forget_password)
    TextView forgetPassword;
    private float mWidth, mHeight;
    private View progress;
    private View mInputLayout;
    private Intent intent;
    private Context context;
    String Time;
    Context mContext;

    @Override
    protected int setMainLayout() {
        return R.layout.activity_sign;
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
        this.mContext = context;
        progress = findViewById(R.id.layout_progress);
        mInputLayout = findViewById(R.id.input_layout);

        p.functionMultiRequest(Api.URLs + "student.htm?", LoginActivity.this, Api.time(), 1);


    }

    @Override
    protected void initBeforeData() {

        str1 = UtilFileDB.NAME();
        str2 = UtilFileDB.PWD();
        pwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        nameTel.setText(str1);
        pwd.setText(str2);



    }

    @Override
    public void onLoadContributorStart() {
    }

    @Override
    public void onLoadContribtorComplete(String topContributor, int position) {

        if (position == 1) {
            TimeInfo Info = JSON.parseObject(topContributor, TimeInfo.class);
            Time = Info.toString();
            Log.e("time", topContributor);
        }  if (position == 2) {
            Log.e("Login", topContributor);
            try {
                LoginInfo userInfo = JSON.parseObject(topContributor, LoginInfo.class);
                if (userInfo.getRet() == 0) {
                    UtilFileDB.ADDSHAREDDATA("zqname", userInfo.getData().getName());
                    UtilFileDB.ADDSHAREDDATA("useruid", userInfo.getData().getId());
                    UtilFileDB.ADDSHAREDDATA("name", nameTel.getText().toString());
                    UtilFileDB.ADDSHAREDDATA("pwd", pwd.getText().toString());
                    UtilFileDB.ADDSHAREDDATA("time", userInfo.getData().getLoginTime());
                    UtilFileDB.ADDSHAREDDATA("tel", userInfo.getData().getPhone());
                    UtilFileDB.ADDSHAREDDATA("score", userInfo.getData().getScore());
                    UtilFileDB.ADDSHAREDDATA("token", userInfo.getToken());
                    UtilFileDB.ADDSHAREDDATA("school", userInfo.getData().getFollowSchool());
                    UtilFileDB.ADDSHAREDDATA("followArticle", userInfo.getData().getFollowArticle());
                    UtilFileDB.ADDSHAREDDATA("rank", userInfo.getData().getRank());
                    UtilFileDB.ADDSHAREDDATA("wlk", userInfo.getData().getWlk());
                    Intent intent = new Intent();
                    intent.putExtra("username", userInfo.getData().getName());
                    Log.e("name",userInfo.getData().getName());
                    setResult(2, intent);
                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                    intent = new Intent(context, MainFragment.class);
                    startActivity(intent);
                    if (userInfo.getData().getScore().equals("0")) {
                        openActivity(MyScore.class);
                    }
                }else {
                    recovery();
                    showToastShort("登录失败");
                }
            } catch (Exception e) {
                showToastShort(R.string.error_no_data);
            }

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
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN
                    && event.getRepeatCount() == 0) {

                dialogExit();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @OnClick({R.id.register, R.id.sign,R.id.forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register:
                openActivity(Register.class);
                break;
            case R.id.sign:
                if (nameTel.getText().toString().trim().equals("")){
                    showToastShort("请输入手机号");
                }
                if (pwd.getText().toString().trim().equals("")){
                    showToastShort("请输入密码");
                }else {
                    // 计算出控件的高与宽
                    mWidth = sign.getMeasuredWidth();
                    mHeight = sign.getMeasuredHeight();
                    // 隐藏输入框
                    inputLayoutName.setVisibility(View.INVISIBLE);
                    inputLayoutPsw.setVisibility(View.INVISIBLE);
                    inputAnimator(mInputLayout, mWidth, mHeight);
                }
                break;
            case R.id.forget_password:
                openActivity(ForgetPassword.class);
                Log.e("hyj","123");
                break;

        }
    }

    /**
     * 输入框的动画效果
     *
     * @param view 控件
     * @param w    宽
     * @param h    高
     */
    private void inputAnimator(final View view, float w, float h) {
        AnimatorSet set = new AnimatorSet();
        ValueAnimator animator = ValueAnimator.ofFloat(0, w);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view
                        .getLayoutParams();
                params.leftMargin = (int) value;
                params.rightMargin = (int) value;
                view.setLayoutParams(params);
            }
        });

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout,
                "scaleX", 1f, 0.5f);
        set.setDuration(3000);
        set.setInterpolator(new AccelerateDecelerateInterpolator());
        set.playTogether(animator, animator2);
        set.start();
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                /**
                 * 动画结束后，先显示加载的动画，然后再隐藏输入框
                 */
                progress.setVisibility(View.VISIBLE);
                progressAnimator(progress);
                mInputLayout.setVisibility(View.INVISIBLE);
                try {
                    if (!NetUtils.isNetworkAvailable(context)) {
                        Toast.makeText(context, "网络未连接", Toast.LENGTH_LONG).show();
                        recovery();
                        return;
                    } else {
                        p.functionMultiRequest(Api.URLs + "student.htm?", LoginActivity.this, Api.Login(nameTel.getText().toString().trim(),
                                UtilTools.MD5(pwd.getText().toString().trim())
                                , UtilTools.currentTimeMillis(), UtilTools.MD5(UtilTools.MD5(Time) + nameTel.getText().toString().trim())), 2);
                        Log.e("key", UtilTools.MD5(UtilTools.MD5(Time) + nameTel.getText().toString().trim()));
                        Log.e("time",UtilTools.MD5("1585118308"));
                    }
/*                    try {
                        if (UtilFileDB.Score()){
                            intent = new Intent(context, MyScore.class);
                            startActivityForResult(intent, 1);
                        }else {
                            intent = new Intent(context, MainFragment.class);
                            startActivity(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }*/
                } catch (Exception E) {
                    showToastShort("登录失败");
                    recovery();
                }
                Log.e("md5", UtilTools.MD5(pwd.getText().toString()));
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }
        });
    }

    /**
     * 出现进度动画
     *
     * @param view
     */
    private void progressAnimator(final View view) {
        PropertyValuesHolder animator = PropertyValuesHolder.ofFloat("scaleX",
                0.5f, 1f);
        PropertyValuesHolder animator2 = PropertyValuesHolder.ofFloat("scaleY",
                0.5f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofPropertyValuesHolder(view,
                animator, animator2);
        animator3.setDuration(100000);
        animator3.setInterpolator(new JellyInterpolator());
        animator3.start();
    }

    /**
     * 恢复初始状态
     */
    private void recovery() {
        progress.setVisibility(View.GONE);
        mInputLayout.setVisibility(View.VISIBLE);
        inputLayoutName.setVisibility(View.VISIBLE);
        inputLayoutPsw.setVisibility(View.VISIBLE);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) mInputLayout.getLayoutParams();
        params.leftMargin = 0;
        params.rightMargin = 0;
        mInputLayout.setLayoutParams(params);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(mInputLayout, "scaleX", 0.5f, 1f);
        animator2.setDuration(500);
        animator2.setInterpolator(new AccelerateDecelerateInterpolator());
        animator2.start();
    }


/*    private static boolean isWifiProxy() {
        final boolean IS_ICS_OR_LATER = Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
        String proxyAddress;
        int proxyPort;
        if (IS_ICS_OR_LATER) {
            proxyAddress = System.getProperty("http.proxyHost");
            String portStr = System.getProperty("http.proxyPort");
            proxyPort = Integer.parseInt((portStr != null ? portStr : "-1"));
        } else {
            proxyAddress = android.net.Proxy.getHost(mContext);
            proxyPort = android.net.Proxy.getPort(mContext);
        }
        return (!TextUtils.isEmpty(proxyAddress)) && (proxyPort != -1);
    }*/


    private void dialogExit() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
        dialog.setMessage("是否确认退出？");
        dialog.setTitle("提示");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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


}
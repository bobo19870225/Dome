package com.yy.dome;

import androidx.annotation.NonNull;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yy.dome.ui.MainFragment;
import com.yy.dome.ui.activity.GuideActivity;
import com.yy.dome.ui.activity.my.LoginActivity;
import com.yy.dome.util.AppManager;
import com.yy.dome.util.UtilFileDB;

import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends Activity implements  EasyPermissions.PermissionCallbacks {

    private ImageView mImageView;
    private AlphaAnimation aa;
    Context context;
    Intent intent;
    String[] perms = {Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private static int RC_CAMERA_AND_LOCATION=1;
    TextView textView;
    RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context=this;
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        AppManager.getInstance().addActivity(MainActivity.this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);  //全屏
        setContentView(R.layout.activity_login);
        //finishApp();

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        initView();
      //  countDownTimer();
    }

    private void finishApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    private void initView() {
        mRelativeLayout= (RelativeLayout)findViewById(R.id.fragment_background);
        mRelativeLayout.setBackgroundResource(R.mipmap.two);
        mImageView = (ImageView) findViewById(R.id.fragment_text);
        aa = new AlphaAnimation(0.1f, 1.0f);
        aa.setDuration(3000);
        countDownTimer();
        mImageView.startAnimation(aa);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //检查版本是否大于M
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    methodRequiresTwoPermission();
                }
/*                intent = new Intent(context, GuideActivity.class);
                startActivity(intent);*/
                try {
                    if (UtilFileDB.ISLOGIN()){
                        intent = new Intent(context, LoginActivity.class);
                        startActivityForResult(intent, 1);
                    }else {
                        intent = new Intent(context, MainFragment.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
/*                intent = new Intent(context, MainFragment.class);
                startActivity(intent);*/
            }
        }, 3000);

        textView=findViewById(R.id.tv);
        textView.setVisibility(View.VISIBLE);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MainFragment.class);
                startActivity(intent);
            }
        });

    }

    private void methodRequiresTwoPermission() {
        if (EasyPermissions.hasPermissions(this, perms)) {//检查是否获取该权限
            Toast.makeText(context,"已经获取权限了",Toast.LENGTH_LONG).show();
        } else {
            EasyPermissions.requestPermissions(this, "获取权限",
                    RC_CAMERA_AND_LOCATION, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Log.i("TAG","获取成功的权限有："+perms);
        Toast.makeText(context,"获取权限成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(context,"未获取的权限"+perms,Toast.LENGTH_LONG).show();
    }
    /**
     * 5秒倒计时；
     */
    public void countDownTimer() {
        CountDownTimer timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("跳过" + millisUntilFinished / 1000);
                textView.setEnabled(false);
            }
            @Override
            public void onFinish() {
                textView.setText("跳过");
                textView.setEnabled(true);


            }
        };
        timer.start();
    }
}
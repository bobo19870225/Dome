package com.yy.dome.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yy.dome.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseControllerActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected T p;

    Unbinder unbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setMainLayout());
        unbinder = ButterKnife.bind(this);
        p = createPresenter();
        p.attachView((V) this);
        initView();
        initBeforeData();
    }


    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return resources;
    }

    protected abstract T createPresenter();


    /***
     * 初始化布局
     */
    protected abstract int setMainLayout();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 初始化先前数据
     */
    protected abstract void initBeforeData();


    /**
     * 弹出toast 显示时长short
     *
     * @param msg
     */
    protected void showToastShort(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    protected void showToastShort(int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    /***
     * 跳转Activity
     * @param mClass
     */
    protected void openActivity(Class<?> mClass) {
        openIntent(new Intent(this, mClass));
    }

    protected void openForResultActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }

    protected void openIntent(Intent intent) {
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (p != null) {
            p.detachView();
        }
        unbinder.unbind();
    }



}

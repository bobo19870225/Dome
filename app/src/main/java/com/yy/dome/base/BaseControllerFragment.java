package com.yy.dome.base;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yy.dome.presenter.BasePresenter;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseControllerFragment<V, T extends BasePresenter<V>> extends Fragment {


    protected T p;

    Unbinder unbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setMainLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        p = createPresenter();
        p.attachView((V) this);
        initView();
        initBeforeData();
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

    /***
     * 跳转Activity
     * @param mClass
     */
    protected void openActivity(Class<?> mClass) {
        openIntent(new Intent(getActivity(), mClass));
    }

    /**
     * 弹出toast 显示时长short
     *
     * @param msg
     */
    protected void showToastShort(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
    protected void showToastShort(int msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }



    protected void openIntent(Intent intent) {
        startActivity(intent);
    }

    protected void openForResultActivity(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (p != null) {
            p.detachView();
        }
        unbinder.unbind();
    }



}

package com.yy.dome.adapter;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;


import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.yy.dome.R;
import com.yy.dome.tool.NetUtils;
import com.yy.dome.ui.activity.home.CourseActivity;
import com.yy.dome.ui.activity.home.ExpertActivity;
import com.yy.dome.ui.activity.home.FindSchool;
import com.yy.dome.ui.activity.home.MajorActivity;
import com.yy.dome.ui.activity.home.ManualVolunteer;
import com.yy.dome.ui.activity.home.PolicyActivity;
import com.yy.dome.ui.activity.home.VipActivity;
import com.yy.dome.ui.activity.home.VolunteerActivity;

import com.yy.dome.util.Utils;
import com.yy.dome.widget.NoScrollGridView;



/**
 * Created by huyongjiang on 2018/8/10
 * Describe:  广告界面
 */
public class FragmentPage extends Fragment implements AdapterView.OnItemClickListener {

    private NoScrollGridView mGridView;
    private String mCity;
    private DisplayImageOptions mDisplayImageOptions;
    public FragmentPage(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDisplayImageOptions = Utils.buildDisplayImageOptions(getActivity(), new PaintDrawable(),
                new PaintDrawable(), new PaintDrawable(), new RoundedBitmapDisplayer(8));
        mDisplayImageOptions.isCacheInMemory();
        mDisplayImageOptions.isCacheOnDisk();
        mDisplayImageOptions.getDelayBeforeLoading();
        mGridView = new NoScrollGridView(getActivity());
        mGridView.setNumColumns(4);
        mGridView.setSelector(R.drawable.list_selector);//点击背景色
        MyHomeGridAdapter  adapter=new MyHomeGridAdapter(getActivity());
        //adapter=new MyHomeGridAdapter(getActivity(),homePageMesName);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(this);

        return mGridView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id ) {

        showDateAdapterItem(position);
    }

    private boolean clickIntent = true;

    @Override
    public void onResume() {
        super.onResume();
        clickIntent = true;
    }

    /**
     * 1:缓存记载
     * 2:直接加载应用
     */
    Intent intent;
    private void showDateAdapterItem(int position) {
        switch (position) {
            case 0:
                if (!NetUtils.isNetworkAvailable(getActivity())) {
                    Toast.makeText(getActivity(),"网络未连接",Toast.LENGTH_LONG).show();
                    return;
                }else{
                    if (clickIntent) {
                        intent = new Intent(getContext(), MajorActivity.class);
                        startActivity(intent);
                        clickIntent = false;
                    }
                }
                break;
            case 1:
                if (!NetUtils.isNetworkAvailable(getActivity())) {
                    Toast.makeText(getActivity(),"网络未连接",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (clickIntent) {
                        intent = new Intent(getContext(), FindSchool.class);
                        startActivity(intent);
                        clickIntent = false;
                    }
                }
                break;
            case 2:
                if (!NetUtils.isNetworkAvailable(getActivity())) {
                    Toast.makeText(getActivity(),"网络未连接",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (clickIntent) {
                        intent = new Intent(getContext(), VolunteerActivity.class);
                        startActivity(intent);
                        clickIntent = false;
                    }
                }
                break;
            case 3:
                if (!NetUtils.isNetworkAvailable(getActivity())) {
                    Toast.makeText(getActivity(),"网络未连接",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (clickIntent) {
                        intent = new Intent(getContext(), ManualVolunteer.class);
                        startActivity(intent);
                        clickIntent = false;
                    }
                }
                break;
            case 4:
                if (!NetUtils.isNetworkAvailable(getActivity())) {
                    Toast.makeText(getActivity(),"网络未连接",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (clickIntent) {
                        intent = new Intent(getContext(), CourseActivity.class);
                        startActivity(intent);
                        clickIntent = false;
                    }
                }
                break;
            case 5:
                if (!NetUtils.isNetworkAvailable(getActivity())) {
                    Toast.makeText(getActivity(),"网络未连接",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (clickIntent) {
                        intent = new Intent(getContext(), ExpertActivity.class);
                        startActivity(intent);
                        clickIntent = false;

                    }
                }
                break;
            case 6:
                if (!NetUtils.isNetworkAvailable(getActivity())) {
                    Toast.makeText(getActivity(),"网络未连接",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (clickIntent) {
                        intent = new Intent(getContext(), PolicyActivity.class);
                        startActivity(intent);
                        clickIntent = false;
                    }
                }
                break;
            case 7:
                if (!NetUtils.isNetworkAvailable(getActivity())) {
                    Toast.makeText(getActivity(),"网络未连接",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    if (clickIntent) {
                        intent = new Intent(getContext(), VipActivity.class);
                        startActivity(intent);
                        clickIntent = false;
                    }
                }
                break;
            default:
                break;
        }
    }

    int[] img={R.mipmap.volunteer_tow,R.mipmap.school,R.mipmap.major,R.mipmap.my_evaluation,
            R.mipmap.course,R.mipmap.express,R.mipmap.dangqun,R.mipmap.vip
            };
    String[] name={"智能填报","查询大学","专业大全","院校鉴别","批次线","专家咨询","位次查询","开通VIP"};
    class MyHomeGridAdapter extends BaseAdapter {

        LayoutInflater inflater;
        private Context context;
        public MyHomeGridAdapter(Context context) {
            this.inflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public Object getItem(int position) {
            return img[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("InflateParams") @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (null == convertView) {
                convertView =inflater.inflate(R.layout.homepage_viewpager_fragment_grid_item, null);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.image_view);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.name.setText(name[position]);
            holder.image.setImageResource(img[position]);
            return convertView;
        }

        class ViewHolder {
            ImageView image;
            TextView name;
        }
    }

    /**
     * 每次启动activity都会调用此方法
     */
    @SuppressLint("RestrictedApi")
    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        if (checkDoubleClick(intent)) {
            super.startActivityForResult(intent, requestCode, options);

        }
    }


    private String mActivityJumpTag;        //activity跳转tag
    private long mClickTime;                //activity跳转时间


    protected boolean checkDoubleClick(Intent intent) {

        // 默认检查通过
        boolean result = true;
        // 标记对象
        String tag;
        if (intent.getComponent() != null) { // 显式跳转
            tag = intent.getComponent().getClassName();
        }else if (intent.getAction() != null) { // 隐式跳转
            tag = intent.getAction();
        }else {
            return true;
        }

        if (tag.equals(mActivityJumpTag) && mClickTime >= SystemClock.uptimeMillis() - 500) {
            // 检查不通过
            result = false;
        }

        // 记录启动标记和时间
        mActivityJumpTag = tag;
        mClickTime = SystemClock.uptimeMillis();
        return result;
    }

    public static void disabledView(final View v) {
        v.setClickable(false);
        // 延迟5秒，恢复点击事件
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                v.setClickable(true);

            }
        }, 500);
    }






}

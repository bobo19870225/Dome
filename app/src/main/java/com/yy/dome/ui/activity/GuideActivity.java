package com.yy.dome.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.yy.dome.R;
import com.yy.dome.adapter.ViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class GuideActivity  extends AppCompatActivity {


    private ViewPager viewPager;
    private List<View> listImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将屏幕设置为全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ys);
        initView();
    }


    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), showView()));
        listImg = new ArrayList<View>();
        listImg.add(findViewById(R.id.y1));
        listImg.add(findViewById(R.id.y2));
        listImg.add(findViewById(R.id.y3));
        viewPager.setOnPageChangeListener(showPageChange);
    }

    ViewPager.OnPageChangeListener showPageChange = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < listImg.size(); i++) {
                if (i == arg0) {
                    listImg.get(arg0).setBackgroundResource(R.drawable.point_deep);
                } else {
                    listImg.get(i).setBackgroundResource(R.drawable.point_smiple);
                }
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

        }
    };

    private List<Fragment> showView() {
        List<Fragment> listView = new ArrayList<Fragment>();
        listView.add(new Fragment1());
        listView.add(new Fragment2());
        listView.add(new Fragment3());
        return listView;
    }
}

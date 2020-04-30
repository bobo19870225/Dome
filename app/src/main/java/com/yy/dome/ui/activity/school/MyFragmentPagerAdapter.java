package com.yy.dome.ui.activity.school;

import android.os.Parcelable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.yy.dome.ui.activity.Fragment1;
import com.yy.dome.ui.activity.Fragment2;
import com.yy.dome.ui.activity.Fragment3;
import com.yy.dome.ui.activity.Fragment4;
import com.yy.dome.ui.activity.OneFragment;

//private String[] mTitles = new String[]{"录取预测", "历史数据", "院校简评","招生计划"};
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles = new String[]{"录取预测", "历史数据", "院校简评","招生计划"};
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new Fragment2();
        } else if (position == 2) {
            return new Fragment3();
        }else if (position==3){
            return new Fragment4();
        }
        return new OneFragment();
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    //ViewPager与TabLayout绑定后，这里获取到PageTitle就是Tab的Text
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }


}
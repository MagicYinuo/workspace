package com.example.administrator.helloas.activity.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/7/14.
 */
public class ViewPagerAdapter extends PagerAdapter {
    private ArrayList<View> views;

    public ViewPagerAdapter(ArrayList<View> views) {
        this.views = views;
    }

    //获得当前的界面数
    @Override
    public int getCount() {
        if (views != null) {
            return views.size();
        } else {
            return 0;
        }
    }

    //判断当前页面是由对象生存view界面
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //销毁position位置的界面
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView(views.get(position));
    }

    //初始化view的界面
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(views.get(position));
        return views.get(position);
    }
}

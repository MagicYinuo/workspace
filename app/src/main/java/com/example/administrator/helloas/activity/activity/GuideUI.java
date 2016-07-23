package com.example.administrator.helloas.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.helloas.R;
import com.example.administrator.helloas.activity.adapter.ViewPagerAdapter;
import com.example.administrator.helloas.activity.utils.PreferenceUtils;

import java.util.ArrayList;

public class GuideUI extends AppCompatActivity implements ViewPager.OnClickListener, ViewPager.OnPageChangeListener {
    private static final String TAG = "GuideUI";
    //view pager对象
    private ViewPager viewPager;
    //view pager适配器
    private ViewPagerAdapter vpAdapter;
    //图片的容器
    private ArrayList<View> views;
    //引导图片
    private static final int[] pics = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3, R.drawable.guide4, R.drawable.guide5};
    //底部小圆点
    private ImageView[] points;
    //当前选中的位置
    private int currentIndex;

    private Button mButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initView();

        initData();


    }


    private void initView() {
        //实例化arraylist对象
        views = new ArrayList<View>();
        //实例化viewpager对象
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //实例化viewpagerAdapter对象
        vpAdapter = new ViewPagerAdapter(views);
        //进入的按钮
        mButton = (Button) findViewById(R.id.button);
    }

    private void initData() {
        //定义布局并设置一个参数
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        //初始化图片列表
        for (int i = 0; i < pics.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(pics[i]);
            views.add(imageView);
        }
        //设置数据
        viewPager.setAdapter(vpAdapter);
        //设置监听
        viewPager.setOnPageChangeListener(this);
        //初始化小点
        initPoint();
    }

    private void initPoint() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.point);
        points = new ImageView[pics.length];
        Log.i(TAG, "长度为:" + points.length);
        for (int i = 0; i < pics.length; i++) {
            points[i] = (ImageView) linearLayout.getChildAt(i);
            points[i].setEnabled(true);
            points[i].setOnClickListener(this);
            points[i].setTag(i);
        }
        //设置当前位置
        currentIndex = 0;
        //设置图标的颜色
        points[currentIndex].setSelected(true);

    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurrentView(position);
        setCurrentDot(position);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == pics.length - 1) {
            Log.i(TAG, "当前界面的位置为:" + position);
            mButton.setVisibility(View.VISIBLE);
            mButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(GuideUI.this, MainUI.class);
                    startActivity(intent);
                    PreferenceUtils.putBoolean(GuideUI.this, SplashUI.KEY_FIRST_ENTER, false);
                    finish();
                }
            });
        } else {
            mButton.setVisibility(View.GONE);
        }
        setCurrentDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public void setCurrentView(int position) {
        if (position < 0 || position >= pics.length - 1) {
            return;
        }
        viewPager.setCurrentItem(position);
    }


    private void setCurrentDot(int position) {
        if (position < 0 || position > pics.length - 1 || position == currentIndex) {
            return;
        }
        points[position].setSelected(true);
        points[currentIndex].setSelected(false);
        currentIndex = position;
    }
}

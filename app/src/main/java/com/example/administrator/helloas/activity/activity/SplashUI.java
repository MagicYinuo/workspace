package com.example.administrator.helloas.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.example.administrator.helloas.R;
import com.example.administrator.helloas.activity.utils.PreferenceUtils;

/**
 * Created by Administrator on 2016/7/15.
 */
public class SplashUI extends AppCompatActivity {
    private static final long ANIMATION_DURATION = 2000;
    public static final String KEY_FIRST_ENTER = "first_enter";
    private static final String TAG = "SplashUI";

    private View mRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_splash);

        //初始化view
        initView();
    }

    private void initView() {
        mRootView = findViewById(R.id.splash_root);


        //旋转图片
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);

        //缩放图片
        ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);

        //透明动画
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);

        AnimationSet animationSet = new AnimationSet(true);

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(ANIMATION_DURATION);
        animationSet.setAnimationListener(new SplashAnimationListener());

        mRootView.startAnimation(animationSet);
    }

    private class SplashAnimationListener implements Animation.AnimationListener {


        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            //动画播放完毕 1.第一次进入 进入到引导界面 2.非第一次进入,进入主页
            new Thread() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //默认为第一次使用
                    boolean flag = PreferenceUtils.getBoolean(getApplicationContext(), KEY_FIRST_ENTER, true);

                    if (flag) {
                        //进入引导页面
                        Log.i(TAG, "第一次进入");
                        Intent intent = new Intent(SplashUI.this, GuideUI.class);
                        startActivity(intent);
                    } else {
                        //进入主页
                        Log.i(TAG, "进入主页");
                        Intent intent = new Intent(SplashUI.this, MainUI.class);
                        startActivity(intent);
                    }

                    finish();

                }
            }.start();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}

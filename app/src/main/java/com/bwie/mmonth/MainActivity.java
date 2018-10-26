package com.bwie.mmonth;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import base.BaseActivity;
import base.BaseApplication;
import utils.ScreenUtils;
import widget.CircleView;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private CircleView cv;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        cv = findViewById(R.id.cv_circle);
    }

    @Override
    public void initData() {
        super.initData();
        Log.i(TAG, "initData: ");
        // 程序开始时获取屏幕宽高并全局设置
        getScreenPixels();

        // 横向和纵向的动画
        ObjectAnimator widthAnimator = ObjectAnimator.ofFloat(cv, "translationX", 0f, (float) BaseApplication.getInstance().getScreenWidth());
        widthAnimator.setDuration(3000);
        ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(cv, "translationY", 0f, (float) BaseApplication.getInstance().getScreenHeight());
        heightAnimator.setDuration(3000);

        widthAnimator.start();
        heightAnimator.start();

        // 添加动画播放监听
        widthAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                // 动画播放结束时跳转主页面
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**
     * 获取屏幕宽高并全局设置
     */
    private void getScreenPixels() {
        int width = ScreenUtils.getInstance(getWindowManager()).getWidth();
        int height = ScreenUtils.getInstance(getWindowManager()).getHeight();
        BaseApplication.getInstance().setScreenWidth(width);
        BaseApplication.getInstance().setScreenHeight(height);
    }
}

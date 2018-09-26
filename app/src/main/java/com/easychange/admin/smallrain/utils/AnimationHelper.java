package com.easychange.admin.smallrain.utils;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;

import com.easychange.admin.smallrain.MyApplication;
import com.easychange.admin.smallrain.R;

public class AnimationHelper {

    /*刚进入页面时的缩放动画*/
    public static void startScaleAnimation(Context context, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.anim_scale_pic);
        view.startAnimation(animation);
    }

    /*刚进入页面时的笔晃动动画*/
    public static void startRotateAnimation(Context context, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.anim_rotate_paint);
        view.startAnimation(animation);
    }

    /*笔消失动画*/
    public static void startPaintGoneAnimation(Context context, View view) {
        Animation animation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.anim_picgone);
        view.startAnimation(animation);
    }

    /*笔飞到图片上的动画*/
    public static void startPaintMoveAnimation(final View view, final AnimationEndInterface animationEndInterface) {
        int screenWidth = MyUtils.getScreenWidth(MyApplication.getGloableContext());
        int screenHeight = MyUtils.getScreenHeight(MyApplication.getGloableContext());
        TranslateAnimation tr = new TranslateAnimation(0, screenWidth / 2.7f, 0, -(screenHeight / 5.5f));
        RotateAnimation ros = new RotateAnimation(0, -45,
                RotateAnimation.RELATIVE_TO_SELF, 0f, RotateAnimation.RELATIVE_TO_SELF, 0f);
        AnimationSet setAnimation = new AnimationSet(true);
        setAnimation.addAnimation(tr);
        setAnimation.addAnimation(ros);
        setAnimation.setDuration(2000);
        setAnimation.setFillAfter(true);
        setAnimation.setFillBefore(false);
        view.startAnimation(setAnimation);
        setAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                animationEndInterface.onAnimationEnd(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    /*文字飞的动画*/
    public static void startTextMoveAnimation(View view, int x, int y) {
        ObjectAnimator obx = ObjectAnimator.ofFloat(view, "translationX", -x);
        obx.setDuration(2000);
        ObjectAnimator oby = ObjectAnimator.ofFloat(view, "translationY", -y);
        oby.setDuration(2000);
        obx.start();
        oby.start();
    }

    /*文字合并的动画*/
    public static void startTextMergeAnimation(View view, int width) {
        ObjectAnimator obx = ObjectAnimator.ofFloat(view, "translationX", width);
        obx.setDuration(2000);
        obx.start();
    }

    public interface AnimationEndInterface {
        void onAnimationEnd(View view);
    }

}

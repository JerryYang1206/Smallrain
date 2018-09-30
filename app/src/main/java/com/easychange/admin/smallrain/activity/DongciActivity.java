package com.easychange.admin.smallrain.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;
import com.easychange.admin.smallrain.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DongciActivity extends BaseActivity {

    @BindView(R.id.tv_paint)
    TextView tvPaint;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rl_root)
    RelativeLayout rl_root;
    @BindView(R.id.fl_root)
    FrameLayout fl_root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongci);
        ButterKnife.bind(this);
        int right = MyUtils.dip2px(DongciActivity.this, 15);
        TranslateAnimation tr = new TranslateAnimation(0, right, 0, 0);
        tr.setDuration(1000);
        tr.setFillAfter(true);
        tvContent.startAnimation(tr);
        tr.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvPaint.setVisibility(View.VISIBLE);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tvPaint.getLayoutParams();
                layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                tvPaint.setLayoutParams(layoutParams);
                tvPaint.setBackground(null);
                tvPaint.setBackgroundColor(Color.parseColor("#ff0000"));


                RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) tvContent.getLayoutParams();
                layoutParams2.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                layoutParams2.height = LinearLayout.LayoutParams.WRAP_CONTENT;
                layoutParams2.leftMargin = 0;
                layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                tvContent.setLayoutParams(layoutParams2);
                tvContent.setBackground(null);
                tvContent.setBackgroundColor(Color.parseColor("#00ff00"));


                tvPaint.post(new Runnable() {
                    @Override
                    public void run() {
                        rl_root.setBackgroundResource(R.drawable.painttext_bg);
                        int i = (MyUtils.dip2px(DongciActivity.this, 135) - (tvPaint.getWidth() + tvContent.getWidth())) / 2;
                        Log.e("xxx", tvPaint.getWidth() + "\n" + tvContent.getWidth() + "\n" + i);
                        TranslateAnimation tr = new TranslateAnimation(0, -i, 0, 0);
                        tr.setDuration(1000);
                        tr.setFillAfter(true);
                        tvContent.startAnimation(tr);
                        TranslateAnimation tr2 = new TranslateAnimation(0, i, 0, 0);
                        tr2.setDuration(1000);
                        tr2.setFillAfter(true);
                        tvPaint.startAnimation(tr2);
                        tr2.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                xxx();
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void xxx() {
        //        rl_root.setBackgroundResource(R.drawable.faguang_bg);
        //        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) rl_root.getLayoutParams();
        //        layoutParams3.width = MyUtils.dip2px(this, 156);
        //        layoutParams3.height = MyUtils.dip2px(this, 44);
        //        layoutParams3.topMargin = MyUtils.dip2px(this, 55.5f);
        //        rl_root.setLayoutParams(layoutParams3);
        rl_root.setBackground(null);
        fl_root.setBackgroundResource(R.drawable.faguang_bg);
    }
}

package com.easychange.admin.smallrain.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easychange.admin.smallrain.MyApplication;
import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;
import com.easychange.admin.smallrain.utils.AnimationHelper;
import com.easychange.admin.smallrain.utils.MyUtils;
import com.easychange.admin.smallrain.views.CircleProgressBar;
import com.easychange.admin.smallrain.views.IndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MingciTest2Activity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.ll_indicator)
    IndicatorView llIndicator;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.iv_img)
    ImageView ivImg;
    @BindView(R.id.iv_content1)
    ImageView ivContent1;
    @BindView(R.id.iv_content2)
    ImageView ivContent2;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.iv_choose1)
    ImageView ivChoose1;
    @BindView(R.id.tv_choose1)
    TextView tvChoose1;
    @BindView(R.id.iv_choose2)
    ImageView ivChoose2;
    @BindView(R.id.tv_choose2)
    TextView tvChoose2;
    @BindView(R.id.ll_choose1)
    LinearLayout ll_choose1;
    @BindView(R.id.ll_choose2)
    LinearLayout ll_choose2;
    @BindView(R.id.iv_choose3)
    ImageView ivChoose3;
    @BindView(R.id.tv_choose3)
    TextView tvChoose3;
    @BindView(R.id.iv_choose4)
    ImageView ivChoose4;
    @BindView(R.id.tv_choose4)
    TextView tvChoose4;
    @BindView(R.id.ll_choose3)
    LinearLayout ll_choose3;
    @BindView(R.id.ll_choose4)
    LinearLayout ll_choose4;
    @BindView(R.id.tv_paint)
    TextView tvPaint;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rl_root)
    RelativeLayout rl_root;
    @BindView(R.id.fl_root)
    FrameLayout flRoot;
    @BindView(R.id.circleProgressBar)
    CircleProgressBar circleProgressBar; // 自定义的进度条
    private boolean isCorrect;
    private int PROGRESS;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                PROGRESS++;
                if (PROGRESS <= 100) {
                    circleProgressBar.setProgress(PROGRESS);
                    Message message = handler.obtainMessage(1);
                    handler.sendMessageDelayed(message, 100);
                } else {
                    handler.removeMessages(1);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mingci_test2);
        ButterKnife.bind(this);
        AnimationHelper.startScaleAnimation(this, ivImg);
        ll_choose2.setOnClickListener(this);
        ll_choose1.setOnClickListener(this);
        ll_choose3.setOnClickListener(this);
        ll_choose4.setOnClickListener(this);
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 100);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pause:
                finish();
                break;
            case R.id.ll_choose1:

                break;
            case R.id.ll_choose2:
                isCorrect = true;
                ll_choose2.setClickable(false);
                AnimationHelper.startScaleAnimation(mContext, ll_choose2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = MyUtils.getScreenWidth(mContext);
                        int screenHeight = MyUtils.getScreenHeight(mContext);
                        Animation scaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.5f);
                        int y = screenHeight - MyUtils.dip2px(MyApplication.getGloableContext(), 350 + 50) - ll_choose2.getHeight();
                        //因为图片透明边距的问题微调
                        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -y+MyUtils.dip2px(MyApplication.getGloableContext(), 18));
                        AnimationSet setAnimation = new AnimationSet(true);
                        setAnimation.setDuration(1000);
                        setAnimation.addAnimation(scaleAnimation);
                        setAnimation.addAnimation(translateAnimation);
                        setAnimation.setFillAfter(true);
                        ll_choose2.startAnimation(setAnimation);
                        setAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                tvPaint.setVisibility(View.VISIBLE);
                                ll_choose2.clearAnimation();
                                ll_choose2.setVisibility(View.INVISIBLE);
                                ivContent1.setVisibility(View.INVISIBLE);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }
                }, 1000);
                break;
            case R.id.ll_choose3:
                if (!isCorrect) {
                    return;
                }
                ll_choose3.setClickable(false);
                AnimationHelper.startScaleAnimation(mContext, ll_choose3);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = MyUtils.getScreenWidth(mContext);
                        int screenHeight = MyUtils.getScreenHeight(mContext);
                        Animation scaleAnimation = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.5f);
                        int y = screenHeight - MyUtils.dip2px(MyApplication.getGloableContext(), 350 + 50) - ll_choose3.getHeight();
                        //因为图片透明边距的问题微调
                        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, -y+MyUtils.dip2px(MyApplication.getGloableContext(), 18));
                        AnimationSet setAnimation = new AnimationSet(true);
                        setAnimation.setDuration(1000);
                        setAnimation.addAnimation(scaleAnimation);
                        setAnimation.addAnimation(translateAnimation);
                        setAnimation.setFillAfter(true);
                        ll_choose3.startAnimation(setAnimation);
                        setAnimation.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                tvContent.setVisibility(View.VISIBLE);
                                ll_choose3.clearAnimation();
                                ll_choose3.setVisibility(View.INVISIBLE);
                                ivContent2.setVisibility(View.INVISIBLE);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mergeText();
                                    }
                                }, 500);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }
                }, 1000);
                break;
            case R.id.ll_choose4:

                break;
        }
    }

    private void mergeText() {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tvPaint.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        tvPaint.setLayoutParams(layoutParams);
        tvPaint.setBackground(null);

        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) tvContent.getLayoutParams();
        layoutParams2.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams2.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams2.leftMargin = 0;
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tvContent.setLayoutParams(layoutParams2);
        tvContent.setBackground(null);

        tvPaint.post(new Runnable() {
            @Override
            public void run() {
                rl_root.setBackgroundResource(R.drawable.painttext_bg);
                int i = (MyUtils.dip2px(MingciTest2Activity.this, 175) - (tvPaint.getWidth() + tvContent.getWidth())) / 2;
                int paintX = MyUtils.dip2px(MingciTest2Activity.this, 75) - tvPaint.getWidth();
                int contentX = MyUtils.dip2px(MingciTest2Activity.this, 80 - 20) - tvContent.getWidth();
                TranslateAnimation tr = new TranslateAnimation(-contentX, -i, 0, 0);
                tr.setDuration(1000);
                tr.setFillAfter(true);
                tvContent.startAnimation(tr);

                TranslateAnimation tr2 = new TranslateAnimation(paintX, i, 0, 0);
                tr2.setDuration(1000);
                tr2.setFillAfter(true);
                tvPaint.startAnimation(tr2);
                tr2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        rl_root.setBackground(null);
                        flRoot.setBackgroundResource(R.drawable.faguang_bg);
                        //最后放大一下
                        Animation aa = android.view.animation.AnimationUtils.loadAnimation(MingciTest2Activity.this, R.anim.anim_scale_pic);
                        ivImg.startAnimation(aa);
                        aa.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }
}

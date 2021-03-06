package com.easychange.admin.smallrain.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
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

public class MingciTestActivity extends BaseActivity implements View.OnClickListener {

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
        setContentView(R.layout.activity_mingci_test);
        ButterKnife.bind(this);
        AnimationHelper.startScaleAnimation(this, ivImg);
        ll_choose2.setOnClickListener(this);
        ll_choose1.setOnClickListener(this);
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 100);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_choose1:
                if (!isCorrect) {
                    return;
                }
                ll_choose1.setClickable(false);
                AnimationHelper.startScaleAnimation(mContext, ll_choose1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = MyUtils.getScreenWidth(mContext);
                        int screenHeight = MyUtils.getScreenHeight(mContext);
                        ObjectAnimator sax = ObjectAnimator.ofFloat(ll_choose1, "scaleX", 1f, 0.8f);
                        ObjectAnimator say = ObjectAnimator.ofFloat(ll_choose1, "scaleY", 1f, 0.5f);
                        ObjectAnimator obx = ObjectAnimator.ofFloat(ll_choose1, "translationX", screenWidth / 3.2f);
                        int y = screenHeight - MyUtils.dip2px(MyApplication.getGloableContext(), 350 + 50) - ll_choose1.getHeight();
                        //因为图片透明边距的问题加10
                        ObjectAnimator oby = ObjectAnimator.ofFloat(ll_choose1, "translationY", -y-MyUtils.dip2px(MyApplication.getGloableContext(), 10));
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(sax, say, obx, oby);
                        set.setDuration(1000);
                        set.start();
                        set.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                tvContent.setVisibility(View.VISIBLE);
                                ll_choose1.setVisibility(View.GONE);
                                ivContent2.setVisibility(View.INVISIBLE);

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        mergeText();
                                    }
                                }, 500);
                            }
                        });
                    }
                }, 1000);
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
                        ObjectAnimator sax = ObjectAnimator.ofFloat(ll_choose2, "scaleX", 1f, 0.8f);
                        ObjectAnimator say = ObjectAnimator.ofFloat(ll_choose2, "scaleY", 1f, 0.5f);
                        int y = screenHeight - MyUtils.dip2px(MyApplication.getGloableContext(), 350 + 50) - ll_choose2.getHeight();
                        ObjectAnimator obx = ObjectAnimator.ofFloat(ll_choose2, "translationX", -screenWidth / 3.2f);
                        //因为图片透明边距的问题加10
                        ObjectAnimator oby = ObjectAnimator.ofFloat(ll_choose2, "translationY", -y-MyUtils.dip2px(MyApplication.getGloableContext(), 10));
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(sax, say, obx, oby);
                        set.setDuration(1000);
                        set.start();
                        set.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                tvPaint.setVisibility(View.VISIBLE);
                                ll_choose2.setVisibility(View.GONE);
                                ivContent1.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                }, 1000);
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
                int i = (MyUtils.dip2px(MingciTestActivity.this, 175) - (tvPaint.getWidth() + tvContent.getWidth())) / 2;
                int paintX = MyUtils.dip2px(MingciTestActivity.this, 75) - tvPaint.getWidth();
                int contentX = MyUtils.dip2px(MingciTestActivity.this, 80 - 20) - tvContent.getWidth();
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
                        AnimationHelper.startScaleAnimation(MingciTestActivity.this, ivImg);
                        rl_root.setBackground(null);
                        flRoot.setBackgroundResource(R.drawable.faguang_bg);
                        //最后放大一下
                        Animation aa = android.view.animation.AnimationUtils.loadAnimation(MingciTestActivity.this, R.anim.anim_scale_pic);
                        ivImg.startAnimation(aa);
                        aa.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {
                                startActivity(new Intent(MingciTestActivity.this, MingciTest2Activity.class));
                                finish();
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

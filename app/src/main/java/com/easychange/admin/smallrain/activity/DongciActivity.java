package com.easychange.admin.smallrain.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
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
import com.easychange.admin.smallrain.views.IndicatorView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DongciActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_pause)
    ImageView ivPause;
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
    @BindView(R.id.tv_paint)
    TextView tvPaint;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.fl_root)
    FrameLayout flRoot;
    @BindView(R.id.iv_choose1)
    ImageView ivChoose1;
    @BindView(R.id.tv_choose1)
    TextView tvChoose1;
    @BindView(R.id.ll_choose1)
    LinearLayout llChoose1;
    @BindView(R.id.iv_choose2)
    ImageView ivChoose2;
    @BindView(R.id.tv_choose2)
    TextView tvChoose2;
    @BindView(R.id.ll_choose2)
    LinearLayout llChoose2;
    private AnimationDrawable frameAnim;
    private int PROGRESS;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                PROGRESS++;
                if (PROGRESS == 3) {
                    if (frameAnim != null && !frameAnim.isRunning()) {
                        frameAnim.start();
                    }
                } else if (PROGRESS == 7) {
                    if (frameAnim != null && frameAnim.isRunning()) {
                        handler.removeMessages(1);
                        frameAnim.stop();
                        return;
                    }
                }
                Message message = handler.obtainMessage(1);
                handler.sendMessageDelayed(message, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dongci);
        ButterKnife.bind(this);
        frameAnim = new AnimationDrawable();
        // 为AnimationDrawable添加动画帧
        frameAnim.addFrame(getResources().getDrawable(R.drawable.eat1), 500);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.eat2), 500);
        frameAnim.addFrame(getResources().getDrawable(R.drawable.eat3), 500);
        frameAnim.setOneShot(false);
        ivImg.setBackgroundDrawable(frameAnim);
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
        llChoose1.setOnClickListener(this);
        llChoose2.setOnClickListener(this);
        ivPause.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pause:
                finish();
                break;
            case R.id.ll_choose2:
                llChoose2.setClickable(false);
                AnimationHelper.startScaleAnimation(mContext, llChoose2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = MyUtils.getScreenWidth(mContext);
                        int screenHeight = MyUtils.getScreenHeight(mContext);
                        ObjectAnimator sax = ObjectAnimator.ofFloat(llChoose2, "scaleX", 1f, 0.8f);
                        ObjectAnimator say = ObjectAnimator.ofFloat(llChoose2, "scaleY", 1f, 0.5f);
                        int y = screenHeight - MyUtils.dip2px(MyApplication.getGloableContext(), 350 + 50) - llChoose2.getHeight();
//                        ObjectAnimator obx = ObjectAnimator.ofFloat(llChoose2, "translationX", -screenWidth / 3.2f);
                        //因为图片透明边距的问题加10
                        ObjectAnimator oby = ObjectAnimator.ofFloat(llChoose2, "translationY", -y - MyUtils.dip2px(MyApplication.getGloableContext(), 10));
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(sax, say, oby);
                        set.setDuration(1000);
                        set.start();
                        set.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                tvContent.setVisibility(View.VISIBLE);
                                llChoose2.setVisibility(View.GONE);
                                ivContent2.setVisibility(View.INVISIBLE);
                                mergeText();
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
                rlRoot.setBackgroundResource(R.drawable.painttext_bg);
                int i = (MyUtils.dip2px(DongciActivity.this, 175) - (tvPaint.getWidth() + tvContent.getWidth())) / 2;
                int paintX = MyUtils.dip2px(DongciActivity.this, 75) - tvPaint.getWidth();
                int contentX = MyUtils.dip2px(DongciActivity.this, 80 - 20) - tvContent.getWidth();
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
                        rlRoot.setBackground(null);
                        flRoot.setBackgroundResource(R.drawable.faguang_bg);
                        if (frameAnim != null && !frameAnim.isRunning()) {
                            frameAnim.start();
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }
}

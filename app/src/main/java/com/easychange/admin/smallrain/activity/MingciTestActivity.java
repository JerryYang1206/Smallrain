package com.easychange.admin.smallrain.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mingci_test);
        ButterKnife.bind(this);
        AnimationHelper.startScaleAnimation(this, ivImg);
        ll_choose2.setOnClickListener(this);
        ll_choose1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_choose1:
                ll_choose1.setClickable(false);
                AnimationHelper.startScaleAnimation(mContext, ll_choose1);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = MyUtils.getScreenWidth(mContext);
                        ObjectAnimator say = ObjectAnimator.ofFloat(ll_choose1, "scaleY", 1f, 0.5f);
                        ObjectAnimator obx = ObjectAnimator.ofFloat(ll_choose1, "translationX", screenWidth / 3f);
                        int y = MyUtils.dip2px(MyApplication.getGloableContext(), 100);
                        ObjectAnimator oby = ObjectAnimator.ofFloat(ll_choose1, "translationY", -y);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(say, obx, oby);
                        set.setDuration(1000);
                        set.start();
                        set.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                            }
                        });
                    }
                }, 1000);
                break;
            case R.id.ll_choose2:
                ll_choose2.setClickable(false);
                AnimationHelper.startScaleAnimation(mContext, ll_choose2);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int screenWidth = MyUtils.getScreenWidth(mContext);
                        ObjectAnimator say = ObjectAnimator.ofFloat(ll_choose2, "scaleY", 1f, 0.5f);
                        int y = MyUtils.dip2px(MyApplication.getGloableContext(), 100);
                        ObjectAnimator obx = ObjectAnimator.ofFloat(ll_choose2, "translationX", -screenWidth / 3f);
                        ObjectAnimator oby = ObjectAnimator.ofFloat(ll_choose2, "translationY", -y);
                        AnimatorSet set = new AnimatorSet();
                        set.playTogether(say, obx, oby);
                        set.setDuration(1000);
                        set.start();
                        set.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);

                            }
                        });
                    }
                }, 1000);
                break;
        }
    }
}

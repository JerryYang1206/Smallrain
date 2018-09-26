package com.easychange.admin.smallrain.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;
import com.easychange.admin.smallrain.utils.MyUtils;
import com.easychange.admin.smallrain.views.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BalloonActivity extends BaseActivity {

    @BindView(R.id.iv_head)
    CircleImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_jiazhang)
    LinearLayout llJiazhang;
    @BindView(R.id.fl_root)
    FrameLayout fl_root;
    @BindView(R.id.iv_mingci)
    ImageView iv_mingci;
    @BindView(R.id.iv_dongci)
    ImageView iv_dongci;
    @BindView(R.id.iv_juzi)
    ImageView iv_juzi;
    private int flutterHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balloon);
        ButterKnife.bind(this);
        initStatusBar();
        initAnimator();
    }

    @OnClick({R.id.iv_head, R.id.ll_jiazhang, R.id.iv_mingci, R.id.iv_dongci, R.id.iv_juzi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                break;
            case R.id.ll_jiazhang:
                break;
            case R.id.iv_mingci:
                startActivity(new Intent(this,MingciActivity.class));
                break;
            case R.id.iv_dongci:
                break;
            case R.id.iv_juzi:
                break;
        }
    }

    private void startFlutterAnimator(ImageView view) {
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(view, "translationY", -flutterHeight, -flutterHeight - 30);
        transYAnim.setDuration(2000);
        transYAnim.setRepeatMode(ObjectAnimator.REVERSE);
        transYAnim.setRepeatCount(-1);
        transYAnim.start();
    }

    private void startAnimator(ImageView view, int x, int y, long duration, final int position) {
        ObjectAnimator transXAnim = ObjectAnimator.ofFloat(view, "translationX", 0, x);
        transXAnim.setDuration(duration);
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(view, "translationY", 0, y);
        transYAnim.setDuration(duration);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(transXAnim, transYAnim);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (position == 2) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            iv_dongci.setImageResource(R.drawable.dongci_dark);
                            iv_juzi.setImageResource(R.drawable.juzi_dark);
                            startFlutterAnimator(iv_mingci);
                        }
                    }, 500);
                }
            }
        });
    }

    private void initAnimator() {
        iv_mingci.setImageResource(R.drawable.mingci_yellow);
        iv_dongci.setImageResource(R.drawable.dongci_yellow);
        iv_juzi.setImageResource(R.drawable.juzi_yellow);
        int screenWidth = MyUtils.getScreenWidth(this);
        int screenHeight = MyUtils.getScreenHeight(this);
        int marginleft = MyUtils.dip2px(this, 100);
        flutterHeight = screenHeight / 4;
        startAnimator(iv_mingci, marginleft + screenWidth / 11, -(screenHeight / 4), 2000, 0);
        startAnimator(iv_dongci, marginleft + screenWidth / 11 * 4, -(screenHeight / 4 - 200), 2500, 1);
        startAnimator(iv_juzi, marginleft + screenWidth / 11 * 7, -(screenHeight / 4 + 300), 3000, 2);
    }

    private void initStatusBar() {
        //最终方案
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 5.0 全透明实现
            // getWindow.setStatusBarColor(Color.TRANSPARENT)
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }


}

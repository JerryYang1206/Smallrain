package com.easychange.admin.smallrain.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.easychange.admin.smallrain.views.DrawImgView;
import com.easychange.admin.smallrain.views.IndicatorView;
import com.easychange.admin.smallrain.views.WaveCircleView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MingciActivity extends BaseActivity {

    @BindView(R.id.iv_pause)
    ImageView ivPause;
    @BindView(R.id.ll_indicator)
    IndicatorView llIndicator;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.drawImg)
    DrawImgView drawImg;
    @BindView(R.id.iv_paint)
    ImageView ivPaint;
    @BindView(R.id.tv_paint)
    TextView tvPaint;
    @BindView(R.id.tv_paint2)
    TextView tvPaint2;
    @BindView(R.id.tv_content)
    TextView tv_content;
    @BindView(R.id.rl_root)
    RelativeLayout rl_root;
    @BindView(R.id.fl_root)
    FrameLayout fl_root;
    @BindView(R.id.fl_point)
    FrameLayout fl_point;
    @BindView(R.id.waveCirlceView)
    WaveCircleView waveCirlceView;
    @BindView(R.id.iv_point)
    ImageView iv_point;
    private int[] paintList = {
            R.drawable.red_paint,
            R.drawable.black_paint,
            R.drawable.white_paint,
            R.drawable.yellow_paint,
            R.drawable.green_paint,
            R.drawable.green_paint,
            R.drawable.gray_paint
    };
    private boolean isClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mingci);
        ButterKnife.bind(this);
        initStatusBar();
        ivPaint.setImageResource(paintList[3]);
        AnimationHelper.startScaleAnimation(this, drawImg);
        ivPaint.post(new Runnable() {
            @Override
            public void run() {
                ivPaint.setImageResource(R.drawable.yellow_paint_guang);
                AnimationHelper.startRotateAnimation(ivPaint);//笔晃动
            }
        });
        drawImg.setOverBack(new DrawImgView.OverBack() {
            @Override
            public void second() {
                drawImg.move2Path();
            }

            @Override
            public void three() {
                drawImg.move3Path();
            }

            @Override
            public void onEnd() {
                AnimationHelper.startPaintGoneAnimation(MingciActivity.this, ivPaint);
                mergeText();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!isClicked){
                    fl_point.setVisibility(View.VISIBLE);
                    waveCirlceView.startWave();
                }
            }
        }, 5000);
    }

    private void mergeText() {
        tvPaint.setVisibility(View.GONE);
        tvPaint2.setVisibility(View.VISIBLE);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tvPaint2.getLayoutParams();
        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        tvPaint2.setLayoutParams(layoutParams);
        tvPaint2.setBackground(null);


        RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) tv_content.getLayoutParams();
        layoutParams2.width = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams2.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams2.leftMargin = 0;
        layoutParams2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tv_content.setLayoutParams(layoutParams2);
        tv_content.setBackground(null);


        tvPaint2.post(new Runnable() {
            @Override
            public void run() {
                rl_root.setBackgroundResource(R.drawable.painttext_bg);
                int i = (MyUtils.dip2px(MingciActivity.this, 135) - (tvPaint2.getWidth() + tv_content.getWidth())) / 2;
                Log.e("xxx", tvPaint2.getWidth() + "\n" + tv_content.getWidth() + "\n" + i);
                int paintX = MyUtils.dip2px(MingciActivity.this, 35) - tvPaint2.getWidth();
                int contentX = MyUtils.dip2px(MingciActivity.this, 85 - 25) - tv_content.getWidth();
                TranslateAnimation tr = new TranslateAnimation(-contentX, -i, 0, 0);
                tr.setDuration(1000);
                tr.setFillAfter(true);
                tv_content.startAnimation(tr);
                TranslateAnimation tr2 = new TranslateAnimation(paintX, i, 0, 0);
                tr2.setDuration(1000);
                tr2.setFillAfter(true);
                tvPaint2.startAnimation(tr2);
                tr2.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        AnimationHelper.startScaleAnimation(MingciActivity.this, drawImg);
                        rl_root.setBackground(null);
                        fl_root.setBackgroundResource(R.drawable.faguang_bg);
                        startActivity(new Intent(MingciActivity.this,LetsTestActivity.class));
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });
    }

    @OnClick({R.id.iv_pause, R.id.iv_home, R.id.iv_paint, R.id.iv_point})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pause:
                finish();
                break;
            case R.id.iv_home:
                finish();
                break;
            case R.id.iv_paint:
            case R.id.iv_point:
                isClicked = true;
                fl_point.setVisibility(View.GONE);
                ivPaint.setImageResource(R.drawable.yellow_paint);
                startmovePaint();
                startmoveText();
                break;
        }
    }

    private void startmoveText() {
        //下面黄字飞上来
        int x1 = MyUtils.dip2px(this, (85 - 35) / 2 + 25);
        int screenHeight = MyUtils.getScreenHeight(MyApplication.getGloableContext());
        int y = screenHeight - MyUtils.dip2px(this, 305 + 35 + 4.5f) - MyUtils.getStatusBarHeight(this);
        AnimationHelper.startTextMoveAnimation(tvPaint, x1, y);
        //右边的字往右移动一些
        int right = MyUtils.dip2px(this, 25);
        TranslateAnimation tr = new TranslateAnimation(0, right, 0, 0);
        tr.setDuration(1000);
        tr.setFillAfter(true);
        tv_content.startAnimation(tr);
    }

    private void startmovePaint() {
        AnimationHelper.startPaintMoveAnimation(ivPaint, new AnimationHelper.AnimationEndInterface() {
            @Override
            public void onAnimationEnd(View view) {
                drawImg.movePath();
                ivPaint.setClickable(false);
                AnimationHelper.startPaintDrawAnimation(ivPaint);
            }
        });
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

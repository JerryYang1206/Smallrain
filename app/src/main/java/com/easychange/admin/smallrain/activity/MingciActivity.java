package com.easychange.admin.smallrain.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.easychange.admin.smallrain.MyApplication;
import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;
import com.easychange.admin.smallrain.utils.AnimationHelper;
import com.easychange.admin.smallrain.utils.MyUtils;
import com.easychange.admin.smallrain.views.DrawImgView;
import com.easychange.admin.smallrain.views.IndicatorView;

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
    @BindView(R.id.tv_content)
    TextView tv_content;
    private int[] paintList = {
            R.drawable.red_paint,
            R.drawable.black_paint,
            R.drawable.white_paint,
            R.drawable.yellow_paint,
            R.drawable.green_paint,
            R.drawable.green_paint,
            R.drawable.gray_paint
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mingci);
        ButterKnife.bind(this);
        initStatusBar();
        ivPaint.setImageResource(paintList[3]);
        AnimationHelper.startScaleAnimation(this, drawImg);
        AnimationHelper.startRotateAnimation(this, ivPaint);
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
                ivPaint.setVisibility(View.GONE);
                AnimationHelper.startTextMergeAnimation(tvPaint, -80);
                AnimationHelper.startTextMergeAnimation(tv_content, 20);
            }
        });
    }

    @OnClick({R.id.iv_pause, R.id.iv_home, R.id.iv_paint})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_pause:
                finish();
                break;
            case R.id.iv_home:
                break;
            case R.id.iv_paint:
                startmovePaint();
                startmoveText();
                break;
        }
    }

    private void startmoveText() {
        int x1 = MyUtils.dip2px(this, (85 - 45) / 2 + 20);
        int x2 = MyUtils.dip2px(this, (85 - 45) / 2 + 10);
        int screenHeight = MyUtils.getScreenHeight(MyApplication.getGloableContext());
        int y = screenHeight - MyUtils.dip2px(this, 305 + 45) - MyUtils.getStatusBarHeight(this);
        AnimationHelper.startTextMoveAnimation(tvPaint, x1, y);
        AnimationHelper.startTextMergeAnimation(tv_content, x2);//右边的字往右移动一些
    }

    private void startmovePaint() {
        AnimationHelper.startPaintMoveAnimation(ivPaint, new AnimationHelper.AnimationEndInterface() {
            @Override
            public void onAnimationEnd(View view) {
                drawImg.movePath();
                ivPaint.setClickable(false);
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

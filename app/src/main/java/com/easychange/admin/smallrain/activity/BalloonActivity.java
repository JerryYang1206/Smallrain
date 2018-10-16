package com.easychange.admin.smallrain.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easychange.admin.smallrain.MainActivity;
import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;
import com.easychange.admin.smallrain.base.BaseDialog;
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
    private float flutterHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balloon);
        ButterKnife.bind(this);
        initAnimator();
    }

    @OnClick({R.id.iv_head, R.id.ll_jiazhang, R.id.iv_mingci, R.id.iv_dongci, R.id.iv_juzi})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                break;
            case R.id.ll_jiazhang:
                showFamilyDialog();
                break;
            case R.id.iv_mingci:
                startActivity(new Intent(this,MingciActivity.class));
                break;
            case R.id.iv_dongci:
                startActivity(new Intent(this,DongciActivity.class));
                break;
            case R.id.iv_juzi:
                startActivity(new Intent(this,JuZiActivity.class));
                break;
        }
    }

    private void startFlutterAnimator(ImageView view) {
        ObjectAnimator transYAnim = ObjectAnimator.ofFloat(view, "translationY", -flutterHeight, -flutterHeight - 30);
        transYAnim.setDuration(1000);
        transYAnim.setRepeatMode(ObjectAnimator.REVERSE);
        transYAnim.setRepeatCount(-1);
        transYAnim.start();
    }

    private void startAnimator(ImageView view, float x, float y, long duration, final int position) {
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
        flutterHeight = screenHeight / 3.6f;
        startAnimator(iv_mingci, marginleft + screenWidth / 11, -(screenHeight / 3.6f), 1300, 0);
        startAnimator(iv_dongci, marginleft + screenWidth / 11 * 4.3f, -(screenHeight / 5.0f), 1700, 1);
        startAnimator(iv_juzi, marginleft + screenWidth / 11 * 7, -(screenHeight / 2.2f), 2000, 2);
    }

    private void showFamilyDialog() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        int height = MyUtils.dip2px(this, 230);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_family)
                //设置dialogpadding
                .setPaddingdp(20, 0, 20, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.Alpah_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, height)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.getView(R.id.tv_yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(BalloonActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        dialog.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}

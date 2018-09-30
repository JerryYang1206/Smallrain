package com.easychange.admin.smallrain.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;
import com.easychange.admin.smallrain.base.BaseDialog;
import com.easychange.admin.smallrain.utils.MyUtils;
import com.easychange.admin.smallrain.views.VerificationSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.layout_quhao)
    LinearLayout layoutQuhao;
    @BindView(R.id.edt_phone)
    EditText edtPhone;
    @BindView(R.id.tv_next)
    TextView tvNext;
    @BindView(R.id.img_home_right)
    ImageView imgHomeRight;
    // 特殊下标位置
    private static final int PHONE_INDEX_3 = 3;
    private static final int PHONE_INDEX_4 = 4;
    private static final int PHONE_INDEX_8 = 8;
    private static final int PHONE_INDEX_9 = 9;
    private TextView mSeekBar_tv;
    private VerificationSeekBar mSeekBar_Sb;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {    //隐藏滑块，显示密码登录
            switch (msg.what) {
                case 0:
                    Toast.makeText(mContext, "验证码发送成功", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    startActivity(new Intent(mContext, AuthCodeActivity.class));
                    finish();
                    break;

                default:
                    break;
            }
        }

    };
    private BaseDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        edtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0) {
                    return;
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != PHONE_INDEX_3 && i != PHONE_INDEX_8 && s.charAt(i) == ' ') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == PHONE_INDEX_4 || sb.length() == PHONE_INDEX_9) && sb.charAt(sb.length() - 1) != ' ') {
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!sb.toString().equals(s.toString())) {
                    int index = start + 1;
                    if (sb.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }

                    edtPhone.setText(sb.toString());
                    edtPhone.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (MyUtils.isMobileNO(edtPhone.getText().toString())) {
                    tvNext.setTextColor(Color.parseColor("#c06d00"));
                    tvNext.setEnabled(true);
                } else {
                    tvNext.setTextColor(Color.parseColor("#b5ada5"));
                    tvNext.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.layout_quhao, R.id.tv_next, R.id.img_home_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_quhao:
                break;
            case R.id.tv_next:
                showGetCodeDialog();
                break;
            case R.id.img_home_right:
                finish();
                break;
        }
    }

    private void showGetCodeDialog() {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        dialog = builder.setViewId(R.layout.dialog_scoll_getcode)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.CENTER)
                //设置动画
                .setAnimation(R.style.Alpah_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        mSeekBar_tv = dialog.getView(R.id.seekbar_tv);
        mSeekBar_Sb = dialog.getView(R.id.seekbar_sb);
        mSeekBar_Sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  //滑块正在滑动过程中的监听
                if (progress >= 85) {       //滑动动作完成，设置滑块的图片是对号的图片
                    mSeekBar_Sb.setThumb(getResources().getDrawable(R.drawable.thumb_right));

                } else if (progress<=15){          //滑动动作没有完成，设置滑块的图片是箭头的图片
                    mSeekBar_Sb.setThumb(getResources().getDrawable(R.drawable.thumb_left));

                }else {
                    mSeekBar_Sb.setThumb(getResources().getDrawable(R.drawable.thumb_center));
                }

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {   //滑块准备滑动前的监听
//                seekBar.setThumbOffset(0);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                if (seekBar.getProgress() < 90) {
                    seekBar.setProgress(11);
                    mSeekBar_tv.setVisibility(View.GONE);
                    mSeekBar_Sb.setThumb(getResources().getDrawable(R.drawable.thumb_left));
                } else {
                    seekBar.setProgress(92);
                    mSeekBar_tv.setVisibility(View.VISIBLE);
                    mSeekBar_Sb.setThumb(getResources().getDrawable(R.drawable.thumb_right));
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(600);
                                Message message = handler.obtainMessage();
                                message.what = 0;
                                handler.sendMessage(message);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    }).start();

                }


//                //滑块滑动滑动完成后的监听
//                if (seekBar.getProgress() < 95) {
//                    seekBar.setProgress(6);
//
//                } else {
//                    seekBar.setProgress(92);
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                //
//
//                                Thread.sleep(600);
//                                Message message = handler.obtainMessage();
//                                message.what = 0;
//                                handler.sendMessage(message);
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//
//                        }
//                    }).start();


//                }
            }
        });

    }


}

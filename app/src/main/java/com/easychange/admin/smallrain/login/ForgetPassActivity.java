package com.easychange.admin.smallrain.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;
import com.easychange.admin.smallrain.utils.MyUtils;
import com.easychange.admin.smallrain.views.VerificationSeekBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPassActivity extends BaseActivity {

    @BindView(R.id.edt_phone_number)
    EditText edtPhoneNumber;
    @BindView(R.id.seekbar_sb)
    VerificationSeekBar seekbarSb;
    @BindView(R.id.seekbar_tv)
    TextView seekbarTv;
    @BindView(R.id.seekbar_rlyt)
    RelativeLayout seekbarRlyt;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.edt_sure_pass)
    EditText edtSurePass;
    @BindView(R.id.tv_success)
    TextView tvSuccess;
    @BindView(R.id.img_home_right)
    ImageView imgHomeRight;
    // 特殊下标位置
    private static final int PHONE_INDEX_3 = 3;
    private static final int PHONE_INDEX_4 = 4;
    private static final int PHONE_INDEX_8 = 8;
    private static final int PHONE_INDEX_9 = 9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        ButterKnife.bind(this);
        initListener();
    }

    private void initListener() {
        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
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

                    edtPhoneNumber.setText(sb.toString());
                    edtPhoneNumber.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        seekbarSb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {  //滑块正在滑动过程中的监听
                if (progress >= 85) {       //滑动动作完成，设置滑块的图片是对号的图片
                    seekbarSb.setThumb(getResources().getDrawable(R.drawable.thumb_right));

                } else if (progress<=15){          //滑动动作没有完成，设置滑块的图片是箭头的图片
                    seekbarSb.setThumb(getResources().getDrawable(R.drawable.thumb_left));

                }else {
                    seekbarSb.setThumb(getResources().getDrawable(R.drawable.thumb_center));
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
                    seekbarTv.setVisibility(View.VISIBLE);
                    seekbarTv.setText("向右拖动滑块验证");
                    seekBar.setThumb(getResources().getDrawable(R.drawable.thumb_left));
                } else {
                    seekBar.setProgress(92);
                    seekbarTv.setVisibility(View.VISIBLE);
                    seekbarTv.setText("验证成功");
                    seekBar.setThumb(getResources().getDrawable(R.drawable.thumb_right));
                }
            }
        });
    }

    @OnClick({R.id.tv_success, R.id.img_home_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_success:
                break;
            case R.id.img_home_right:
                finish();
                break;
        }
    }
}

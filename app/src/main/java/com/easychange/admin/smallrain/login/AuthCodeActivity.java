package com.easychange.admin.smallrain.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;
import com.easychange.admin.smallrain.utils.SendSmsTimerUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AuthCodeActivity extends BaseActivity {

    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.edt_code)
    com.xw.repo.fillblankview.FillBlankView edtCode;
    @BindView(R.id.img_home_right)
    ImageView imgHomeRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_code);
        ButterKnife.bind(this);
        SendSmsTimerUtils.sendSms(tvGetCode, R.color.text_code, R.color.text_code);
        initListener();
    }

    private void initListener() {
        edtCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length()>=6){
                    startActivity(new Intent(mContext,SetLoginPassActivity.class));
                }
            }
        });
    }

    @OnClick({R.id.tv_get_code, R.id.img_home_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_code:
                SendSmsTimerUtils.sendSms(tvGetCode, R.color.text_code, R.color.text_code);
                break;
            case R.id.img_home_right:
                finish();
                break;
        }
    }
}

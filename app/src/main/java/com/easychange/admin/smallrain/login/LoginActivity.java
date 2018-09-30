package com.easychange.admin.smallrain.login;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easychange.admin.smallrain.MainActivity;
import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_pt_login)
    TextView tvPtLogin;
    @BindView(R.id.view_pt_login)
    View viewPtLogin;
    @BindView(R.id.layout_pt_login)
    LinearLayout layoutPtLogin;
    @BindView(R.id.tv_kj_login)
    TextView tvKjLogin;
    @BindView(R.id.view_kj_login)
    View viewKjLogin;
    @BindView(R.id.layout_kj_login)
    LinearLayout layoutKjLogin;
    @BindView(R.id.edt_phone_number)
    EditText edtPhoneNumber;
    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.tv_forget_pass)
    TextView tvForgetPass;
    @BindView(R.id.layout_pt_content)
    LinearLayout layoutPtContent;
    @BindView(R.id.img_home_right)
    ImageView imgHomeRight;
    @BindView(R.id.edt_kj_number)
    EditText edtKjNumber;
    @BindView(R.id.edt_kj_code)
    EditText edtKjCode;
    @BindView(R.id.img_get_code)
    ImageView imgGetCode;
    @BindView(R.id.tv_kj_sure_login)
    TextView tvKjSureLogin;
    @BindView(R.id.tv_kj_register)
    TextView tvKjRegister;
    @BindView(R.id.edt_duanxin_code)
    EditText edtDuanxinCode;
    @BindView(R.id.tv_get_code)
    TextView tvGetCode;
    @BindView(R.id.layout_kj_content)
    LinearLayout layoutKjContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.layout_pt_login, R.id.layout_kj_login, R.id.tv_login, R.id.tv_register, R.id.tv_forget_pass, R.id.img_home_right
            , R.id.img_get_code, R.id.tv_kj_sure_login, R.id.tv_kj_register, R.id.tv_get_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_pt_login:
                layoutPtContent.setVisibility(View.VISIBLE);
                layoutKjContent.setVisibility(View.GONE);
                viewPtLogin.setVisibility(View.VISIBLE);
                viewKjLogin.setVisibility(View.INVISIBLE);
                tvPtLogin.setTextColor(Color.parseColor("#c06d00"));
                tvKjLogin.setTextColor(Color.parseColor("#000000"));
                break;
            case R.id.layout_kj_login:
                layoutPtContent.setVisibility(View.GONE);
                layoutKjContent.setVisibility(View.VISIBLE);
                viewPtLogin.setVisibility(View.INVISIBLE);
                viewKjLogin.setVisibility(View.VISIBLE);
                tvPtLogin.setTextColor(Color.parseColor("#000000"));
                tvKjLogin.setTextColor(Color.parseColor("#c06d00"));
                break;
            case R.id.tv_login:
                startActivity(new Intent(mContext, MainActivity.class));
                break;
            case R.id.tv_register:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
            case R.id.tv_forget_pass:
                startActivity(new Intent(mContext, ForgetPassActivity.class));
                break;
            case R.id.img_home_right:
                finish();
                break;
            case R.id.img_get_code:
                break;
            case R.id.tv_kj_sure_login:
                startActivity(new Intent(mContext, MainActivity.class));
                break;
            case R.id.tv_kj_register:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
            case R.id.tv_get_code:
                break;
        }
    }

}

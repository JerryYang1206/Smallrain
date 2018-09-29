package com.easychange.admin.smallrain.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.easychange.admin.smallrain.MainActivity;
import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetLoginPassActivity extends BaseActivity {

    @BindView(R.id.edt_pass)
    EditText edtPass;
    @BindView(R.id.edt_sure_pass)
    EditText edtSurePass;
    @BindView(R.id.tv_success)
    TextView tvSuccess;
    @BindView(R.id.img_home_right)
    ImageView imgHomeRight;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_login_pass);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_success, R.id.img_home_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_success:
                if (TextUtils.isEmpty(edtPass.getText().toString())){
                    Toast.makeText(mContext, "请输入密码",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(edtSurePass.getText().toString())){
                    Toast.makeText(mContext, "请输入确认密码",Toast.LENGTH_SHORT).show();
                }else if (!edtSurePass.getText().toString().equals(edtPass.getText().toString())){
                    Toast.makeText(mContext, "两次输入的密码不一致",Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(mContext,LoginActivity.class));
                }
                break;
            case R.id.img_home_right:
                finish();
                break;
        }
    }
}

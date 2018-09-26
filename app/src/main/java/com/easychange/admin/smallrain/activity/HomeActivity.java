package com.easychange.admin.smallrain.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;
import com.easychange.admin.smallrain.login.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.layout_more)
    LinearLayout layoutMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        startActivity(new Intent(this,BalloonActivity.class));
    }

    @OnClick({R.id.btn_register, R.id.btn_login, R.id.layout_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.btn_login:
                break;
            case R.id.layout_more:
//                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}

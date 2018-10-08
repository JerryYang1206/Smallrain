package com.easychange.admin.smallrain.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LetsTestActivity extends BaseActivity {

    @BindView(R.id.iv_bg)
    ImageView ivBg;
    @BindView(R.id.tv_count)
    TextView tvCount;
    private int TIME = 3;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                TIME--;
                if (TIME != 0)
                    tvCount.setText(TIME + "");
                if (TIME > 0) {
                    Message message = handler.obtainMessage(1);
                    handler.sendMessageDelayed(message, 1000);
                } else {
                    startActivity(new Intent(LetsTestActivity.this, MingciTestActivity.class));
                    finish();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lets_test);
        ButterKnife.bind(this);
        Message message = handler.obtainMessage(1);
        handler.sendMessageDelayed(message, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
    }
}

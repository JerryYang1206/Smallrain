package com.easychange.admin.smallrain.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseFragment;
import com.easychange.admin.smallrain.views.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * admin  2018/8/24 wan
 */
public class MoreFragment extends BaseFragment {
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.img_user_data)
    CircleImageView imgUserData;
    @BindView(R.id.img_home_right)
    ImageView imgHomeRight;
    @BindView(R.id.layout_write_data)
    LinearLayout layoutWriteData;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.layout_change_user_name)
    LinearLayout layoutChangeUserName;
    @BindView(R.id.tv_user_pass)
    TextView tvUserPass;
    @BindView(R.id.layout_change_user_pass)
    LinearLayout layoutChangeUserPass;
    @BindView(R.id.tv_buttom_user_phone)
    TextView tvButtomUserPhone;
    @BindView(R.id.layout_change_user_phone)
    LinearLayout layoutChangeUserPhone;
    @BindView(R.id.tv_wangzhan)
    TextView tvWangzhan;
    @BindView(R.id.tv_youxiang)
    TextView tvYouxiang;
    @BindView(R.id.layout_see_weichart)
    LinearLayout layoutSeeWeichart;
    @BindView(R.id.tv_qq_qun)
    TextView tvQqQun;
    @BindView(R.id.layout_pingjia)
    LinearLayout layoutPingjia;
    @BindView(R.id.layout_out_login)
    LinearLayout layoutOutLogin;
    Unbinder unbinder;

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_more, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.img_user_data, R.id.img_home_right, R.id.layout_write_data, R.id.layout_change_user_name, R.id.layout_change_user_pass, R.id.layout_change_user_phone, R.id.layout_see_weichart, R.id.layout_pingjia, R.id.layout_out_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_user_data:
                break;
            case R.id.img_home_right:
                mActivity.finish();
                break;
            case R.id.layout_write_data:

                break;
            case R.id.layout_change_user_name:
                break;
            case R.id.layout_change_user_pass:
                break;
            case R.id.layout_change_user_phone:
                break;
            case R.id.layout_see_weichart:
                break;
            case R.id.layout_pingjia:
                break;
            case R.id.layout_out_login:
                break;
        }
    }
}

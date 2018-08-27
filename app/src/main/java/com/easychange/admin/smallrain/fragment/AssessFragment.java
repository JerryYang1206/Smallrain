package com.easychange.admin.smallrain.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseFragment;

/**
 * admin  2018/8/24 wan
 */
public class AssessFragment extends BaseFragment {
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_assess , null);

        return view;
    }

    @Override
    protected void initLazyData() {

    }
}

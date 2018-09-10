package com.easychange.admin.smallrain.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.easychange.admin.smallrain.R;
import com.easychange.admin.smallrain.base.BaseFragment;
import com.easychange.admin.smallrain.views.CustomHorizontalProgresNoNum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * admin  2018/8/24 wan
 */
public class RecordsFragment extends BaseFragment {
    @BindView(R.id.progress)
    CustomHorizontalProgresNoNum progress;
    @BindView(R.id.tv_progress)
    TextView tvProgress;
    @BindView(R.id.recycler_progress)
    RecyclerView recyclerView;
    Unbinder unbinder;
    private List<String> mlist = new ArrayList<>();


    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_records, null);
        unbinder = ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {
        if (mlist.size() <= 0) {
            mlist.add("");
            mlist.add("");
            mlist.add("");
            mlist.add("");
            mlist.add("");
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setNestedScrollingEnabled(false);
        ProgressAdapter titleAdapter = new ProgressAdapter(R.layout.item_progress_recycle, mlist);
        recyclerView.setAdapter(titleAdapter);

        progress.setProgress(35);
        tvProgress.setText(progress.getProgress() + "%");
    }

    @Override
    protected void initLazyData() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class ProgressAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public ProgressAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {

        }
    }

}

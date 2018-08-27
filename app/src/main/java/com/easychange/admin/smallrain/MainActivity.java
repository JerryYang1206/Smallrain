package com.easychange.admin.smallrain;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.easychange.admin.smallrain.adapter.FragPagerAdapter;
import com.easychange.admin.smallrain.fragment.AssessFragment;
import com.easychange.admin.smallrain.fragment.MoreFragment;
import com.easychange.admin.smallrain.fragment.ProductFragment;
import com.easychange.admin.smallrain.fragment.RecordsFragment;
import com.easychange.admin.smallrain.views.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.act_main_vp)
    NoScrollViewPager actMainVp;
    @BindView(R.id.btn_top_one)
    Button btnTopOne;
    @BindView(R.id.btn_top_two)
    Button btnTopTwo;
    @BindView(R.id.btn_top_three)
    Button btnTopThree;
    @BindView(R.id.btn_top_four)
    Button btnTopFour;
    @BindView(R.id.btn_bottom_one)
    Button btnBottomOne;
    @BindView(R.id.btn_bottom_two)
    Button btnBottomTwo;
    @BindView(R.id.btn_bottom_three)
    Button btnBottomThree;
    @BindView(R.id.btn_bottom_four)
    Button btnBottomFour;

    private List<Fragment> fragments;

    private FragPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragments = new ArrayList<>();
        fragments.add(new ProductFragment());
        fragments.add(new RecordsFragment());
        fragments.add(new AssessFragment());
        fragments.add(new MoreFragment());

        pagerAdapter = new FragPagerAdapter(getSupportFragmentManager(), fragments);
        actMainVp.setAdapter(pagerAdapter);
        actMainVp.setNoScroll(true);
    }

    @OnClick({R.id.btn_bottom_one, R.id.btn_bottom_two, R.id.btn_bottom_three, R.id.btn_bottom_four})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_bottom_one:
                setVisible(1);
                actMainVp.setCurrentItem(0);

                break;
            case R.id.btn_bottom_two:
                setVisible(2);
                actMainVp.setCurrentItem(1);

                break;
            case R.id.btn_bottom_three:
                setVisible(3);
                actMainVp.setCurrentItem(2);

                break;
            case R.id.btn_bottom_four:
                setVisible(4);
                actMainVp.setCurrentItem(3);

                break;
        }
    }

    private void setVisible(int visible) {
        if (visible == 1) {
            btnBottomOne.setVisibility(View.INVISIBLE);
            btnBottomTwo.setVisibility(View.VISIBLE);
            btnBottomThree.setVisibility(View.VISIBLE);
            btnBottomFour.setVisibility(View.VISIBLE);

            btnTopOne.setVisibility(View.VISIBLE);
            btnTopTwo.setVisibility(View.INVISIBLE);
            btnTopThree.setVisibility(View.INVISIBLE);
            btnTopFour.setVisibility(View.INVISIBLE);
        } else if (visible == 2) {
            btnBottomOne.setVisibility(View.VISIBLE);
            btnBottomTwo.setVisibility(View.INVISIBLE);
            btnBottomThree.setVisibility(View.VISIBLE);
            btnBottomFour.setVisibility(View.VISIBLE);

            btnTopOne.setVisibility(View.INVISIBLE);
            btnTopTwo.setVisibility(View.VISIBLE);
            btnTopThree.setVisibility(View.INVISIBLE);
            btnTopFour.setVisibility(View.INVISIBLE);

        } else if (visible == 3) {
            btnBottomOne.setVisibility(View.VISIBLE);
            btnBottomTwo.setVisibility(View.VISIBLE);
            btnBottomThree.setVisibility(View.INVISIBLE);
            btnBottomFour.setVisibility(View.VISIBLE);

            btnTopOne.setVisibility(View.INVISIBLE);
            btnTopTwo.setVisibility(View.INVISIBLE);
            btnTopThree.setVisibility(View.VISIBLE);
            btnTopFour.setVisibility(View.INVISIBLE);

        } else if (visible == 4) {
            btnBottomOne.setVisibility(View.VISIBLE);
            btnBottomTwo.setVisibility(View.VISIBLE);
            btnBottomThree.setVisibility(View.VISIBLE);
            btnBottomFour.setVisibility(View.INVISIBLE);

            btnTopOne.setVisibility(View.INVISIBLE);
            btnTopTwo.setVisibility(View.INVISIBLE);
            btnTopThree.setVisibility(View.INVISIBLE);
            btnTopFour.setVisibility(View.VISIBLE);

        }

    }
}

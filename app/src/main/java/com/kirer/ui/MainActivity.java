package com.kirer.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.kirer.R;
import com.kirer.common.BaseActivity;
import com.kirer.ui.fragment.GoodsListFragment;
import com.kirer.ui.fragment.ScenicListFragment;
import com.kirer.ui.fragment.TravelListFragment;
import com.kirer.widget.image.round.RoundedImageView;
import com.kirer.widget.tab.smart.SmartTabLayout;
import com.kirer.widget.tab.smart.utils.v4.FragmentPagerItemAdapter;
import com.kirer.widget.tab.smart.utils.v4.FragmentPagerItems;

import cn.bmob.v3.BmobUser;

public class MainActivity extends BaseActivity {


    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    private RoundedImageView userCenter;

    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPagerTab = (SmartTabLayout) findViewById(R.id.view_pager_tab);

        String[] titles = {"游记", "景区", "活动", "拼车", "餐饮"};

        Bundle travelBundle = new Bundle();
        travelBundle.putInt(TravelListFragment.KEY_TRAVEL_TYPE, TravelListFragment.HOME_TRAVEL);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(titles[0], TravelListFragment.class, travelBundle)
                .add(titles[1], ScenicListFragment.class)
                .add(titles[2], ScenicListFragment.class)
                .add(titles[3], ScenicListFragment.class)
                .add(titles[4], GoodsListFragment.class)
                .create());

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);


        userCenter = (RoundedImageView) findViewById(R.id.user_center);
        userCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (BmobUser.getCurrentUser(MainActivity.this) == null) {
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, UserCenterActivity.class));
                }
            }
        });

    }
}

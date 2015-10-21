package com.kirer.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.kirer.R;
import com.kirer.bean.Scenic;
import com.kirer.common.BaseActivity;
import com.kirer.ui.fragment.CommentsFragment;
import com.kirer.ui.fragment.IntroduceFragment;
import com.kirer.ui.fragment.TravelListFragment;
import com.kirer.widget.tab.smart.SmartTabLayout;
import com.kirer.widget.tab.smart.utils.v4.FragmentPagerItemAdapter;
import com.kirer.widget.tab.smart.utils.v4.FragmentPagerItems;

/**
 * Created by xinwenbo on 15/8/11.
 */
public class ScenicHomeActivity extends BaseActivity {

    private KenBurnsView headerImage;
    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;

    private Scenic scenic;

    @Override
    public void init(Bundle savedInstanceState) {
        setContentView(R.layout.activity_scenic_home);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        Intent intent = getIntent();
        scenic = (Scenic) intent.getSerializableExtra(Scenic.class.getSimpleName());
        initView();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setToolbar(R.mipmap.ic_arrow_back, "");

        headerImage = (KenBurnsView) findViewById(R.id.header_image);
        scenic.getCover().loadImage(this, headerImage);

        viewPager = (ViewPager) findViewById(R.id.view_pager);

        viewPagerTab = (SmartTabLayout) findViewById(R.id.view_pager_tab);
        String[] titles = {"简介", "点评", "游记"};

        Bundle introduceBundle = new Bundle();
        introduceBundle.putSerializable(Scenic.class.getSimpleName(), scenic);

        Bundle travelBundle = new Bundle();
        travelBundle.putInt(TravelListFragment.KEY_TRAVEL_TYPE, TravelListFragment.SCENIC_TRAVEL);

        final FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(titles[0], IntroduceFragment.class, introduceBundle)
                .add(titles[1], CommentsFragment.class)
                .add(titles[2], TravelListFragment.class, travelBundle)
                .create());

        viewPager.setAdapter(adapter);
        viewPagerTab.setViewPager(viewPager);
    }
}

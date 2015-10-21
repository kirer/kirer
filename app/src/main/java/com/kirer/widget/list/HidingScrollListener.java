package com.kirer.widget.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.kirer.utils.DisplayUtil;

/*
* This class is a ScrollListener for RecyclerView that allows to show/hide
* views when list is scrolled.
* */
public abstract class HidingScrollListener extends RecyclerView.OnScrollListener {

    private int mTabsOffset = 0;
    private int mHeaderOffset = 0;
    private int mStatusBarHeight;
    private int mTabsHeight;
    private int mTabsPadding;
    private int mHeaderHeight;
    private int mTotalScrolledDistance;

    private float percent = 0;


    public HidingScrollListener(Context context) {
        mStatusBarHeight = DisplayUtil.getStatusBarHeight(context);
        mTabsHeight = DisplayUtil.getTabsHeight(context);
        mTabsPadding = DisplayUtil.getTabsPadding(context);
        mHeaderHeight = DisplayUtil.getHeaderHeight(context);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        clipTabsOffset();
        clipHeaderOffset();

        onMoved(mTabsOffset, mHeaderOffset, percent);

        if ((mTabsOffset < mTabsPadding && dy > 0) || (mTabsOffset > 0 && dy < 0)) {
            mTabsOffset += dy;
        }

        Log.e("onScrolled", "mTabsOffset   -->  " + mTabsOffset);
        Log.e("onScrolled", "mTabsPadding  -->  " + mTabsPadding);
        percent = Math.abs(mTabsOffset * 1.0f / mTabsPadding * 1.0f);
        Log.e("onScrolled", "percent       -->  " + percent);

        if ((mHeaderOffset < mHeaderHeight - mTabsHeight - mStatusBarHeight && dy > 0) || (mHeaderOffset > 0 && dy < 0)) {
            mHeaderOffset += dy * ((mHeaderHeight - mTabsHeight - mStatusBarHeight) * 1.0 / (mTabsPadding - mStatusBarHeight) * 1.0) * 1;
        }

        if (mTotalScrolledDistance < 0) {
            mTotalScrolledDistance = 0;
        } else {
            mTotalScrolledDistance += dy;
        }
    }

    private void clipTabsOffset() {
        if (mTabsOffset > mTabsPadding) {
            mTabsOffset = mTabsPadding;
        } else if (mTabsOffset < 0) {
            mTabsOffset = 0;
        }
    }

    private void clipHeaderOffset() {
        if (mHeaderOffset > mHeaderHeight - mTabsHeight - mStatusBarHeight) {
            mHeaderOffset = mHeaderHeight - mTabsHeight - mStatusBarHeight;
        } else if (mHeaderOffset < 0) {
            mHeaderOffset = 0;
        }
    }

    public abstract void onMoved(int tabsOffset, int headerOffset, float percent);

}

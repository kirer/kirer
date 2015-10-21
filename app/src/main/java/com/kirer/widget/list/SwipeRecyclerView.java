package com.kirer.widget.list;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.kirer.R;
import com.kirer.adapter.BaseAdapter;
import com.kirer.utils.DisplayUtil;
import com.kirer.widget.progressbar.other.SmoothProgressBar;

import org.json.JSONArray;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindCallback;

/**
 * Created by tiptimes on 15/8/7.
 */
public class SwipeRecyclerView extends RelativeLayout implements SwipeRefreshLayout.OnRefreshListener, BaseAdapter.OnLoadMoreListener, FindCallback {
    //        Log.e("SwipeRecyclerView", "dataClass name --> " + dataClass.getSimpleName());
    private Context mContext;
    private RelativeLayout container;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private SmoothProgressBar progressBar;

    private BaseAdapter adapter;
    private Class dataClass;

    public SwipeRecyclerView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_swipe_recycler, this);
        container = (RelativeLayout) findViewById(R.id.container);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.primary);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        progressBar = (SmoothProgressBar) findViewById(R.id.progressbar);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        recyclerView.setLayoutManager(layoutManager);
    }

    public void addWhere(String where) {
        this.where = where;
    }

    public void setAdapter(BaseAdapter adapter, Class dataClass) {
        this.adapter = adapter;
        adapter.setOnLoadMoreListener(this);
        recyclerView.setAdapter(adapter);
        this.dataClass = dataClass;
        onRefresh();
    }

    private int STATE_TYPE = STATE_REFRESH;
    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多
    private int limit = 10;// 每页的数据是10条
    private int curPage = 0;
    private String where = "";

    private void queryData(int page) {
        BmobQuery query = new BmobQuery(dataClass.getSimpleName());
        query.setLimit(limit);
        if (!where.equals("")) {
            query.addWhereEndsWith("objectId", where);
        }
        query.setSkip(page * limit);
        query.findObjects(mContext, this);
    }

    @Override
    public void onRefresh() {
        STATE_TYPE = STATE_REFRESH;
        swipeRefreshLayout.setRefreshing(true);
        queryData(0);
    }

    @Override
    public void onLoadMore() {
        STATE_TYPE = STATE_MORE;
        progressBar.setVisibility(View.VISIBLE);
        queryData(curPage);
    }

    @Override
    public void onSuccess(JSONArray jsonArray) {
        if (JSON.parseArray(jsonArray.toString(), dataClass).size() > 0) {
            if (STATE_TYPE == STATE_REFRESH) {
                curPage = 0;
                adapter.setDataList(JSON.parseArray(jsonArray.toString(), dataClass));
                List data = JSON.parseArray(jsonArray.toString(), dataClass);
                adapter.getDataList().add(data.get(0));
                adapter.getDataList().add(data.get(0));
                adapter.getDataList().add(data.get(0));
                adapter.getDataList().add(data.get(0));
                adapter.getDataList().add(data.get(0));
                adapter.getDataList().add(data.get(0));
                adapter.getDataList().add(data.get(0));
                adapter.notifyDataSetChanged();
            } else {
                adapter.getDataList().addAll(JSON.parseArray(jsonArray.toString(), dataClass));
                adapter.notifyDataSetChanged();
            }
            curPage++;
        }
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(int i, String s) {

    }


    public void Some(final View tabView, final View headerView, final View tabBg) {
//
        int paddingTop = DisplayUtil.getTabsPadding(mContext) + DisplayUtil.getTabsHeight(mContext) + DisplayUtil.getTabsHeight(mContext);
        recyclerView.setPadding(recyclerView.getPaddingLeft(), paddingTop, recyclerView.getPaddingRight(), recyclerView.getPaddingBottom());

        HidingScrollListener hidingScrollListener = new HidingScrollListener(mContext) {
            @Override
            public void onMoved(int tabsOffset, int headerOffset, float percent) {
                tabView.setTranslationY(-tabsOffset);
                tabBg.setVisibility(View.VISIBLE);
                tabBg.setAlpha(percent);
                headerView.setTranslationY(-headerOffset);
            }
        };
        recyclerView.setOnScrollListener(hidingScrollListener);
    }
}

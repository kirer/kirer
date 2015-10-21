package com.kirer.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirer.R;
import com.kirer.adapter.ScenicAdapter;
import com.kirer.bean.Scenic;
import com.kirer.widget.list.SwipeRecyclerView;

public class ScenicListFragment extends Fragment {

    private SwipeRecyclerView swipeRecyclerView;
    private ScenicAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scenic_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRecyclerView = (SwipeRecyclerView) view.findViewById(R.id.swipe_recycler_view);

        adapter = new ScenicAdapter();
        swipeRecyclerView.setAdapter(adapter, Scenic.class);

    }

}

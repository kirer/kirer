package com.kirer.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirer.R;
import com.kirer.adapter.CommentAdapter;
import com.kirer.bean.Travels;
import com.kirer.widget.list.SwipeRecyclerView;

public class CommentsFragment extends Fragment {

    private SwipeRecyclerView swipeRecyclerView;
    private CommentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comments, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRecyclerView = (SwipeRecyclerView) view.findViewById(R.id.swipe_recycler_view);

        adapter = new CommentAdapter();
        swipeRecyclerView.setAdapter(adapter, Travels.class);
        swipeRecyclerView.Some(getActivity().findViewById(R.id.view_pager_tab),getActivity().findViewById(R.id.header_image), getActivity().findViewById(R.id.view_pager_tab_bg));
    }

}

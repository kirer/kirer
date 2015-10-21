package com.kirer.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirer.R;
import com.kirer.adapter.TravelAdapter;
import com.kirer.bean.Travels;
import com.kirer.widget.list.SwipeRecyclerView;

public class TravelListFragment extends Fragment {

    public final static String KEY_TRAVEL_TYPE = "TRAVEL_TYPE";
    public int TRAVEL_TYPE = -1;
    public final static int HOME_TRAVEL = 0;
    public final static int SCENIC_TRAVEL = 1;


    private SwipeRecyclerView swipeRecyclerView;
    private TravelAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        TRAVEL_TYPE = bundle.getInt(KEY_TRAVEL_TYPE);
        return inflater.inflate(R.layout.fragment_travel_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeRecyclerView = (SwipeRecyclerView) view.findViewById(R.id.swipe_recycler_view);

        adapter = new TravelAdapter();
        swipeRecyclerView.setAdapter(adapter, Travels.class);

        if (TRAVEL_TYPE == SCENIC_TRAVEL) {
            swipeRecyclerView.Some(getActivity().findViewById(R.id.view_pager_tab), getActivity().findViewById(R.id.header_image), getActivity().findViewById(R.id.view_pager_tab_bg));
        }
    }

}

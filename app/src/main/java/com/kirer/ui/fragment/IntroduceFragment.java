package com.kirer.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kirer.R;
import com.kirer.adapter.BaseAdapter;
import com.kirer.bean.Scenic;
import com.kirer.widget.list.SwipeRecyclerView;

public class IntroduceFragment extends Fragment {

    private SwipeRecyclerView swipeRecyclerView;
    private IntroduceAdapter adapter;

    private Scenic scenic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        scenic = (Scenic) bundle.getSerializable(Scenic.class.getSimpleName());
        return inflater.inflate(R.layout.fragment_scenic_introduce, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRecyclerView = (SwipeRecyclerView) view.findViewById(R.id.swipe_recycler_view);

        adapter = new IntroduceAdapter();
        swipeRecyclerView.addWhere(scenic.getObjectId());
        swipeRecyclerView.setAdapter(adapter, Scenic.class);
        swipeRecyclerView.Some(getActivity().findViewById(R.id.view_pager_tab_container), getActivity().findViewById(R.id.header_image), getActivity().findViewById(R.id.view_pager_tab_bg));

    }


    private class IntroduceAdapter extends BaseAdapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.item_introduce, viewGroup, false);
            return IntroduceViewHolder.newInstance(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            super.onBindViewHolder(viewHolder, i);
            IntroduceViewHolder holder = (IntroduceViewHolder) viewHolder;
            Scenic scenic = (Scenic) dataList.get(i);
            holder.setmItemIntroduce(scenic.getDes());
        }

        @Override
        public int getItemCount() {
            return dataList == null ? 0 : dataList.size();
        }
    }

    static class IntroduceViewHolder extends RecyclerView.ViewHolder {

        private TextView mItemIntroduce;

        public IntroduceViewHolder(View itemView, TextView mItemIntroduce) {
            super(itemView);
            this.mItemIntroduce = mItemIntroduce;
            ViewGroup.LayoutParams lp = mItemIntroduce.getLayoutParams();
            lp.height = ((Activity) itemView.getContext()).getWindowManager().getDefaultDisplay().getHeight();
            this.mItemIntroduce.setLayoutParams(lp);
        }

        public static IntroduceViewHolder newInstance(View itemView) {
            TextView mItemIntroduce = (TextView) itemView.findViewById(R.id.item_introduce);
            return new IntroduceViewHolder(itemView, mItemIntroduce);
        }

        public void setmItemIntroduce(String introduce) {
            this.mItemIntroduce.setText(introduce);
        }
    }

}

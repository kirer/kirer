package com.kirer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirer.R;
import com.kirer.bean.Travels;
import com.kirer.holder.CommentViewHolder;
import com.kirer.holder.TravelViewHolder;

/**
 * Created by tiptimes on 15/8/7.
 */
public class CommentAdapter extends BaseAdapter {

    private Context mContext;

    public CommentAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_comment, viewGroup, false);
        return CommentViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder,i);
        CommentViewHolder holder = (CommentViewHolder) viewHolder;
        Travels travels = (Travels) dataList.get(i);
        holder.setItemAvatar(mContext, travels.getCover());
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }
}

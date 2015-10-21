package com.kirer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kirer.R;
import com.kirer.bean.Travels;
import com.kirer.holder.GoodViewHolder;
import com.kirer.holder.TravelViewHolder;

/**
 * Created by tiptimes on 15/8/7.
 */
public class GoodsAdapter extends BaseAdapter {

    private Context mContext;

    public GoodsAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods, viewGroup, false);
        return GoodViewHolder.newInstance(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        super.onBindViewHolder(viewHolder,i);
        GoodViewHolder holder = (GoodViewHolder) viewHolder;
        Travels travels = (Travels) dataList.get(i);
//        holder.setItemTag(mContext,travels.getTag());
        holder.setItemCover(mContext, travels.getCover());
        holder.setItemTitle(travels.getTitle());
//        holder.setItemAvatar(mContext, travels.getCover());
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }
}

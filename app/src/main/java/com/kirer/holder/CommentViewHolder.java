package com.kirer.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.kirer.R;
import com.kirer.adapter.OnItemClickListener;
import com.kirer.widget.image.round.RoundedImageView;

import cn.bmob.v3.datatype.BmobFile;


/**
 * Created by tiptimes on 15/8/7.
 */
public class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private RoundedImageView itemAvatar;
    private TextView itemName;
    private TextView itemContent;

    public CommentViewHolder(View itemView,RoundedImageView itemAvatar, TextView itemName, TextView itemContent) {
        super(itemView);
        this.itemAvatar = itemAvatar;
        this.itemName = itemName;
        this.itemContent = itemContent;
    }


    public static CommentViewHolder newInstance(View itemView) {
        RoundedImageView itemAvatar = (RoundedImageView) itemView.findViewById(R.id.item_author_avatar);
        TextView itemName = (TextView) itemView.findViewById(R.id.item_name);
        TextView itemContent = (TextView) itemView.findViewById(R.id.item_content);
        return new CommentViewHolder(itemView,itemAvatar, itemName, itemContent);
    }


    public void setItemName(String name) {
        this.itemName.setText(name);
    }

    public void setItemAvatar(Context context, BmobFile avatar) {
        avatar.loadImage(context, this.itemAvatar);
    }

    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.onItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_cover:
                onItemClickListener.onItemClick(view);
                break;
        }
    }

}

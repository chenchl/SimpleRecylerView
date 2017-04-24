package com.ccl.simplerecylerview.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ccl on 2017/4/24.
 * RecyclerView封装抽取
 */
public class BaseAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private OnItemCilckListener listener;

    public BaseAdapterHolder(View itemView) {
        super(itemView);
        //ButterKnife.bind(this, itemView); //自行选择是否使用
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setOnItemClickListener(OnItemCilckListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClick(v, getLayoutPosition());//getLayoutPosition获取最新变化后的position
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (listener != null) {
            listener.onItemLongClick(v, getLayoutPosition());
        }
        return true;
    }

    /**
     * 点击监听
     */
    public interface OnItemCilckListener {

        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }
}

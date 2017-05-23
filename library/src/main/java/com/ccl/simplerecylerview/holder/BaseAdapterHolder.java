package com.ccl.simplerecylerview.holder;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by ccl on 2017/4/24.
 * RecyclerView封装抽取
 */
public class BaseAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private OnItemCilckListener listener;
    private SparseArray<View> mViews;//使用SparseArray考虑到数据量不大的情况下性能更优，且key为ViewId是正好是int类型符合SparseArray定义
    private View mConvertView;

    public BaseAdapterHolder(View itemView) {
        super(itemView);
        //ButterKnife.bind(this, itemView); //自行选择是否使用
        mConvertView = itemView;
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
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {//不存在是存储到SparseArray里 下次使用时不必再次调用findviewbyid 提升效率
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 获取当前view
     *
     * @return
     */
    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 点击监听
     */
    public interface OnItemCilckListener {

        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }
}

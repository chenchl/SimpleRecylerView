package com.ccl.simplerecylerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.ccl.simplerecylerview.holder.BaseAdapterHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ccl on 2017/4/24.
 * RecyclerView封装抽取
 */
public abstract class RecyclerViewAdapter<E, V extends BaseAdapterHolder> extends RecyclerView.Adapter<BaseAdapterHolder>
        implements BaseAdapterHolder.OnItemCilckListener {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_LOADMORE = 1;
    private final boolean isHasMore;
    private final Context context;
    protected ArrayList<E> list;//数据集合
    private OnItemCilckListener listener;

    public RecyclerViewAdapter(Context context, ArrayList<E> list, boolean isHasMore) {
        this.list = list;
        this.isHasMore = isHasMore;
        this.context = context;
    }

    @Override
    public BaseAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM)
            return toCreateViewHolder(context, parent, viewType);
        else if (viewType == TYPE_LOADMORE)
            return toCreateLoadMore(context, parent, viewType);
        return null;
    }

    @Override
    public void onBindViewHolder(BaseAdapterHolder holder, int position) {
        bindData((V) holder, position);
        //设置点击监听事件
        holder.setOnItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        if (isHasMore)
            return getTotalItemCount() + 1;
        else
            return getTotalItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (!isHasMore)
            return TYPE_ITEM;
        // 最后一个item设置为加载更多
        if (position + 1 == getItemCount()) {
            return TYPE_LOADMORE;
        } else {
            return TYPE_ITEM;
        }
    }

    /**
     * 设置点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemCilckListener listener) {
        this.listener = listener;
    }

    /**
     * 添加进集合
     *
     * @param list
     */
    public void addInfoList(List<E> list) {
        this.list.addAll(list);
    }

    /**
     * 清空集合
     */
    public void clearInfoList() {
        list.clear();
    }

    /**
     * 插入数据
     *
     * @param position 插入位置
     */
    public void addData(int position, E data) {
        if (list == null)
            list = new ArrayList<E>();
        list.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 清除某一数据 并刷新
     *
     * @param position
     */
    public void removedata(int position) {
        if (!list.isEmpty()) {
            list.remove(position);
            notifyItemRemoved(position);
        }
    }

    @Override
    public void onItemClick(View v, int position) {
        if (listener != null)
            listener.onItemClick(v, position);
    }

    @Override
    public void onItemLongClick(View v, int position) {
        if (listener != null)
            listener.onItemLongClick(v, position);
    }

    /**
     * 点击监听
     */
    public interface OnItemCilckListener {

        void onItemClick(View v, int position);

        void onItemLongClick(View v, int position);
    }

    /**
     * 创建viewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    public abstract V toCreateViewHolder(Context context, ViewGroup parent, int viewType);

    /**
     * 创建加载更多
     *
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract V toCreateLoadMore(Context context, ViewGroup parent, int viewType);

    /**
     * 给holder绑定数据
     *
     * @param holder
     * @param position
     */
    protected abstract void bindData(V holder, int position);

    /**
     * 获取item总数
     *
     * @return
     */
    protected abstract int getTotalItemCount();
}

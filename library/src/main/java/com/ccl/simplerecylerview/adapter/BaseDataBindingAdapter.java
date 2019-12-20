package com.ccl.simplerecylerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * created by ccl on 2019/12/20
 **/
public abstract class BaseDataBindingAdapter<D, B extends ViewDataBinding> extends RecyclerView.Adapter {

    protected Context mContext;
    protected List<D> mList = new ArrayList<>();
    private OnItemClickListener listener;
    private OnItemLongClickListener longListener;

    public BaseDataBindingAdapter(Context mContext, List<D> mList) {
        this.mContext = mContext;
        this.mList = mList;
        notifyDataSetChanged();
    }

    public BaseDataBindingAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public List<D> getList() {
        return mList;
    }

    public void addData(List<D> list) {
        int start = mList.size();
        mList.addAll(list);
        notifyItemRangeInserted(start, list.size());
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 清除某一数据 并刷新
     *
     * @param position
     */
    public void removedata(int position) {
        if (!mList.isEmpty()) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int resID) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext), this.getItemLayout(resID), parent, false);
        return new BaseDataBindingViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        B binding = DataBindingUtil.getBinding(holder.itemView);
        this.onBindItem(binding, this.mList.get(position), holder);
        if (binding != null) {
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 设置点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 点击监听
     */
    public interface OnItemClickListener<D> {

        void onItemClick(View v, int position, D data);

    }

    /**
     * 设置长按点击监听
     *
     * @param listener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longListener = listener;
    }

    /**
     * 长按点击监听
     */
    public interface OnItemLongClickListener<D> {

        void onItemLongClick(View v, int position, D data);

    }


    public abstract @LayoutRes
    int getItemLayout(int resID);

    protected abstract void onBindItem(B binding, D item, RecyclerView.ViewHolder holder);

    public class BaseDataBindingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        BaseDataBindingViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(v, getLayoutPosition(), mList.get(getLayoutPosition()));
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (longListener != null) {
                longListener.onItemLongClick(v, getLayoutPosition(), mList.get(getLayoutPosition()));//getLayoutPosition获取最新变化后的position
                return true;
            }
            return false;
        }
    }
}

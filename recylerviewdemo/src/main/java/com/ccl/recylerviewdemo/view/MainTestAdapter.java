package com.ccl.recylerviewdemo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ccl.recylerviewdemo.R;
import com.ccl.simplerecylerview.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by ccl on 2017/4/24.
 */
public class MainTestAdapter extends RecyclerViewAdapter<String, MainTestHolder> {

    public MainTestAdapter(Context context, ArrayList<String> list, boolean isHasMore) {
        super(context, list, isHasMore);
    }

    @Override
    public MainTestHolder toCreateViewHolder(Context context, ViewGroup parent, int viewType) {
        return new MainTestHolder(LayoutInflater.from(context).inflate(R.layout.item_list, parent, false));
    }

    @Override
    protected MainTestHolder toCreateLoadMore(Context context, ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void bindData(MainTestHolder holder, int position) {
        holder.idNum.setText(list.get(position));
    }

    @Override
    protected int getTotalItemCount() {
        return list.size();
    }


}

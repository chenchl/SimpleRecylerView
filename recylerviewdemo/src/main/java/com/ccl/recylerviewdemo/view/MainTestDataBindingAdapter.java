package com.ccl.recylerviewdemo.view;

import android.content.Context;

import com.ccl.recylerviewdemo.R;
import com.ccl.recylerviewdemo.databinding.ItemListBinding;
import com.ccl.simplerecylerview.adapter.BaseDataBindingAdapter;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

/**
 * created by ccl on 2019/12/20
 **/
public class MainTestDataBindingAdapter extends BaseDataBindingAdapter<String, ItemListBinding> {

    public MainTestDataBindingAdapter(Context mContext) {
        super(mContext);
    }

    public MainTestDataBindingAdapter(Context mContext, List<String> mList) {
        super(mContext, mList);
    }

    @Override
    public int getItemLayout(int resID) {
        return R.layout.item_list;
    }

    @Override
    protected void onBindItem(ItemListBinding binding, String item, RecyclerView.ViewHolder holder) {
        binding.idNum.setText(item);
    }
}

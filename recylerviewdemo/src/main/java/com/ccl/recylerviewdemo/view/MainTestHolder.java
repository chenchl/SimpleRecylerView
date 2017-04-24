package com.ccl.recylerviewdemo.view;

import android.view.View;
import android.widget.TextView;

import com.ccl.recylerviewdemo.R;
import com.ccl.simplerecylerview.holder.BaseAdapterHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ccl on 2017/4/24.
 */
public class MainTestHolder extends BaseAdapterHolder {

    @BindView(R.id.id_num)
    TextView idNum;

    public MainTestHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

package com.ccl.recylerviewdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ccl.recylerviewdemo.view.MainTestAdapter;
import com.ccl.recylerviewdemo.view.MainTestDataBindingAdapter;
import com.ccl.simplerecylerview.ItemDecoration.RecycleViewDivider;
import com.ccl.simplerecylerview.adapter.BaseDataBindingAdapter;
import com.ccl.simplerecylerview.adapter.RecyclerViewAdapter;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recylerview)
    RecyclerView recylerview;
    private ArrayList<String> mDatas;
    private MainTestAdapter mainTestAdapter;
    private MainTestDataBindingAdapter mainTestDataBindingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        mainTestAdapter = new MainTestAdapter(this, mDatas, false);
        mainTestAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemCilckListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(MainActivity.this, "short=" + position, Toast.LENGTH_SHORT).show();
                mainTestAdapter.addData(position + 1, "add one");
            }

            @Override
            public void onItemLongClick(View v, int position) {
                Toast.makeText(MainActivity.this, "long=" + position, Toast.LENGTH_SHORT).show();
                mainTestAdapter.removedata(position);
            }
        });
        mainTestDataBindingAdapter = new MainTestDataBindingAdapter(this, mDatas);
        mainTestDataBindingAdapter.setOnItemClickListener(new BaseDataBindingAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View v, int position, String data) {
                Toast.makeText(MainActivity.this, "short=" + position + "  " + data, Toast.LENGTH_SHORT).show();
                ArrayList<String> strings = new ArrayList<>();
                strings.add("add "+ position);
                mainTestDataBindingAdapter.addData(strings);
            }

        });
        mainTestDataBindingAdapter.setOnItemLongClickListener(new BaseDataBindingAdapter.OnItemLongClickListener<String>() {
            @Override
            public void onItemLongClick(View v, int position, String data) {
                Toast.makeText(MainActivity.this, "long=" + position, Toast.LENGTH_SHORT).show();
                mainTestDataBindingAdapter.removedata(position);
            }
        });
        //recylerview.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.HORIZONTAL));
        recylerview.setLayoutManager(new LinearLayoutManager(this));
        recylerview.setAdapter(mainTestDataBindingAdapter);
        recylerview.setItemAnimator(new DefaultItemAnimator());
        recylerview.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, R.drawable.default_divider));//纵向view对应横向分割线
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }
}

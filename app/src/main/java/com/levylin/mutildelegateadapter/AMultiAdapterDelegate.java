package com.levylin.mutildelegateadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * 多类型数据适配器
 * Created by LinXin on 2017/6/16.
 */
public abstract class AMultiAdapterDelegate {

    protected RecyclerView.Adapter mAdapter;
    protected Context mContext;

    public AMultiAdapterDelegate(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @LayoutRes
    public abstract int getLayoutId();

    public abstract int getItemType();

    public abstract void convert(BaseViewHolder holder, MultiItemEntity item);

    public void onCreateViewHolder(BaseViewHolder holder, int viewType) {

    }

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        mContext = recyclerView.getContext();
    }
}

package com.levylin.mutildelegateadapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * 多类型数据适配器
 * Created by LinXin on 2017/6/16.
 */
public class MultiDelegateQuickAdapter<T extends MultiItemEntity> extends BaseMultiItemQuickAdapter<T, BaseViewHolder> {

    private SparseArray<Delegate<T>> delegates = new SparseArray<>();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultiDelegateQuickAdapter(List<T> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder holder, T item) {
        Delegate<T> delegate = delegates.get(holder.getItemViewType());
        if (delegate != null) {
            delegate.convert(holder, item);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = super.onCreateViewHolder(parent, viewType);
        Delegate<T> delegate = delegates.get(viewType);
        if (delegate != null) {
            delegate.onCreateViewHolder(holder, viewType);
        }
        return holder;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        for (int i = 0; i < delegates.size(); i++) {
            int key = delegates.keyAt(i);
            Delegate<T> delegate = delegates.get(key);
            delegate.onAttachedToRecyclerView(recyclerView);
        }
    }

    public void addDelegate(Delegate<T> delegate) {
        delegates.append(delegate.getItemType(), delegate);
        addItemType(delegate.getItemType(), delegate.getLayoutId());
    }

    /**
     * 多类型数据适配器
     * Created by LinXin on 2017/6/16.
     */
    public static abstract class Delegate<T extends MultiItemEntity> {

        protected RecyclerView.Adapter mAdapter;
        protected Context mContext;

        public Delegate(RecyclerView.Adapter adapter) {
            this.mAdapter = adapter;
        }

        @LayoutRes
        public abstract int getLayoutId();

        public abstract int getItemType();

        public abstract void convert(BaseViewHolder holder, T item);

        public void onCreateViewHolder(BaseViewHolder holder, int viewType) {

        }

        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            mContext = recyclerView.getContext();
        }
    }
}
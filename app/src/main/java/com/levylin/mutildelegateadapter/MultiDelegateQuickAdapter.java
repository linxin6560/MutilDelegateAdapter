package com.levylin.mutildelegateadapter;

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
public class MultiDelegateQuickAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private SparseArray<AMultiAdapterDelegate> delegates = new SparseArray<>();

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultiDelegateQuickAdapter(List<MultiItemEntity> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder holder, MultiItemEntity item) {
        AMultiAdapterDelegate delegate = delegates.get(holder.getItemViewType());
        if (delegate != null) {
            delegate.convert(holder, item);
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = super.onCreateViewHolder(parent, viewType);
        AMultiAdapterDelegate delegate = delegates.get(viewType);
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
            AMultiAdapterDelegate delegate = delegates.get(key);
            delegate.onAttachedToRecyclerView(recyclerView);
        }
    }

    public void addMultiDelegate(AMultiAdapterDelegate delegate) {
        delegates.append(delegate.getItemType(), delegate);
        addItemType(delegate.getItemType(), delegate.getLayoutId());
    }
}
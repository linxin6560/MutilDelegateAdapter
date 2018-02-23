package com.levylin.mutildelegateadapter.delegate;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.levylin.mutildelegateadapter.MultiDelegateQuickAdapter;
import com.levylin.mutildelegateadapter.MultipleItem;
import com.levylin.mutildelegateadapter.R;

/**
 * Created by LinXin on 2018/2/23.
 */

public class ImageDelegate extends MultiDelegateQuickAdapter.Delegate<MultipleItem>{

    public ImageDelegate(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_image_view;
    }

    @Override
    public int getItemType() {
        return MultipleItem.IMG;
    }

    @Override
    public void convert(BaseViewHolder holder, MultipleItem item) {

    }
}

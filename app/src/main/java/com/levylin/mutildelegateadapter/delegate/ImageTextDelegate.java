package com.levylin.mutildelegateadapter.delegate;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.levylin.mutildelegateadapter.MultiDelegateQuickAdapter;
import com.levylin.mutildelegateadapter.MultipleItem;
import com.levylin.mutildelegateadapter.R;

/**
 * Created by LinXin on 2018/2/23.
 */

public class ImageTextDelegate extends MultiDelegateQuickAdapter.Delegate<MultipleItem> {
    public ImageTextDelegate(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_img_text_view;
    }

    @Override
    public int getItemType() {
        return MultipleItem.IMG_TEXT;
    }

    @Override
    public void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getLayoutPosition() % 2) {
            case 0:
                helper.setImageResource(R.id.iv, R.mipmap.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.iv, R.mipmap.animation_img2);
                break;

        }
    }
}

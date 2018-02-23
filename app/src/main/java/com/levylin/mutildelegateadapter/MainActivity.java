package com.levylin.mutildelegateadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.levylin.mutildelegateadapter.delegate.ImageDelegate;
import com.levylin.mutildelegateadapter.delegate.ImageTextDelegate;
import com.levylin.mutildelegateadapter.delegate.TextDelegate;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MultipleItem Use");
        mRecyclerView = findViewById(R.id.rv_list);
        final List<MultipleItem> data = DataServer.getMultipleItemData();
        final MultiDelegateQuickAdapter<MultipleItem> multipleItemAdapter = new MultiDelegateQuickAdapter<>(data);
        multipleItemAdapter.addDelegate(new TextDelegate(multipleItemAdapter));
        multipleItemAdapter.addDelegate(new ImageDelegate(multipleItemAdapter));
        multipleItemAdapter.addDelegate(new ImageTextDelegate(multipleItemAdapter));
        final GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        mRecyclerView.setAdapter(multipleItemAdapter);
    }
}

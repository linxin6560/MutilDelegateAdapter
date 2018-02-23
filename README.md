##介绍
基于[BaseRecyclerViewAdapterHelper](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)的多类型适配器

##使用

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
                
要做的事情如代码所示，只需要定义delegate，添加到MultiDelegateQuickAdapter即可。
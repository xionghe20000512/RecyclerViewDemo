package com.example.recyclerviewtest.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.beans.ItemBean;

import java.util.List;

public class StaggeredViewAdapter extends RecyclerViewBaseAdapter {
    public StaggeredViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        //在这里创建条目控件
        View view = View.inflate(parent.getContext(), R.layout.item_staggered_view, null);

        return view;
    }
}

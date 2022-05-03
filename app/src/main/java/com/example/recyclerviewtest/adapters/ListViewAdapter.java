package com.example.recyclerviewtest.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.beans.ItemBean;

import java.util.List;

public class ListViewAdapter extends RecyclerViewBaseAdapter {

//    //普通条目类型
//    public static final int TYPE_NORMAL = 0;
//    //加载更多
//    public static final int TYPE_LOADER_MORE = 1;

    public ListViewAdapter(List<ItemBean> data){
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent,int viewType) {
        //穿进去的view其实就是条目的界面
        /*
            两个步骤：
            1、拿到view
            2、创建InnerHolder
         */
        View view = View.inflate(parent.getContext(), R.layout.item_list_view, null);

        return view;
    }

//    @Override
//    public int getItemViewType(int position) {
//        if (position == getItemCount() - 1) {
//            //最后一个返回加载更多
//            return TYPE_LOADER_MORE;
//        }else{
//            return TYPE_NORMAL;
//        }
//    }
}

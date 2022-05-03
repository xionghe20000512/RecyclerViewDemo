package com.example.recyclerviewtest.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.R;
import com.example.recyclerviewtest.beans.MoreTypeBean;

import java.util.List;

public class MoreTypeAdapter extends RecyclerView.Adapter {

    //定义三个常量标识，因为有三中类型
    public static final int TYPE_FULL_IMAGE = 0;
    public static final int TYPE_RIGHT_IMAGE = 1;
    public static final int TYPE_THREE_IMAGE = 2;


    private final List<MoreTypeBean> mData;

    public MoreTypeAdapter(List<MoreTypeBean> data){
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /**
         * 根据viewType来创建条目，这样，条目就可以不一样了
         */

        View view;

        //这里面创建ViewHolder
        if (viewType == TYPE_FULL_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_full_image,null);
            return new FullImageHolder(view);
        }else if (viewType == TYPE_RIGHT_IMAGE){
            view = View.inflate(parent.getContext(), R.layout.item_type_left_title_right_image,null);
            return new RightImageHolder(view);
        }else{
            view = View.inflate(parent.getContext(), R.layout.item_type_three_image,null);
            return new ThreeImageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //设置数据

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    //复写方法，根据条件返回条目类型
    @Override
    public int getItemViewType(int position) {
        MoreTypeBean moreTypeBean = mData.get(position);
        if (moreTypeBean.type == 0) {
            return TYPE_FULL_IMAGE;
        }else if (moreTypeBean.type == 1){
            return TYPE_RIGHT_IMAGE;
        }else{
            return TYPE_THREE_IMAGE;
        }
    }

    /**
     * 这是三个视图样式
     */
    private class FullImageHolder extends RecyclerView.ViewHolder{

        public FullImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class ThreeImageHolder extends RecyclerView.ViewHolder{

        public ThreeImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private class RightImageHolder extends RecyclerView.ViewHolder{

        public RightImageHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

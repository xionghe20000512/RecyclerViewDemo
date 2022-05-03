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

public abstract class RecyclerViewBaseAdapter extends RecyclerView.Adapter<RecyclerViewBaseAdapter.InnerHolder> {

    private final List<ItemBean> mData;
    private OnItemClickListener mOnItemClickListener;

    public RecyclerViewBaseAdapter(List<ItemBean> data){
        this.mData = data;
    }

    /**
     * 找到控件
     * 具体操作-设置抽象类，由具体类去实现
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerViewBaseAdapter.InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = getSubView(parent,viewType);
        return new InnerHolder(view);
    }

    /**
     * 这个就是要让子类去实现
     * @param parent
     * @param viewType
     * @return
     */
    protected abstract View getSubView(ViewGroup parent,int viewType);

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBaseAdapter.InnerHolder holder, int position) {
        //设置数据
        holder.setData(mData.get(position));

        //设置点击事件（找到位置）
        holder.itemView.setTag(position);
        //设置点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int clickPosition = (int) v.getTag();
                    mOnItemClickListener.onItemClick(clickPosition);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    /**
     * 编写回调的步骤：
     * 1.创建这个接口。
     * 2.定义接口内部方法
     * 3.提供设置接口的方法（外部实现）
     * 4.接口的调用
     */
    public void setOnItemClickListener(OnItemClickListener listener) {
        //设置监听，其实就是设置接口，一个回调的借口
        this.mOnItemClickListener = listener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class InnerHolder extends RecyclerView.ViewHolder {

        private ImageView mIcon;
        private TextView mTitle;

        /**
         * 这里的itemView是上面的view
         * 找到内部的控件
         * @param itemView
         */
        public InnerHolder(@NonNull View itemView) {
            super(itemView);

            //找到条目的控件
            mIcon = (ImageView)itemView.findViewById(R.id.icon);
            mTitle = (TextView)itemView.findViewById(R.id.title);

        }

        /**
         * 这个方法用于设置数据
         * @param itemBean
         */
        public void setData(ItemBean itemBean) {

            //设置数据
            mIcon.setImageResource(itemBean.icon);
            mTitle.setText(itemBean.title);
        }
    }
}

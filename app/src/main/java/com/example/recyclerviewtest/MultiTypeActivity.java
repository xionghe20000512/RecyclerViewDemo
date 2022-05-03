package com.example.recyclerviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerviewtest.adapters.MoreTypeAdapter;
import com.example.recyclerviewtest.beans.Datas;
import com.example.recyclerviewtest.beans.MoreTypeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiTypeActivity extends Activity {

    private static final String TAG = "MultiTypeActivity";
    private RecyclerView mRecyclerView;
    private List<MoreTypeBean> mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_type);

        //找到控件
        initView();

        //准备数据
        initData();

        //创建设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //创建适配器
        MoreTypeAdapter adapter = new MoreTypeAdapter(mData);

        //设置适配器
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        mData = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i< Datas.icons.length; i++){
            MoreTypeBean data = new MoreTypeBean();
            data.pic = Datas.icons[i];
            data.type = random.nextInt(3);
            Log.d(TAG,"type == "+data.type);
            mData.add(data);
        }
    }

    private void initView() {
        mRecyclerView = this.findViewById(R.id.more_type_list);
    }
}

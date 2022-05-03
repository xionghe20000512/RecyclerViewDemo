package com.example.recyclerviewtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.recyclerviewtest.adapters.GridViewAdapter;
import com.example.recyclerviewtest.adapters.ListViewAdapter;
import com.example.recyclerviewtest.adapters.RecyclerViewBaseAdapter;
import com.example.recyclerviewtest.adapters.StaggeredViewAdapter;
import com.example.recyclerviewtest.beans.Datas;
import com.example.recyclerviewtest.beans.ItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 总结：
 * 1.首先我们要有控件，这个RecyclerView需要导入依赖
 * 2.找到控件（通过findViewById）
 * 3.准备好数据
 * 4.设置布局管理器
 * 5.创建适配器
 * 6.设置适配器
 * 7.显示数据
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private RecyclerView mList;
    private List<ItemBean> mData;
    private RecyclerViewBaseAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到控件(RecyclerView的控件（整体）)
        mList = (RecyclerView)this.findViewById(R.id.recycler_view);
        //处理下拉刷新
        mRefreshLayout = this.findViewById(R.id.refresh_layout);
        handleDownPullUpdate();

        //准备数据
        /**
         * 一般来说，在现实开发中，数据是从网络中获取，这里只是演示，
         * 在现实开发中也要模拟数据，比如后台没有准备好数据
         */
        initData();
        //设置默认的显示样式
        showList(true,false);
    }

    private void handleDownPullUpdate() {
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent);
        mRefreshLayout.setEnabled(true);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //执行刷新数据的操作
                /**
                 * 当我们在顶部下拉的时候，这个方法就会被触发
                 * 但是，这个方法是MainThread是主线程，不可以执行耗时操作，
                 * 一般来说，我们去请求数据再开一个线程获取
                 * 这里面演示的话，我们只添加一条数据
                 */
                //添加数据
                ItemBean data = new ItemBean();
                data.title = "我是新添加的数据";
                data.icon = R.mipmap.pic_3;
                mData.add(0,data);//添加到第0个位置（最顶上）
                //更新UI
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //这里做两件事，一件是刷新停止，另外一件是更新列表
                        mAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }
                },3000);
            }
        });
    }

    private void initListener() {

        mAdapter.setOnItemClickListener(new RecyclerViewBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //这里处理点击事件（实现接口方法）
                Toast.makeText(MainActivity.this, "点击了"+position+"条目", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 这个方法用于模拟数据
     */
    private void initData() {
        //List<DataBean> --> Adapter --> setAdapter --> 显示数据
        //创建集合
        mData = new ArrayList<>();

        //模拟数据
        for (int i = 0; i< Datas.icons.length; i++){
            //创建对象---------这里就不用get、set方法了，太麻烦
            ItemBean data = new ItemBean();
            data.icon = Datas.icons[i];
            data.title = "我是第"+i+"个条目";

            //添加到集合里
            mData.add(data);
        }
    }

    /**
     * 创建菜单
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 当子菜单被选中后
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            //listView部分
            case R.id.list_view_vertical_standard:
                Log.d(TAG,"点击了list_view_vertical_standard");
                showList(true,false);
                break;
            case R.id.list_view_vertical_reverse:
                Log.d(TAG,"点击了list_view_vertical_reverse");
                showList(true,true);
                break;
            case R.id.list_view_horizontal_standard:
                Log.d(TAG,"点击了list_view_horizontal_standard");
                showList(false,false);
                break;
            case R.id.list_view_horizontal_reverse:
                Log.d(TAG,"点击了list_view_horizontal_reverse");
                showList(false,true);
                break;

            //gridView部分
            case R.id.grid_view_vertical_standard:
                Log.d(TAG,"点击了grid_view_vertical_standard");
                showGrid(true,false);
                break;
            case R.id.grid_view_vertical_reverse:
                Log.d(TAG,"点击了grid_view_vertical_reverse");
                showGrid(true,true);
                break;
            case R.id.grid_view_horizontal_standard:
                Log.d(TAG,"点击了grid_view_horizontal_standard");
                showGrid(false,false);
                break;
            case R.id.grid_view_horizontal_reverse:
                Log.d(TAG,"点击了grid_view_horizontal_reverse");
                showGrid(false,true);
                break;

            //staggerView部分
            case R.id.stagger_view_vertical_standard:
                Log.d(TAG,"点击了stagger_view_vertical_standard");
                showStagger(true,false);
                break;
            case R.id.stagger_view_vertical_reverse:
                Log.d(TAG,"点击了stagger_view_vertical_reverse");
                showStagger(true,true);
                break;
            case R.id.stagger_view_horizontal_standard:
                Log.d(TAG,"点击了stagger_view_horizontal_standard");
                showStagger(false,false);
                break;
            case R.id.stagger_view_horizontal_reverse:
                Log.d(TAG,"点击了stagger_view_horizontal_reverse");
                showStagger(false,true);
                break;

            //多种条目类型
            case R.id.multiple_type:
                //跳到新的Activity里面实现这个功能
                Intent intent = new Intent(this,MultiTypeActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 这个方法用于实现瀑布流
     */
    private void showStagger(boolean isVertical,boolean isReverse) {
        //准备布局管理器
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,isVertical?StaggeredGridLayoutManager.VERTICAL:StaggeredGridLayoutManager.HORIZONTAL);
        //设置方向
        layoutManager.setReverseLayout(isReverse);
        mList.setLayoutManager(layoutManager);

        //创建适配器
        mAdapter = new StaggeredViewAdapter(mData);

        //设置适配器
        mList.setAdapter(mAdapter);

        //创建监听事件
        initListener();

    }

    /**
     * 这个方法用于实现和GridView一样的效果
     */
    private void showGrid(boolean isVertical,boolean isReverse) {
        //创建布局管理器
        //第二个参数是列数
        GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        //通过设置布局管理器来控制
        //设置水平还是垂直，设置反向还是正向
        layoutManager.setOrientation(isVertical?LinearLayoutManager.VERTICAL:LinearLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);

        //设置布局管理器
        mList.setLayoutManager(layoutManager);

        //创建适配器
        mAdapter = new GridViewAdapter(mData);

        //设置适配器
        mList.setAdapter(mAdapter);

        //创建监听事件
        initListener();

    }

    /**
     * 这个方法用于显示listView一样的效果
     */
    private void showList(boolean isVertical,boolean isReverse) {
        //RecyclerView需要设置样式，其实就是设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        //通过设置布局管理器来控制
        //设置水平还是垂直，设置反向还是正向
        layoutManager.setOrientation(isVertical?LinearLayoutManager.VERTICAL:LinearLayoutManager.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);

        //设置布局管理器
        mList.setLayoutManager(layoutManager);

        //创建适配器，把数据穿进去
        mAdapter = new ListViewAdapter(mData);
        //设置到RecyclerView里面
        mList.setAdapter(mAdapter);

        //创建监听事件
        initListener();
    }
}

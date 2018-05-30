package com.example.englishplay.bookrecycle.fragment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.englishplay.bookrecycle.MainActivity;
import com.example.englishplay.bookrecycle.R;
import com.example.englishplay.bookrecycle.adopter.BookAdopter;
import com.example.englishplay.bookrecycle.bean.Book;
import com.example.englishplay.bookrecycle.buy_sell.BuyActivity;
import com.example.englishplay.bookrecycle.tools.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.content.ContentValues.TAG;

/**
 * Created by 解奕鹏 on 2018/5/16.
 */

public class FragmentMainOne extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private View view;
    private SwipeRefreshLayout refreshLayout;
//    public Application application;
    private RecyclerView oneRecycleView;
    private BookAdopter bookAdopter;
    private Context context=new MainActivity().context;
    private List<Book> bookList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_main_one, container, false);
//        application=getActivity().getApplication();
        initBook();
        initViews();
        initData();
        initClick();//点击事件写在Adopter里面
        return view;
    }

    private void initData() {
        //下拉刷新
        refreshLayout.setOnRefreshListener(this);
        //refreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.purple,R.color.swipefefresh_bg); // 进度动画颜色
        //设置RecyclerView的布局方式
        GridLayoutManager layoutManager=new GridLayoutManager(view.getContext(),2);//网格布局
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        bookAdopter=new BookAdopter(bookList);
        oneRecycleView.setLayoutManager(layoutManager);
        oneRecycleView.setAdapter(bookAdopter);
    }

    /**
     * item的点击事件
     */
    private void initClick() {
        bookAdopter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(view.getContext(), BuyActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        refreshLayout=view.findViewById(R.id.one_refresh_Layout);
        oneRecycleView=view.findViewById(R.id.one_recycleView);
    }

    private void initBook() {
        for (int i = 0; i < 10; i++) {
            Random random=new Random();
            int a=random.nextInt(899)+100;
            Book book=new Book("¥："+a,"text"+i);
            bookList.add(book);
        }
    }

    /**
     * 刷新的实现方法
     */
    @Override
    public void onRefresh() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false); // 是否显示刷新进度;false:不显示
            }
        },3000);
    }
}

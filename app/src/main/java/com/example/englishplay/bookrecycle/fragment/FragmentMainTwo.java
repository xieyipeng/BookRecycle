package com.example.englishplay.bookrecycle.fragment;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.englishplay.bookrecycle.R;
import com.example.englishplay.bookrecycle.adopter.BookAdopter;
import com.example.englishplay.bookrecycle.bean.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 解奕鹏 on 2018/5/17.
 */

public class FragmentMainTwo extends Fragment implements SwipeRefreshLayout.OnRefreshListener{

    private View view;
    private SwipeRefreshLayout refreshLayout;
//    public Application application;
    private RecyclerView oneRecycleView;
    private List<Book> bookList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_main_two, container, false);
//        application=getActivity().getApplication();
        initBook();
        initViews();
        initClick();//点击事件写在Adopter里面
        initData();
        return view;
    }
    private void initData() {
        //下拉刷新
        refreshLayout.setOnRefreshListener(this);
        //refreshLayout.setColorSchemeResources(R.color.orange, R.color.green, R.color.purple,R.color.swipefefresh_bg); // 进度动画颜色
        //设置RecyclerView的布局方式
        GridLayoutManager layoutManager=new GridLayoutManager(view.getContext(),2);//网格布局
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        BookAdopter bookAdopter = new BookAdopter(bookList);
        oneRecycleView.setLayoutManager(layoutManager);
        oneRecycleView.setAdapter(bookAdopter);
    }
    private void initClick() {

    }
    private void initViews() {
        refreshLayout=view.findViewById(R.id.two_refresh_Layout);
        oneRecycleView=view.findViewById(R.id.two_recycleView);
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

package com.example.englishplay.bookrecycle;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.englishplay.bookrecycle.adopter.BookAdopter;
import com.example.englishplay.bookrecycle.bean.Book;
import com.example.englishplay.bookrecycle.fragment.FragmentMainFour;
import com.example.englishplay.bookrecycle.fragment.FragmentMainOne;
import com.example.englishplay.bookrecycle.fragment.FragmentMainThree;
import com.example.englishplay.bookrecycle.fragment.FragmentMainTwo;

import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/guolin_blog/article/details/13171191    郭林
//https://blog.csdn.net/yanzi1225627/article/details/30763555
//https://blog.csdn.net/zkjthinking/article/details/77043770    自定义view，图标眼睛转动

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentMainOne messageFragment;
    private FragmentMainTwo contactsFragment;
    private FragmentMainThree newsFragment;
    private FragmentMainFour settingFragment;

    private View messageLayout;
    private View contactsLayout;
    private View newsLayout;
    private View settingLayout;

    private ImageView messageImage;
    private ImageView contactsImage;
    private ImageView newsImage;
    private ImageView settingImage;

    private TextView messageText;
    private TextView contactsText;
    private TextView newsText;
    private TextView settingText;

    public Context context=MainActivity.this;
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        setToolBar();
        setMainActionBar();
        setNavigationView();

        initViews();
        fragmentManager = getSupportFragmentManager();
        // 第一次启动时选中第0个tab
        setTabSelection(0);
    }

    /**
     * NavigationView
     */
    private void setNavigationView() {
        NavigationView navigationView;
        navigationView = findViewById(R.id.main_nav_view);
        navigationView.setCheckedItem(R.id.nav_menu_call);//默认选中
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * 侧边栏
     */
    private void setMainActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);//显示导航按钮
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        }
    }

    /**
     * toolBar
     */
    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.main_toolBar);
        setSupportActionBar(toolbar);
    }


    private void initViews() {
        messageLayout = findViewById(R.id.message_layout);
        contactsLayout = findViewById(R.id.contacts_layout);
        newsLayout = findViewById(R.id.news_layout);
        settingLayout = findViewById(R.id.setting_layout);
        messageImage = findViewById(R.id.message_image);
        contactsImage = findViewById(R.id.contacts_image);
        newsImage = findViewById(R.id.news_image);
        settingImage = findViewById(R.id.setting_image);
        messageText = findViewById(R.id.message_text);
        contactsText = findViewById(R.id.contacts_text);
        newsText = findViewById(R.id.news_text);
        settingText = findViewById(R.id.setting_text);
        messageLayout.setOnClickListener(this);
        contactsLayout.setOnClickListener(this);
        newsLayout.setOnClickListener(this);
        settingLayout.setOnClickListener(this);

        drawerLayout = findViewById(R.id.main_drawerLayout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_layout:
                setTabSelection(0);
                break;
            case R.id.contacts_layout:
                setTabSelection(1);
                break;
            case R.id.news_layout:

                setTabSelection(2);
                break;
            case R.id.setting_layout:
                setTabSelection(3);
                break;
            default:
                break;
        }
    }

    private void setTabSelection(int index) {
        // 每次选中之前先清楚掉上次的选中状态
        clearSelection();
        // 开启一个Fragment事务
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
        hideFragments(transaction);
        switch (index) {
            case 0:
                // 当点击了消息tab时，改变控件的图片和文字颜色
//                messageImage.setImageResource(R.drawable.message_selected);
//                messageText.setTextColor(Color.WHITE);
                if (messageFragment == null) {
                    messageFragment = new FragmentMainOne();
                    transaction.add(R.id.content, messageFragment);
                } else {
                    transaction.show(messageFragment);
                }
                break;
            case 1:
                // 当点击了联系人tab时，改变控件的图片和文字颜色
//                contactsImage.setImageResource(R.drawable.contacts_selected);
//                contactsText.setTextColor(Color.WHITE);
                if (contactsFragment == null) {
                    contactsFragment = new FragmentMainTwo();
                    transaction.add(R.id.content, contactsFragment);
                } else {
                    transaction.show(contactsFragment);
                }
                break;
            case 2:
                // 当点击了动态tab时，改变控件的图片和文字颜色
//                newsImage.setImageResource(R.drawable.news_selected);
//                newsText.setTextColor(Color.WHITE);
                if (newsFragment == null) {
                    newsFragment = new FragmentMainThree();
                    transaction.add(R.id.content, newsFragment);
                } else {
                    transaction.show(newsFragment);
                }
                break;
            case 3:
            default:
                // 当点击了设置tab时，改变控件的图片和文字颜色
//                settingImage.setImageResource(R.drawable.setting_selected);
//                settingText.setTextColor(Color.WHITE);
                if (settingFragment == null) {
                    settingFragment = new FragmentMainFour();
                    transaction.add(R.id.content, settingFragment);
                } else {
                    transaction.show(settingFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
//        messageImage.setImageResource(R.drawable.message_unselected);
        messageText.setTextColor(Color.parseColor("#82858b"));
//        contactsImage.setImageResource(R.drawable.contacts_unselected);
        contactsText.setTextColor(Color.parseColor("#82858b"));
//        newsImage.setImageResource(R.drawable.news_unselected);
        newsText.setTextColor(Color.parseColor("#82858b"));
//        settingImage.setImageResource(R.drawable.setting_unselected);
        settingText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (messageFragment != null) {
            transaction.hide(messageFragment);
        }
        if (contactsFragment != null) {
            transaction.hide(contactsFragment);
        }
        if (newsFragment != null) {
            transaction.hide(newsFragment);
        }
        if (settingFragment != null) {
            transaction.hide(settingFragment);
        }
    }

    /**
     * ToolBar item
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    /**
     * ToolBar item 点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);//打开侧边栏
                break;
            case R.id.toolbar_item_backup:
                //第一行代码 P414
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }
}

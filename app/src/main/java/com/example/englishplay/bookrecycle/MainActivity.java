package com.example.englishplay.bookrecycle;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.englishplay.bookrecycle.fragment.FragmentMainPerson;
import com.example.englishplay.bookrecycle.fragment.FragmentMainBuy;
import com.example.englishplay.bookrecycle.fragment.FragmentMainFirst;
import com.example.englishplay.bookrecycle.fragment.FragmentMainSell;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentMainBuy fragmentMainBuy;
    private FragmentMainSell fragmentMainSell;
    private FragmentMainFirst fragmentMainFirst;
    private FragmentMainPerson fragmentMainPerson;

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

    public Context context = MainActivity.this;
    private DrawerLayout drawerLayout;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        setToolBar();
        setMainActionBar();
        setNavigationView();
        initViews();
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
    }

    /**
     * NavigationView
     */
    private void setNavigationView() {
        NavigationView navigationView;
        navigationView = findViewById(R.id.main_nav_view);
//        navigationView.setCheckedItem(R.id.nav_menu_call);//默认选中
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    /**
     * toolBar
     */
    private void setToolBar() {
        Toolbar toolbar = findViewById(R.id.main_buy_toolBar);
        setSupportActionBar(toolbar);
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
                messageImage.setImageResource(R.drawable.ic_main_first_selected);
                messageText.setTextColor(Color.parseColor("#ff6d6b"));
                if (fragmentMainFirst == null) {
                    fragmentMainFirst = new FragmentMainFirst();
                    transaction.add(R.id.content, fragmentMainFirst);
                } else {
                    transaction.show(fragmentMainFirst);
                }
                break;
            case 1:
                contactsImage.setImageResource(R.drawable.ic_main_buy_select);
                contactsText.setTextColor(Color.parseColor("#ff6d6b"));
                if (fragmentMainBuy == null) {
                    fragmentMainBuy = new FragmentMainBuy();
                    transaction.add(R.id.content, fragmentMainBuy);
                } else {
                    transaction.show(fragmentMainBuy);
                }
                break;
            case 2:
                newsImage.setImageResource(R.drawable.ic_main_sell_select);
                newsText.setTextColor(Color.parseColor("#ff6d6b"));
                if (fragmentMainSell == null) {
                    fragmentMainSell = new FragmentMainSell();
                    transaction.add(R.id.content, fragmentMainSell);
                } else {
                    transaction.show(fragmentMainSell);
                }
                break;
            case 3:
            default:
                settingImage.setImageResource(R.drawable.ic_main_order_select);
                settingText.setTextColor(Color.parseColor("#ff6d6b"));
                if (fragmentMainPerson == null) {
                    fragmentMainPerson = new FragmentMainPerson();
                    transaction.add(R.id.content, fragmentMainPerson);
                } else {
                    transaction.show(fragmentMainPerson);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 清除掉所有的选中状态。
     */
    private void clearSelection() {
        messageImage.setImageResource(R.drawable.ic_main_first);
        messageText.setTextColor(Color.parseColor("#82858b"));
        contactsImage.setImageResource(R.drawable.ic_main_buy);
        contactsText.setTextColor(Color.parseColor("#82858b"));
        newsImage.setImageResource(R.drawable.ic_main_sell);
        newsText.setTextColor(Color.parseColor("#82858b"));
        settingImage.setImageResource(R.drawable.ic_main_order);
        settingText.setTextColor(Color.parseColor("#82858b"));
    }

    /**
     * 将所有的Fragment都置为隐藏状态。
     *
     * @param transaction 用于对Fragment执行操作的事务
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (fragmentMainBuy != null) {
            transaction.hide(fragmentMainBuy);
        }
        if (fragmentMainSell != null) {
            transaction.hide(fragmentMainSell);
        }
        if (fragmentMainFirst != null) {
            transaction.hide(fragmentMainFirst);
        }
        if (fragmentMainPerson != null) {
            transaction.hide(fragmentMainPerson);
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

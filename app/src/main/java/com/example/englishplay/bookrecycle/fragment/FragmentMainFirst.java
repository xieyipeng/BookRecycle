package com.example.englishplay.bookrecycle.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.acker.simplezxing.activity.CaptureActivity;
import com.bumptech.glide.Glide;
import com.example.englishplay.bookrecycle.CustomerServiceActivity;
import com.example.englishplay.bookrecycle.R;
import com.example.englishplay.bookrecycle.SearchResultActivity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static android.support.constraint.Constraints.TAG;

/**
 * Created by 解奕鹏 on 2018/5/17.
 */

public class FragmentMainFirst extends Fragment implements OnBannerListener {
    private Banner banner;
    private ImageView customerNewsImageView;
    ImageView searchOneSearchButton;
    private View view;
    private ImageView card1ImageView;
    private ImageView card2ImageView;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main_first, container, false);

        initViews();
        initClick();
        initBanner();
        return view;
    }

    private void initClick() {
        Bitmap bitmap1 = getHttpBitmap("https://img1.doubanio.com/view/subject/l/public/s28735609.jpg");//机器学习
        card1ImageView.setImageBitmap(bitmap1);
        Bitmap bitmap2 = getHttpBitmap("https://img3.doubanio.com/view/subject/l/public/s9108113.jpg");
        card2ImageView.setImageBitmap(bitmap2);
        searchOneSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestPermission();
                startActivityForResult(new Intent(view.getContext(), CaptureActivity.class), CaptureActivity.REQ_CODE);
            }
        });
        customerNewsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), CustomerServiceActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        searchOneSearchButton = view.findViewById(R.id.search_one_search_ImageView);
        banner = view.findViewById(R.id.main_first_fragment_banner_Banner);
        customerNewsImageView = view.findViewById(R.id.main_first_customer_news_imageview);
        card1ImageView = view.findViewById(R.id.main_first_card1_ImageView);
        card2ImageView = view.findViewById(R.id.main_first_card2_ImageView);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CaptureActivity.REQ_CODE:
                switch (resultCode) {
                    case RESULT_OK:
                        String resutlt = data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT);
                        Log.e(TAG, "onActivityResult: 成功 " + resutlt);
                        Intent intent = new Intent(view.getContext(), SearchResultActivity.class);
                        intent.putExtra("barcode", resutlt);
                        startActivity(intent);
                        break;
                    case RESULT_CANCELED:
                        if (data != null) {
                            Toast.makeText(view.getContext(), "操作取消", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "onActivityResult: result_canceled" + data.getStringExtra(CaptureActivity.EXTRA_SCAN_RESULT));
                        }
                        break;
                }
                break;
        }
    }

    private void requestPermission() {
//        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CAMERA)
//                != PackageManager.PERMISSION_GRANTED) {
//            // 第一次请求权限时，用户如果拒绝，下一次请求shouldShowRequestPermissionRationale()返回true
//            // 向用户解释为什么需要这个权限
//            if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CAMERA)
//                    != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions((Activity) context,
//                                           new String[]{Manifest.permission.CAMERA},
//                                        TAKE_PHOTO_REQUEST_CODE);
//            } else {
//                camera();
//            }
//        }
    }

    private void camera() {

    }

    private void initBanner() {
        //放图片地址的集合
        ArrayList<String> list_path = new ArrayList<>();
        //放标题的集合
        ArrayList<String> list_title = new ArrayList<>();

        list_path.add("https://s1.ax1x.com/2018/06/17/Cx1zPH.png");
        list_path.add("https://s1.ax1x.com/2018/06/17/Cx1jaD.png");
        list_path.add("https://s1.ax1x.com/2018/06/17/Cx1vIe.png");
        list_title.add("好好学习");
        list_title.add("天天向上");
        list_title.add("热爱劳动");
        //设置内置样式，共有六种可以点入方法内逐一体验使用。
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //设置图片加载器，图片加载器在下方
        banner.setImageLoader(new MyLoader());
        //设置图片网址或地址的集合
        banner.setImages(list_path);
        //设置轮播的动画效果，内含多种特效，可点入方法内查找后内逐一体验
        banner.setBannerAnimation(Transformer.Default);
        //设置轮播图的标题集合
        banner.setBannerTitles(list_title);
        //设置轮播间隔时间
        banner.setDelayTime(3000);
        //设置是否为自动轮播，默认是“是”。
        banner.isAutoPlay(true);
        //设置指示器的位置，小点点，左中右。
        banner.setIndicatorGravity(BannerConfig.CENTER)
                //以上内容都可写成链式布局，这是轮播图的监听。比较重要。方法在下面。
                .setOnBannerListener(this)
                //必须最后调用的方法，启动轮播图。
                .start();
    }

    //轮播图的监听方法
    @Override
    public void OnBannerClick(int position) {
        Log.i("tag", "你点了第" + position + "张轮播图");
    }

    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    public static Bitmap getHttpBitmap(String url) {
        URL myFileURL;
        Bitmap bitmap = null;
        try {
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn = (HttpURLConnection) myFileURL.openConnection();
            //设置超时时间为6000毫秒，conn.setConnectionTiem(0);表示没有时间限制
            conn.setConnectTimeout(6000);
            //连接设置获得数据流
            conn.setDoInput(true);
            //不使用缓存
            conn.setUseCaches(false);
            //这句可有可无，没有影响
            //conn.connect();
            //得到数据流
            InputStream is = conn.getInputStream();
            //解析得到图片
            bitmap = BitmapFactory.decodeStream(is);
            //关闭数据流
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "getHttpBitmap: error");
        }
        return bitmap;
    }
}

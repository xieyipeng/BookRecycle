package com.example.englishplay.bookrecycle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishplay.bookrecycle.bean.Book;
import com.example.englishplay.bookrecycle.buy_sell.BuyActivity;
import com.example.englishplay.bookrecycle.buy_sell.SellActivity;
import com.example.englishplay.bookrecycle.tools.HttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


//急速数据：http://www.jisuapi.com/my/profile.php
//AppKey:1388c8484a75e228
//http://www.jisuapi.com/api/barcode2/

public class SearchResultActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView bookTitle;
    private TextView bookPrice;
    private TextView bookAuthor;
    private TextView bookPublish;
    private TextView bookPubdate;
    private TextView bookPages;

    private Button buyButton;
    private Button sellButton;

    private String result;
    private String barcode;
    private Book book = new Book();
//    public static final String APPKEY="1388c8484a75e228";//急速数据AppKey
//    public static final String URL = "http://api.jisuapi.com/barcode2/query";

    public static final String dbURL = "https://api.douban.com/v2/book/isbn/:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        barcode = getIntent().getStringExtra("barcode");//获得barcode
        initView();
        initClicks();
        Get();
    }

    private void initClicks() {
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchResultActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });
        sellButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchResultActivity.this, SellActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        imageView=findViewById(R.id.search_book_ImageView);
        bookTitle=findViewById(R.id.search_book_title);
        bookAuthor=findViewById(R.id.search_book_author);
        bookPublish=findViewById(R.id.search_book_publisher);
        bookPubdate=findViewById(R.id.search_book_pubdate);
        bookPages=findViewById(R.id.search_book_pages);
        bookPrice=findViewById(R.id.search_book_price);

        buyButton=findViewById(R.id.search_result_buy);
        sellButton=findViewById(R.id.search_result_sell);
    }

    private void Get() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = dbURL + barcode;
                result = HttpRequest.sendGet(url);
                Log.e("SearchResultActivity", "run: "+result );
                JSONObject json = null;
                try {
                    json = new JSONObject(result);
                    book.setId(json.optString("id"));
                    book.setTitle(json.getString("title"));
                    book.setPublisher(json.getString("publisher"));
                    book.setPrice(json.getString("price"));
                    book.setPages(json.getString("pages"));
                    book.setBinding(json.getString("binding"));
                    book.setImage_media(json.getString("image"));
                    book.setPubdate(json.getString("pubdate"));
                    String a=json.getString("author");
                    a=a.replace("[","");
                    a=a.replace("]","");
                    a=a.replace("\"","");
                    book.setAuthor(a);

                    final Bitmap bitmap = getHttpBitmap(book.getImage_media());
                    Log.e("SearchResultActivity", "run: "+book.getImage_media() );

                    imageView.post(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setImageBitmap(bitmap);
                        }
                    });

                    bookTitle.post(new Runnable() {
                        @Override
                        public void run() {
                            bookTitle.setText(book.getTitle());
                        }
                    });

                    bookAuthor.post(new Runnable() {
                        @Override
                        public void run() {
                            bookAuthor.setText(book.getAuthor());
                        }
                    });

                    bookPages.post(new Runnable() {
                        @Override
                        public void run() {
                            bookPages.setText(book.getPages());
                        }
                    });

                    bookPrice.post(new Runnable() {
                        @Override
                        public void run() {
                            bookPrice.setText(book.getPrice());
                        }
                    });

                    bookPubdate.post(new Runnable() {
                        @Override
                        public void run() {
                            bookPubdate.setText(book.getPubdate());
                        }
                    });

                    bookPublish.post(new Runnable() {
                        @Override
                        public void run() {
                            bookPublish.setText(book.getPublisher());
                        }
                    });

                } catch (JSONException e) {
                    Log.e("SearchResultActivity", "run: json error" );
                }
            }
        }).start();
    }
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            myFileURL = new URL(url);
            //获得连接
            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
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
        }catch(Exception e){
            e.printStackTrace();
        }
        return bitmap;
    }
}

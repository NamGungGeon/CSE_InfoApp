package com.example.denky.greattimetable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by denky on 2017-03-05.
 */


public class MainActivity extends AppCompatActivity {
    ViewPager pager;
    Thread thread = null;
    Handler handler = null;
    int p=0;	//페이지번호
    int v=1;	//화면 전환 뱡향 1:left to right, 2:right to left
    int cur = 0;
    int maxScroll = 3; // 최대 CustomAdapter의 자료 수
    /*
    static public Bitmap cropBitmap(Bitmap original) { // Bitmap의 세로를 절반으로 자름
        Bitmap result = Bitmap.createBitmap(original
                , original.getWidth()
                , original.getHeight()
                , original.getWidth()
                , original.getHeight() / 2);
        if (result != original) {
            original.recycle();
        }
        return result;
    }
    */

    protected void onCreate(Bundle savedInstanceState) {
        Bitmap[] Img ;
        Img = new Bitmap[maxScroll];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);
        CustomAdapter adapter = new CustomAdapter(getLayoutInflater());
        /******************포스터 Bitmap 가공******************
        for (int i = 0; i < maxScroll; i++) {
            Img[i] = BitmapFactory.decodeResource(getResources(), R.drawable.poster1 + i);
            Img[i] = cropBitmap(Img[i]);
        }
        adapter.getImg(maxScroll, Img); // 가공된 비트맵 전달
        /******************포스터 Bitmap 가공******************/
        //ViewPager에 Adapter 설정
        pager.setAdapter(adapter);
        handler = new Handler() {

            public void handleMessage(Message msg) { // 여기서 이미지 스크롤
                if (v == 1) { // 화면전환이 left to right
                    cur++;
                    pager.setCurrentItem(cur, true);
                    if (cur >= maxScroll) {
                        v = 0;
                    }
                } else if (v == 0) {
                    cur--;
                    pager.setCurrentItem(cur, true);
                    if (cur == 0) {
                        v = 1;
                    }
                }
            }
        };

        thread = new Thread() {
            public void run() {
                super.run();
                while (true) {
                    try {
                        Thread.sleep(10000);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                }
            }
        };
        thread.start();
    }
}

package com.example.denky.greattimetable;

import android.graphics.Bitmap;
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

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager= (ViewPager)findViewById(R.id.pager);

        //ViewPager에 설정할 Adapter 객체 생성
        //ListView에서 사용하는 Adapter와 같은 역할.
        //다만. ViewPager로 스크롤 될 수 있도록 되어 있다는 것이 다름
        //PagerAdapter를 상속받은 CustomAdapter 객체 생성
        //CustomAdapter에게 LayoutInflater 객체 전달
        CustomAdapter adapter= new CustomAdapter(getLayoutInflater());

        //ViewPager에 Adapter 설정
        pager.setAdapter(adapter);
        handler = new Handler(){

            public void handleMessage(Message msg) { // 여기서 이미지 스크롤
                if(v == 1) { // 화면전환이 left to right
                    cur++;
                    pager.setCurrentItem(cur, true);
                    if(cur >=maxScroll){
                        v = 0;
                    }
                } else if(v == 0){
                    cur--;
                    pager.setCurrentItem(cur, true);
                    if(cur == 0){
                        v = 1;
                    }
                }
            }
        };

        thread = new Thread(){
            //run은 jvm이 쓰레드를 채택하면, 해당 쓰레드의 run메서드를 수행한다.
            public void run() {
                super.run();
                while(true){
                    try {
                        Thread.sleep(5000);
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

package com.example.denky.greattimetable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by denky on 2017-03-15.
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
       // ScrollView main_scrollview = (ScrollView) findViewById(R.id.main_scrollView);//스크롤뷰 선언
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listview = (ListView)findViewById(R.id.first_listview);
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
        //카테고리 소스 : 1
        ArrayList<Listview> category1_data=new ArrayList<>();
        Listview category1_l1=new Listview("국가장학재단","클릭하세용", "국가장학금1");
        Listview category1_l2=new Listview("국가장학재단","클릭하세용2", "국가장학금2");
        Listview category1_l3=new Listview("국가장학재단","클릭하세용3", "국가우수장학금(이공계)");




        category1_data.add(category1_l1);
        category1_data.add(category1_l2);
        category1_data.add(category1_l3);
        ListviewAdapter category1_adapter=new ListviewAdapter(this,R.layout.list_item,category1_data);
        listview.setAdapter(category1_adapter);
        //카테고리 소스 : 1

        //카테고리 소스 : 2
        ArrayList<Listview> category2_data=new ArrayList<>();
        Listview category2_l1=new Listview("국가장학재단","클릭하세용", "국가장학금1");
        Listview category2_l2=new Listview("국가장학재단","클릭하세용2", "국가장학금2");
        Listview category2_l3=new Listview("국가장학재단","클릭하세용3", "국가우수장학금(이공계)");

        category2_data.add(category2_l1);
        category2_data.add(category2_l2);
        category2_data.add(category2_l3);
        ListviewAdapter category2_adapter=new ListviewAdapter(this,R.layout.list_item,category2_data);
        listview.setAdapter(category2_adapter);
        //카테고리 소스 : 2
/*
        //카테고리 소스 : 3
        ArrayList<Listview> category3_data=new ArrayList<>();
        Listview category3_l1=new Listview("국가장학재단","클릭하세용", "국가장학금1");
        Listview category3_l2=new Listview("국가장학재단","클릭하세용2", "국가장학금2");
        Listview category3_l3=new Listview("국가장학재단","클릭하세용3", "국가우수장학금(이공계)");

        category3_data.add(category3_l1);
        category3_data.add(category3_l2);
        category3_data.add(category3_l3);
        ListviewAdapter category3_adapter=new ListviewAdapter(this,R.layout.list_item,category3_data);
        listview.setAdapter(category3_adapter);
        //카테고리 소스 : 3

        //카테고리 소스 : 4
        ArrayList<Listview> category4_data=new ArrayList<>();
        Listview category4_l1=new Listview("국가장학재단","클릭하세용", "국가장학금1");
        Listview category4_l2=new Listview("국가장학재단","클릭하세용2", "국가장학금2");
        Listview category4_l3=new Listview("국가장학재단","클릭하세용3", "국가우수장학금(이공계)");

        category1_data.add(category4_l1);
        category1_data.add(category4_l2);
        category1_data.add(category4_l3);
        ListviewAdapter category4_adapter=new ListviewAdapter(this,R.layout.list_item,category4_data);
        listview.setAdapter(category4_adapter);
        //카테고리 소스 : 4
        //카테고리 소스 : 5
        ArrayList<Listview> category5_data=new ArrayList<>();
        Listview category5_l1=new Listview("국가장학재단","클릭하세용", "국가장학금1");
        Listview category5_l2=new Listview("국가장학재단","클릭하세용2", "국가장학금2");
        Listview category5_l3=new Listview("국가장학재단","클릭하세용3", "국가우수장학금(이공계)");

        category1_data.add(category5_l1);
        category1_data.add(category5_l2);
        category1_data.add(category5_l3);
        ListviewAdapter category5_adapter=new ListviewAdapter(this,R.layout.list_item,category5_data);
        listview.setAdapter(category5_adapter);
        //카테고리 소스 : 5

*/


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


    //All Listener Methods are not defined perfectly yet.
    public void clickedMypageBtn(View v){
        Intent openMypage=new Intent(getApplicationContext(),MypageActivity.class);
        startActivity(openMypage);
    }

    public void clickedHomepage(View v){
        Intent openHomepage=new Intent(Intent.ACTION_VIEW, Uri.parse("http://cse.konkuk.ac.kr"));
        startActivity(openHomepage);
    }
    public void scrollToEnd(final ScrollView sv){ // 스크롤업업
       sv.post(new Runnable() {
            @Override
            public void run() {
                sv.fullScroll(View.FOCUS_UP);
            }

        });
    }

    //뒤로가기 버튼을 두번 연속으로 눌러야 종료되게끔 하는 메소드
    private long time= 0;
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=1500){
            time=System.currentTimeMillis();
            Toast.makeText(getApplicationContext(),"뒤로 버튼을 한번 더 누르면 종료합니다.",Toast.LENGTH_SHORT).show();
        }else if(System.currentTimeMillis()-time<1500){
            finish();
        }
    }



}

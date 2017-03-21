package com.example.denky.greattimetable;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by denky on 2017-03-15.
 */


public class MainActivity extends AppCompatActivity {

    String univ=null;
    String major=null;

    ViewPager pager;
    Thread thread = null;
    Handler handler = null;
    Button contest_btn;
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
    ArrayList<ListItem> listitem = new ArrayList<ListItem>();
    ListItem Item;

    ListView first_listview;
    ListView second_listview;
    ListView third_listview;
    ListView fourth_listview;
    ListView fifth_listview;
    protected void onCreate(Bundle savedInstanceState) {
        // ScrollView main_scrollview = (ScrollView) findViewById(R.id.main_scrollView);//스크롤뷰 선언
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first_listview = (ListView)findViewById(R.id.first_listview);
        second_listview = (ListView)findViewById(R.id.second_listview);
        third_listview = (ListView)findViewById(R.id.third_listview);
        fourth_listview = (ListView)findViewById(R.id.fourth_listview);
        fifth_listview = (ListView)findViewById(R.id.fifth_listview);
        Button contest_btn = (Button)findViewById(R.id.contest_btn);
        pager = (ViewPager) findViewById(R.id.pager);
        CustomAdapter adapter = new CustomAdapter(getLayoutInflater());


        contest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ContestActivity.class);
                startActivity(i);
            }
        });


        phpDown php1 = new phpDown(1, first_listview, MainActivity.this);
        php1.getData("http://denkybrain.cafe24.com/cse/scholarship.php");

        phpDown php2 = new phpDown(2, second_listview, MainActivity.this);
        php2.getData("http://denkybrain.cafe24.com/cse/community.php");

        phpDown php3 = new phpDown(3, third_listview, MainActivity.this);
        php3.getData("http://denkybrain.cafe24.com/cse/team.php");
        phpDown php4 = new phpDown(4, fourth_listview, MainActivity.this);
        php4.getData("http://denkybrain.cafe24.com/cse/usedmarket.php");
        //카테고리 소스 : 5  - 추천 사이트 ----- //
        phpDown php5 = new phpDown(5, fifth_listview, MainActivity.this);
        php5.getData("http://denkybrain.cafe24.com/cse/recommend_site2.php");
        //카테고리 소스 : 5

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

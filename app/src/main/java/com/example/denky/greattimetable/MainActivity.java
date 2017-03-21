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

    String myJSON;
    ListView fifth_listview;
    private static final String TAG_RESULTS="result";
    private static final String TITLE = "title";
    private static final String URL = "url";
    private static final String UPDATER ="updater";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    protected void showList(){
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for(int i=0;i<peoples.length();i++){
                JSONObject c = peoples.getJSONObject(i);
                String temp_id = c.getString(TITLE);
                String temp_name = c.getString(URL);
                String temp_updater = c.getString(UPDATER);
                HashMap<String,String> persons = new HashMap<String,String>();
                persons.put(TITLE,temp_id);
                persons.put(URL,temp_name);
                persons.put(UPDATER,temp_updater);
                personList.add(persons);

            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, personList, R.layout.list_item,
                    new String[]{TITLE,URL,UPDATER},
                    new int[]{R.id.list_text, R.id.list_context}
            );
            fifth_listview.setAdapter(adapter);



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void getData(String url){
        class GetDataJSON extends AsyncTask<String, Void, String>{

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while((json = bufferedReader.readLine())!= null){
                        sb.append(json+"\n");
                    }
                    return sb.toString().trim();

                }catch(Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result){
                myJSON=result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }


    protected void onCreate(Bundle savedInstanceState) {
        // ScrollView main_scrollview = (ScrollView) findViewById(R.id.main_scrollView);//스크롤뷰 선언
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView first_listview = (ListView)findViewById(R.id.first_listview);
        ListView second_listview = (ListView)findViewById(R.id.second_listview);
        ListView third_listview = (ListView)findViewById(R.id.third_listview);
        ListView fourth_listview = (ListView)findViewById(R.id.fourth_listview);
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

        /******************포스터 Bitmap 가공******************
         for (int i = 0; i < maxScroll; i++) {
         Img[i] = BitmapFactory.decodeResource(getResources(), R.drawable.poster1 + i);
         Img[i] = cropBitmap(Img[i]);
         }
         adapter.getImg(maxScroll, Img); // 가공된 비트맵 전달
         /******************포스터 Bitmap 가공******************/
        //ViewPager에 Adapter 설정
        //카테고리 소스 : 1
        //카테고리 소스 : 2
        ArrayList<Listview> category1_data=new ArrayList<>();
        Listview category1_l1=new Listview("국가장학재단","클릭하세용", "국가장학금1");
        Listview category1_l2=new Listview("국가장학재단","클릭하세용2", "국가장학금2");
        Listview category1_l3=new Listview("국가장학재단","클릭하세용3", "국가우수장학금(이공계)");

        category1_data.add(category1_l1);
        category1_data.add(category1_l2);
        category1_data.add(category1_l3);
        ListviewAdapter category1_adapter=new ListviewAdapter(this,R.layout.list_item,category1_data , 2);
        first_listview.setAdapter(category1_adapter);
        //카테고리 소스 : 2
        //카테고리 소스 : 2
        ArrayList<Listview> category2_data=new ArrayList<>();
        Listview category2_l1=new Listview("건대파워코더","", "닭그네 탄핵됌ㅅㄱ링");
        Listview category2_l2=new Listview("코딩장인","클릭하세용2", "하루500줄코딩미만잡");
       // Listview category2_l3=new Listview("국가장학재단","클릭하세용3", "국가우수장학금(이공계)");

        category2_data.add(category2_l1);
        category2_data.add(category2_l2);
        //category2_data.add(category2_l3);
        ListviewAdapter category2_adapter=new ListviewAdapter(this,R.layout.list_item,category2_data , 2);
        second_listview.setAdapter(category2_adapter);
        //카테고리 소스 : 2

        //카테고리 소스 : 3
        ArrayList<Listview> category3_data=new ArrayList<>();
        Listview category3_l1=new Listview("국가장학재단","클릭하세용", "국가장학금1");
        Listview category3_l2=new Listview("국가장학재단","클릭하세용2", "국가장학금2");
        Listview category3_l3=new Listview("국가장학재단","클릭하세용3", "국가우수장학금(이공계)");

        category3_data.add(category3_l1);
        category3_data.add(category3_l2);
        category3_data.add(category3_l3);
        ListviewAdapter category3_adapter=new ListviewAdapter(this,R.layout.list_item,category3_data, 3);
        third_listview.setAdapter(category3_adapter);
        //카테고리 소스 : 3

        //카테고리 소스 : 4
        ArrayList<Listview> category4_data=new ArrayList<>();
        Listview category4_l1=new Listview("국가장학재단","클릭하세용", "국가장학금1");
        Listview category4_l2=new Listview("국가장학재단","클릭하세용2", "국가장학금2");
        Listview category4_l3=new Listview("국가장학재단","클릭하세용3", "국가우수장학금(이공계)");

        category4_data.add(category4_l1);
        category4_data.add(category4_l2);
        category4_data.add(category4_l3);
        ListviewAdapter category4_adapter=new ListviewAdapter(this,R.layout.list_item,category4_data, 4);
        fourth_listview.setAdapter(category4_adapter);
        //카테고리 소스 : 4
        personList = new ArrayList<HashMap<String,String>>();
        //카테고리 소스 : 5  - 추천 사이트 ----- //
        getData("http://denkybrain.cafe24.com/cse/recommend_site2.php");


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

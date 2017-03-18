
package com.example.denky.greattimetable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by denky on 2017-03-15.
 */


public class ContestActivity extends AppCompatActivity {
    ViewPager pager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contest);
        ListView first_listview = (ListView)findViewById(R.id.contest_listview);
        CustomAdapter adapter = new CustomAdapter(getLayoutInflater());

        ArrayList<Listview> category1_data=new ArrayList<>();
        Listview category1_l1=new Listview("item1","클릭하세용", "내용입니다");
        Listview category1_l2=new Listview("item2","클릭하세용2", "내용입니다");
        Listview category1_l3=new Listview("item3","클릭하세용3", "내용입니다");

        category1_data.add(category1_l1);
        category1_data.add(category1_l2);
        category1_data.add(category1_l3);
        ListviewAdapter category1_adapter=new ListviewAdapter(this,R.layout.list_item,category1_data, 1);
        first_listview.setAdapter(category1_adapter);
    }

    public void clickedMypageBtn(View v){
        Intent openMypage=new Intent(getApplicationContext(),MypageActivity.class);
        startActivity(openMypage);
    }

    public void clickedHomepage(View v){
        Intent openHomepage=new Intent(Intent.ACTION_VIEW, Uri.parse("http://cse.konkuk.ac.kr"));
        startActivity(openHomepage);
    }

    //뒤로가기 버튼을 두번 연속으로 눌러야 종료되게끔 하는 메소드
    private long time= 0;
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis()-time>=1500){
            time=System.currentTimeMillis();
            finish();
    }
    }

}

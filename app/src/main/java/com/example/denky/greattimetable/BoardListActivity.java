
package com.example.denky.greattimetable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by denky on 2017-03-15.
 */


public class BoardListActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        ListView board_listview = (ListView)findViewById(R.id.board_listview);
        ArrayList<Listview> category1_data=new ArrayList<>();
        Listview category1_l1=new Listview("자유게시판","", "");
        Listview category1_l2=new Listview("인기게시판","", "");
        Listview category1_l3=new Listview("일베저장소","", "");
        category1_data.add(category1_l1);
        category1_data.add(category1_l2);
        category1_data.add(category1_l3);
        ListviewAdapter category1_adapter = new ListviewAdapter(this,R.layout.list_litem_board,category1_data, 10);
        board_listview.setAdapter(category1_adapter);
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

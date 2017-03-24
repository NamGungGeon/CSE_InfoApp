
package com.example.denky.greattimetable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by denky on 2017-03-15.
 */


public class BoardSiteListActivity extends AppCompatActivity {
    ListView board_listview;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_site);
        board_listview = (ListView)findViewById(R.id.board_site_listview);
       // Log.d("result", String.valueOf(board_listview));
        phpDown php = new phpDown(6, board_listview, BoardSiteListActivity.this);
        php.getData("http://denkybrain.cafe24.com/cse/recommend_site2.php");
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

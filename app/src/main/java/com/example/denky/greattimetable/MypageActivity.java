package com.example.denky.greattimetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Windows10 on 2017-03-16.
 */

public class MypageActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_mypage);

        TextView appInfo=(TextView)findViewById(R.id.appinfo);
        appInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAppInfo=new Intent(getApplicationContext(),com.example.denky.greattimetable.AppInformation.class);
                startActivity(openAppInfo);
            }
        });
    }

}

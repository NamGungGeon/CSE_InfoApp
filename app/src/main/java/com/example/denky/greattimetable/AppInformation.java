package com.example.denky.greattimetable;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Windows10 on 2017-03-16.
 */

public class AppInformation extends AppCompatActivity {
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        setContentView(R.layout.app_information);

        //show application information
        TextView temp=(TextView)findViewById(R.id.appVerision);
        temp.setText("앱 버전: "+AppInfo.appVersion);

        temp=(TextView)findViewById(R.id.lastUpdate);
        temp.setText("마지막 업데이트일: "+AppInfo.lastUpdate);

        temp=(TextView)findViewById(R.id.developerInfo);
        temp.setText("개발자 정보: "+AppInfo.developerInfo);

    }
}

//When this app is updated, you must change the value in this class.
class AppInfo {
    //This class is never instantiation
    private AppInfo(){}

    //Using private and getter/setter is better for stable structure,
    //but that is require so much resource, so we select 'final' and 'static'
    static final String appVersion="1.0.0";
    static final String lastUpdate="2017-03-16";
<<<<<<< HEAD
    static final String developerInfo="남궁건 신문기 정위대";
=======
    static final String developerInfo="컴공찐-따 3인방";
>>>>>>> 컴공/add_MyPage_and_See_appInfo_function
}

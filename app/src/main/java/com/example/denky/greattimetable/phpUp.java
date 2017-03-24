package com.example.denky.greattimetable;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by denky on 2017-03-25.
 */
public class phpUp {
    String id, password, univ, major, nickname;
    phpUp(String id, String password, String major, String nickname, String univ){
        this.id = id;
        this.password = password;
        this.major = major;
        this.nickname = nickname;
        this.univ = univ;
    }
    public void HttpPostData() {
        try {
            //--------------------------
            //   URL 설정하고 접속하기
            //--------------------------
            final String SIGNUP = "http://denkybrain.cafe24.com/cse/signup.php";
            URL url = new URL(SIGNUP);       // URL 설정
            HttpURLConnection http = (HttpURLConnection) url.openConnection();   // 접속
            //--------------------------
            //   전송 모드 설정 - 기본적인 설정이다
            //--------------------------
            http.setDefaultUseCaches(false);
            http.setDoInput(true);                         // 서버에서 읽기 모드 지정
            http.setDoOutput(true);                       // 서버로 쓰기 모드 지정
            http.setRequestMethod("POST");         // 전송 방식은 POST

            // 서버에게 웹에서 <Form>으로 값이 넘어온 것과 같은 방식으로 처리하라는 걸 알려준다
            http.setRequestProperty("content-type", "application/x-www-form-urlencoded");
            //--------------------------
            //   서버로 값 전송
            //--------------------------
            StringBuffer buffer = new StringBuffer();
            buffer.append("id").append("=").append(id).append("&");                 // php 변수에 값 대입
            buffer.append("password").append("=").append(password).append("&");   // php 변수 앞에 '$' 붙이지 않는다
            buffer.append("univ").append("=").append(univ).append("&");           // 변수 구분은 '&' 사용
            buffer.append("major").append("=").append(major).append("&");
            buffer.append("nickname").append("=").append(nickname);

            OutputStreamWriter outStream = new OutputStreamWriter(http.getOutputStream(), "EUC-KR");
            PrintWriter writer = new PrintWriter(outStream);
            writer.write(buffer.toString());
            writer.flush();
            //--------------------------
            //   서버에서 전송받기 -> 아직 php에서 데이터 전송 완료 echo 안 보냄
            //--------------------------
            /*
            InputStreamReader tmp = new InputStreamReader(http.getInputStream(), "EUC-KR");
            BufferedReader reader = new BufferedReader(tmp);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = reader.readLine()) != null) {       // 서버에서 라인단위로 보내줄 것이므로 라인단위로 읽는다
                builder.append(str + "\n");                     // View에 표시하기 위해 라인 구분자 추가
            }
            //myResult = builder.toString();                       // 전송결과를 전역 변수에 저장
            //((TextView)(findViewById(R.id.text_result))).setText(myResult);
            //Toast.makeText(MainActivity.this, "전송 후 결과 받음", 0).show();
            */
        } catch (MalformedURLException e) {
            //
        } catch (IOException e) {
            //
        } // try
    } // HttpPostData
}
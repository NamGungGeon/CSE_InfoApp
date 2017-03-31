package com.example.denky.greattimetable;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by denky on 2017-03-18.
 */


public class SignupActivity extends AppCompatActivity {

    String id;
    boolean valid_id=false;
    boolean isCheck_id=false;
    EditText _id;

    String nickname;
    boolean valid_nn=false;
    boolean isCheck_nn=false;
    EditText _nickname;

    String password;
    EditText pw;
    EditText checkpw;

    TextView univ;
    String selectedUniv;

    TextView major;
    String selectedMajor;

    AlertDialogFragment dialogFragment=new AlertDialogFragment();
    Dialog dialog;
    Bundle bundle;

    ImageView checkid_icon;
    ImageView checknn_icon;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _id=(EditText)findViewById(R.id.idText);
        pw=(EditText)findViewById(R.id.passwordText);
        checkpw=(EditText)findViewById(R.id.check_password);

        _nickname=(EditText)findViewById(R.id.nickname);

        univ=(TextView)findViewById(R.id.univ);
        major=(TextView)findViewById(R.id.major);

        dialogFragment.setActivity(this);
        bundle=savedInstanceState;

        checkid_icon=(ImageView)findViewById(R.id.checkIdBt);
        checknn_icon=(ImageView)findViewById(R.id.checkNicknameBt);

    }

    public void clickedIdCheckBtn(View v){
        id=_id.getText().toString();

        if(isCheck_id==false){
            isCheck_id=true;
        }
        //id 타당성 검사
        if(id.length()<6 || id.length()>12){
            dialogFragment.setAlertText("아이디는 6자 이상 12자 이하여야 합니다.");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
        }else{
            valid_id=true;
        }
    }

    public void clickedSignupBtn(View v){
        //id타당성 검사
        if(!isCheck_id) {
            dialogFragment.setAlertText("아이디 중복체크를 해야 합니다");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
            return;
        }else if(!valid_id) {
            dialogFragment.setAlertText("아이디는 6자 이상 12자 이하여야 합니다");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
            return;
        }

        //password 타당성 검사
        password=pw.getText().toString();
        if(password.length()<6|| password.length()>15){
            dialogFragment.setAlertText("비밀번호는 6자 이상 15자 이하여야 합니다.");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
            return;
        }else if(!password.equals(checkpw.getText())){
            dialogFragment.setAlertText("비밀번호와 비밀번호 확인이 같지 않습니다.");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
            return;
            }
        password=null;

        //닉네임 타당성 검사
        if(!isCheck_nn) {
            dialogFragment.setAlertText("닉네임 중복체크를 해야 합니다");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
            return;
        }else if(!valid_id) {
            dialogFragment.setAlertText("아이디는 6자 이상 12자 이하여야 합니다");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
            return;
        }

        //학교 타당성 검사
        if(univ.getText().equals("학교 선택")){
            dialogFragment.setAlertText("학교를 선택하세요.");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
            return;
        }

        //학과 타당성 검사
        if(univ.getText().equals("학과 선택")){
            dialogFragment.setAlertText("학과를 선택하세요.");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
            return;
        }

        //모든 조건을 통과하면 회원가입 완료, POST형식으로 php에 데이터 전송
        /*
        phpUp p1 = new phpUp(id, password, major, nickname, univ);
        p1.HttpPostData();
        */

        dialogFragment.setAlertText("회원가입 완료.");
        dialog=dialogFragment.onCreateDialog(bundle);
        dialog.show();
        finish();
        return;
    }

    public void clickedNicknameCheckBtn(View v){
        nickname=_nickname.getText().toString();

        if(isCheck_nn==false){
            isCheck_nn=true;
        }
        //닉네임 타당성 검사
        if(nickname.length()<9 || nickname.length()>2){
            dialogFragment.setAlertText("닉네임은 3자 이상 8자 이하여야 합니다.");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
        }else{
            valid_nn=true;
        }
    }
    public void clickedUniv(View v){
        ListDialogFragment univListDialog=new ListDialogFragment();
        univListDialog.setData(this,"학교 선택",BasicData.univ,univ);
        dialog=univListDialog.onCreateDialog(bundle);
        dialog.show();
    }

    public void clickedMajor(View v){
        ListDialogFragment majorListDialog=new ListDialogFragment();
        majorListDialog.setData(this,"학과 선택",BasicData.major,major);
        dialog=majorListDialog.onCreateDialog(bundle);
        dialog.show();
    }



}




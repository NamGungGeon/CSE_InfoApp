package com.example.denky.greattimetable;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by denky on 2017-03-18.
 */


public class SignupActivity extends AppCompatActivity {

    String id;
    boolean valid_id=false;
    boolean isCheck_id=false;

    String password;

    String univ;
    String magor;

    EditText _id;
    EditText pw;
    EditText checkpw;

    AlertDialogFragment dialogFragment=new AlertDialogFragment();
    Dialog dialog;
    Bundle bundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        _id=(EditText)findViewById(R.id.idText);
        pw=(EditText)findViewById(R.id.passwordText);
        checkpw=(EditText)findViewById(R.id.check_password);

        dialogFragment.setActivity(this);
        bundle=savedInstanceState;
    }

    public void clickedIdCheckBtn(View v){
        id=_id.getText().toString();

        if(isCheck_id==false){
            isCheck_id=true;
        }
        //id 타당성 검사
        if(id.length()<6 || id.length()>12){
            dialogFragment.setAlertText("id는 6자 이상 12자 이하여야 합니다.");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
        }else{
            valid_id=true;
        }
    }
    public void clickedSignupBtn(View v){
        //id타당성 검사
        if(!isCheck_id) {
            dialogFragment.setAlertText("id 중복체크를 해야 합니다");
            dialog=dialogFragment.onCreateDialog(bundle);
            dialog.show();
            return;
        }else if(!valid_id) {
            dialogFragment.setAlertText("id는 6자 이상 12자 이하여야 합니다");
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

        //모든 조건을 통과하면 회원가입 완료, POST형식으로 php에 데이터 전송
        /*
        phpUp p1 = new phpUp(String id, String password, String major, String nickname, String univ);
        p1.HttpPostData();
        */

        dialogFragment.setAlertText("회원가입 완료.");
        dialog=dialogFragment.onCreateDialog(bundle);
        dialog.show();
        finish();
        return;
    }

    public void clickedUniv(View v){
        UnivListDialogFragment univListDialog=new UnivListDialogFragment();
        univListDialog.setActivity(this);
        dialog=univListDialog.onCreateDialog(bundle);
        dialog.show();
    }

    public void clickedMajor(View v){
        MajorListDialogFragment majorListDialog=new MajorListDialogFragment();
        majorListDialog.setActivity(this);
        dialog=majorListDialog.onCreateDialog(bundle);
        dialog.show();
    }



}




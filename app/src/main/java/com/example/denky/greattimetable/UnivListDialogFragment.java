package com.example.denky.greattimetable;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

/**
 * Created by Windows10 on 2017-03-21.
 */


public class UnivListDialogFragment extends DialogFragment{

    Activity activity;
    CharSequence univ[]={"머구머학교", "건국대학교"};

    public void setActivity(Activity activity){
        this.activity=activity;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstance){

        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setTitle("대학 선택")
                .setItems(univ, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        return builder.create();
    }

}

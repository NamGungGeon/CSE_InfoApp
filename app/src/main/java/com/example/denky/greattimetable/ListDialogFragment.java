package com.example.denky.greattimetable;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

/**
 * Created by Windows10 on 2017-03-21.
 */


public class ListDialogFragment extends DialogFragment {
    Activity activity=null;
    String title=null;
    CharSequence list[]=null;
    TextView v=null;

    public void setData(Activity activity, String title, CharSequence list[], TextView v){
        this.activity=activity;
        this.v=v;
        this.title=title;
        this.list=list;
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstance){

        if(v!=null && activity!=null && title!=null && list!=null){

            AlertDialog.Builder builder=new AlertDialog.Builder(activity);
            builder.setTitle(title)
                    .setItems(list, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            v.setText(list[i]);
                        }
                    });

            return builder.create();

        }
        return null;
    }
}


package com.example.denky.greattimetable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * Created by Windows10 on 2017-03-21.
 */

public class AlertDialogFragment extends DialogFragment {

    private String alertText;
    private Activity activity;

    public void setAlertText(String alertText){
        this.alertText=alertText;
    }
    public void setActivity(Activity activity){
        this.activity=activity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if(alertText==null){
            return null;
        }
        builder.setMessage(alertText)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

package com.edlogiq.neurongym.neurongym;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import com.edlogiq.neurongym.R;

public class AlertDialogManager {


    public void showAlertDialog(Context context, String title, String message,
                                Boolean status) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        if (status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

        // Setting OK Button
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });
        /*alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
			}
		});*/

        // Showing Alert Message
        alertDialog.show();
    }
}

package com.edlogiq.neurongym.neurongym;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.edlogiq.neurongym.R;

/**
 * Created by incarnation-pc on 3/27/2015.
 */
public class Receiver extends BroadcastReceiver {

    private static final int MY_NOTIFICATION_ID=1;
    NotificationManager notificationManager;
    Notification myNotification;

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.e("date time","Received");
//        SimpleDateFormat df = new SimpleDateFormat(" d/MM/yyyy, HH:mm");
//        String date = df.format(Calendar.getInstance().getTime());
//
//        String[] val= (date.split(","));
//        Log.e("date time", val[0] + " " + val[1]);
//
//        Intent myIntent = new Intent(context, ThemeSelect.class);
//        myIntent.putExtra("value", "REMINDER");
//        myIntent.putExtra("date", val[0].trim());
//        myIntent.putExtra("time", val[1].trim());
//
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, myIntent, 0);

        myNotification = new NotificationCompat.Builder(context)
                .setContentTitle("Pocket Expense Manager")
                .setContentText("Reminder")
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.instruction_icon_red)
                .build();

        notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);



}
}

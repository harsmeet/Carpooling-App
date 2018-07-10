package com.example.neeraj.projctwithapi;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.neeraj.projctwithapi.Helper.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

public class Confrmactivity extends AppCompatActivity {
    String idd, seat;
    TextView txtname, txtmobile, txtseats;
    DatabaseHelper myhelpr = new DatabaseHelper(this);
    private Cursor cursor = null;
    String name, mobile, totalseat;

    Button plus, logout, bn;
    String ss;
    int aa;

    public static final int NOTIFY_ID = 100;
    public static final String YES_ACTION = "com.tinbytes.simplenotificationapp.YES_ACTION";
    public static final String MAYBE_ACTION = "com.tinbytes.simplenotificationapp.MAYBE_ACTION";
    public static final String NO_ACTION = "com.tinbytes.simplenotificationapp.NO_ACTION";

    public NotificationManager notificationManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_confrmactivity);
            logout = (Button) findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

//
                                              takemeTosignup();

                                              Intent i = new Intent(Confrmactivity.this, MainActivity.class);
                                              startActivity(i);
                                          }
                                      }
            );


            idd = getIntent().getExtras().getString("idd");
            seat = getIntent().getExtras().getString("seat");
            txtname = (TextView) findViewById(R.id.tViewName);
            txtmobile = (TextView) findViewById(R.id.tViewMobile);

            txtseats = (TextView) findViewById(R.id.tViewTotalSeat);
            txtseats.setText("Total No. of Seats:" + seat);
            bn = (Button) findViewById(R.id.confm);

//            plus = (Button) findViewById(R.id.buttonPlus);
//            minus = (Button) findViewById(R.id.buttonMinus);

            cursor = myhelpr.fetchAlldetails(idd);
            if (cursor != null) {
                cursor.moveToFirst();

                name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
                txtname.setText(" Name: " + name);

                mobile = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_MOB));
                txtmobile.setText("Mobile: " + mobile);


            }

        } catch (Exception e) {
            Toast.makeText(Confrmactivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        bn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View V) {
                showActionButtonsNotification();
            }
        });

        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        processIntentAction(getIntent());
        getSupportActionBar().hide();
    }

    public Intent getNotificationIntent() {
        Intent intent = new Intent(this, Confrmactivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        return intent;
    }

    public void showActionButtonsNotification() {
        Intent yesIntent = getNotificationIntent();
        yesIntent.setAction(YES_ACTION);

        Intent maybeIntent = getNotificationIntent();
        maybeIntent.setAction(MAYBE_ACTION);

        Intent noIntent = getNotificationIntent();
        noIntent.setAction(NO_ACTION);

        Notification notification = new NotificationCompat.Builder(this)
                .setContentIntent(PendingIntent.getActivity(this, 0, getNotificationIntent(), PendingIntent.FLAG_UPDATE_CURRENT))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Action Buttons Notification Received")
                .setContentTitle("Hi there!")
                .setContentText("I want to join the ride.")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .addAction(new Action(
                        R.mipmap.ic_thumb_up_black_36dp,
                        getString(R.string.yes),
                        PendingIntent.getActivity(this, 0, yesIntent, PendingIntent.FLAG_UPDATE_CURRENT)))
                .addAction(new Action(
                        R.mipmap.ic_thumbs_up_down_black_36dp,
                        getString(R.string.maybe),
                        PendingIntent.getActivity(this, 0, maybeIntent, PendingIntent.FLAG_UPDATE_CURRENT)))
                .addAction(new Action(
                        R.mipmap.ic_thumb_down_black_36dp,
                        getString(R.string.no),
                        PendingIntent.getActivity(this, 0, noIntent, PendingIntent.FLAG_UPDATE_CURRENT)))
                .build();

        notificationManager.notify(NOTIFY_ID, notification);
    }

    @Override
    public void onNewIntent(Intent intent) {
        processIntentAction(intent);
        super.onNewIntent(intent);
    }

    public void processIntentAction(Intent intent) {
        if (intent.getAction() != null) {
            switch (intent.getAction()) {
                case YES_ACTION:
                    Toast.makeText(this, "Yes :)", Toast.LENGTH_SHORT).show();
                    break;
                case MAYBE_ACTION:
                    Toast.makeText(this, "Maybe :|", Toast.LENGTH_SHORT).show();
                    break;
                case NO_ACTION:
                    Toast.makeText(this, "No :(", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

    }

    void takemeTosignup() {
        Intent i = new Intent(Confrmactivity.this, MainActivity.class);
        startActivity(i);

    }
}















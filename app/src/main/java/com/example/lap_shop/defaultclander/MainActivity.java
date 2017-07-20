package com.example.lap_shop.defaultclander;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static android.R.attr.description;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    Uri baseUri;
    long Date;
    ContentValues event;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DateDialog(MainActivity.this, new DateDialog.DateLisiner() {
                    @Override
                    public void ondatechange(long date) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm aa");
                        Date = date;
                        System.out.println(simpleDateFormat.format(date) + "whduhwqu");
                        AddEvent();

//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//
//                            if (checkSelfPermission(Manifest.permission.READ_CALENDAR)
//                                    == PackageManager.PERMISSION_DENIED || checkSelfPermission(Manifest.permission.WRITE_CALENDAR)
//                                    == PackageManager.PERMISSION_DENIED) {
//
//                                String[] permissions = {Manifest.permission.SEND_SMS};
//
//                                requestPermissions(permissions, PERMISSION_REQUEST_CODE);
//
//                            }
//                        } else {
//                            MainActivity.this.getContentResolver().insert(baseUri, event);
//                        }

                    }
                }).show();
            }
        });

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                MainActivity.this.getContentResolver().insert(baseUri, event);


                break;
        }
    }


    public void AddEvent() {

        try {
            ContentResolver cr = MainActivity.this.getContentResolver();
            ContentValues values = new ContentValues();
            values.put(CalendarContract.Events.DTSTART, Date);
            values.put(CalendarContract.Events.DTEND, Date + 60 * 60 * 1000);
            values.put(CalendarContract.Events.TITLE, "wdwqs");
            values.put(CalendarContract.Events.DESCRIPTION, description);
            values.put(CalendarContract.Events.EVENT_LOCATION,"wqdewq");
            values.put(CalendarContract.Events.HAS_ALARM,1);
            values.put(CalendarContract.Events.CALENDAR_ID, 1);
            values.put(CalendarContract.Events.EVENT_TIMEZONE, Calendar.getInstance()
                    .getTimeZone().getID());
            System.out.println(Calendar.getInstance().getTimeZone().getID());
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Uri uri = cr.insert(CalendarContract.Events.CONTENT_URI, values);

            long eventId = Long.parseLong(uri.getLastPathSegment());
            Log.d("Ketan_Event_Id", String.valueOf(eventId));

        } catch (Exception e) {
            e.printStackTrace();
        }

//        if (Build.VERSION.SDK_INT >= 8) {
//            baseUri = Uri.parse("content://com.android.calendar/events");
//        } else {
//            baseUri = Uri.parse("content://calendar/events");
//        }

    }
}

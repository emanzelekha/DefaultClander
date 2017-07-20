package com.example.lap_shop.defaultclander;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by LAP_SHOP on 21/02/2017.
 */

public class DateDialog extends Dialog {
    private Button okButton, cancelButton, timeButton;
    private DatePicker datePicker;
    private static TimePicker TimePicker;
    private DateLisiner DateLisiner;
    private SimpleDateFormat dateFormat;
    int flag = 0;
    static int hour;
    static int minute;

    public DateDialog(final Activity activity, final DateLisiner DateLisiner) {
        super(activity);
        setContentView(R.layout.datedialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        this.DateLisiner = DateLisiner;
        okButton = (Button) findViewById(R.id.dialog_ok_button);
        timeButton = (Button) findViewById(R.id.time);
        cancelButton = (Button) findViewById(R.id.dialog_cancel_button);
        datePicker = (DatePicker) findViewById(R.id.reservation_step_1_datePicker);
        TimePicker = (TimePicker) findViewById(R.id.time_picker);
        datePicker.setMinDate(System.currentTimeMillis() - 1000);
        dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    timeButton.setText("Date");
                    datePicker.setVisibility(View.GONE);
                    TimePicker.setVisibility(View.VISIBLE);
                    flag = 1;
                } else {
                    timeButton.setText("Time");
                    datePicker.setVisibility(View.VISIBLE);
                    TimePicker.setVisibility(View.GONE);
                    flag = 0;
                }

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getDateFromDatePicker(datePicker);
                DateLisiner.ondatechange(getDateFromDatePicker(datePicker));
                dismiss();

            }
        });

    }


    public interface DateLisiner {
        void ondatechange(long date);
    }


    public static long getDateFromDatePicker(DatePicker datePicker) {
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();
        Date date = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hour = TimePicker.getHour();
            minute = TimePicker.getMinute();
        } else {
            hour = TimePicker.getCurrentHour();
            minute = TimePicker.getCurrentMinute();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute);



        return calendar.getTimeInMillis();
    }


}

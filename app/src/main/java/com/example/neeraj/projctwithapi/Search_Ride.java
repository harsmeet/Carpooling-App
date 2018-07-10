package com.example.neeraj.projctwithapi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Search_Ride extends AppCompatActivity {

    EditText g, h, i, j, k;


    Button plus, minus, op;
    String ss;
    int u;
    Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_ride);
        g = (EditText) findViewById(R.id.t1);
        h = (EditText) findViewById(R.id.t2);
        i = (EditText) findViewById(R.id.t3);
        j = (EditText) findViewById(R.id.t4);
        k = (EditText) findViewById(R.id.tt5);

        plus = (Button) findViewById(R.id.button11);
        minus = (Button) findViewById(R.id.button12);
        op = (Button) findViewById(R.id.R_searched);

        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {

                    Intent lid = new Intent(Search_Ride.this, Ride_List.class);
                    lid.putExtra("user_date", g.getText().toString());
                    lid.putExtra("user_email", h.getText().toString());
                    lid.putExtra("user_source", i.getText().toString());
                    lid.putExtra("user_dest", j.getText().toString());
                    lid.putExtra("user_seat", k.getText().toString());

                    startActivity(lid);


                } catch (Exception e) {
                    Toast.makeText(Search_Ride.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        try {
            plus.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                    ss = k.getText().toString();
                    String d = ss;
                    u = Integer.parseInt(d);
                    if (u > 8) {
                        Toast.makeText(Search_Ride.this, "Max Number Reached", Toast.LENGTH_SHORT).show();
                    } else {
                        u = u + 1;
                        String sa = Integer.toString(u);
                        k.setText(sa);
                    }

                }
            });
        } catch (Exception e) {
            Toast.makeText(Search_Ride.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

        try {
            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ss = k.getText().toString();
                    String d = ss;
                    u = Integer.parseInt(d);
                    if (u <= 0) {
                        Toast.makeText(Search_Ride.this, "Min Number Reached", Toast.LENGTH_SHORT).show();
                    } else {
                        u = u - 1;
                        String sa = Integer.toString(u);
                        k.setText(sa);
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(Search_Ride.this, e.toString(), Toast.LENGTH_SHORT).show();
        }


        try {
            g.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(Search_Ride.this, date, myCalendar
                            .get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }

            });
        } catch (Exception e) {
            Toast.makeText(Search_Ride.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        g.setText(sdf.format(myCalendar.getTime()));


        final EditText ED = (EditText) findViewById(R.id.t2);

        ED.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Search_Ride.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        ED.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });
    }
}
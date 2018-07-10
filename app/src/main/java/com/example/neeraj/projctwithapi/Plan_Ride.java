package com.example.neeraj.projctwithapi;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.neeraj.projctwithapi.Helper.DatabaseHelper;
import com.example.neeraj.projctwithapi.Helper.User;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Plan_Ride extends AppCompatActivity {


    EditText w, t, a, b, numbr;
    SharedPreferences sharedPreferences;
    public static final String myprefnce="logdtl";
    public static final String key_id="eid";

    String name,password;
    Button plus, minus, bn;
    String ss;
    int aa;

    DatabaseHelper mydb=new DatabaseHelper(this);
    User user =new User();
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_ride);


        w=(EditText)findViewById(R.id.editText3);
        t=(EditText)findViewById(R.id.editText4);

        a=(EditText)findViewById(R.id.editText5);
        b=(EditText)findViewById(R.id.editText6);

        numbr = (EditText) findViewById(R.id.editText7);

        plus = (Button) findViewById(R.id.button8);
        minus = (Button) findViewById(R.id.button9);

        bn=(Button)findViewById(R.id.button4);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    sharedPreferences=getSharedPreferences(myprefnce, Context.MODE_PRIVATE);
                    String value=sharedPreferences.getString(key_id, "");

                    user.setDate(w.getText().toString().trim());
                    user.setEmail(t.getText().toString().trim());
                    user.setSource(a.getText().toString().trim());
                    user.setDest(b.getText().toString().trim());
                    user.setseat(numbr.getText().toString().trim());
                    user.setConid(value.trim());
                    mydb.addUser(user);

                    cursor = mydb.fetchAlltask();
                    int cu = cursor.getCount();

                    Toast.makeText(Plan_Ride.this, Integer.toString(cu), Toast.LENGTH_LONG).show();
                }
                catch (Exception e) {
                    Toast.makeText(Plan_Ride.this,e.toString(), Toast.LENGTH_SHORT).show();

                }
            }
        });

        try{
        plus.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                ss = numbr.getText().toString();
                String d = ss;
                aa = Integer.parseInt(d);
                if (aa > 8) {
                    Toast.makeText(Plan_Ride.this, "Max Number Reached", Toast.LENGTH_SHORT).show();
                } else {
                    aa = aa + 1;
                    String sa = Integer.toString(aa);
                    numbr.setText(sa);
                }

            }
        });
    } catch (Exception e) {
        Toast.makeText(Plan_Ride.this, e.toString(), Toast.LENGTH_SHORT).show();
    }

        try {
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ss = numbr.getText().toString();
                String d = ss;
                aa = Integer.parseInt(d);
                if (aa <= 0) {
                    Toast.makeText(Plan_Ride.this, "Min Number Reached", Toast.LENGTH_SHORT).show();
                } else {
                    aa = aa - 1;
                    String sa = Integer.toString(aa);
                    numbr.setText(sa);
                }
            }
        });
    } catch (Exception e) {
        Toast.makeText(Plan_Ride.this, e.toString(), Toast.LENGTH_SHORT).show();
    }

        try {
            final EditText ED = (EditText) findViewById(R.id.editText4);

            ED.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Calendar mcurrentTime = Calendar.getInstance();
                    int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                    int minute = mcurrentTime.get(Calendar.MINUTE);

                    TimePickerDialog mTimePicker;
                    mTimePicker = new TimePickerDialog(Plan_Ride.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                            ED.setText(selectedHour + ":" + selectedMinute);
                        }
                    }, hour, minute, true);
                    mTimePicker.setTitle("Select Time");
                    mTimePicker.show();

                }
            });

            w.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DatePickerDialog(Plan_Ride.this, date, myCalendar
                            .get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }

            });
        } catch (Exception e) {
            Toast.makeText(Plan_Ride.this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


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

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        w.setText(sdf.format(myCalendar.getTime()));
    }}



package com.example.neeraj.projctwithapi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.neeraj.projctwithapi.Helper.DatabaseHelper;

public class Ride_List extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    String date, email, source, dest, seat;
    DatabaseHelper mydata;
    Button submit;
    Cursor cursor = null;
    private SimpleCursorAdapter dataadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_ride_list);

        mydata = new DatabaseHelper(this);
        submit = (Button) findViewById(R.id.button6);

        try {
            date = getIntent().getExtras().getString("user_date");
            email = getIntent().getExtras().getString("user_email");
            source = getIntent().getExtras().getString("user_source");
            dest = getIntent().getExtras().getString("user_dest");
            seat = getIntent().getExtras().getString("user_seat");

            fill();

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Ride_List.this, date, Toast.LENGTH_SHORT).show();
                    S_R_Saved c = new S_R_Saved();

                    c.setDate(date);
                    c.setEmail(email);
                    c.setSource(source);
                    c.setDest(dest);
                    c.setseat(seat);

                    Toast.makeText(Ride_List.this, date, Toast.LENGTH_SHORT).show();

                    Toast.makeText(Ride_List.this, "success", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(Ride_List.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public void fill() {
        cursor = mydata.getride2();
        int ss = cursor.getCount();
        Toast.makeText(Ride_List.this, Integer.toString(ss), Toast.LENGTH_SHORT).show();

        final String[] columns = new String[]{

                DatabaseHelper.COLUMN_USER_DATE,
                DatabaseHelper.COLUMN_USER_EMAIL,
                DatabaseHelper.COLUMN_USER_SOURCE,
                DatabaseHelper.COLUMN_USER_DEST,
                DatabaseHelper.COLUMN_USER_SEAT

        };
        int[] to = new int[]{

                R.id.et_date,
                R.id.et_time,
                R.id.et_source,
                R.id.et_dest,
                R.id.et_seat

        };
        dataadapter = new SimpleCursorAdapter(this, R.layout.list_show2, cursor, columns, to, 0);
        ListView lv = (ListView) findViewById(R.id.listView11);
        lv.setAdapter(dataadapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lv, View view, int position, long id) {
                try {
                    Cursor cursor = (Cursor) lv.getItemAtPosition(position);

                    String idd = cursor.getString(cursor.getColumnIndexOrThrow("con_id"));
                    String seat = cursor.getString(cursor.getColumnIndexOrThrow("user_seat"));
                    Intent s = new Intent(Ride_List.this, Confrmactivity.class);
                    s.putExtra("idd", idd);
                    s.putExtra("seat", seat);
                    startActivity(s);
                } catch (Exception e) {
                    Toast.makeText(Ride_List.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}

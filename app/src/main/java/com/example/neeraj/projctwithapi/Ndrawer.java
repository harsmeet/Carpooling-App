package com.example.neeraj.projctwithapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.neeraj.projctwithapi.Helper.DatabaseHelper;

public class Ndrawer extends AppCompatActivity {

    DatabaseHelper mydb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ndrawer);
        Button bb = (Button) findViewById(R.id.button);

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(Ndrawer.this, Search_Ride.class);
                startActivity(d);
            }
        });
        Button bd = (Button) findViewById(R.id.button2);
        bd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(Ndrawer.this, Plan_Ride.class);
                startActivity(d);
            }
        });
    }
}

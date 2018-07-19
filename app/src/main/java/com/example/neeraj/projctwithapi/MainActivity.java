package com.example.neeraj.projctwithapi;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.neeraj.projctwithapi.Helper.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper= new DatabaseHelper(this);
SharedPreferences preferences;
    public static final String myprefnce="logdtl";
    public static final String key_id="eid";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
preferences=getSharedPreferences(myprefnce, Context.MODE_PRIVATE);

    }


    public void onButtonClick(View v) {
        if (v.getId() == R.id.b1) {



            EditText a = (EditText) findViewById(R.id.editText2);
            String str = a.getText().toString();


                        EditText b = (
                                EditText) findViewById(R.id.editText);
            String pass = b.getText().toString();

            try {

                if(str.equals(""))
                {
                    Toast.makeText(MainActivity.this, " no id ", Toast.LENGTH_SHORT).show();
                }
                else if (pass.equals(""))
                {
                    Toast.makeText(MainActivity.this, " no pass ", Toast.LENGTH_SHORT).show();
                }
                else if (helper.chkuser(str, pass)) {
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString(key_id,str);
                    editor.commit();
                   // preferences.Editor editor=preferences.edit();
                    Toast.makeText(MainActivity.this, " YOU ARE LOGIN NOW..!! ", Toast.LENGTH_SHORT).show();


                    Intent i = new Intent(MainActivity.this, Ndrawer.class);
                    startActivity(i);


                }
                 else {
                    Toast.makeText(MainActivity.this, "USER-NAME/PASSWORD MISMATCHES", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e)
            {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
            }

        }
        if (v.getId() == R.id.signuphereb1) {
            Intent i = new Intent(MainActivity.this, Signup.class);
            startActivity(i);
        }



    }
}



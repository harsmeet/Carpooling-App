package com.example.neeraj.projctwithapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.neeraj.projctwithapi.Helper.Contact;
import com.example.neeraj.projctwithapi.Helper.DatabaseHelper;

/**
 * Created by Hars on 4/28/2017.
 */

public class Signup extends Activity {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }

    public void onSignUpClick(View v) {
        if (v.getId() == R.id.signupb2) {
            EditText name = (EditText) findViewById(R.id.ptname);
            EditText email = (EditText) findViewById(R.id.edemail);
            EditText mob = (EditText) findViewById(R.id.mobile);
            EditText uname = (EditText) findViewById(R.id.ptusername);
            EditText passwd = (EditText) findViewById(R.id.etpasswd);
            EditText confmpasswd = (EditText) findViewById(R.id.etconfirmpass);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String mobstr = mob.getText().toString();
            String passwdstr = passwd.getText().toString();
            String confmpasswdstr = confmpasswd.getText().toString();

            if (!passwdstr.equals(confmpasswdstr)) {

                Toast.makeText(Signup.this, "Wrong Password..!", Toast.LENGTH_SHORT).show();
            } else if (!helper.chkuser2(unamestr)) {
//                   insert the details in database
                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setMob(mobstr);
                c.setPass(passwdstr);

                helper.insertContact(c);
                Toast.makeText(Signup.this, "YOU ARE SUCCESSFULLY SIGN-UP", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Signup.this, MainActivity.class);
                startActivity(i);
            }

        } else {
            Toast.makeText(Signup.this, "FILL THE ABOVE DETAILS", Toast.LENGTH_SHORT).show();
        }

    }
}

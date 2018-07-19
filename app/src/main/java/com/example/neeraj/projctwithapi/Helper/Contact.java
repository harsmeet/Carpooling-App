package com.example.neeraj.projctwithapi.Helper;

/**
 * Created by Hars on 4/28/2017.
 */

public class Contact {

int id;
    String name,email,uname,mob,pass;

    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return this.id;
    }

    public void setName(String name)
    {
        this.name=name;
    }
        public String getName()
        {
            return this.name;
        }

    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getEmail()
    {
        return this.email;
    }


    public void setUname(String uname)
    {
        this.uname=uname;
    }
    public String getUname()
    {
        return this.uname;
    }

    public void setMob(String mob)
    {
        this.mob=mob;
    }
    public String getMob()
    {
        return this.mob;
    }

    public void setPass(String pass)
    {
        this.pass=pass;
    }
    public String getpass()
    {
        return this.pass;
    }
}



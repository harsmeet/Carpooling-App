package com.example.neeraj.projctwithapi.Helper;

/**
 * Created by neeraj on 05-07-2017.
 */

public class User
{private int id;
        private String date;

        private String Email;
    private String source;
    private String dest;
    private String seat;
    private String conid;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getEmail()
        {
            return Email;
        }
        public void setEmail(String Email) {
            this.Email = Email;
        }

    public String getSource()
    {
        return source;
    }
    public void setSource(String source) {
        this.source = source;}

    public String getDest()
    {
        return dest;
    }
    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSeat()
    {
        return seat;
    }
    public void setseat(String seat) {
        this.seat = seat;
    }

    public String getConid()
    {
        return conid;
    }
    public void setConid(String conid) {
        this.conid = conid;
    }

}



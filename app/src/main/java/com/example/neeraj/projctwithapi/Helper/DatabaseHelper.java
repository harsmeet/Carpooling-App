package com.example.neeraj.projctwithapi.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by neeraj on 05-07-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // @Override
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "finalda.db";
    private static final String TABLE_USER = "regitruser1";

//Signup database name

    private static final String TABLE_NAME = "contacts";

    private static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    public static final String COLUMN_MOB = "Mob";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;

    // User Table Columns names for table one
    public static final String COLUMN_USER_ID = "_id";
    public static final String COLUMN_USER_DATE = "user_date";
    public static final String COLUMN_USER_EMAIL = "user_email";
    public static final String COLUMN_USER_SOURCE = "user_source";
    public static final String COLUMN_USER_DEST = "user_dest";
    public static final String COLUMN_USER_SEAT = "user_seat";
    public static final String COLUMN_CON_ID = "con_id";
//    for name and mob. no. of user
   // public static final String COLUMN_CON_ID = "id";



    //for task table



    // create table sql query for one
    private String CREATE_USER_TABLE1 = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_DATE + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT, " + COLUMN_USER_SOURCE + " TEXT, " + COLUMN_USER_DEST + " TEXT, " + COLUMN_USER_SEAT + " TEXT, " + COLUMN_CON_ID + " TEXT " + ")";


    private String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT," + COLUMN_UNAME + " TEXT, " + COLUMN_MOB + " TEXT, " + COLUMN_PASS + " TEXT " + ")";




    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE1);
        db.execSQL(TABLE_CREATE);
//        db.execSQL(CREATE_USER_TABLE2);
        this.db=db;
    }

    public void insertContact(Contact c) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_UNAME, c.getUname());
        values.put(COLUMN_PASS, c.getpass());
        values.put(COLUMN_MOB, c.getMob());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public boolean chkuser(String usern, String Paswrd) {
        String[] columns =
                {
                        COLUMN_ID
                };
        db = this.getReadableDatabase();
        String selection = COLUMN_UNAME + " = ? " + " AND " + COLUMN_PASS + " = ? ";
        String[] selectArgs = {usern, Paswrd};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectArgs, null, null, null);
        int cursorcount = cursor.getCount();
        if (cursorcount > 0) {
            return true;
        }
        return false;

    }


    public boolean chkuser2(String usern) {
        String[] columns =
                {
                        COLUMN_ID
                };
        db = this.getReadableDatabase();
        String selection = COLUMN_UNAME + " = ? ";
        String[] selectArgs = {usern};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectArgs, null, null, null);
        int cursorcount = cursor.getCount();
        db.close();
        if (cursorcount > 0) {
            return true;
        }
        return false;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        //  db.execSQL(DROP_USER_TABLE1);
        // db.execSQL(DROP_USER_TABLE2);

        // Create tables again
        onCreate(db);

    }
    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_DATE, user.getDate());
            values.put(COLUMN_USER_EMAIL, user.getEmail());

            values.put(COLUMN_USER_SOURCE, user.getSource());
            values.put(COLUMN_USER_DEST, user.getDest());
            values.put(COLUMN_USER_SEAT, user.getSeat());
            values.put(COLUMN_CON_ID, user.getConid());
            // Inserting Row
          long val= db.insert(TABLE_USER, null, values);
            db.close();
        } catch (Exception e) {
            String f = e.toString();
        }

    }


    public Cursor getride2() {
        String query = "select * from " + TABLE_USER + "";
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor mcursor=null;
        mcursor=mydb.rawQuery(query,null);
        mcursor.getCount();
        if(mcursor != null)
        {
            mcursor.moveToFirst();
        }
        return mcursor;
    }


    /**
     * This method to check user exist or not
     *
     * @param name
     * @return true/false
     */
    public boolean checkUser(String name) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_DATE + " = ?";

        // selection argument
        String[] selectionArgs = {name};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param nam
     */

    public Cursor selectRecords(String nam) {

        Cursor mCursor = null;
        String[] columns = {
                COLUMN_USER_ID,

                COLUMN_USER_DATE,
                COLUMN_USER_EMAIL,
                COLUMN_USER_SOURCE,
                COLUMN_USER_DEST,
                COLUMN_USER_SEAT
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_DATE + " = ?";

        // selection arguments
        String[] selectionArgs = {nam};

        mCursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order


//    Getting data from database table
        //Cursor cursor = dataBase.rawQuery("select * from " + TABLE_USER +" where "+ COLUMN_USER_EID + "=" + nam , null)

        return mCursor;
    }


    public Cursor fetchAlltask() {
        String selectQuery = "SELECT * from " + TABLE_USER + "";
        SQLiteDatabase mDb = this.getReadableDatabase();
        Cursor mCursor = null;
        mCursor = mDb.rawQuery(selectQuery, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public Cursor fetchAlldetails( String id) {
        String selectQuery = "SELECT * from " + TABLE_NAME + " where "+ COLUMN_UNAME +"= '"+ id +"' ";
        SQLiteDatabase mDb = this.getReadableDatabase();
        Cursor mCursor = null;
        mCursor = mDb.rawQuery(selectQuery, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}


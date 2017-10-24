package com.debashishdeka.newsapp;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Debashish on 10/22/2017.
 */

public class dbclass extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "users.db";
    private static final String TABLE_COL_ID = "id";
    private static final String TABLE_COL_NAME = "name";
    private static final String TABLE_COL_EMAIL = "email";
    private static final String TABLE_COL_PASSWORD = "password";
    private static final String TABLE_NAME = "user_list";
    private SQLiteDatabase database;

    private static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + TABLE_COL_ID + " INTEGER PRIMARY KEY NOT NULL , " +
            TABLE_COL_NAME + " TEXT NOT NULL , " + TABLE_COL_EMAIL + " TEXT NOT NULL , " +
            TABLE_COL_PASSWORD + " TEXT NOT NULL);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        Log.i("xxx","table created !! ");
        this.database = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        this.database.execSQL(query);
        this.onCreate(this.database);
    }

    public dbclass(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void insertUser(UserInfo user) {
        database = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor c = database.rawQuery(query,null);
        int cnt = c.getCount();
        ContentValues val = new ContentValues();
        val.put(TABLE_COL_ID,cnt);
        val.put(TABLE_COL_NAME,user.getName());
        val.put(TABLE_COL_EMAIL,user.getEmail());
        val.put(TABLE_COL_PASSWORD,user.getPassword());
        this.database.insert(TABLE_NAME, null, val);
        //Log.i("yyy", "inserted finally !!! ");
        this.database.close();
    }
    public String get_password(String user_name) {
        database = this.getReadableDatabase();
        String query = "SELECT " + TABLE_COL_NAME + ", " + TABLE_COL_PASSWORD + " FROM " + TABLE_NAME;
        Cursor c = database.rawQuery(query,null);
        Log.i("search count = ", Integer.toString(c.getCount()));
        String _pass = "not exits !";
        if(c.moveToFirst()) {
            do {
                String _name = c.getString(0);
                Log.i("found name = ",_name);
                Log.i("required name = ",user_name);
                if(_name.equals(user_name)) {
                    _pass = c.getString(1);
                    break;
                }
            }while (c.moveToNext());
        }
//        Log.i("ggg", "returned " + _pass);
        return _pass;
    }
    public Boolean confirm_user_name(String user_name) {
        database = this.getReadableDatabase();
        String query = "SELECT " + TABLE_COL_NAME + " FROM " + TABLE_NAME;
        Cursor c = database.rawQuery(query,null);
        Boolean fine = true;
        if(c.moveToFirst()) {
            do {
                String _name = c.getString(0);
                if(_name.equals(user_name)) {
                    fine = false;
                    break;
                }
            }while (c.moveToNext());
        }
        return fine;
    }

}

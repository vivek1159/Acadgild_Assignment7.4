package com.example.vivek.assignment74;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by vivek on 25-04-2016.
 */
public class ListDB extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public static String DATABASE_NAME = "List.db";
    public static int DATABASE_VERSION = 1;
    public static String TABLE_NAME = "LIST_TABLE";
    public static String CREATE_DB = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            " ID NUMBER AUTO INCREMENT PRIMARY KEY, FIRST_NAME TEXT NOT NULL, LAST_NAME TEXT NOT NULL)";

    public static String TAG = "ListDB";
    public ListDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB);

        ContentValues values = new ContentValues();
        values.put("FIRST_NAME", "Venkat");
        values.put("LAST_NAME", "VIVEK");
        db.insert(TABLE_NAME, null, values);
        values.put("FIRST_NAME", "USHA");
        values.put("LAST_NAME", "VENKATARAMAN");
        db.insert(TABLE_NAME, null, values);
        values.put("FIRST_NAME", "HARISH");
        values.put("LAST_NAME", "VENKAT");
        db.insert(TABLE_NAME, null, values);
        values.put("FIRST_NAME", "HARI");
        values.put("LAST_NAME", "VENKATARAMAN");
        db.insert(TABLE_NAME, null, values);
        Log.d(TAG, "All values inserted");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL(CREATE_DB);
    }


    public ArrayList<String> getData() {
        db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);

        ArrayList<String> list = new ArrayList<>();
        if (c!=null)
        {
            c.moveToFirst();
            do {
                StringBuilder str = new StringBuilder();
                str.append("ID:").append(c.getInt(c.getColumnIndex("ID"))).append(",First Name:").
                        append(c.getString(c.getColumnIndex("FIRST_NAME"))).append(",Last Name:").
                        append(c.getString(c.getColumnIndex("LAST_NAME")));
                Log.d(TAG,str.toString());
                list.add(str.toString());
            }while(c.moveToNext());
            c.close();
            db.close();
            return list;
        }
        else{
            db.close();
            return null;
        }

    }
}

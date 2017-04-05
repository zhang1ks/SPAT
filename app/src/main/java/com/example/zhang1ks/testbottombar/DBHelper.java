package com.example.zhang1ks.testbottombar;

/**
 * Created by zhang1ks on 2016-12-16.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String SAVEDRESOURCE_TABLE_NAME = "savedresource";
    public static final String SAVEDRESOURCE_COLUMN_ID = "id";
    public static final String SAVEDRESOURCE_COLUMN_NAME = "name";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table savedresource" +
                        "(id integer primary key, name text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS savedresource");
        onCreate(db);
    }

    public boolean insertSavedResource (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        db.insert("savedresource", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from savedresource where id="+id+"", null );
        return res;
    }

//    public int numberOfRows(){
//        SQLiteDatabase db = this.getReadableDatabase();
//        int numRows = (int) DatabaseUtils.queryNumEntries(db, SavedResource_TABLE_NAME);
//        return numRows;
//    }

    public boolean updateSavedResource (Integer id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        db.update("savedresource", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteSavedResource (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("savedresource",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllSavedResource() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from savedresource", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(SAVEDRESOURCE_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }
}
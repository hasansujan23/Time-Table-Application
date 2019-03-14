package com.example.sujanhasan.listviewdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Time_Demo.db";
    private static final String TABLE_NAME="time_details";
    private static final int VERSION_NUMBER=1;
    private static final String DAY="Day";
    private static final String P1="p1";
    private static final String P2="p2";
    private static final String P3="p3";
    private static final String P4="p4";
    private static final String P5="p5";
    private static final String P6="p6";
    private static final String P7="p7";
    private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+DAY+" VARCHAR(50) PRIMARY KEY,"+P1+" VARCHAR(50),"+P2+" VARCHAR(50),"+P3+" VARCHAR(50),"+P4+" VARCHAR(50),"+P5+" VARCHAR(50),"+P6+" VARCHAR(50),"+P7+" VARCHAR(50));";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String SELECT_ALL="SELECT * FROM "+TABLE_NAME;
    private static final String DAY1="Friday";
    private static final String[] columns={"p1","p2","p3","p4","p5","p6","p7"};
    private Context context;

    public MyDatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            Toast.makeText(context,"onCreate method is called",Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);
        }catch (Exception e){
            Toast.makeText(context,"Error : "+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        try {
            Toast.makeText(context,"onUpgrade method is called",Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context,"Exception: "+e,Toast.LENGTH_LONG).show();
        }

    }

    public long insertData(String day,String p1,String p2,String p3,String p4,String p5,String p6,String p7){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DAY,day);
        contentValues.put(P1,p1);
        contentValues.put(P2,p2);
        contentValues.put(P3,p3);
        contentValues.put(P4,p4);
        contentValues.put(P5,p5);
        contentValues.put(P6,p6);
        contentValues.put(P7,p7);
        long rowId=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }

    public Cursor displayAllData(String dayName){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //Cursor cursor=sqLiteDatabase.rawQuery(SELECT,null);
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,columns,"Day=?",new String[]{dayName},null,null,null,null);
        return cursor;
    }

    public Cursor showAllDay(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //Cursor cursor=sqLiteDatabase.rawQuery(SELECT_ALL,null);
        Cursor cursor=sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,DAY,null);
        return cursor;
    }

    public int deleteData(String Day){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,DAY+"=?",new String[] {Day});
    }

    public int updateData(String Day,String p1,String p2,String p3,String p4,String p5,String p6,String p7){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DAY,Day);
        contentValues.put(P1,p1);
        contentValues.put(P2,p2);
        contentValues.put(P3,p3);
        contentValues.put(P4,p4);
        contentValues.put(P5,p5);
        contentValues.put(P6,p6);
        contentValues.put(P7,p7);

        int row=sqLiteDatabase.update(TABLE_NAME,contentValues,DAY+"=?",new String[] {Day});
        return row;
    }
}

package com.example.bulbul.mysqlitedatabasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by bulbul on 12/23/2017.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="Student.db";
    private static final String TABLE_NAME="student_details";
    private static final String ID="_id";
    private static final String NAME="Name";
    private static final String AGE="Age";
    private static final String GENDER="Gender";
    private static final int VERSION_NUMBER=3;
    private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+NAME+" VARCHAR(255),"+AGE+" INTEGER,"+GENDER+" VARCHAR(15));";
    private static final String DROP_TABLE="DROP TABLE IF EXISTS"+TABLE_NAME;
    private static final String SELECT_ALL="SELECT * FROM "+TABLE_NAME;
    private Context context;
    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try{
            Toast.makeText(context,"Oncreate is called",Toast.LENGTH_LONG).show();
            db.execSQL(CREATE_TABLE);
        }catch(Exception e){
            Toast.makeText(context,"Exception :"+e,Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

      /* try{
            Toast.makeText(context,"OnUpgrade is called",Toast.LENGTH_LONG).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }catch (Exception e){
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_LONG).show();
        } */

    }

    public long insertData(String name,String age,String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);
        long rowId = db.insert(TABLE_NAME,null,contentValues);
        return rowId;
    }

    public Cursor displayAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL,null);
        return cursor;
    }

    public boolean updateData(String id,String name,String age,String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(NAME,name);
        contentValues.put(AGE,age);
        contentValues.put(GENDER,gender);

        db.update(TABLE_NAME,contentValues,ID+" = ?",new String[]{id});
        return true;
    }

    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID+" = ?",new String[]{id});
    }
}

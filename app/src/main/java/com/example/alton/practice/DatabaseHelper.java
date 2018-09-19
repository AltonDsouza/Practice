package com.example.alton.practice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alton on 9/18/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String database_name="student.db";
    public static final String table_name="student_table";
    public static final String COL1="ID";
    public static final String COL2="name";
    public static final String COL3="surname";
    public static final String COL4="marks";

    //whenever this constructor is called,your database is created.
    public DatabaseHelper(Context context) {
        super(context, database_name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+table_name+" (id integer primary key autoincrement,name text,surname text,marks integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+table_name);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name,String surname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL2,name);
        cv.put(COL3,surname);
        cv.put(COL4,marks);
        long res=db.insert(table_name,null,cv);
        if(res==-1){
            return false;
        }else
            {return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        //Cursor traverses through the records in the database
        //Cursor c=db.rawQuery("select * from "+table_name+" where ID="+id,null);
        Cursor c=db.rawQuery("select * from "+table_name,null);
        return c;

    }

    public boolean updateData(String id,String name,String surname,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COL1,id);
        cv.put(COL2,name);
        cv.put(COL3,surname);
        cv.put(COL4,marks);
        db.update(table_name,cv,"ID = ?",new String[]{id});
        return true;
    }
    public boolean deleteData(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        //db.delete(table_name,"ID = ?",new String[]{id});
        db.execSQL("delete from "+table_name+" where ID= "+id);

        return true;
    }
}

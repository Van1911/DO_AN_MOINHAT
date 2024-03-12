package com.example.do_an.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DangKiHelper extends SQLiteOpenHelper {
    public static final String DBName="waterbus_trensong";

    public DangKiHelper(@Nullable Context context) {
        super(context, DBName, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table Account(userName TEXT primary key ,password TEXT,roleID TEXT,email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("");
    }
    public boolean insertData(String userName,String password){
        SQLiteDatabase myDB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("userName",userName);
        contentValues.put("password",password);
        long result =myDB.insert("Account",null,contentValues);
        if(result==-1)return  false;
        else return true;
    }
    public boolean checkUserName(String userName){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from Account where userName = ?",new String[]{userName});
        if(cursor.getCount()>0)return true;
        else return false;
    }
    public boolean checkUser(String userName,String passWord){
        SQLiteDatabase myDB=this.getWritableDatabase();
        Cursor cursor=myDB.rawQuery("select * from Account where userName = ? and passWord = ?",new String[]{userName,passWord});
        if(cursor.getCount()>0)return true;
        else return false;
    }
}

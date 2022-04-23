package com.example.navigationdrawer;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    protected static  String dbname = "bahikhata_database.db";
    public database(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String q = "create table udhari_tbl(id integer  primary key autoincrement,name text,amount int,address text,phone text,date text,status text,date_stamp text,description text,img BLOB)";
        String q2="create table image_tbl(id integer primary key autoincrement,img BLOB)";
        sqLiteDatabase.execSQL(q);
        sqLiteDatabase.execSQL(q2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists udhari_tbl");
        sqLiteDatabase.execSQL("drop table if exists image_tbl");
        onCreate(sqLiteDatabase);
    }

    public byte[] LoadImage(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select img from image_tbl",null);
        if(cursor.moveToNext()){
            return cursor.getBlob(0);

        }else {
            return null;
        }


    }
    public boolean SaveImage(byte[] img){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("img",img);
        long r = db.insert("image_tbl", null, c);
        if (r == -1)
            return false;
        else
            return true;

    }
    public boolean insert_udhari(String name,int amount,String address,String phone,String date,String status,String date_stamp,String description,byte[] image){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        c.put("name",name);
        c.put("amount",amount);
        c.put("address",address);
        c.put("phone",phone);
        c.put("date",date);
        c.put("status",status);
        c.put("date_stamp",date_stamp);
        c.put("description",description);
        c.put("img",image);
        long r = db.insert("udhari_tbl", null, c);
        if (r == -1)
            return false;
        else
            return true;
    }
    public Cursor getCursorData (){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from udhari_tbl", null);
        return cursor;

    }
    public Cursor getData(String qry){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery(qry, null);
        return cursor;

    }

    public boolean updateStatus(String id){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put("status","paid");
        Cursor cursor=DB.rawQuery("select * from udhari_tbl where id=?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("udhari_tbl", c, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean deletePaidData (String status)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from udhari_tbl where status = ?", new String[]{status});
        if (cursor.getCount() > 0) {
            long result = DB.delete("udhari_tbl", "status=?", new String[]{status});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }
    public boolean deleteAllData ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("delete from udhari_tbl");
        return true;
    }
//
//
//    public Cursor getdata ()
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from sanvicake", null);
//        return cursor;
//
//    }
//    public Cursor getTodayOrders (String cdate)
//    {
//        SQLiteDatabase DB = this.getWritableDatabase();
//        Cursor cursor = DB.rawQuery("Select * from sanvicake where date = ?", new String[]{cdate} , null);
//        return cursor;
//
//    }
}

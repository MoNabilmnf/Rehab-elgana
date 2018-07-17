package com.example.mohamed_nabil.toa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by nody on 13/03/2018.
 */
public class DB_Sql extends SQLiteOpenHelper {
    public static final String DBname = "Data.db";
    public DB_Sql(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       db.execSQL("Create table doaalist (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT ,page TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    db.execSQL("DROP TABLE IF EXISTS doaalist");


        onCreate(db);
    }



    public boolean insert(int id , String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("page",id);
        contentValues.put("name",name);
        long res = db.insert("doaalist",null,contentValues);
        if (res == -1)
            return false;
        else
            return true;
    }
    public ArrayList getitems()
    {
        ArrayList arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from doaalist",null);
        res.moveToFirst();
        while (res.isAfterLast()==false)
        {
            String t1 = res.getString(1);
            arrayList.add(t1);
            res.moveToNext();
        }
        return arrayList;

    }

    public void Insert_phone_contact(String [] contact) {


        SQLiteDatabase DB = this.getWritableDatabase();
        for (int i = 0; i < contact.length; i++) {
            ContentValues cv = new ContentValues();
            cv.put("id",i);
            cv.put("name", contact[i]);
           long res =  DB.insert("doaaids", null, cv);




        }
        DB.close();
    }

    public ArrayList getitemss()
    {
        ArrayList arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from doaaids",null);
        res.moveToFirst();
        while (res.isAfterLast()==false)
        {
            String t1 = res.getString(1);
            arrayList.add(t1);
            res.moveToNext();
        }
        return arrayList;

    }

    public int checkfav(String names)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from doaalist where name = "+"'"+names+"'"  ,null);
        int count = c.getCount();
        return count;
    }

    public Cursor getitemsid(String names)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select page from doaalist where name = "+"'"+names+"'"  ,null);
    }
   /* public Cursor getitemsids(String names)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select id from doaaids where name = "+"'"+names+"'"  ,null);
    }*/

   /* public void Delete()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ "doaalist");
        db.close();
    }*/
public Integer DeleteId(String id)
{
    SQLiteDatabase DB = this.getWritableDatabase();
    int i = DB.delete("doaalist","page=?",new String[]{id});
    return i;
}
}

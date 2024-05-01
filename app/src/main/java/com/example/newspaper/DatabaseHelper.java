package com.example.newspaper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "usersignup";
    static int version = 1;

    String user = "CREATE TABLE if not exists 'signUp' (\n" +
            "\t'Id'\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t'UserName'\tTEXT,\n" +
            "\t'Email'\tTEXT,\n" +
            "\t'DoB'\tTEXT,\n" +
            "\t'Gender'\tTEXT,\n" +
            "\t'Password'\tTEXT,\n" +
            "\t'Image'\tBLOB\n" +
            ") ";

    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(user);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(user);
    }

    public void Insert(ContentValues contentValues) {
        getWritableDatabase().insert("signUp", null, contentValues);
    }

    public ArrayList<Collect> getAllUsers() {
        String sql = "Select * from signUp";
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);
        ArrayList<Collect> list = new ArrayList<Collect>();
        while (cursor.moveToNext()) {
            Collect col = new Collect();
            col.Id = cursor.getString(cursor.getColumnIndex("Id"));
            col.Username = cursor.getString(cursor.getColumnIndex("UserName"));
            col.Email = cursor.getString(cursor.getColumnIndex("Email"));
            col.Gender = cursor.getString(cursor.getColumnIndex("Gender"));
            col.Password = cursor.getString(cursor.getColumnIndex("Password"));
            col.Image = cursor.getBlob(cursor.getColumnIndex("Image"));
            col.DoB = cursor.getString(cursor.getColumnIndex("DoB"));
            list.add(col);
        }
        cursor.close();
        return list;
    }

    public Collect getUser(String id) {
        String sql = "Select * from signUp where id=" + id;
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        Collect col = new Collect();
        while (cursor.moveToNext()) {
            col.Id = cursor.getString(cursor.getColumnIndex("Id"));
            col.Username = cursor.getString(cursor.getColumnIndex("UserName"));
            col.Email = cursor.getString(cursor.getColumnIndex("Email"));
            col.Gender = cursor.getString(cursor.getColumnIndex("Gender"));
            col.Password = cursor.getString(cursor.getColumnIndex("Password"));
            col.Image = cursor.getBlob(cursor.getColumnIndex("Image"));
            col.DoB = cursor.getString(cursor.getColumnIndex("DoB"));
        }
        cursor.close();
        return col;
    }

    public void updateUser(ContentValues contentValues, String id) {
        getWritableDatabase().update("signUp", contentValues, "id=" + id, null);
//        getWritableDatabase().update("signUp",contentValues, "id=?", new String[] {id});
    }

    public void delete(String id) {
        getWritableDatabase().delete("signUp", "id=" + id, null);
    }

    public boolean login(String Username, String Password) {
        String sql = "Select count(*) from signUp where UserName = '" + Username + "' and Password = '" + Password + "'";
        SQLiteStatement sqLiteStatement = getReadableDatabase().compileStatement(sql);
        long l = sqLiteStatement.simpleQueryForLong();

        if (l == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Collect login1(String Username, String Password) {
        String sql = "Select * from signUp where UserName = '" + Username + "' and Password = '" + Password + "'";
//        SQLiteStatement sqLiteStatement = getReadableDatabase().compileStatement(sql);
//        long l = sqLiteStatement.simpleQueryForLong();
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        Collect col = new Collect();
        while (cursor.moveToNext()) {
            col.Id = cursor.getString(cursor.getColumnIndex("Id"));
            col.Username = cursor.getString(cursor.getColumnIndex("UserName"));
            col.Email = cursor.getString(cursor.getColumnIndex("Email"));
            col.Gender = cursor.getString(cursor.getColumnIndex("Gender"));
            col.Password = cursor.getString(cursor.getColumnIndex("Password"));
            col.Image = cursor.getBlob(cursor.getColumnIndex("Image"));
            col.DoB = cursor.getString(cursor.getColumnIndex("DoB"));
        }
        cursor.close();
        return col;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

package com.mehran.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.concurrent.Executor;

public class DBHandler extends SQLiteOpenHelper {
    private Executor executor;
    private Cursor cursor;

    private static final String databaseName = "Store.db";
    private static final int version = 1;
    private static final String TableName = "FavoriteFragment";
    private static final String idColume = "ID";
    public static final String titleColume = "Title";
    public static final String imageUrlColume = "ImageUrl";
    public static final String urlColume = "Url";

    public DBHandler(Context context, Executor executor) {
        super(context, databaseName, null, version);
        this.executor = executor;
    }

    @Override
    public void onCreate(SQLiteDatabase liteDatabase) {

        String query = "CREATE TABLE " + TableName + " ("
                + idColume + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + titleColume + " TEXT,"
                + imageUrlColume + " TEXT,"
                + urlColume + " TEXT)";

        liteDatabase.execSQL(query);

    }

    public void addData(String title, String url, String imageurl) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DBHandler.this.getWritableDatabase(); // should use this part thread
                ContentValues values = new ContentValues();

                values.put(titleColume, title);
                values.put(imageUrlColume, imageurl);
                values.put(urlColume, url);

                db.insert(TableName, null, values);

                db.close();
            }
        });
    }

    public void deleteData(String title ,String url ,String imageUrl) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase db = DBHandler.this.getWritableDatabase();

                db.delete(TableName, "Title=?", new String[]{title});
                db.delete(TableName, "ImageUrl=?", new String[]{imageUrl});
                db.delete(TableName, "Url=?", new String[]{url});

                db.close();
            }
        });

    }

    public Cursor readData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase dp = getReadableDatabase();

                cursor = dp.rawQuery("SELECT * FROM " + TableName, null);
            }
        });
        return cursor;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }
}

package com.mehran.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DBHandler extends SQLiteOpenHelper {
    private Executor executor;
    private Cursor cursor;

    private static final String databaseName = "newsApp.db";
    private static final int version = 1;
    private static final String TableName = "Favorite";
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


    public ArrayList<String> readData(int ColumeName) {
        ArrayList<String> itemsFromColume = new ArrayList<>();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                SQLiteDatabase dp = DBHandler.this.getReadableDatabase();

                Cursor cursor = dp.rawQuery("SELECT * FROM "+TableName,null);

                while (cursor.moveToFirst()){
                    String getItem = cursor.getString(ColumeName);

                    itemsFromColume.add(getItem);
                }
            }
        });

        return itemsFromColume;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }
}

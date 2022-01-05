package com.mehran.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String databaseName = "newsApp";
    private static final int version = 1;
    private static final String TableName = "Favorite";
    private static final String idColume = "ID";
    private static final String titleColume = "Title";
    private static final String imageUrlColume = "ImageUrl";
    private static final String urlColume = "Url";

    public DBHandler(Context context) {
        super(context, databaseName, null, version);
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
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(titleColume, title);
        values.put(imageUrlColume, imageurl);
        values.put(urlColume, url);

        db.insert(TableName, null, values);

        db.close();
    }

    public boolean isEmptyFavoriteTable() {
        SQLiteDatabase gp = this.getWritableDatabase();

        String count = "SELECT count(*) FROM "+TableName;
        Cursor cursor = gp.rawQuery(count ,null);
        cursor.moveToFirst();
        int countIn = cursor.getInt(0);
        if (countIn > 1){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE  IF EXISTS " + TableName);
        onCreate(sqLiteDatabase);
    }
}

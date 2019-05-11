package com.example.komputer.crudmvvm.model;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GamesStore";
    private static final String TABLE_NAME= "Games";
    private static final String KEY_ID = "Id";
    private static final String TITLE = "Title";
    private static final String RELEASE_DATE = "Release_Date";
    private static final String GENRE = "Genre";
    private static final String PLATFORM = "Platform";
    private static final String PRICE = "Price";

    public DBHelper (Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_GAMES_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT,"
                + RELEASE_DATE + " TEXT,"
                + GENRE + " TEXT,"
                + PLATFORM + " INT,"
                + PRICE + " TEXT"
                +")";
        db.execSQL(CREATE_GAMES_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void AddGame (String title, String release_date, String genre, String platform, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(TITLE, title);
        wartosci.put(RELEASE_DATE, release_date);
        wartosci.put(GENRE, genre);
        wartosci.put(PLATFORM, platform);
        wartosci.put(PRICE, price);

        db.insert(TABLE_NAME,null,wartosci);
    }
    public Cursor ShowAllGames() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return cursor;
    }

    public Cursor ShowGame(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " +TABLE_NAME+ " WHERE Id=" + id,null);
        return cursor;
    }
    public void UpdateGame(String id, String title, String release_date, String genre, String platform, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(KEY_ID,id);
        wartosci.put(TITLE, title);
        wartosci.put(RELEASE_DATE, release_date);
        wartosci.put(GENRE, genre);
        wartosci.put(PLATFORM, platform);
        wartosci.put(PRICE, price);

        db.update(TABLE_NAME,wartosci,"Id = ?",new String[] {id});
    }
    public void DeleteGame (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"Id = ?",new String[] {id});
    }
}

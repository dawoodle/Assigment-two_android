package com.example.yourpantry;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "FoodExpiry.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_food";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "food_name";
    private static final String COLUMN_TYPE = "food_type";
    private static final String COLUMN_DATE = "food_date";


    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_TYPE + " TEXT, " +
                        COLUMN_DATE + " TEXT); ";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);


    }
    public void addFood(String title, String type, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_TYPE , type);
        cv.put(COLUMN_DATE, date);
       long result = db.insert(TABLE_NAME,null,cv);
       if(result == -1){
           Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
       }else{
           Toast.makeText(context,"Successful Click on your food item to update and delete",Toast.LENGTH_LONG).show();
       }
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
           cursor =  db.rawQuery(query,null);
        }
        return cursor;
    }

    public void updateData(String title, String type , String date){
        SQLiteDatabase db = this. getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, title);
        cv.put(COLUMN_TYPE , type);
        cv.put(COLUMN_DATE, date);

        long result = db.update(TABLE_NAME, cv, "food_name=?", new String[]{title});
        if(result == -1){
            Toast.makeText(context,"failed to update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successful Press the arrow button above to go see your updated food list",Toast.LENGTH_LONG).show();
        }
    }

    public void  deleteOneRow(String row_id){
        SQLiteDatabase db = this. getWritableDatabase();
        long result = db.delete(TABLE_NAME,"food_name=?",new String[]{row_id});
        if(result == -1){
            Toast.makeText(context,"failed to delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Successful",Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}

package com.example.fady.onplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Fady on 11/5/2017.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static  final  String TAG = DbHelper.class.getSimpleName();

    public static final String DB_NAME = "myapp.db";
    public  static final int DB_VERSION = 1;

    public  static final String USER_TABLE = "users";
    public  static final String COLUMN_ID = "_id";
    public  static final String COIUMN_EMAIL = "email";
    public  static final String COLUMN_PASS = "password";

    public static final String CREATE_TABLE_USERS = "CREATE TABLE " + USER_TABLE + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COIUMN_EMAIL + " TEXT," + COLUMN_PASS + " TEXT );";


    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);

    }


    public void addUser (String email, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COIUMN_EMAIL, email);
        values.put(COLUMN_PASS, password);

        long id = db.insert(USER_TABLE, null, values);
        db.close();

        Log.d(TAG, "user inserted" + id);
    }


    public boolean getUser(String email, String password)
    {
        String selectQuery = "select * from  " + USER_TABLE + " where " +
                COIUMN_EMAIL + " = " + "'"+email+"'" + " and " + COLUMN_PASS + " = " + "'"+password+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        cursor.moveToFirst();

        if(cursor.getCount() >0)
        {return true;}
        cursor.close();
        db.close();
        return false;
    }
}

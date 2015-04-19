package com.ysag.slice.db;

import android.content.*;
import android.database.sqlite.*;
import android.util.Log;

public class SliceOpenHelper extends SQLiteOpenHelper {
    public static final String TABLE_SLICES = "slices";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PATH = "path";
    public static final String COLUMN_DRAWABLE_PATH = "drawable_path";

    private static final String DB_NAME = "slices.db";
    private static final int DB_VERSION = 1;


    private static final String CREATE_TABLE_SLICE_SQL =
        "CREATE TABLE " + TABLE_SLICES + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," +
                                               COLUMN_NAME + " TEXT," +
                                               COLUMN_PATH + " TEXT," +
                                               COLUMN_DRAWABLE_PATH + " TEXT);";

    
    public SliceOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SLICE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SLICES);
        onCreate(db);
    }

}

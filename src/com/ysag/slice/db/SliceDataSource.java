package com.ysag.slice.db;

import java.util.*;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.util.Log;

import com.ysag.slice.core.*;

public class SliceDataSource {
    private SQLiteDatabase mDb;
    private SliceOpenHelper mHelper;
    private final String[] allColumns = new String[] {
        SliceOpenHelper.COLUMN_ID,
        SliceOpenHelper.COLUMN_NAME,
        SliceOpenHelper.COLUMN_PATH,
        SliceOpenHelper.COLUMN_DRAWABLE_PATH
    };

    public SliceDataSource(Context context) {
        mHelper = new SliceOpenHelper(context);
    }

    public void open() throws SQLException {
        mDb = mHelper.getWritableDatabase();
    }

    public void close() {
        mHelper.close();
    }

    public Slice createSlice(String name, String path, String drawablePath) {
        ContentValues cv = new ContentValues();
        cv.put(SliceOpenHelper.COLUMN_NAME, name);
        cv.put(SliceOpenHelper.COLUMN_PATH, path);
        cv.put(SliceOpenHelper.COLUMN_DRAWABLE_PATH, drawablePath);
        long id = mDb.insert(SliceOpenHelper.TABLE_SLICES, null, cv);
        return new Slice(id, name, path, drawablePath);
    }

    public List<Slice> getAllSlices() {
        List<Slice> ret = new ArrayList<Slice>();

        Cursor cursor = mDb.query(SliceOpenHelper.TABLE_SLICES, allColumns, null, null, null, null, null); 
        cursor.moveToFirst();

        for (; !cursor.isAfterLast(); cursor.moveToNext()) {
            Slice slice = cursorToSlice(cursor);
            ret.add(slice);
        }
        return ret;
    }

    private Slice cursorToSlice(Cursor cursor) {
        long id = cursor.getLong(cursor.getColumnIndex(SliceOpenHelper.COLUMN_ID));
        String name = cursor.getString(cursor.getColumnIndex(SliceOpenHelper.COLUMN_NAME));
        String path = cursor.getString(cursor.getColumnIndex(SliceOpenHelper.COLUMN_PATH));
        String drawablePath = cursor.getString(cursor.getColumnIndex(SliceOpenHelper.COLUMN_DRAWABLE_PATH));
        return new Slice(id, name, path, drawablePath);
    }
}

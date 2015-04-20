package com.ysag.slice;

import java.util.*;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.support.v7.app.ActionBarActivity;

import com.ysag.slice.adapters.SliceAdapter;
import com.ysag.slice.util.Util;
import com.ysag.slice.db.SliceDataSource;
import com.ysag.slice.core.Slice;

public class MainActivity extends ActionBarActivity
{
    private SliceDataSource mSource;
    private SliceAdapter mAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mSource = new SliceDataSource(this);

        try {
            mSource.open();
        } catch (Exception e) {
            Util.log("Exception while opening datasource");
            e.printStackTrace();
            return;
        }

        GridView grid = (GridView) findViewById(R.id.grid);
        Util.log("Grid: " + grid);
        mAdapter = new SliceAdapter(this, mSource);
        grid.setAdapter(mAdapter);

        grid.setOnItemClickListener(new GridView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Slice slice = (Slice) v.getTag();
                if (slice == null) {
                    // Add new
                    Toast.makeText(MainActivity.this, "Add new to " + position, Toast.LENGTH_SHORT).show();
                    String colorString = Util.chooseRandom(COLORS);
                    Slice s = mSource.createSlice("slice" + position, "path", colorString);
                    mAdapter.notifyDataSetChanged();
                } else {
                    // Play slice
                    Toast.makeText(MainActivity.this, "Play slice " + position, Toast.LENGTH_SHORT).show();
                }
            }
        });

        grid.setOnItemLongClickListener(new GridView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Slice slice = (Slice) view.getTag();
                if (slice != null) {
                    Toast.makeText(MainActivity.this, "Record slice " + position, Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_clear:
            mSource.deleteAllSlices();
            mAdapter.notifyDataSetChanged();
            return true;
        default:
            return false;
        }
    }

    @Override
    public void onDestroy() {
        if (mSource != null) {
            mSource.close();
            mSource = null;
        }
        super.onDestroy();
    }

    private static final List<String> COLORS = Arrays.asList(new String[] {
        "@color/0xffff0000",
        "@color/0xff00ff00",
        "@color/0xff0000ff",
        "@color/0xffffff00",
        "@color/0xffff00ff",
        "@color/0xff00ffff"
    });

}

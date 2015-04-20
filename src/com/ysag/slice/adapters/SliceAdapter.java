package com.ysag.slice.adapters;

import java.util.*;

import android.app.*;
import android.os.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.graphics.drawable.*;

import com.ysag.slice.db.SliceDataSource;
import com.ysag.slice.core.Slice;
import com.ysag.slice.util.Util;
import com.ysag.slice.R;

public class SliceAdapter extends BaseAdapter {
        private final Context mContext;
        private final SliceDataSource mSource;
        private List<Slice> mSlices;

        public SliceAdapter(Context c, SliceDataSource source) {
            mContext = c;
            mSource = source;
            mSlices = mSource.getAllSlices();
        }

        @Override
        public int getCount() {
            return mSlices.size() + 1;
        }
        
        @Override
        public Slice getItem(int position) {
            if (position >= mSlices.size()) {
                return null;
            }
            return mSlices.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            Util.log("parent: " + parent);
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.griditem, parent, false);
                view.setLayoutParams(new RelativeLayout.LayoutParams(200, 200));
                //view.setPadding(8, 8, 8, 8);
            }

            Slice slice = getItem(position);
            if (slice == null) {
                // Show the add new tile.
                Util.log("Going to set text");
                ((TextView) view.findViewById(R.id.slice_name)).setText(R.string.add_new);
                int i = Util.randInt(0, drefs.length - 1);
                Drawable d = Util.loadDrawable(drefs[i]);
                ((ImageView) view.findViewById(R.id.slice_img)).setImageDrawable(d);
            } else {
                ImageView imageView = (ImageView) view.findViewById(R.id.slice_img);
                imageView.setImageDrawable(slice.getDrawable());
                TextView textView = (TextView) view.findViewById(R.id.slice_name);
                textView.setText(slice.getName());
            }
            view.setTag(slice);
            return view;
        }
        
        @Override
        public void notifyDataSetChanged() {
            mSlices = mSource.getAllSlices();
            super.notifyDataSetChanged();
        }

        private static final String[] drefs = new String[] {
            "@color/0xffff0000",
            "@color/0xff00ff00",
            "@color/0xff0000ff"
        };
}

package com.ysag.slice.util;

import android.util.Log;
import android.graphics.drawable.*;
import java.util.*;

public class Util {
    public static final String TAG = "slice";

    public static void log(String msg) {
        Log.d(TAG, msg);
    }

    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    public static<T> T chooseRandom(List<T> coll) {
        int idx = randInt(0, coll.size() - 1);
        return coll.get(idx);
    }

    public static Drawable loadDrawable(String dref) {
        if (dref.startsWith(D_COLOR)) {
            int radix = 10;
            String colorString = dref.substring(D_COLOR.length());
            if (colorString.startsWith("0x")) {
                colorString = colorString.substring(2);
                radix = 16;
            }
            log("value: " + colorString + "," + "radix: " + radix);
            int color = (int) Long.parseLong(colorString, radix);
            return new ColorDrawable(color); 
        }
        return Drawable.createFromPath(dref);
    }

    private static final Random rand = new Random();

    private static final String D_COLOR = "@color/";
}

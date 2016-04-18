package com.sunny.typography.util;

import android.content.Context;
import android.util.DisplayMetrics;


/**
 * Created by hzsunyj on 15/12/16.
 */
public class DisplayUtil {
    public static int screenWidth;
    public static int screenHeight;
    public static float density;

    public static void init(Context context) {
        getDisplayInfo(context);
    }

    public static int dp2px(Context context, int dp) {
        density = getDensity(context);
        return (int) (dp * density + 0.5);
    }

    private static float getDensity(Context context) {
        if (density == 0) {
            getDisplayInfo(context);
        }
        return density;
    }

    private static void getDisplayInfo(Context context) {
        DisplayMetrics metrics = context.getApplicationContext().getResources().getDisplayMetrics();
        density = metrics.density;
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
    }
}

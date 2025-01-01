package com.thorient.quiz.prefrence;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsUtil {

    private static final String PREFS_NAME = "MyAppPrefs";

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void setValue(Context context, String key, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getValue(Context context, String key, int defaultValue) {
        return getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static void increaseValue(Context context, String key, int increment) {
        int currentValue = getValue(context, key, 0);
        int newValue = currentValue + increment;
        setValue(context, key, newValue); // Set the new incremented value
    }

    public static void decreaseValue(Context context, String key, int decrement) {
        int currentValue = getValue(context, key, 0);
        int newValue = currentValue - decrement;
        setValue(context, key, newValue); // Set the new decremented value
    }
}


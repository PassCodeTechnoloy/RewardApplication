package com.thorient.quiz.prefrence;

import android.app.Activity;
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

    public static void increaseValue(Activity context, String key, int increment) {
        int currentValue = getValue(context, key, 0);
        int newValue = currentValue + increment;
        setValue(context, key, newValue); // Set the new incremented value
    }

    public static void decreaseValue(Context context, String key, int decrement) {
        int currentValue = getValue(context, key, 0);
        int newValue = currentValue - decrement;
        setValue(context, key, newValue); // Set the new decremented value
    }

    public static void saveBoolValue(Context context,boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("saveBool", 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("myBool", value);
        editor.apply();
    }

    public static boolean loadBoolValue(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("saveBool", 0);
        boolean text = sharedPreferences.getBoolean("myBool", false);
        return text;
    }
}


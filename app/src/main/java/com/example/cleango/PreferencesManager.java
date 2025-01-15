package com.example.cleango;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREF_NAME = "AppPreferences";
    private static final String KEY_HO_TEN = "HoTen";
    private static final String KEY_ROLE = "Role";
    private static PreferencesManager instance;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private PreferencesManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static PreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesManager(context);
        }
        return instance;
    }

    public void saveHoTen(String hoTen) {
        editor.putString(KEY_HO_TEN, hoTen);
        editor.apply();
    }

    public String getHoTen() {
        return sharedPreferences.getString(KEY_HO_TEN, null);
    }

    public void saveRole(String role) {
        editor.putString(KEY_ROLE, role);
        editor.apply();
    }

    public String getRole() {
        return sharedPreferences.getString(KEY_ROLE, null);
    }
    public void clearData() {
        sharedPreferences.edit().clear().apply();
    }
    public void clearPreferences() {
        editor.clear();
        editor.apply();
    }
}

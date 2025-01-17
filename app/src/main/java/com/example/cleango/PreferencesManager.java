package com.example.cleango;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesManager {
    private static final String PREF_NAME = "AppPreferences";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_HO_TEN = "HoTen";
    private static final String KEY_TAI_KHOAN= "TaiKhoan";
    private static final String KEY_MAT_KHAU= "MatKhau";
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
    public void saveTaiKhoan(String taiKhoan) {
        editor.putString(KEY_TAI_KHOAN, taiKhoan);
        editor.apply();
    }

    public String getTaiKhoan() {
        return sharedPreferences.getString(KEY_TAI_KHOAN, null);
    }
    public void saveMatKhau(String matKhau) {
        editor.putString(KEY_MAT_KHAU, matKhau);
        editor.apply();
    }

    public String getMatKhau() {
        return sharedPreferences.getString(KEY_MAT_KHAU, null);
    }
    public void saveHoTen(String hoTen) {
        editor.putString(KEY_HO_TEN, hoTen);
        editor.apply();
    }

    public String getHoTen() {
        return sharedPreferences.getString(KEY_HO_TEN, null);
    }

    public void saveRole(String vaiTro) {
        editor.putString(KEY_ROLE, vaiTro);
        editor.apply();
    }

    public String getRole() {
        return sharedPreferences.getString(KEY_ROLE, null);
    }
    public void saveUserId(int userId) {
        editor.putInt(KEY_USER_ID, userId);
        editor.apply();
    }

    // Lấy User ID
    public int getUserId() {
        return sharedPreferences.getInt(KEY_USER_ID, -1); // Default value is -1
    }

    public void clearData() {
        sharedPreferences.edit().clear().apply();
    }
    public void clearPreferences() {
        editor.clear();
        editor.apply();
    }
}

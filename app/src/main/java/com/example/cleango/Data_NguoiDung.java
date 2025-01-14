package com.example.cleango;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Data_NguoiDung extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NguoiDung.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NGUOIDUNG = "NguoiDung";
    private static final String COLUMN_ID = "MaNguoiDung";
    private static final String COLUMN_USERNAME = "TaiKhoan";
    private static final String COLUMN_PASSWORD = "MatKhau";
    private static final String COLUMN_FULLNAME = "HoTen";
    private static final String COLUMN_ADDRESS = "DiaChi";
    private static final String COLUMN_AVATAR = "Avatar";
    private static final String COLUMN_ROLE = "Role";

    public Data_NguoiDung(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NGUOIDUNG + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT UNIQUE NOT NULL, "
                + COLUMN_PASSWORD + " TEXT NOT NULL, "
                + COLUMN_FULLNAME + " TEXT NOT NULL, "
                + COLUMN_ADDRESS + " TEXT DEFAULT 'Chưa cập nhật', "
                + COLUMN_AVATAR + " TEXT, "
                + COLUMN_ROLE + " TEXT CHECK (" + COLUMN_ROLE + " IN ('Thành viên', 'Cộng tác viên')) NOT NULL)";
        db.execSQL(createTable);
        addDefaultAccounts(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NGUOIDUNG);
        onCreate(db);
    }

    private void addDefaultAccounts(SQLiteDatabase db) {
        ContentValues adminValues = new ContentValues();
        adminValues.put(COLUMN_USERNAME, "0364554001");
        adminValues.put(COLUMN_PASSWORD, "123");
        adminValues.put(COLUMN_FULLNAME, "Quỳnh Đỗ");
        adminValues.put(COLUMN_ROLE, "Thành viên");
        db.insert(TABLE_NGUOIDUNG, null, adminValues);
    }

    public boolean registerUser(String username, String password, String fullname, String address, String avatar, String role) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_FULLNAME, fullname);
        values.put(COLUMN_ADDRESS, address != null ? address : "Chưa cập nhật");
        values.put(COLUMN_AVATAR, avatar);

        // Đảm bảo vai trò chỉ là "Thành viên" hoặc "Cộng tác viên"
        role = (role == null || role.isEmpty() || (!role.equals("Thành viên") && !role.equals("Cộng tác viên")))
                ? "Thành viên" : role;
        values.put(COLUMN_ROLE, role);

        long result = db.insert(TABLE_NGUOIDUNG, null, values);
        return result != -1;
    }

    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NGUOIDUNG,
                new String[]{COLUMN_ID},
                COLUMN_USERNAME + "=?",
                new String[]{username},
                null,
                null,
                null);
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }

    public boolean login(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NGUOIDUNG,
                new String[]{COLUMN_ID},
                COLUMN_USERNAME + "=? AND " + COLUMN_PASSWORD + "=?",
                new String[]{username, password},
                null,
                null,
                null);

        boolean isValid = cursor.moveToFirst();
        cursor.close();
        return isValid;
    }

    public String getUserRole(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NGUOIDUNG,
                new String[]{COLUMN_ROLE},
                COLUMN_USERNAME + "=?",
                new String[]{username},
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            String role = cursor.getString(0);
            cursor.close();
            return role;
        }
        cursor.close();
        return null;
    }

    public Cursor getAllUsers() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NGUOIDUNG, null, null, null, null, null, null);
    }

    public boolean updateUser(int userId, String newUsername, String newPassword, String newFullname, String newAddress, String newAvatar, String newRole) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, newUsername);
        values.put(COLUMN_PASSWORD, newPassword);
        values.put(COLUMN_FULLNAME, newFullname);
        values.put(COLUMN_ADDRESS, newAddress != null ? newAddress : "Chưa cập nhật");
        values.put(COLUMN_AVATAR, newAvatar);
        if (newRole == null || newRole.isEmpty() || (!newRole.equals("Thành viên") && !newRole.equals("Cộng tác viên"))) {
            return false; // Không cập nhật nếu vai trò không hợp lệ
        }
        values.put(COLUMN_ROLE, newRole);

        int rowsAffected = db.update(TABLE_NGUOIDUNG, values, COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
        return rowsAffected > 0;
    }

    public boolean deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete(TABLE_NGUOIDUNG, COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
        return rowsDeleted > 0;
    }
}

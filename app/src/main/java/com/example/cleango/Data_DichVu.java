package com.example.cleango;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Data_DichVu extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DichVu.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DICHVU = "DichVu";
    private static final String TABLE_CHITIETDICHVU = "ChiTietDichVu";
    //private static final String TABLE_DATLICH = "DatLich";

    // Các cột bảng DichVu
    private static final String COLUMN_TENDICHVU = "TenDichVu";
    private static final String COLUMN_MOTA = "MoTa";

    // Các cột bảng ChiTietDichVu
    private static final String COLUMN_THOIGIANHOANTHANH = "ThoiGianHoanThanh";
    private static final String COLUMN_KHOILUONGCV = "KhoiLuongCV";
    private static final String COLUMN_GIATIEN = "GiaTien";

    // Các cột bảng DatLich
//    private static final String COLUMN_ID = "ID";
//    private static final String COLUMN_MA_NGUOIDUNG = "MaNguoiDung";
//    private static final String COLUMN_DIACHI = "DiaChi";
//    private static final String COLUMN_HOTEN = "HoTen";
//    private static final String COLUMN_SDT = "SDT";
//    private static final String COLUMN_PTTT = "PTTT";
//    private static final String COLUMN_GHICHU = "GhiChu";
//    private static final String COLUMN_GIATIENDICHVU = "GiaTien";
//    private static final String COLUMN_THOIGIANBATDAU = "ThoiGianBatDau";
//    private static final String COLUMN_THOIGIANLAMVIEC = "ThoiGianHoanThanh";
//    private static final String COLUMN_NGAYBATDAU = "NgayBatDau";

    public Data_DichVu(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng DichVu
        String createTableDichVu = "CREATE TABLE " + TABLE_DICHVU + " ("
                + COLUMN_TENDICHVU + " TEXT PRIMARY KEY, "
                + COLUMN_MOTA + " TEXT);";

        // Tạo bảng ChiTietDichVu
        String createTableChiTietDichVu = "CREATE TABLE " + TABLE_CHITIETDICHVU + " ("
                + COLUMN_TENDICHVU + " TEXT, "
                + COLUMN_THOIGIANHOANTHANH + " TEXT, "
                + COLUMN_KHOILUONGCV + " TEXT, "
                + COLUMN_GIATIEN + " REAL NOT NULL, "
                + "FOREIGN KEY (" + COLUMN_TENDICHVU + ") REFERENCES " + TABLE_DICHVU + "(" + COLUMN_TENDICHVU + "));";

        // Tạo bảng DatLich
//        String createTableDatLich = "CREATE TABLE " + TABLE_DATLICH + " ("
//                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + COLUMN_TENDICHVU + " TEXT, "
//                + COLUMN_MA_NGUOIDUNG + " INTEGER, "
//                + COLUMN_DIACHI + " TEXT, "
//                + COLUMN_HOTEN + " TEXT, "
//                + COLUMN_SDT + " INTEGER, "
//                + COLUMN_PTTT + " TEXT, "
//                + COLUMN_GHICHU + " TEXT, "
//                + COLUMN_GIATIENDICHVU + " REAL, "
//                + COLUMN_THOIGIANBATDAU + " TEXT, "
//                + COLUMN_THOIGIANLAMVIEC + " TEXT, "
//                + COLUMN_NGAYBATDAU + " DATETIME, "
//                + "FOREIGN KEY (" + COLUMN_TENDICHVU + ") REFERENCES " + TABLE_DICHVU + "(" + COLUMN_TENDICHVU + "), "
//                + "FOREIGN KEY (" + COLUMN_MA_NGUOIDUNG + ") REFERENCES NguoiDung(MaNguoiDung));";

        // Thực thi các câu lệnh SQL để tạo bảng
        db.execSQL(createTableDichVu);
        db.execSQL(createTableChiTietDichVu);
        //db.execSQL(createTableDatLich);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Chỉ cần xóa các bảng cũ và tạo lại các bảng mới khi phiên bản thay đổi
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DICHVU);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHITIETDICHVU);
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATLICH);
        onCreate(db);
    }

    // Thêm dịch vụ
    public boolean addDichVu(String tenDichVu, String moTa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENDICHVU, tenDichVu);
        values.put(COLUMN_MOTA, moTa);

        long result = db.insert(TABLE_DICHVU, null, values);
        return result != -1;
    }

    // Thêm chi tiết dịch vụ
    public boolean addChiTietDichVu(String tenDichVu, String thoiGianHoanThanh, String khoiLuongCV, double giaTien) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TENDICHVU, tenDichVu);
        values.put(COLUMN_THOIGIANHOANTHANH, thoiGianHoanThanh);
        values.put(COLUMN_KHOILUONGCV, khoiLuongCV);
        values.put(COLUMN_GIATIEN, giaTien);

        long result = db.insert(TABLE_CHITIETDICHVU, null, values);
        return result != -1;
    }

    // Thêm đặt lịch
//    public boolean addDatLich(String tenDichVu, int maNguoiDung, String diaChi, String hoTen, int sdt, String pttt, String ghiChu, double giaTien, String thoiGianBatDau, String thoiGianHoanThanh, String ngayBatDau) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_TENDICHVU, tenDichVu);
//        values.put(COLUMN_MA_NGUOIDUNG, maNguoiDung);
//        values.put(COLUMN_DIACHI, diaChi);
//        values.put(COLUMN_HOTEN, hoTen);
//        values.put(COLUMN_SDT, sdt);
//        values.put(COLUMN_PTTT, pttt);
//        values.put(COLUMN_GHICHU, ghiChu);
//        values.put(COLUMN_GIATIENDICHVU, giaTien);
//        values.put(COLUMN_THOIGIANBATDAU, thoiGianBatDau);
//        values.put(COLUMN_THOIGIANLAMVIEC, thoiGianHoanThanh);
//        values.put(COLUMN_NGAYBATDAU, ngayBatDau);
//
//        long result = db.insert(TABLE_DATLICH, null, values);
//        return result != -1;
//    }

    // Lấy tất cả dịch vụ
    public Cursor getAllDichVu() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_DICHVU, null);
    }

    // Lấy chi tiết dịch vụ theo loại dịch vụ
    public Cursor getChiTietDichVuByTen(String tenDichVu) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_CHITIETDICHVU, null, COLUMN_TENDICHVU + "=?", new String[]{tenDichVu}, null, null, null);
    }

    // Thêm dịch vụ Dọn nhà
    public void addDichVuDonNha(Context context) {
        Data_DichVu dataDichVu = new Data_DichVu(context);

        // Thêm dịch vụ "Dọn nhà"
        String tenDichVu = "Dọn nhà";
        String moTa = "Dịch vụ dọn dẹp nhà cửa, vệ sinh, lau chùi các khu vực trong nhà như: phòng khách, phòng tắm, nhà bếp,...";

        if (dataDichVu.addDichVu(tenDichVu, moTa)) {
            Log.i("DichVu", "Dịch vụ dọn nhà đã được thêm thành công!");

            // Danh sách các chi tiết dịch vụ
            String[] thoiGianHoanThanh = {"2 giờ", "3 giờ", "4 giờ"};
            String[] khoiLuongCV = {
                    "Tối đa 55m² hoặc 2 phòng",
                    "Tối đa 85m² hoặc 3 phòng",
                    "Tối đa 105m² hoặc 4 phòng"
            };
            double[] giaTien = {280000.0, 480000.0, 640000.0};

            // Thêm từng chi tiết dịch vụ
            for (int i = 0; i < thoiGianHoanThanh.length; i++) {
                if (dataDichVu.addChiTietDichVu(tenDichVu, thoiGianHoanThanh[i], khoiLuongCV[i], giaTien[i])) {
                    Log.d("ChiTietDichVu", "Chi tiết dịch vụ dọn nhà (Lựa chọn " + (i + 1) + ") đã được thêm thành công!");
                } else {
                    Log.i("ChiTietDichVu", "Lỗi khi thêm chi tiết dịch vụ (Lựa chọn " + (i + 1) + ").");
                }
            }
        } else {
            Log.i("DichVu", "Lỗi khi thêm dịch vụ dọn nhà.");
        }
    }
    // Phương thức đếm số lượng bản ghi trong bảng DichVu
    public int getSoLuongDichVu() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_DICHVU;
        Cursor cursor = db.rawQuery(query, null);

        int count = 0;
        if (cursor != null) {
            cursor.moveToFirst();
            count = cursor.getInt(0); // Lấy số lượng bản ghi từ cursor
            cursor.close();
        }

        return count;
    }

}

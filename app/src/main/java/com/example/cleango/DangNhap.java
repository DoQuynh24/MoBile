package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

public class DangNhap extends AppCompatActivity {
    private EditText etTaiKhoan, etMatKhau;
    private Button btnDangNhap;
    private Data_NguoiDung dataNguoiDung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etTaiKhoan = findViewById(R.id.et_TaiKhoan);
        etMatKhau = findViewById(R.id.et_MatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        TextView btnRegister = findViewById(R.id.btnRegister);

        // Khởi tạo cơ sở dữ liệu
        dataNguoiDung = new Data_NguoiDung(this);

        // Xử lý sự kiện click cho btnRegister
        btnRegister.setOnClickListener(v -> {
            // Chuyển sang giao diện register.xml
            Intent intent = new Intent(DangNhap.this, DangKy.class);
            startActivity(intent);
        });

        // Xử lý sự kiện khi nhấn nút "Đăng nhập"
        btnDangNhap.setOnClickListener(view -> {
            String taiKhoan = etTaiKhoan.getText().toString().trim();
            String matKhau = etMatKhau.getText().toString().trim();

            if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
                Toast.makeText(DangNhap.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra thông tin đăng nhập
            String vaiTro = dataNguoiDung.login(taiKhoan, matKhau);
            Log.d("LoginDebug", "Tài khoản: " + taiKhoan + ", Vai trò: " + vaiTro);

            if (vaiTro != null) {
                Toast.makeText(DangNhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                // Lấy tên người dùng
                SQLiteDatabase db = dataNguoiDung.getReadableDatabase();
                Cursor cursor = db.query(
                        "NguoiDung", new String[]{"HoTen"}, "TaiKhoan = ?",
                        new String[]{taiKhoan}, null, null, null
                );

                String hoTen = null;
                if (cursor != null && cursor.moveToFirst()) {
                    int hoTenIndex = cursor.getColumnIndex("HoTen");
                    if (hoTenIndex != -1) {
                        hoTen = cursor.getString(hoTenIndex);
                    }
                    cursor.close();
                }
                int userId = -1;
                if (hoTen != null) {
                    PreferencesManager preferencesManager = PreferencesManager.getInstance(DangNhap.this);

                    preferencesManager.saveHoTen(hoTen);
                    preferencesManager.saveTaiKhoan(taiKhoan);
                    preferencesManager.saveMatKhau(matKhau);
                    preferencesManager.saveRole(vaiTro);

                    // Chuyển hướng dựa trên vai trò
                    Intent intent;
                    if ("Thành viên".equals(vaiTro)) {
                        intent = new Intent(DangNhap.this, MainActivity.class); // Thành viên
                    } else if ("Cộng tác viên".equals(vaiTro)) {
                        intent = new Intent(DangNhap.this, MainCtvActivity.class); // Cộng tác viên
                    } else {
                        Toast.makeText(DangNhap.this, "Vai trò không hợp lệ!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(DangNhap.this, "Không tìm thấy thông tin người dùng!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

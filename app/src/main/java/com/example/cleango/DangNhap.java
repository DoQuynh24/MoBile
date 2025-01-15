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
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;


import androidx.appcompat.app.AppCompatActivity;

public class DangNhap extends AppCompatActivity {
    private EditText etTaiKhoan, etMatKhau;
    private Button btnDangNhap;
    private Data_NguoiDung dataNguoiDung;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        Data_DichVu dataDichVu = new Data_DichVu(this);
        SQLiteDatabase db = dataDichVu.getWritableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Ánh xạ các view từ giao diện XML
        etTaiKhoan = findViewById(R.id.et_TaiKhoan);
        etMatKhau = findViewById(R.id.et_MatKhau);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        TextView btnRegister = findViewById(R.id.btnRegister);
        // Khởi tạo cơ sở dữ liệu
        dataNguoiDung = new Data_NguoiDung(this);

        // Xử lý sự kiện click cho btnRegister
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang giao diện register.xml
                Intent intent = new Intent(DangNhap.this, DangKy.class);
                startActivity(intent);
            }
        });
        // Xử lý sự kiện khi nhấn nút "Đăng nhập"
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String taiKhoan = etTaiKhoan.getText().toString().trim();
                String matKhau = etMatKhau.getText().toString().trim();

                // Kiểm tra thông tin đăng nhập
                if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
                    Toast.makeText(DangNhap.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gọi phương thức login để lấy vai trò
                String vaiTro = dataNguoiDung.login(taiKhoan, matKhau);

                Log.d("LoginDebug", "Tài khoản: " + taiKhoan + ", Vai trò: " + vaiTro);

                if (vaiTro != null) {
                    Toast.makeText(DangNhap.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent;

                    // Xử lý vai trò
                    switch (vaiTro) {
                        case "Thành viên":
                            intent = new Intent(DangNhap.this, MainActivity.class);
                            break;
                        case "Cộng tác viên":
                            intent = new Intent(DangNhap.this, CollabActivity.class);
                            break;
                        default:
                            Toast.makeText(DangNhap.this, "Vai trò không hợp lệ!", Toast.LENGTH_SHORT).show();
                            return;
                    }

                    // Lấy tên người dùng từ cơ sở dữ liệu
                    SQLiteDatabase db = dataNguoiDung.getReadableDatabase();
                    Cursor cursor = db.query(
                            "NguoiDung", // Tên bảng
                            new String[]{"HoTen"}, // Trường cần lấy
                            "TaiKhoan = ?", // Điều kiện
                            new String[]{taiKhoan}, // Tham số
                            null, null, null
                    );

                    String hoTen = null;
                    if (cursor != null && cursor.moveToFirst()) {
                        int hoTenIndex = cursor.getColumnIndex("HoTen");
                        if (hoTenIndex != -1) { // Check if the column index is valid
                            hoTen = cursor.getString(hoTenIndex);
                        }
                        cursor.close();
                    }

                    if (hoTen != null) {
                        PreferencesManager preferencesManager = PreferencesManager.getInstance(DangNhap.this);
                        preferencesManager.saveHoTen(hoTen);
                        preferencesManager.saveRole(vaiTro);

                        // Khởi chạy Activity
                        startActivity(intent);
                        finish(); // Đảm bảo rằng người dùng không thể quay lại màn hình đăng nhập
                    } else {
                        // Nếu không tìm thấy tên người dùng
                        Toast.makeText(DangNhap.this, "Không tìm thấy thông tin người dùng!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Sai thông tin đăng nhập
                    Toast.makeText(DangNhap.this, "Tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

}


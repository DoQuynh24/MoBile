package com.example.cleango;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

public class DangKy extends AppCompatActivity {
    private EditText etHoTen, etTaiKhoan, etMatKhau;
    private Button btnDangKy;
    private RadioGroup roleGroup;
    private Data_NguoiDung sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Liên kết giao diện với Java
        etHoTen = findViewById(R.id.et_HoTen);
        etTaiKhoan = findViewById(R.id.et_TaiKhoan);
        etMatKhau = findViewById(R.id.et_MatKhau);
        btnDangKy = findViewById(R.id.btnDangKy);
        roleGroup = findViewById(R.id.roleGroup);

        sqliteHelper = new Data_NguoiDung(this);

        // Cài đặt sự kiện cho nút đăng ký
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin từ các trường nhập liệu
                String hoTen = etHoTen.getText().toString().trim();
                String taiKhoan = etTaiKhoan.getText().toString().trim();
                String matKhau = etMatKhau.getText().toString().trim();

                // Kiểm tra nếu các trường không trống
                if (hoTen.isEmpty() || taiKhoan.isEmpty() || matKhau.isEmpty()) {
                    Toast.makeText(DangKy.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Kiểm tra định dạng tài khoản (chỉ số và tối đa 10 số, không có ký tự đặc biệt)
                if (!taiKhoan.matches("^[0-9]{1,10}$")) {
                    Toast.makeText(DangKy.this, "Tài khoản chỉ được nhập số và tối đa 10 số, không có ký tự đặc biệt!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Lấy vai trò từ RadioGroup
                String vaiTro = "";
                int selectedRoleId = roleGroup.getCheckedRadioButtonId();  // Lấy id của RadioButton được chọn

                if (selectedRoleId != -1) {
                    RadioButton selectedRole = findViewById(selectedRoleId);  // Tìm RadioButton theo id
                    vaiTro = selectedRole.getText().toString();  // Lấy vai trò từ RadioButton
                } else {
                    Toast.makeText(DangKy.this, "Vui lòng chọn vai trò!", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra xem tài khoản đã tồn tại chưa
                if (sqliteHelper.isUserExists(taiKhoan)) {
                    Toast.makeText(DangKy.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                } else {
                    // Gọi phương thức đăng ký người dùng
                    boolean isRegistered = sqliteHelper.registerUser(taiKhoan, matKhau, hoTen, vaiTro);
                    Log.d("DangKy", "Đăng ký: " + isRegistered);
                    if (isRegistered) {
                        Log.i("DangKy", "Đăng ký thành công!");
                        Toast.makeText(DangKy.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DangKy.this, DangNhap.class);
                        startActivity(intent);
                        finish(); // Đóng Activity DangKy để không quay lại được
                    } else {
                        Log.e("DangKy", "Đăng ký thất bại.");
                        Toast.makeText(DangKy.this, "Đăng ký thất bại. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}

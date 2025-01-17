package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;

public class InforActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.infor);

        // Kết nối các thành phần trong layout
        ImageView btnBack = findViewById(R.id.btnBack);
        TextView btnUpdate = findViewById(R.id.btnUpdate);

        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();
        String taiKhoan = preferencesManager.getTaiKhoan();
        String matKhau = preferencesManager.getMatKhau();
        String vaiTro = preferencesManager.getRole();

        // Hiển thị dữ liệu người dùng vào TextView và EditText
        TextView tvHello = findViewById(R.id.appName);
        if (hoTen != null) {
            tvHello.setText(hoTen);
        }

        TextView tvTaiKhoan = findViewById(R.id.tvTaiKhoan);
        if (taiKhoan != null) {
            tvTaiKhoan.setText("(+84) " + taiKhoan);
        }

        EditText etHoTen = findViewById(R.id.et_HoTen);
        if (hoTen != null) {
            etHoTen.setText(hoTen);
        }

        EditText etTaiKhoan = findViewById(R.id.et_TaiKhoan);
        if (taiKhoan != null) {
            etTaiKhoan.setText(taiKhoan);
        }

        TextView tvRole = findViewById(R.id.tvRole);
        if (vaiTro != null) {
            tvRole.setText(vaiTro);
        }

        EditText etMatKhau = findViewById(R.id.et_MatKhau);
        if (matKhau != null) {
            etMatKhau.setText(matKhau);
        }

        btnUpdate.setOnClickListener(view -> {
            // Lấy dữ liệu mới từ EditText
            String updatedHoTen = etHoTen.getText().toString().trim();
            String updatedTaiKhoan = etTaiKhoan.getText().toString().trim();
            String updatedMatKhau = etMatKhau.getText().toString().trim();

            // Kiểm tra xem người dùng có nhập đủ thông tin không
            if (updatedHoTen.isEmpty() || updatedTaiKhoan.isEmpty() || updatedMatKhau.isEmpty()) {
                Toast.makeText(InforActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Tạo một dialog xác nhận
            new AlertDialog.Builder(InforActivity.this)
                    .setTitle("Cập nhật thông tin")
                    .setMessage("Bạn có chắc chắn muốn cập nhật thông tin không?")
                    .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Cập nhật thông tin vào PreferencesManager
                            preferencesManager.saveHoTen(updatedHoTen);
                            preferencesManager.saveTaiKhoan(updatedTaiKhoan);
                            preferencesManager.saveMatKhau(updatedMatKhau);

                            // Thông báo cập nhật thành công
                            Toast.makeText(InforActivity.this, "Cập nhật thông tin thành công!", Toast.LENGTH_SHORT).show();

                            // Quay lại màn hình trước đó hoặc thực hiện các hành động khác
                            Intent intent = new Intent(InforActivity.this, AccountActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("Không", null)
                    .show();
        });

        // Xử lý sự kiện click cho nút Back
        btnBack.setOnClickListener(v -> {
            // Quay lại AccountActivity
            Intent intent = new Intent(InforActivity.this, AccountActivity.class);
            startActivity(intent);
            finish();
        });


    }
}

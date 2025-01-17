package com.example.cleango;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class WorkWaitingActivity extends AppCompatActivity {
    private Data_DichVu dataDichVu;
    private TextView tvTenDichVu, tvThoiGianBatDau, tvThoiGianHoanThanh, tvGiaTien, tvDiaChi, tvPTTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.work_waiting);
        // Khai báo các TextView
        TextView tabRepeat = findViewById(R.id.tab_repeat);
        TextView tabPackage = findViewById(R.id.tab_package);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        ImageButton btnHome = findViewById(R.id.btnHome);
        ImageButton btnProfile = findViewById(R.id.btnProfile);

// Khởi tạo đối tượng Data_DichVu
        dataDichVu = new Data_DichVu(this);

// Lấy ID của đơn đặt lịch từ Intent
        int idDatLich = getIntent().getIntExtra("ID_DatLich", -1);

        Log.d("DatLichID", "ID Đặt Lịch nhận được từ Intent: " + idDatLich);

// Lấy dữ liệu từ cơ sở dữ liệu
        Cursor cursor = dataDichVu.getDatLichById(idDatLich);

        if (cursor != null && cursor.moveToFirst()) {
            // Đổ dữ liệu từ cursor vào mảng
            String[] data = dataDichVu.loadDatLichToCursor(cursor);

            // Kiểm tra dữ liệu đã có
            if (data.length > 0) {
                // Cập nhật TextView với dữ liệu từ cursor
                tvTenDichVu.setText(data[0]);  // Dịch vụ
                tvThoiGianBatDau.setText(data[1]);  // Thời gian bắt đầu
                tvThoiGianHoanThanh.setText(data[2]);  // Thời gian hoàn thành
                tvGiaTien.setText(String.format("Giá: %.0f VND", Double.parseDouble(data[3])));  // Giá tiền
                tvDiaChi.setText(data[4]);  // Địa chỉ
                tvPTTT.setText(data[5]);  // Phương thức thanh toán
            } else {
                // Xử lý khi không có dữ liệu
                Log.e("DatLich", "Không có dữ liệu cho Đặt Lịch với ID: " + idDatLich);
            }
        } else {
            // Xử lý khi không có cursor hoặc cursor rỗng
            Log.e("DatLich", "Lỗi khi truy vấn dữ liệu từ cơ sở dữ liệu");
        }

// Xử lý sự kiện click cho tab_repeat
        tabRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkWaitingActivity.this, WorkRepeatActivity.class);
                startActivity(intent);
            }
        });

// Xử lý sự kiện click cho tab_package
        tabPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkWaitingActivity.this, WorkPackageActivity.class);
                startActivity(intent);
            }
        });

// Xử lý sự kiện click cho btnHome
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PreferencesManager preferencesManager = PreferencesManager.getInstance(WorkWaitingActivity.this);
                String role = preferencesManager.getRole(); // Lấy vai trò từ SharedPreferences

                Intent intent;
                if ("Cộng tác viên".equals(role)) {
                    intent = new Intent(WorkWaitingActivity.this, MainCtvActivity.class); // Chuyển đến MainCtvActivity nếu là Cộng tác viên
                } else if ("Thành viên".equals(role)) {
                    intent = new Intent(WorkWaitingActivity.this, MainActivity.class); // Chuyển đến MainActivity nếu là Thành viên
                } else {
                    intent = new Intent(WorkWaitingActivity.this, MainActivity.class);
                }
                startActivity(intent);
            }
        });

// Xử lý sự kiện click cho btnSupport
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến MessChatActivity
                Intent intent = new Intent(WorkWaitingActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });

// Xử lý sự kiện click cho btnProfile
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (hoTen != null) {
                    // Nếu đã đăng nhập, chuyển sang AccountActivity
                    Intent intent = new Intent(WorkWaitingActivity.this, AccountActivity.class);
                    startActivity(intent);
                } else {
                    // Nếu chưa đăng nhập, chuyển sang AccActivity
                    Intent intent = new Intent(WorkWaitingActivity.this, AccActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

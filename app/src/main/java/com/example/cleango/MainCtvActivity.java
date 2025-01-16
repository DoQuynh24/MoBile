package com.example.cleango;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class MainCtvActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_ctv);
        PreferencesManager preferencesManager = PreferencesManager.getInstance(this);
        String hoTen = preferencesManager.getHoTen();
        // Các nút khác
        ImageButton btnHistory = findViewById(R.id.btnHistory);
        ImageButton btnSupport = findViewById(R.id.btnSupport);
        ImageButton btnProfile = findViewById(R.id.btnProfile);
        TextView notifyTextView = findViewById(R.id.Notify); // TextView Notify
        Button btnNhanViec = findViewById(R.id.btnNhanViec); // Nút "Nhận việc"
        // Hiển thị tên người dùng vào TextView
        TextView tvHello = findViewById(R.id.appName);
        if (hoTen != null) {
            // Nếu đã đăng nhập, hiển thị tên người dùng
            tvHello.setText("Xin chào " + hoTen);
        } else {
            // Nếu chưa đăng nhập, hiển thị thông báo
            tvHello.setText("Xin chào, vui lòng đăng nhập!");
        }
        // Xử lý sự kiện click cho btnHistory
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCtvActivity.this, CtvWelfActivity.class);
                startActivity(intent);
            }
        });

        // Xử lý sự kiện click cho btnSupport
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCtvActivity.this, MessChatActivity.class);
                startActivity(intent);
            }
        });
        btnProfile.setOnClickListener(v -> {
            if (hoTen != null) {
                // Nếu đã đăng nhập, chuyển sang AccountActivity
                Intent intent = new Intent(MainCtvActivity.this, AccountActivity.class);
                startActivity(intent);
            } else {
                // Nếu chưa đăng nhập, chuyển sang AccActivity
                Intent intent = new Intent(MainCtvActivity.this, AccActivity.class);
                startActivity(intent);
            }
        });
        // Xử lý sự kiện click cho TextView Notify
        notifyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainCtvActivity.this, MainCtvWorkActivity.class);
                startActivity(intent);
            }
        });
        // Xử lý sự kiện click cho nút Nhận việc
        btnNhanViec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển dữ liệu sang MainCtvWorkActivity
                Intent intent = new Intent(MainCtvActivity.this, MainCtvWorkActivity.class);
                intent.putExtra("job_title", "Dọn dẹp nhà!"); // Truyền tiêu đề công việc
                intent.putExtra("job_time", "10:00"); // Truyền thời gian bắt đầu
                intent.putExtra("job_date", "17/01/2025");
                intent.putExtra("job_duration", "4 giờ"); // Truyền thời gian làm
                intent.putExtra("job_price", "640.000 VND"); // Truyền giá tiền
                intent.putExtra("job_address", "Phường Nhân Hòa - Thị xã Mỹ Hào - Tỉnh Hưng Yên"); // Truyền địa chỉ
                startActivity(intent);
            }
        });
    }
}
